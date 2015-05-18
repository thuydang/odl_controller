package de.dailab.nemo.ima.controller.app;


//import de.dailab.nemo.ima.controller.app.FlowManager;

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
	//void setFlowWriter();
	
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
	void addInitialFlows();
	

}
