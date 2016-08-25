package org.sb.sample.push.webapp.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.sb.sample.push.webapp.device.DeviceDao;
import org.sb.sample.push.webapp.gcmsender.GcmSender;

// Will map the resource to the URL devices
@Path("/messages")
public class MessagesResource {

  // Allows to insert contextual objects into the class,
  // e.g. ServletContext, Request, Response, UriInfo
  @Context
  UriInfo uriInfo;
  @Context
  Request request;

  @POST
  @Produces(MediaType.TEXT_HTML)
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public void sendMessage(@FormParam("message") String message,
      @Context HttpServletResponse servletResponse) throws IOException {
    GcmSender gcmSender = new GcmSender();
    for (String key : DeviceDao.instance.getModel().keySet()) {
		gcmSender.send(message, key);
	}
    servletResponse.sendRedirect("../../org.sb.sample.push.webapp");
  }

} 