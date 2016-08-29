package org.sb.sample.push.client;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GCMMessageResponse implements IMessageResponse {

	public String multicast_id;
	
	public String success;
	
	public String failure;
	public String canonical_ids;
	
	public List<GCMMessageId> results;

	@Override
	public String toString() {
		return "GCMMessageResponse [multicast_id=" + multicast_id + ", success=" + success + ", failure=" + failure
				+ ", canonical_ids=" + canonical_ids + ", results=" + results + "]";
	}
	
	
	
}
