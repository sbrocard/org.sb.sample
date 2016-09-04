package org.sb.sample.push.webapp.rest;

import java.net.URI;

import javax.inject.Singleton;
import javax.ws.rs.core.Application;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.Binder;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.sb.sample.push.client.INotificationService;
import org.sb.sample.push.client.device.Device;
import org.sb.sample.push.client.device.IDeviceService;
import org.sb.sample.push.client.device.impl.DeviceDao;
import org.sb.sample.push.client.device.impl.DeviceService;
import org.sb.sample.push.client.device.impl.IDeviceDao;
import org.sb.sample.push.client.http.HttpPost;
import org.sb.sample.push.client.impl.NotificationServiceImpl;

public class Utils {

	private static Binder binder;

	static {
		binder = new AbstractBinder() {
	    	final IDeviceDao deviceDao = new DeviceDao();
	    	final IDeviceService deviceService = new DeviceService(deviceDao);
	    	
	        @Override
	        protected void configure() {
	            bind(deviceService).to(IDeviceService.class);
	            bindFactory(TestHttpPostFactory.class).to(HttpPost.class).in(Singleton.class);
	            bind(NotificationServiceImpl.class).to(INotificationService.class).in(Singleton.class);
	        }
	    };
	}
	
	private Utils() {
	}
	
	public static Device createDevice(IDeviceService deviceService, String id) {
		Device device1 = new Device();
		device1.setId(id);
		deviceService.registerDevice(device1);
		return device1;
	}
	
    public static Application configure(JerseyTest injectMe, URI baseUri, final Class<?>... classes) {
		TestHttpPostFactory.baseUri = baseUri;

		ResourceConfig resourceConfig = new ResourceConfig(classes);
		resourceConfig.register(binder);

		ServiceLocator locator = ServiceLocatorUtilities.bind(binder);
		locator.inject(injectMe);

		return resourceConfig;
    }
}
