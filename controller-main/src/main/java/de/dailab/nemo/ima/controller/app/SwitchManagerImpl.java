/**
 * Copyright (c) 2013 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package de.dailab.nemo.ima.controller.app;

import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.md.sal.binding.api.DataChangeListener;
import org.opendaylight.controller.md.sal.common.api.data.LogicalDatastoreType;
import org.opendaylight.controller.sal.binding.api.NotificationService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.FlowCapableNode;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.tables.Table;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.service.rev130819.SalFlowService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.Nodes;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.service.rev130709.PacketProcessingService;
import org.opendaylight.yangtools.concepts.ListenerRegistration;
import org.opendaylight.yangtools.concepts.Registration;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
import org.opendaylight.controller.hosttracker.IfIptoHost;
import org.opendaylight.controller.hosttracker.IfNewHostNotify;
import org.opendaylight.controller.hosttracker.hostAware.HostNodeConnector;

import de.dailab.nemo.ima.controller.app.event.PacketInDispatcherImpl;
import de.dailab.nemo.ima.controller.app.event.WakeupOnNode;
import de.dailab.nemo.ima.controller.app.event.NewHostEventDispatcher;
import de.dailab.nemo.ima.controller.app.event.DataChangeListenerRegistrationHolder;
import de.dailab.nemo.ima.controller.app.inventory.InventoryReader;

import de.dailab.nemo.ima.controller.app.flow.FlowWriterServiceImpl;
import de.dailab.nemo.ima.controller.app.flow.InitialFlowWriter;
import de.dailab.nemo.ima.controller.app.flow.ReactiveFlowWriter;
 */

import de.dailab.nemo.ima.controller.app.SwitchManager;
import de.dailab.nemo.ima.controller.app.FlowWriterServiceImpl;
import de.dailab.nemo.ima.controller.app.FlowWriterService;
import de.dailab.nemo.ima.controller.app.HostMobilityEventListenerImpl;

/**
 * NOOP. Listens to packetIn notification and
 * <ul>
 * <li>in HUB mode simply floods all switch ports (except ingress port)</li>
 * <li>in LSWITCH mode collects source MAC address of packetIn and bind it with ingress port.
 * If target MAC address is already bound then a flow is created (for direct communication between
 * corresponding MACs)</li>
 * </ul>
 */
public class SwitchManagerImpl implements SwitchManager {

	protected static final Logger LOG = LoggerFactory
			.getLogger(SwitchManagerImpl.class);

	private NotificationService notificationService;
	private DataBroker dataBroker;
	private PacketProcessingService packetProcessingService;
	private SalFlowService salFlowService;
	private FlowWriterService flowWriterService;

	private SwitchHandlerFacadeImpl switchHandler;

	private Registration packetInRegistration;
	private Registration inventoryListenerReg = null; 
	private ListenerRegistration<DataChangeListener> dataChangeListenerRegistration;

	public SwitchManagerImpl() {
	}

	/**
	 * @param notificationService the notificationService to set
	 */
	@Override
	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	/**
	 * @param dataBroker the dataBroker to set
	 */
	@Override
	public void setDataBroker(DataBroker dataBroker) {
		this.dataBroker = dataBroker;
	}

	/**
	 * @param packetProcessingService the packetProcessingService to set
	 */
	@Override
	public void setPacketProcessingService(
			PacketProcessingService packetProcessingService) {
		this.packetProcessingService = packetProcessingService;
	}

	/**
	 * @param salFlowService the salFlowService to set
	 */
    @Override
    public void setSalFlowService(
            SalFlowService salFlowService) {
        this.salFlowService = salFlowService;
    }

	/**
	 * @param flowWriterService the salFlowService to set
	 */
    @Override
    public void setFlowWriterService(
            FlowWriterService flowWriterService) {
        this.flowWriterService = flowWriterService;
    }


