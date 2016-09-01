package org.sb.sample.push.webapp.rest;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Test;
import org.sb.sample.push.webapp.ServiceLocator;

public class DeviceResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(DevicesResource.class);
    }
 
    @Test
    public void testGetDeviceJson() {
    	Utils.createDevice("toto");
    	Utils.createDevice("titi");
    	String theDevice =
    			target("/devices/toto").request(MediaType.APPLICATION_JSON)
    			    .get(String.class);
    	
        assertEquals("must return the json version of the device", "{\"id\":\"toto\"}", theDevice);
    }
    
    @Test
    public void testGetDeviceXml() {
    	Utils.createDevice("toto");
    	Utils.createDevice("titi");
    	String theDevice =
    			target("/devices/toto").request(MediaType.APPLICATION_XML)
    			    .get(String.class);
    	
        assertEquals("must return the XML version of the device", "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><device><id>toto</id></device>", theDevice);
    }
    
    @After
	public void tearDown() throws Exception {
		super.tearDown();
		ServiceLocator.instance.reset();
     }
    
}
