
package de.dailab.nemo.ima.controller.app;


import de.dailab.nemo.ima.controller.app.HostMobilityEventHandler;

/**
 * Listen to Database and detect Hostmobility events.
 * Maintain a list of HostMobilityEventHandler and dispatch events.
 */
public interface HostMobilityEventListener {

	/**
	 * Add Handler
	 */
	public void addHandler(HostMobilityEventHandler handler);

	/**
	 * Register as Database listener.
	 */
	public void registerAsDataChangeListener();

	/**
	 * Register as Database listener.
	 */
	public void close();
}
