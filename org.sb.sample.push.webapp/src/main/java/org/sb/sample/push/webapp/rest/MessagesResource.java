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

import org.sb.sample.push.client.DataMessage;
import org.sb.sample.push.client.IMessageResponse;
import org.sb.sample.push.client.INotificationService;
import org.sb.sample.push.client.MessageRequest;
import org.sb.sample.push.client.device.IDeviceService;
import org.sb.sample.push.webapp.ServiceLocator;

// Will map the resource to the URL devices
@Path("/messages")
public class MessagesResource {

	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	public MessagesResource() {
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void sendMessage(@FormParam("message") String message,
			@Context HttpServletResponse servletResponse) throws IOException {
		INotificationService notificationService = ServiceLocator.instance.getNotificationService();
		IDeviceService deviceService = ServiceLocator.instance.getDeviceService();
		if (notificationService != null && deviceService != null) {
			for (String key : deviceService.getDeviceIds(IDeviceService.SEARCH_CRITERIA_ALL)) {
				MessageRequest messageRequest = new MessageRequest();
				messageRequest.data = new DataMessage(message);
				IMessageResponse sendMessageTo = notificationService.sendMessageTo(key, messageRequest);
				System.out.println(sendMessageTo);
			}
		} else {
			// TODO sb
		}
		// TODO sb remettre ca, ca fait echouer les tests
//		servletResponse.sendRedirect("../../org.sb.sample.push.webapp");
	}

} 