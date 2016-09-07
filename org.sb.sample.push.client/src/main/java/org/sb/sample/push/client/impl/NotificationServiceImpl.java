package org.sb.sample.push.client.impl;

import javax.inject.Inject;

import org.sb.sample.push.client.IMessageResponse;
import org.sb.sample.push.client.INotificationService;
import org.sb.sample.push.client.MessageRequest;
import org.sb.sample.push.client.MessageToDeviceGroupsResponse;
import org.sb.sample.push.client.http.HttpPost;

public class NotificationServiceImpl implements INotificationService {

	private HttpPost httpPost;
	
	@Inject
	public NotificationServiceImpl(HttpPost httpPost) {
		this.httpPost = httpPost;
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
