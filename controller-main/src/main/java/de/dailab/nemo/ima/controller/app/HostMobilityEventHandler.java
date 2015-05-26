
package de.dailab.nemo.ima.controller.app;

import org.opendaylight.yang.gen.v1.urn.opendaylight.address.tracker.rev140617.address.node.connector.Addresses;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.node.NodeConnector;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node;

public interface HostMobilityEventHandler {


	/**
	 * Handler Method
	 * @param addrs 
	 * @param node 
	 * @param nodConnector 
	 */
	public void onHostMobilityEvent(Node node, NodeConnector nodeConnector, Addresses addrs);

}
