package org.sb.sample.push.client;

/**
 * Interface that defines how to push notification
 * TODO sb, create a reactive interface with RxJava 
 */
public interface INotificationService {

//	IMessageResponse sendMessageTo(String to, String message);

	IMessageResponse sendMessageTo(String to, MessageRequest request);
	
	IMessageResponse sendMessageToTopic(String topic, MessageRequest request);
	
	IMessageResponse sendMessageConditionTopics(String condition, MessageRequest request);
	
	MessageToDeviceGroupsResponse sendMessageToDeviceGroup(String deviceGroup, MessageRequest request);
	
}
