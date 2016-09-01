package org.sb.sample.push.client.device.impl;

import java.util.Collection;
import java.util.Map;

import org.sb.sample.push.client.device.Device;

/**
 * Interface that defines the interaction with the persistence 
 * layer.
 * @author sbrocard
 *
 */
public interface IDeviceDao {

	Map<String, Device> getModel();

	/**
	 * Only for the tests
	 * TODO sb, to be removed when dependency injection is in place
	 */
	void reset();

	/** Add a device
	 * 
	 * It throws a {@link DeviceDaoException} if the device is already existing.
	 * @param device the description of the device to add
	 * @exception DeviceDaoException when the device could not be added
	 */
	void add(Device device) throws DeviceDaoException;

	/**
	 * Tells if the device already exists.
	 * @param device
	 * @return true if the device exists, false otherwise
	 */
	boolean contains(Device device);

	/**
	 * Tells if the device already exists.
	 * @param id id of the device to be searched
	 * @return true if the device exists, false otherwise
	 */
	boolean contains(String id);

	/** 
	 * Delete a device
	 * 
	 * @param device the description of the device to add
	 * @exception DeviceDaoException when the device could not be deleted
	 */
	void delete(Device device) throws DeviceDaoException;

	/**
	 * Find a device with the given id
	 * 
	 * @param id if of the device to search for
	 * @return the device instance or <code>null</code> if the device is not found
	 */
	Device findDevice(String id);
	
	/**
	 * Return the list of all registered devices.
	 * @return collection of existing devices
	 */
	Collection<Device> getAllDevices();

	/**
	 * Return the list of all registered device ids.
	 * @return collection of existing device ids
	 */
	Collection<String> getAllDeviceIds();
}
