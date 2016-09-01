package org.sb.sample.push.webapp.rest;

import org.sb.sample.push.client.device.Device;
import org.sb.sample.push.webapp.ServiceLocator;

public class Utils {

	private Utils() {
	}
	
	public static Device createDevice(String id) {
		Device device1 = new Device();
		device1.setId(id);
		ServiceLocator.instance.getDeviceService().registerDevice(device1);
		return device1;
	}
}
