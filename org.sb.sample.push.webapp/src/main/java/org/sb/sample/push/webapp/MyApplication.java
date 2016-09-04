package org.sb.sample.push.webapp;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.sb.sample.push.client.INotificationService;
import org.sb.sample.push.client.device.IDeviceService;
import org.sb.sample.push.client.device.impl.DeviceDao;
import org.sb.sample.push.client.device.impl.DeviceService;
import org.sb.sample.push.client.device.impl.IDeviceDao;
import org.sb.sample.push.client.http.HttpPost;
import org.sb.sample.push.client.impl.NotificationServiceImpl;

public class MyApplication extends ResourceConfig {


	/**
	 * TODO sb, get the target URL from configuration of the app
	 */
	private static final String HTTP_ANDROID_GOOGLEAPIS_COM_GCM_SEND = "http://android.googleapis.com/gcm/send";

	/**
	 * TODO sb, get the key from the configuration of the app
	 */
	public static final String API_KEY = "AIzaSyAg4fBYuH361390QCL5LukBJm1qrH1yntM";

	private final IDeviceDao deviceDao = new DeviceDao();
	private final IDeviceService deviceService = new DeviceService(deviceDao);
	private final HttpPost httpPost;
	private final INotificationService notificationService;

	public MyApplication() {
		registerFactories();

		HttpPost httpPost2 = null;
		try {
			httpPost2 = new HttpPost(API_KEY, HTTP_ANDROID_GOOGLEAPIS_COM_GCM_SEND);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		httpPost = httpPost2;
		notificationService = new NotificationServiceImpl(httpPost);
	}

	private void registerFactories() {
		register(new AbstractBinder() {
			@Override
			protected void configure() {
				bind(deviceService).to(IDeviceService.class);
				bind(notificationService).to(INotificationService.class);
			}
		});
	}
}