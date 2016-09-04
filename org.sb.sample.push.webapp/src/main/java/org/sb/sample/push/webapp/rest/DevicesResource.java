package org.sb.sample.push.webapp.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.sb.sample.push.client.device.Device;
import org.sb.sample.push.client.device.IDeviceService;

// Will map the resource to the URL devices
@Path("/devices")
public class DevicesResource {

	@Inject
	private IDeviceService deviceService;
  // Allows to insert contextual objects into the class,
  // e.g. ServletContext, Request, Response, UriInfo
  @Context
  UriInfo uriInfo;
  @Context
  Request request;

  // Return the list of devices to the user in the browser
  @GET
  @Produces(MediaType.TEXT_XML)
  public List<Device> getDevicesBrowser() {
    return getDevicesImpl();
  }

  private List<Device> getDevicesImpl() {
	  return new ArrayList<Device>(deviceService.findDevices(IDeviceService.SEARCH_CRITERIA_ALL));
  }

  // Return the list of devices for applications
  @GET
  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
  public List<Device> getDevices() {
    return getDevicesImpl();
  }

  // retuns the number of devices
  // Use http://localhost:8080/org.sb.sample.push.webapp/rest/devicvaluees/count
  // to get the total number of records
  @GET
  @Path("count")
  @Produces(MediaType.TEXT_PLAIN)
  public String getCount() {
    int count = deviceService.getDeviceCount();
    return String.valueOf(count);
  }

  @POST
  @Produces(MediaType.TEXT_HTML)
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public void newDevice(@FormParam("id") String id,
      @Context HttpServletResponse servletResponse) throws IOException {
    Device device = new Device(id);
    deviceService.registerDevice(device);
  }

  // Defines that the next path parameter after devices is
  // treated as a parameter and passed to the DeviceResources
  // Allows to type http://localhost:8080/org.sb.sample.push.webapp/rest/devices/1
  // 1 will be treaded as parameter device and passed to DeviceResource
  @Path("{device}")
  public DeviceResource getDevice(@PathParam("device") String id) {
    return new DeviceResource(deviceService, uriInfo, request, id);
  }

} 