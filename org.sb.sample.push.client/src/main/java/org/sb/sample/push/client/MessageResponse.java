package org.sb.sample.push.client;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MessageResponse implements IMessageResponse {

	public String message_id;

	/**
	 * "TopicsMessageRateExceeded"
	 */
	public String error;

	@Override
	public String toString() {
		return "MessageResponse [message_id=" + message_id + ", error=" + error + "]";
	} 


}
