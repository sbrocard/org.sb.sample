package org.sb.sample.push.client.device.impl;

import java.util.Collection;
import java.util.Collections;

import org.sb.sample.push.client.device.Device;
import org.sb.sample.push.client.device.IDeviceService;
import org.sb.sample.push.client.device.Topic;

public class DeviceService implements IDeviceService {

	/**
	 * TODO sb, inject the impl with dependency injection
	 */
	private final IDeviceDao dao = new DeviceDao();
	
	@Override
	public void registerDevice(Device device) {
		dao.getModel().put(device.getId(), device);
	}

	@Override
	public void unregisterDevice(Device device) {
		// TODO Auto-generated method stub

	}

	@Override
	public Device findDevice(String id) {
		return dao.getModel().get(id);
	}

	@Override
	public Collection<Device> findDevices(String searchCriteria) {
		if (SEARCH_CRITERIA_ALL.equals(searchCriteria)) {
			return Collections.unmodifiableCollection(dao.getModel().values());
		}
		return Collections.<Device>emptyList();	
	}

	@Override
	public void updateDevice(Device device) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerTopics(Device device, Topic[] topics) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unregisterTopics(Device device, Topic[] topics) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getDeviceCount() {
		return dao.getModel().size();
	}

	@Override
	public Device deleteDevice(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<String> getDeviceIds(String searchCriteria) {
		if (SEARCH_CRITERIA_ALL.equals(searchCriteria)) {
			return Collections.unmodifiableCollection(dao.getModel().keySet());
		}
		return Collections.<String>emptyList();
	}

	@Override
	public boolean existsDevice(String id) {
		return dao.getModel().containsKey(id);
	}

	@Override
	public void reset() {
		dao.reset();
	}

}
