package org.sb.sample.push.client.device;

import java.util.Collection;

public interface IDeviceService {

	final String SEARCH_CRITERIA_ALL = "all";
	
	void registerDevice(Device device);
	
	void unregisterDevice(Device device);
	
	Device deleteDevice(String id);

	/** Find the device with the given id.
	 * @param id
	 * @return a device instance, or <code>null</code> if the device is not found
	 */
	Device findDevice(String id);

	/**
	 * @param searchCriteria
	 * @return an unmutable collection of device
	 */
	Collection<Device> findDevices(String searchCriteria);

	void updateDevice(Device device);

	void registerTopics(Device device, Topic[] topics);
	
	void unregisterTopics(Device device, Topic[] topics);
	
	
	/** used for test purposes
	 * @return
	 */
	int getDeviceCount();

	/**
	 * return the ids of the devices that follow the given searchCriteria.
	 * {@link #SEARCH_CRITERIA_ALL} is a special criteria that must return all the ids of the devices
	 * @param searchCriteria criteria of the search, implementation specific
	 * @return iterable on the id list
	 */
	Iterable<String> getDeviceIds(String searchCriteria);

	/**
	 * query the service to determine if a device with the specific id exists
	 * @param id
	 * @return true if the device exists
	 */
	boolean existsDevice(String id);

	/**
	 * Only for the tests
	 * TODO sb, to be removed when dependency injection is in place
	 */
	void reset();

}