	/**
	 * starting switch
	 */
	@Override
	public void start() {
		LOG.debug("start() -->");

		switchHandler = new SwitchHandlerFacadeImpl();
		// Services dependancy
		switchHandler.setPacketProcessingService(packetProcessingService);

        FlowManager flowManager = new FlowManagerImpl();
        flowManager.setSalFlowService(salFlowService);
        flowManager.setFlowWriterService(flowWriterService);
        switchHandler.setFlowManager(flowManager);

		switchHandler.setFlowWriterService(flowWriterService);
		switchHandler.setSalFlowService(salFlowService);

		// Event listeners holder
		PacketInDispatcherImpl packetInDispatcher = new PacketInDispatcherImpl();
		NodeEventDispatcherImpl nodeEventDispatcher = new NodeEventDispatcherImpl();
		HostMobilityEventListenerImpl hostMobilityEventListener = 
			new HostMobilityEventListenerImpl(dataBroker);

		switchHandler.setPacketInDispatcher(packetInDispatcher);
        packetInRegistration = notificationService.registerNotificationListener(packetInDispatcher);

		switchHandler.setNodeEventDispatcher(nodeEventDispatcher);
		switchHandler.setHostMobilityEventListener(hostMobilityEventListener);
		hostMobilityEventListener.registerAsDataChangeListener();

		// Listen to Node Appeared Event
		NodeListener nodeListener = new NodeListener();
		nodeListener.setSwitchHandler(switchHandler); 
		inventoryListenerReg = notificationService.registerNotificationListener(nodeListener);

		// Setup FlowWrtierService
		//FlowWriterServiceImpl flowWriterService = new FlowWriterServiceImpl(salFlowService);

		// Listen for switch appearing and write initial flows
		//InitialFlowWriter initialFlowWriter = new InitialFlowWriter(salFlowService);
		//inventoryListenerReg = notificationService.registerNotificationListener(initialFlowWriter);

		// Setup InventoryReader
		//InventoryReader inventoryReader = new InventoryReader(dataBroker);
		// Listen for arp paket and write reactive flows
		//ReactiveFlowWriter reactiveFlowWriter = new ReactiveFlowWriter(inventoryReader, flowWriterService);
		//reactFlowWriterReg = notificationService.registerNotificationListener(reactiveFlowWriter);

		// Listen for PacketIn 
		/*
        FlowCommitWrapper dataStoreAccessor = new FlowCommitWrapperImpl(dataBroker);

				IfNewHostNotify newHostListener = new NewHostEventDispatcher(); 

        PacketInDispatcherImpl packetInDispatcher = new PacketInDispatcherImpl();
        SwitchHandlerFacadeImpl switchHandler = new SwitchHandlerFacadeImpl();
        switchHandler.setRegistrationPublisher(this);
        switchHandler.setDataStoreAccessor(dataStoreAccessor);
        switchHandler.setPacketProcessingService(packetProcessingService);
        switchHandler.setPacketInDispatcher(packetInDispatcher);

        // Register switch event listeners
        packetInRegistration = notificationService.registerNotificationListener(packetInDispatcher);
        newHostRegistration = notificationService.registerNotificationListener(newHostListener);
        // other events

        WakeupOnNode wakeupListener = new WakeupOnNode();
        wakeupListener.setSwitchHandler(switchHandler);
				// register to receive callback (wakeupListener) when changes happens in Operational Data Store.
				// Only interested in change to nodes/node/flowcapablenode/table.
        dataChangeListenerRegistration = dataBroker.registerDataChangeListener(LogicalDatastoreType.OPERATIONAL,
                InstanceIdentifier.builder(Nodes.class)
                        .child(Node.class)
                        .augmentation(FlowCapableNode.class)
                        .child(Table.class).build(),
                wakeupListener,
                DataBroker.DataChangeScope.SUBTREE);
		 */
		LOG.debug("start() <--");
	}

	/**
	 * stopping  switch
	 */
	@Override
	public void stop() {
		LOG.debug("stop() -->");
		//TODO: remove flow (created in #start())

		try {
			if(inventoryListenerReg != null) {
				inventoryListenerReg.close();
			} 
		} catch (Exception e) {
			LOG.error("Error unregistering inventory listener. Exception:", e);
		}

        try {
            //packetInRegistration.close();

        } catch (Exception e) {
            LOG.error("Error unregistering packet in listener. Exception:", e);
        }

		switchHandler.stop();
		/*
				try {
					if(reactFlowWriterReg != null) {
						reactFlowWriterReg.close();
					}
				} catch (Exception e) {
					LOG.error("Error unregistering arp listener . Exception:", e);
				}


        try {
            //dataChangeListenerRegistration.close();
        } catch (Exception e) {
            LOG.error("Error unregistering data change listener. Exception:", e);
        }
		 */

		LOG.info("SwitchManager (instance {}) torn down.", this);                                 
		LOG.debug("stop() <--");
	}


	/*
    @Override
    public ListenerRegistration<DataChangeListener> getDataChangeListenerRegistration() {
        return dataChangeListenerRegistration;
    }
	 */
}
