package org.sb.sample.push.webapp.device;

import java.util.HashMap;
import java.util.Map;

public enum DeviceDao {
	  instance;
	  
	  private Map<String, Device> contentProvider = new HashMap<>();
	  
	  private DeviceDao() {
	    
	  }
	  public Map<String, Device> getModel(){
	    return contentProvider;
	  }
}
