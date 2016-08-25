package org.sb.sample.push.webapp.device;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Device {

	private String id;

	public Device() {
		this(null);
	}
	
	public Device(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
