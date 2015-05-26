
package de.dailab.nemo.ima.controller.app;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.CheckedFuture;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.md.sal.binding.api.DataChangeListener;
import org.opendaylight.controller.md.sal.binding.api.ReadOnlyTransaction;
import org.opendaylight.controller.md.sal.binding.api.WriteTransaction;
import org.opendaylight.controller.md.sal.common.api.data.AsyncDataBroker.DataChangeScope;
import org.opendaylight.controller.md.sal.common.api.data.AsyncDataChangeEvent;
import org.opendaylight.controller.md.sal.common.api.data.LogicalDatastoreType;
import org.opendaylight.yang.gen.v1.urn.opendaylight.address.tracker.rev140617.AddressCapableNodeConnector;
import org.opendaylight.yang.gen.v1.urn.opendaylight.address.tracker.rev140617.address.node.connector.Addresses;
import org.opendaylight.yang.gen.v1.urn.opendaylight.host.tracker.rev140624.HostId;
import org.opendaylight.yang.gen.v1.urn.opendaylight.host.tracker.rev140624.HostNode;
import org.opendaylight.yang.gen.v1.urn.opendaylight.host.tracker.rev140624.host.AttachmentPointsBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.Nodes;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.node.NodeConnector;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.NetworkTopology;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.TopologyId;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.TpId;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.Topology;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.TopologyKey;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.Link;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.Node;

import org.opendaylight.yangtools.concepts.ListenerRegistration;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dailab.nemo.ima.controller.app.HostMobilityEventListener;
import de.dailab.nemo.ima.controller.app.HostMobilityEventHandler;
//import de.dailab.nemo.ima.controller.app.inventory.Host;

/**
 *
 */
public class HostMobilityEventListenerImpl implements HostMobilityEventListener, DataChangeListener {
		private List<HostMobilityEventHandler> listHandler = new ArrayList<HostMobilityEventHandler>();
    private static final int CPUS = Runtime.getRuntime().availableProcessors();
    /**
     * As defined on
     * controller/opendaylight/md-sal/topology-manager/src/main/java/org/opendaylight/md/controller/topology/manager/FlowCapableTopologyProvider.java
     */
    private static final String TOPOLOGY_NAME = "flow:1";

    private static final Logger log = LoggerFactory.getLogger(HostMobilityEventListenerImpl.class);

    private final DataBroker dataService;
    private String topologyId = null;

    ExecutorService exec = Executors.newFixedThreadPool(CPUS);

    //if needed?: private final ConcurrentClusterAwareHostHashMap<HostId, Host> hosts;
    private ListenerRegistration<DataChangeListener> addrsNodeListerRegistration;
    private ListenerRegistration<DataChangeListener> hostNodeListerRegistration;

    public HostMobilityEventListenerImpl(DataBroker dataService) {
        Preconditions.checkNotNull(dataService, "dataBrokerService should not be null.");
        this.dataService = dataService;
        if (topologyId == null || topologyId.isEmpty()) {
            this.topologyId = TOPOLOGY_NAME;
        } else {
            this.topologyId = topologyId;
        }
        //this.hosts = new ConcurrentClusterAwareHostHashMap<>(dataService, this.topologyId);
    }

		/**
		 * Add HostMobilityEvent Handlers
		 */
		@Override
		public void addHandler(HostMobilityEventHandler handler) {
			listHandler.add(handler);
		}

		@Override
    public void registerAsDataChangeListener() {
			log.info("Register As DataChangeListener");

        InstanceIdentifier<Addresses> addrCapableNodeConnectors = //
                InstanceIdentifier.builder(Nodes.class) //
                .child(org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node.class) //
                .child(NodeConnector.class) //
                .augmentation(AddressCapableNodeConnector.class)//
                .child(Addresses.class).build();
        this.addrsNodeListerRegistration = dataService.registerDataChangeListener(LogicalDatastoreType.OPERATIONAL, addrCapableNodeConnectors, this, DataChangeScope.SUBTREE);

        InstanceIdentifier<HostNode> hostNodes = InstanceIdentifier.builder(NetworkTopology.class)//
                .child(Topology.class, new TopologyKey(new TopologyId(topologyId)))//
                .child(Node.class)
                .augmentation(HostNode.class).build();
        this.hostNodeListerRegistration = dataService.registerDataChangeListener(LogicalDatastoreType.OPERATIONAL, hostNodes, this, DataChangeScope.SUBTREE);

        InstanceIdentifier<Link> lIID = InstanceIdentifier.builder(NetworkTopology.class)//
                .child(Topology.class, new TopologyKey(new TopologyId(topologyId)))//
                .child(Link.class).build();

        this.addrsNodeListerRegistration = dataService.registerDataChangeListener(LogicalDatastoreType.OPERATIONAL, lIID, this, DataChangeScope.BASE);
		}

