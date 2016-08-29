package org.sb.sample.push.client.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import javax.xml.bind.JAXBException;

import org.json.JSONObject;
import org.sb.sample.push.client.GCMMessageResponse;
import org.sb.sample.push.client.IMessageResponse;
import org.sb.sample.push.client.INotificationService;
import org.sb.sample.push.client.MessageRequest;
import org.sb.sample.push.client.MessageToDeviceGroupsResponse;
import org.sb.sample.push.client.http.HttpPost;

public class NotificationServiceImpl implements INotificationService {

	private HttpPost httpPost;

	public NotificationServiceImpl(HttpPost httpPost) throws MalformedURLException, URISyntaxException {
		this.httpPost = httpPost;
	}
	
	/**
	 * @deprecated
	 * @param args
	 */
	public void send(String... args) {
		try {
			// Prepare JSON containing the GCM message content. What to send and where to send.
			JSONObject jGcmData = new JSONObject();
			JSONObject jData = new JSONObject();
			jData.put("message", args[0].trim());
			//jData.put("msgcnt", "1");
			// Where to send GCM message.
			if (args.length > 1 && args[1] != null) {
				jGcmData.put("to", args[1].trim());
			} else {
				jGcmData.put("to", "/topics/global");
			}
			// What to send in GCM message.
			jGcmData.put("data", jData);

			httpPost.sendImpl(jGcmData.toString().getBytes(), GCMMessageResponse.class);
		} catch (IOException | JAXBException e) {
			System.out.println("Unable to send GCM message.");
			System.out.println("Please ensure that API_KEY has been replaced by the server " +
					"API key, and that the device's registration token is correct (if specified).");
			e.printStackTrace();
		}
	}

	@Override
	public IMessageResponse sendMessageTo(String to, MessageRequest request) {
		request.to = to;
		return httpPost.sendHttpRequest(request);
	}

	@Override
	public IMessageResponse sendMessageToTopic(String topic, MessageRequest request) {
		request.to = "topic/" + topic;
		return httpPost.sendHttpRequest(request);
	}

	@Override
	public IMessageResponse sendMessageConditionTopics(String condition, MessageRequest request) {
		request.condition = condition; // TOOD sb, validate the syntax or let the server do it ?
		return httpPost.sendHttpRequest(request);
	}

	@Override
	public MessageToDeviceGroupsResponse sendMessageToDeviceGroup(String deviceGroup, MessageRequest request) {
		return (MessageToDeviceGroupsResponse) sendMessageTo(deviceGroup, request);
	}

}
