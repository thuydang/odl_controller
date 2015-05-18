package de.dailab.nemo.ima.controller.app;

import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeUpdated;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.service.rev130709.PacketProcessingService;

import de.dailab.nemo.ima.controller.app.FlowManager;

/**
 *
 */
public interface SwitchHandler {
	/**
	 * Set's Flow Manager. 
	 * <p/>
	 * A set of flow is managed per switch.
	 * <p/>
	 * Long description.
	 * <p/>
	 * inject {@link FlowManager}
	 *
	 * @param data
	 */
	void setFlowManager(FlowManager flowManager);

	/**
	 * Handle node-appeared event. 
	 * <p/>
	 * 
	 * <p/>
	 * Long description.
	 * <p/>
	 * inject {@link NodeRef}
	 *
	 * @param data
	 */
	void onNodeAppeared(NodeUpdated nodeRef);

	//void setPacketProcessingService(PacketProcessingService packetProcessingService);
}
