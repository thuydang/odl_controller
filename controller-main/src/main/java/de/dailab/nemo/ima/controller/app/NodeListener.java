package de.dailab.nemo.ima.controller.app;

import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeConnectorRemoved;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeConnectorUpdated;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeRef;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeRemoved;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeUpdated;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.OpendaylightInventoryListener;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yangtools.yang.common.RpcResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dailab.nemo.ima.controller.app.SwitchHandler;

/**
 * Listen on node events and delegate SwitchHandler.
 * Registers as ODL Inventory listener so that it can activate handler 
 * once a new node i.e. switch is added
 */
public class NodeListener implements OpendaylightInventoryListener {
  private final Logger _logger = LoggerFactory.getLogger(NodeListener.class);
	
	private SwitchHandler switchHandler;

	public void setSwitchHandler(SwitchHandler switchHandler) {
		this.switchHandler = switchHandler;
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
    switchHandler.onNodeAppeared(nodeUpdated);
  }

}
