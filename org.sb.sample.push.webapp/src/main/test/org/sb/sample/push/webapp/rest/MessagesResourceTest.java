package org.sb.sample.push.webapp.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sb.sample.push.client.device.Device;
import org.sb.sample.push.client.http.HttpPost;
import org.sb.sample.push.client.impl.NotificationServiceImpl;
import org.sb.sample.push.webapp.ServiceLocator;

public class MessagesResourceTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new ResourceConfig(MessagesResource.class, GoogleServiceResourceMock.class);
	}

	@Test
	public void testSendMessage() throws MalformedURLException, URISyntaxException, InterruptedException {
		createDevice("theId1");

		String html = sendMessageToDevices(1);

		assertEquals("returns a non empty page", "", html);
		assertEquals("the message must have been sent to the Google service", 1, GoogleServiceResourceMock.getMessages().size());
		assertEquals("{\"xsi:type\":\"messageRequest\",\"to\":\"theId1\",\"data\":{\"message\":\"the Message\"}}", GoogleServiceResourceMock.getMessages().get(0));
	}

	private Device createDevice(String id) {
		Device device1 = new Device();
		device1.setId(id);
		ServiceLocator.instance.getDeviceService().registerDevice(device1);
		return device1;
	}

	@Test
	public void testSendMessageTwoDevices() throws MalformedURLException, URISyntaxException, InterruptedException {

		createDevice("theId1");
		createDevice("theId2");

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

	@Before
	public void setUp() throws Exception {
		super.setUp();

		NotificationServiceImpl notificationService = new NotificationServiceImpl(new HttpPost("The_API_KEY", getBaseUri() + "gcm/send"));
		ServiceLocator.instance.setNotificationService(notificationService);
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		ServiceLocator.instance.reset();
		GoogleServiceResourceMock.reset();
	}

}
