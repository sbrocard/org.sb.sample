package org.sb.sample.push.client;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GCMMessageId {

	public String message_id;

	@Override
	public String toString() {
		return "GCMMessageId [message_id=" + message_id + "]";
	}
	
	
}