		@Override
		public void onDataChanged(final AsyncDataChangeEvent<InstanceIdentifier<?>, DataObject> change) {

			// handle event here
			exec.submit(new Runnable() {
				@Override
				public void run() {
					if (change == null) {
						log.info("In onDataChanged: No processing done as change even is null.");
						return;
					}
					Map<InstanceIdentifier<?>, DataObject> updatedData = change.getUpdatedData();
					Map<InstanceIdentifier<?>, DataObject> createdData = change.getCreatedData();
					Map<InstanceIdentifier<?>, DataObject> originalData = change.getOriginalData();
					Set<InstanceIdentifier<?>> deletedData = change.getRemovedPaths();

					for (InstanceIdentifier<?> iid : deletedData) {
						if (iid.getTargetType().equals(Node.class)) {
							Node node = ((Node) originalData.get(iid));
							InstanceIdentifier<Node> iiN = (InstanceIdentifier<Node>) iid;
							HostNode hostNode = node.getAugmentation(HostNode.class);
							if (hostNode != null) {
								log.debug("Deleted - HostNode: {}", hostNode);
								/*
									 synchronized (hosts) {
									 try {
									 hosts.removeLocally(iiN);
									 } catch (ClassCastException ex) {
									 }
									 }
									 */
							}
						} else if (iid.getTargetType().equals(Link.class)) {
							// TODO performance improvement here
							log.debug("Deleted - Link: {}, Original data: {}", iid, originalData.get(iid));
							//linkRemoved((InstanceIdentifier<Link>) iid, (Link) originalData.get(iid));
						}
					}

					for (Map.Entry<InstanceIdentifier<?>, DataObject> entrySet : updatedData.entrySet()) {
						InstanceIdentifier<?> iiD = entrySet.getKey();
						final DataObject dataObject = entrySet.getValue();
						if (dataObject instanceof Addresses) {
								log.debug("Updated - Addresses: {}", dataObject);

							packetReceived((Addresses) dataObject, iiD);
						} else if (dataObject instanceof Node) {
								log.debug("Updated - Node: {}", dataObject);
							/*
								 synchronized (hosts) {
								 hosts.putLocally((InstanceIdentifier<Node>) iiD, Host.createHost((Node) dataObject));
								 }
								 */
						}
					}

					for (Map.Entry<InstanceIdentifier<?>, DataObject> entrySet : createdData.entrySet()) {
						InstanceIdentifier<?> iiD = entrySet.getKey();
						final DataObject dataObject = entrySet.getValue();
						if (dataObject instanceof Addresses) {
								log.debug("Created - Addresses: {}", dataObject);
							packetReceived((Addresses) dataObject, iiD);
						} else if (dataObject instanceof Node) {
								log.debug("Created - Node: {}", dataObject);
							/*
								 synchronized (hosts) {
								 hosts.putLocally((InstanceIdentifier<Node>) iiD, Host.createHost((Node) dataObject));
								 }
								 */
						} else if (dataObject instanceof Link) {
								log.debug("Created - Link: {}", dataObject);
						}

					}
				}
			});
		}

		/**
		 * Read Full Node, NodeConnector Object from Datastore. 
		 */
    public void packetReceived(Addresses addrs, InstanceIdentifier<?> ii) {
        InstanceIdentifier<NodeConnector> iinc = ii.firstIdentifierOf(NodeConnector.class);
        InstanceIdentifier<org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node> iin//
                = ii.firstIdentifierOf(org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node.class);

        ListenableFuture<Optional<NodeConnector>> futureNodeConnector;
        ListenableFuture<Optional<org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node>> futureNode;
        try (ReadOnlyTransaction readTx = dataService.newReadOnlyTransaction()) {
            futureNodeConnector = readTx.read(LogicalDatastoreType.OPERATIONAL, iinc);
            futureNode = readTx.read(LogicalDatastoreType.OPERATIONAL, iin);
            readTx.close();
        }
        Optional<NodeConnector> opNodeConnector = null;
        Optional<org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node> opNode = null;
        try {
            opNodeConnector = futureNodeConnector.get();
            opNode = futureNode.get();
        } catch (ExecutionException | InterruptedException ex) {
            log.warn(ex.getLocalizedMessage());
        }
        if (opNode != null && opNode.isPresent()
                && opNodeConnector != null && opNodeConnector.isPresent()) {
            processHost(opNode.get(), opNodeConnector.get(), addrs);
        }
    }

