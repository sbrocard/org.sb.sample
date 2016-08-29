package org.sb.sample.push.client.device.impl;

import java.util.HashMap;
import java.util.Map;

import org.sb.sample.push.client.device.Device;

public class DeviceDao implements IDeviceDao {

	private Map<String, Device> contentProvider = new HashMap<>();

	public DeviceDao() {
	}

	public Map<String, Device> getModel(){
		return contentProvider;
	}

	/**
	 * Only for the tests
	 * TODO sb, to be removed when dependency injection is in place
	 */
	@Override
	public void reset() {
		contentProvider.clear();
	}
}
