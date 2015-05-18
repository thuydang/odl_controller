/**
 * Copyright (c) . and others.  All rights reserved.
 *
 */

package de.dailab.nemo.ima.controller.app;

import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.tables.Table;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.service.rev130709.PacketProcessingService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeUpdated;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dailab.nemo.ima.controller.app.FlowManager;

/**
 * 
 */
public class SwitchHandlerFacadeImpl implements SwitchHandler {
    
    private static final Logger LOG = LoggerFactory
            .getLogger(SwitchHandlerFacadeImpl.class);
		private PacketProcessingService packetProcessingService;
		private FlowManager flowManager;
		private PacketInDispatcherImpl packetInDispatcher;
		private NodeEventDispatcherImpl nodeEventDispatcher;


    @Override
    public synchronized void onNodeAppeared(NodeUpdated nodeUpdated) {
        LOG.debug("Switch appeared, switch: {}", nodeUpdated);
       
        /**
         * appearedTablePath is in form of /nodes/node/node-id/table/table-id
         * so we shorten it to /nodes/node/node-id to get identifier of switch.
         * 
         */
        InstanceIdentifier<Node> nodePath = null; //InstanceIdentifierUtils.getNodePath(appearedTablePath);
//        LOG.debug("NodePath {}", nodePath);
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
            FlowManager flowManager = new FlowManagerImpl();
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
            PacketHandlerImpl packetHandler = new PacketHandlerImpl();
            /**
             * We set runtime dependencies
             */
            //packetHandler.setDataStoreAccessor(dataStoreAccessor);
            packetHandler.setPacketProcessingService(packetProcessingService);
            packetHandler.setFlowManager(flowManager);
            
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
}
