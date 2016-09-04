package org.sb.sample.push.webapp.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Test;
import org.sb.sample.push.client.device.IDeviceService;

public class MessagesResourceTest extends JerseyTest {

	@Inject
	private IDeviceService deviceService;

	@Override
	protected Application configure() {
		return Utils.configure(this, getBaseUri(), MessagesResource.class, GoogleServiceResourceMock.class);
	}
	
	@Test
	public void testSendMessage() throws MalformedURLException, URISyntaxException, InterruptedException {
		Utils.createDevice(deviceService, "theId1");

		String html = sendMessageToDevices(1);

		assertEquals("returns a non empty page", "", html);
		assertEquals("the message must have been sent to the Google service", 1, GoogleServiceResourceMock.getMessages().size());
		assertEquals("{\"xsi:type\":\"messageRequest\",\"to\":\"theId1\",\"data\":{\"message\":\"the Message\"}}", GoogleServiceResourceMock.getMessages().get(0));
	}

	@Test
	public void testSendMessageTwoDevices() throws MalformedURLException, URISyntaxException, InterruptedException {

		Utils.createDevice(deviceService, "theId1");
		Utils.createDevice(deviceService, "theId2");

		String html = sendMessageToDevices(2);

		assertEquals("returns a non empty page", "", html);
		assertEquals("the message must have been sent to the Google service twice", 2, GoogleServiceResourceMock.getMessages().size());

		String expectedMessageId1 = "{\"xsi:type\":\"messageRequest\",\"to\":\"theId1\",\"data\":{\"message\":\"the Message\"}}";
		assertTrue(GoogleServiceResourceMock.getMessages().contains(expectedMessageId1));

		String expectedMessageId2 = "{\"xsi:type\":\"messageRequest\",\"to\":\"theId2\",\"data\":{\"message\":\"the Message\"}}";
		assertTrue(GoogleServiceResourceMock.getMessages().contains(expectedMessageId2));
	}

	private String sendMessageToDevices(int count) throws InterruptedException {
		GoogleServiceResourceMock.resetCount(count);
		Form form = new Form();
		String message = "the Message";
		form.param("message", message);

		String html =
				target("/messages").request(MediaType.TEXT_HTML_TYPE)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE),
						String.class);
		GoogleServiceResourceMock.getCount().await(1, TimeUnit.MINUTES);
		return html;
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		deviceService.reset();
		deviceService = null;
		GoogleServiceResourceMock.reset();
	}

}
