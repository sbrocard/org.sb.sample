package org.sb.sample.push.client.device.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.sb.sample.push.client.device.Device;

public class DeviceDao implements IDeviceDao {

	private ConcurrentHashMap<String, Device> devices = new ConcurrentHashMap<String, Device>();

	public DeviceDao() {
	}

	public Map<String, Device> getModel(){
		return devices;
	}

	/**
	 * Only for the tests
	 * TODO sb, to be removed when dependency injection is in place
	 */
	@Override
	public void reset() {
		devices.clear();
	}

	@Override
	public void add(Device device) throws DeviceDaoException {
		if (devices.putIfAbsent(device.getId(), device) != null) {
			throw new DeviceDaoException("device with id [" + device.getId() + "] already existing");
		}
	}

	@Override
	public boolean contains(Device device) {
		return contains(device.getId());
	}

	@Override
	public void delete(Device device) throws DeviceDaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Device findDevice(String id) {
		return devices.get(id);
	}

	@Override
	public boolean contains(String id) {
		return devices.containsKey(id);
	}

	@Override
	public Collection<Device> getAllDevices() {
		return Collections.unmodifiableCollection(devices.values());
	}

	@Override
	public Collection<String> getAllDeviceIds() {
		List<String> list = new ArrayList<String>();
		for (String id : devices.keySet()) {
			list.add(id);
		}
		return list;
	}
}
