/**
 * Copyright (c) 2014 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package de.dailab.nemo.ima.controller.app;

import java.util.HashMap;
import java.util.Map;

import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeConnectorRemoved;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeConnectorUpdated;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeRef;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeRemoved;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeUpdated;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.OpendaylightInventoryListener;

import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 */
public class NodeEventDispatcherImpl implements OpendaylightInventoryListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(NodeEventDispatcherImpl.class);
    private Map<InstanceIdentifier<Node>, OpendaylightInventoryListener> handlerMapping;
    
    /**
     * default constructor
     */
    public NodeEventDispatcherImpl() {
        handlerMapping = new HashMap<>();
    }

		@Override
		public void onNodeConnectorRemoved(NodeConnectorRemoved nodeConnectorRemoved) {
			//do nothing
		}

		@Override
		public void onNodeConnectorUpdated(NodeConnectorUpdated nodeConnectorUpdated) {
			//do nothing
		}

		@Override
		public void onNodeRemoved(NodeRemoved nodeRemoved) {
			//do nothing
		}

		@Override
		public void onNodeUpdated(NodeUpdated nodeUpdated) {
			// find corresponding handler
			/**
         * Notification contains reference to ingress port
         * in a form of path in inventory: /nodes/node/node-connector
         * 
         * In order to get path we shorten path to the first node reference
         * by using firstIdentifierOf helper method provided by InstanceIdentifier,
         * this will effectively shorten the path to /nodes/node.
         * 
         */

        //InstanceIdentifier<?> ingressPort = notification.getIngress().getValue();
        InstanceIdentifier<Node> nodeOfPacket = null; // = ingressPort.firstIdentifierOf(Node.class);

       	LOG.debug("PacketIn from Node: {}", nodeOfPacket);
        /**
         * We lookup up the the packet-in listener for this node.
         * 
         */
        OpendaylightInventoryListener nodeHandler = handlerMapping.get(nodeOfPacket);
        
        /**
         * 
         * If we have packet-processing listener, we delegate notification.
         * 
         */
        if (nodeHandler != null) {
            nodeHandler.onNodeUpdated(nodeUpdated);
        } else {
        	LOG.debug("NULL nodeHandler found...");
        }
    }
    
    /**
     * @return the handlerMapping
     */
    public Map<InstanceIdentifier<Node>, OpendaylightInventoryListener> getHandlerMapping() {
        return handlerMapping;
    }
}