		private void processHost(org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node node,
				NodeConnector nodeConnector,
				Addresses addrs) {
			//List<Link> linksToAdd = new ArrayList<>();
			//synchronized (hosts) {
			//log.debug("Processing node. Node: {}", node);
			//log.debug("Processing Address. Addresses: {}", addrs);
			//writeDatatoMDSAL(linksToAdd, linksToRem);
			//}
			//log.debug("Processing nodeConnector. NodeConnector: {}", nodeConnector);
			// Delegate  switch handler to handle Addresses added to nodeConnector
			for (HostMobilityEventHandler handler : listHandler) {
				handler.onHostMobilityEvent(node, nodeConnector, addrs);
			}
		}


		/**
		 * It verifies if a given NodeConnector is *internal*. An *internal*
		 * NodeConnector is considered to be all NodeConnetors that are NOT attached
     * to hosts created by hosttracker.
     *
     * @param nodeConnector the nodeConnector to check if it is internal or not.
     * @return true if it was found a host connected to this nodeConnetor, false
     * if it was not found a network topology or it was not found a host
     * connected to this nodeConnetor.
     */
    private boolean isNodeConnectorInternal(NodeConnector nodeConnector) {
        TpId tpId = new TpId(nodeConnector.getKey().getId().getValue());
        InstanceIdentifier<NetworkTopology> ntII
                = InstanceIdentifier.builder(NetworkTopology.class).build();
        ListenableFuture<Optional<NetworkTopology>> lfONT;
        try (ReadOnlyTransaction rot = dataService.newReadOnlyTransaction()) {
            lfONT = rot.read(LogicalDatastoreType.OPERATIONAL, ntII);
            rot.close();
        }
        Optional<NetworkTopology> oNT;
        try {
            oNT = lfONT.get();
        } catch (InterruptedException | ExecutionException ex) {
            log.warn(ex.getLocalizedMessage());
            return false;
        }
        if (oNT != null && oNT.isPresent()) {
            NetworkTopology networkTopo = oNT.get();
            for (Topology t : networkTopo.getTopology()) {
                if (t.getLink() != null) {
                    for (Link l : t.getLink()) {
											/*
                        if ((l.getSource().getSourceTp().equals(tpId)
                                && !l.getDestination().getDestTp().getValue().startsWith(Host.NODE_PREFIX))
                                || (l.getDestination().getDestTp().equals(tpId)
                                && !l.getSource().getSourceTp().getValue().startsWith(Host.NODE_PREFIX))) {
                            return true;
                        }
												*/
                    }
                }
            }
        }
        return false;
    }

		private void writeDatatoMDSAL(List<Link> linksToAdd, List<Link> linksToRemove) {
			// TODO: update flow table after host moved.
			/*
			final WriteTransaction writeTx = dataService.newWriteOnlyTransaction();
			if (linksToAdd != null) {
				for (Link l : linksToAdd) {
					InstanceIdentifier<Link> lIID = Utilities.buildLinkIID(l.getKey(), topologyId);
					log.trace("Writing link from MD_SAL: " + lIID.toString());
					writeTx.merge(LogicalDatastoreType.OPERATIONAL, lIID, l, true);
				}
			}
			if (linksToRemove != null) {
				for (Link l : linksToRemove) {
					InstanceIdentifier<Link> lIID = Utilities.buildLinkIID(l.getKey(), topologyId);
					log.trace("Removing link from MD_SAL: " + lIID.toString());
					writeTx.delete(LogicalDatastoreType.OPERATIONAL, lIID);
				}
			}
			final CheckedFuture writeTxResultFuture = writeTx.submit();
			Futures.addCallback(writeTxResultFuture, new FutureCallback() {
				@Override
				public void onSuccess(Object o) {
					log.debug("Hosttracker write successful for tx :{}", writeTx.getIdentifier());
				}

				@Override
				public void onFailure(Throwable throwable) {
					log.error("Hosttracker write transaction {} failed", writeTx.getIdentifier(), throwable.getCause());
				}
			});
			*/
		}




		@Override
		public void close() {
			try {
				if(addrsNodeListerRegistration != null) {
					addrsNodeListerRegistration.close();

					hostNodeListerRegistration.close();
				}
			} catch (Exception e) {
				log.error("Error unregistering Hostmobility listener . Exception:", e);
			}

			try {
				if(hostNodeListerRegistration != null) {
					hostNodeListerRegistration.close();
				}
				} catch (Exception e) {
					log.error("Error unregistering Hostmobility listener . Exception:", e);
				}


		}
}
