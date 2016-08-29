package org.sb.sample.push.client.device.impl;

import java.util.Map;

import org.sb.sample.push.client.device.Device;

public interface IDeviceDao {

	Map<String, Device> getModel();

	/**
	 * Only for the tests
	 * TODO sb, to be removed when dependency injection is in place
	 */
	void reset();

}
