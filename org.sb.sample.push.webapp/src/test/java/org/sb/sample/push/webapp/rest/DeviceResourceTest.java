package org.sb.sample.push.webapp.rest;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Test;
import org.sb.sample.push.client.device.IDeviceService;

public class DeviceResourceTest extends JerseyTest {

	@Inject
	private IDeviceService deviceService;

	@Override
    protected Application configure() {
        return Utils.configure(this, getBaseUri(), DevicesResource.class);
    }
 
    @Test
    public void testGetDeviceJson() {
    	Utils.createDevice(deviceService, "toto");
    	Utils.createDevice(deviceService, "titi");
    	String theDevice =
    			target("/devices/toto").request(MediaType.APPLICATION_JSON)
    			    .get(String.class);
    	
        assertEquals("must return the json version of the device", "{\"id\":\"toto\"}", theDevice);
    }
    
    @Test
    public void testGetDeviceXml() {
    	Utils.createDevice(deviceService, "toto");
    	Utils.createDevice(deviceService, "titi");
    	String theDevice =
    			target("/devices/toto").request(MediaType.APPLICATION_XML)
    			    .get(String.class);
    	
        assertEquals("must return the XML version of the device", "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><device><id>toto</id></device>", theDevice);
    }
    
    @After
	public void tearDown() throws Exception {
		super.tearDown();
		deviceService.reset();
     }
    
}
