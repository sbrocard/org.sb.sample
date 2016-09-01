package org.sb.sample.push.webapp.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Test;
import org.sb.sample.push.webapp.ServiceLocator;

public class DevicesResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(DevicesResource.class);
    }
 
    @Test
    public void testNewDevice() {
    	Form form = new Form();
    	String id = "theId";
		form.param("id", id);
    	
    	String empty =
    			target("/devices").request(MediaType.TEXT_HTML_TYPE)
    			    .post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE),
    			    		String.class);
    	
        assertEquals("returns an empty page", "", empty);
        assertTrue("the device must have been inserted", ServiceLocator.instance.getDeviceService().existsDevice(id));
    }

    @Test
    public void testDeviceCount() {
    	Utils.createDevice("toto");
    	Utils.createDevice("titi");
    	String count =
    			target("/devices/count").request(MediaType.TEXT_PLAIN)
    			    .get(String.class);
    	
        assertEquals("returns an empty page", "2", count);
    }
    
    @Test
    public void testGetDevicesXml() {
    	Utils.createDevice("toto");
    	Utils.createDevice("titi");
    	String theDevice =
    			target("/devices").request(MediaType.APPLICATION_XML)
    			    .get(String.class);
    	
        assertEquals("must return the XML version of the devices", "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><devices><device><id>toto</id></device><device><id>titi</id></device></devices>", theDevice);
    }

    @Test
    public void testGetDevicesJson() {
    	Utils.createDevice("toto");
    	Utils.createDevice("titi");
    	String theDevice =
    			target("/devices").request(MediaType.APPLICATION_JSON)
    			    .get(String.class);
    	
        assertEquals("must return the JSON version of the devices", 
        		"[{\"id\":\"toto\"},{\"id\":\"titi\"}]", theDevice);
    }

    @After
	public void tearDown() throws Exception {
		super.tearDown();
		ServiceLocator.instance.reset();
     }
    
}
