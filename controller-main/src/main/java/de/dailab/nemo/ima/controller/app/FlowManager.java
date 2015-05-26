package de.dailab.nemo.ima.controller.app;

import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev100924.MacAddress;
import org.opendaylight.yang.gen.v1.urn.opendaylight.address.tracker.rev140617.address.node.connector.Addresses;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.service.rev130819.SalFlowService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.node.NodeConnector;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeConnectorId;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeId;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;

/**
 *
 */
public interface FlowManager {
	/**
	 * Set some util to needed to write flows. 
	 * <p/>
	 * A set of flow is managed per switch.
	 * <p/>
	 * Long description.
	 * <p/>
	 * inject {@link Someclass}
	 *
	 * @param data
	 */
	void setSalFlowService(SalFlowService salFlowService);
	
	void setFlowWriterService(FlowWriterService flowWriterService);

	/**
	 * Add default rules for a new switch. 
	 * <p/>
	 * A set of flow is managed per switch.
	 * <p/>
	 * Long description.
	 * <p/>
	 * inject {@link Someclass}
	 *
	 * @param data
	 */
	void addInitialFlows(InstanceIdentifier<Node> nodeId);

	void writeForwardToMacFlow(Node node, NodeConnector nodeConnector,
			Addresses addrs);

	void writeForwardToMacFlow(NodeId nodeId, NodeConnectorId nodeConnectorId, MacAddress aMac);
	

}
