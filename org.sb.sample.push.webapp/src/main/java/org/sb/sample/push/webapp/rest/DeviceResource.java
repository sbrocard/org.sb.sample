package org.sb.sample.push.webapp.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import org.sb.sample.push.client.device.Device;
import org.sb.sample.push.webapp.ServiceLocator;

public class DeviceResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;
	public DeviceResource(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	//Application integration     
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Device getDevice() {
		return findDevice();
	}

	private Device findDevice() {
		Device device = ServiceLocator.instance.getDeviceService().findDevice(id);
		if(device==null)
			throw new RuntimeException("Get: Device with " + id +  " not found");
		return device;
	}

	// for the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public Device getDeviceHTML() {
		return findDevice();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putDevice(JAXBElement<Device> todo) {
		Device c = todo.getValue();
		return putAndGetResponse(c);
	}

	@DELETE
	public void deleteDevice() {
		Device c = ServiceLocator.instance.getDeviceService().deleteDevice(id);
		if(c==null)
			throw new RuntimeException("Delete: Device with " + id +  " not found");
	}

	private Response putAndGetResponse(Device device) {
		Response res;
		if(ServiceLocator.instance.getDeviceService().existsDevice(device.getId())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		ServiceLocator.instance.getDeviceService().registerDevice(device);
		return res;
	}



} 