/**
 * Copyright (c) 2014 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package de.dailab.nemo.ima.controller.app;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev100924.MacAddress;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeConnectorRef;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeUpdated;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.node.NodeConnector;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.node.NodeConnectorKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.NodeKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node;
/*
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev100924.MacAddress;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.FlowId;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.tables.Table;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.tables.table.Flow;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.tables.table.FlowBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.tables.table.FlowKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.FlowCookie;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeConnectorId;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeConnectorRef;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeId;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeRef;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.node.NodeConnector;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.node.NodeConnectorKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.NodeKey;
*/
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.service.rev130709.PacketProcessingListener;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.service.rev130709.PacketProcessingService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.service.rev130709.PacketReceived;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.service.rev130709.TransmitPacketInput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.service.rev130709.TransmitPacketInputBuilder;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dailab.nemo.ima.controller.app.SwitchHandler;
import de.dailab.nemo.ima.controller.util.InstanceIdentifierUtils;
import de.dailab.nemo.ima.controller.util.PacketUtils;

/**
 * Simple Learning Switch implementation which does mac learning for one switch.
 *
 *
 */
public class PacketHandlerImpl implements SwitchHandler, PacketProcessingListener {

	private static final Logger log = LoggerFactory.getLogger(PacketHandlerImpl.class);
    private static final byte[] ETH_TYPE_IPV4 = new byte[] { 0x08, 0x00 };
    private static final byte[] ETH_TYPE_ARP = new byte[] { 0x08, 0x06 };

	private PacketProcessingService packetProcessingService;
	private FlowManager flowManager;

	public PacketHandlerImpl(FlowManager flowManager) {
		setFlowManager(flowManager);
	}
	
	@Override
	public void setFlowManager(FlowManager flowManager) {
		this.flowManager = flowManager;
	}


	@Override
	public void onPacketReceived(PacketReceived notification) {

        //log.debug("Received packet via match: {}", notification.getMatch());

        // detect and compare node - we support one switch

        // read src MAC and dst MAC
        byte[] dstMacRaw = PacketUtils.extractDstMac(notification.getPayload());
        byte[] srcMacRaw = PacketUtils.extractSrcMac(notification.getPayload());
        byte[] etherType = PacketUtils.extractEtherType(notification.getPayload());

        MacAddress dstMac = PacketUtils.rawMacToMac(dstMacRaw);
        MacAddress srcMac = PacketUtils.rawMacToMac(srcMacRaw);

        //NodeConnectorKey ingressKey = InstanceIdentifierUtils.getNodeConnectorKey(notification.getIngress().getValue());
        NodeConnectorKey ingressKey = notification.getIngress().getValue().firstKeyOf(NodeConnector.class, NodeConnectorKey.class);
        NodeKey nodeKey = (notification.getIngress().getValue().firstIdentifierOf(Node.class)).firstKeyOf(Node.class, NodeKey.class);


        // learn by IPv4 traffic only
        if (Arrays.equals(ETH_TYPE_IPV4, etherType) || Arrays.equals(ETH_TYPE_ARP, etherType)) {
        	log.debug("Received packet from MAC match: {}, ingress: {}", srcMac, ingressKey.getId());
        	log.debug("Received packet to   MAC match: {}", dstMac);
        	log.debug("Ethertype: {}", Integer.toHexString(0x0000ffff & ByteBuffer.wrap(etherType).getShort()));
        	
        	//flowManager.writeForwardToMacFlow(nodeKey.getId(), ingressKey.getId(), srcMac);
        	/*NodeConnectorRef previousPort = mac2portMapping.put(srcMac, notification.getIngress());
            if (previousPort != null && !notification.getIngress().equals(previousPort)) {
                NodeConnectorKey previousPortKey = InstanceIdentifierUtils.getNodeConnectorKey(previousPort.getValue());
                log.debug("mac2port mapping changed by mac {}: {} -> {}", srcMac, previousPortKey, ingressKey.getId());
            }
            */
            // if dst MAC mapped:
        	/*
            NodeConnectorRef destNodeConnector = mac2portMapping.get(dstMac);
            if (destNodeConnector != null) {
                synchronized (coveredMacPaths) {
                    if (!destNodeConnector.equals(notification.getIngress())) {
                        // add flow
                        addBridgeFlow(srcMac, dstMac, destNodeConnector);
                        addBridgeFlow(dstMac, srcMac, notification.getIngress());
                    } else {
                        LOG.debug("useless rule ignoring - both MACs are behind the same port");
                    }
                }
                log.debug("packetIn-directing.. to {}",
                        InstanceIdentifierUtils.getNodeConnectorKey(destNodeConnector.getValue()).getId());
                sendPacketOut(notification.getPayload(), notification.getIngress(), destNodeConnector);
            } else {
                // flood
                log.debug("packetIn-still flooding.. ");
                flood(notification.getPayload(), notification.getIngress());
            }
            */
        } else {
            // non IPv4 package
            //flood(notification.getPayload(), notification.getIngress());
        }
	
	}

	@Override
	public void onNodeAppeared(NodeUpdated nodeRef) {
		// TODO Auto-generated method stub
		
	}

	public void setPacketProcessingService(
			PacketProcessingService packetProcessingService) {
		// TODO Auto-generated method stub
		
	}

}
