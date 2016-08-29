package org.sb.sample.push.client;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DataMessage extends Data {

	public DataMessage() {
	}

	public DataMessage(String message) {
		this.message = message;
	}

	public String message;
}
