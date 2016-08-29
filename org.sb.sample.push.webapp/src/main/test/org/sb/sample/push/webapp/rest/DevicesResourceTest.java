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
    	
    	String html =
    			target("/devices").request(MediaType.TEXT_HTML_TYPE)
    			    .post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE),
    			    		String.class);
    	
        assertEquals("returns an empty page", "", html);
        assertTrue("the device must have been inserted", ServiceLocator.instance.getDeviceService().existsDevice(id));
    }

     @After
     public void tearDown() {
    	 ServiceLocator.instance.reset();
     }
    
}
