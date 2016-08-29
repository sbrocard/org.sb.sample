package org.sb.sample.push.client;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * <code></code>
 * 
 */
@XmlRootElement
public class Message {

	public String to;
	public String condition;

	public DataMessage data;
	
	public String collapse_key;
	
	/**
	 * Setting the lifespan of a message
	 * The value of this parameter must be a duration from 0 to 2,419,200 seconds
	 */
	public Integer time_to_live;
	
	public Boolean delay_while_idle;

	/**
	 * "normal" or "high"
	 * TODO sb, try to put an enum instead
	 */
	public String priority;
	
	
	public Message() {
	}

}
