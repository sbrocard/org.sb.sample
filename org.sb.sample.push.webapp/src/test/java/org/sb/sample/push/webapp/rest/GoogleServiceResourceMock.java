package org.sb.sample.push.webapp.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.sb.sample.push.client.GCMMessageResponse;

// Will map the resource to the URL devices
@Path("/gcm")
public class GoogleServiceResourceMock {

	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	private static final List<String> messages = new ArrayList<String>();
	private static volatile CountDownLatch countDownLatch = new CountDownLatch(0);

	public GoogleServiceResourceMock() {
	}

	@Path("/send")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public GCMMessageResponse sendMessage(String message/*,
			@Context HttpServletResponse servletResponse*/) throws IOException {
		messages.add(message);
		countDownLatch.countDown();
		GCMMessageResponse gcmMessageResponse = new GCMMessageResponse();
		gcmMessageResponse.success = "ok";
		return gcmMessageResponse;
	}

	public static List<String> getMessages() {
		return messages;
	}
	
	public static CountDownLatch getCount()	{
		return countDownLatch;
	}

	public static void resetCount(int count) {
		countDownLatch = new CountDownLatch(count);
	}

	public static void reset() {
		messages.clear();
		resetCount(0);
	}

} 