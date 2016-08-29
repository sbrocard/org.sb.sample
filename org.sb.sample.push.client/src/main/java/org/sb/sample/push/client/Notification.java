package org.sb.sample.push.client;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Notification {

	public String body;
	public String title;
	public String icon;
}
