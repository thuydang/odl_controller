/**
 * Copyright (c) . and others.  All rights reserved.
 *
 */

package de.dailab.nemo.ima.controller.app;

import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.service.rev130709.PacketProcessingService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.address.tracker.rev140617.address.node.connector.Addresses;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.service.rev130819.SalFlowService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.NodeKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.node.NodeConnector;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.node.NodeConnectorKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeUpdated;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.Nodes;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.tables.Table;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dailab.nemo.ima.controller.app.FlowManager;
import de.dailab.nemo.ima.controller.app.FlowWriterService;
import de.dailab.nemo.ima.controller.app.HostMobilityEventListener;
import de.dailab.nemo.ima.controller.app.HostMobilityEventHandler;

/**
 * 
 */
public class SwitchHandlerFacadeImpl implements SwitchHandler, HostMobilityEventHandler {
    
    private static final Logger LOG = LoggerFactory
            .getLogger(SwitchHandlerFacadeImpl.class);

		private PacketProcessingService packetProcessingService;
		private FlowManager flowManager;
		private SalFlowService salFlowService;
		private FlowWriterService flowWriterService;

		private PacketInDispatcherImpl packetInDispatcher;
		private NodeEventDispatcherImpl nodeEventDispatcher;
		private HostMobilityEventListener hostMobilityEventListener;


		/**
		 * Handle event involving multi switches.
		 */
		public void onMultiSwitchHandlerEvent() {
			// TODO: handle topology changes.
		}

		/**
		 * Handle Host Mobility Event
		 */
		@Override
		public void onHostMobilityEvent(Node node, NodeConnector nodeConnector, Addresses addrs) {
			LOG.debug("Processing nodeConnector. NodeConnector: {}", nodeConnector);
			InstanceIdentifier<Node> nodeIId = InstanceIdentifier.builder(Nodes.class)
	                .child(Node.class, new NodeKey(node.getId())).toInstance();
			//InstanceIdentifier<NodeConnector> nodeConnectorIId =  nodeIId
			//		.child(NodeConnector.class, new NodeConnectorKey(nodeConnector.getId()));
			//NodeConnectorRef nodeConnectorRef = new NodeConnectorRef(instanceIdentifier);
			
			//Let each node hanlde flow write
			SwitchHandler switchHandlerImpl = nodeEventDispatcher.getHandlerMapping().get(nodeIId);
			((SwitchHandlerImpl) switchHandlerImpl).onHostMobility(node, nodeConnector, addrs);

		}


		/**
		 * Initiate per node/switch Handler when new node appears.
		 */
    @Override
    public synchronized void onNodeAppeared(NodeUpdated nodeUpdated) {
        LOG.debug("Switch appeared, switch: {}", nodeUpdated);
       
        /**
         * appearedTablePath is in form of /nodes/node/node-id/table/table-id
         * so we shorten it to /nodes/node/node-id to get identifier of switch.
         * 
         */
        InstanceIdentifier<Node> nodePath = (InstanceIdentifier<Node>) nodeUpdated.getNodeRef().getValue(); 
				//InstanceIdentifier<Node> nodePath = 
				//		InstanceIdentifierUtils.getNodePath(appearedTablePath);
        LOG.debug("NodePath {}", nodePath);
				/**
         * We check if we already initialized NodeEventDispatcher for that node,
         * if not we create new handler for switch.
         * 
         */
        if (!nodeEventDispatcher.getHandlerMapping().containsKey(nodePath)) {
            // delegate this node (owning appearedTable) to SimpleLearningSwitchHandler  
            SwitchHandlerImpl simpleSwitch = new SwitchHandlerImpl();
            /**
             * We set runtime dependencies
             */
            //simpleSwitch.setDataStoreAccessor(dataStoreAccessor);

            simpleSwitch.setFlowManager(flowManager);
            
            /**
             * We propagate table event to newly instantiated instance of learning switch
             */
            simpleSwitch.onNodeAppeared(nodeUpdated);
            /**
             * We update mapping of already instantiated LearningSwitchHanlders
             */
            nodeEventDispatcher.getHandlerMapping().put(nodePath, simpleSwitch);
        }

        /**
         * We check if we already initialized dispatcher for that node,
         * if not we create new handler for switch.
         * 
         */
        if (!packetInDispatcher.getHandlerMapping().containsKey(nodePath)) {
            // delegate this node (owning appearedTable) to SimpleLearningSwitchHandler  
            PacketHandlerImpl packetHandler = new PacketHandlerImpl(flowManager);
            /**
             * We set runtime dependencies
             */
            //packetHandler.setDataStoreAccessor(dataStoreAccessor);
            packetHandler.setPacketProcessingService(packetProcessingService);
            
            /**
             * We propagate table event to newly instantiated instance of learning switch
             */
            packetHandler.onNodeAppeared(nodeUpdated);
            /**
             * We update mapping of already instantiated LearningSwitchHanlders
             */
            packetInDispatcher.getHandlerMapping().put(nodePath, packetHandler);
        }
    }

		public void stop() {
				hostMobilityEventListener.close();
				/*
				
				*/
		}

		/*
    @Override
    public void setRegistrationPublisher(
            DataChangeListenerRegistrationHolder registrationPublisher) {
        //NOOP
    }
    
    @Override
    public void setDataStoreAccessor(FlowCommitWrapper dataStoreAccessor) {
        this.dataStoreAccessor = dataStoreAccessor;
    }
   	*/ 
    public void setPacketProcessingService(
            PacketProcessingService packetProcessingService) {
        this.packetProcessingService = packetProcessingService;
    }

    @Override
    public void setFlowManager(
            FlowManager flowManager) {
        this.flowManager = flowManager;
    }

    /**
     * @param salFlowService
     */
    public void setSalFlowService(
            SalFlowService salFlowService) {
        this.salFlowService = salFlowService;
    }

		/**
     * @param flowWriterService
     */
    public void setFlowWriterService(
            FlowWriterService flowWriterService) {
        this.flowWriterService = flowWriterService;
    }

    /**
     * @param NodeEventDispatcherImpl
     */
    public void setNodeEventDispatcher(
            NodeEventDispatcherImpl nodeEventDispatcher) {
        this.nodeEventDispatcher = nodeEventDispatcher;
    }

    /**
     * @param packetInDispatcher
     */
    public void setPacketInDispatcher(PacketInDispatcherImpl packetInDispatcher) {
        this.packetInDispatcher = packetInDispatcher;
    }

    /**
     * @param hostMobilityEventListener
     */
    public void setHostMobilityEventListener(HostMobilityEventListener hostMobilityEventListener) {
        this.hostMobilityEventListener = hostMobilityEventListener;
				hostMobilityEventListener.addHandler(this);
    }
}
