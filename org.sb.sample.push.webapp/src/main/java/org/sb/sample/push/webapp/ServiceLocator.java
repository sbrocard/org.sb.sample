package org.sb.sample.push.webapp;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.sb.sample.push.client.INotificationService;
import org.sb.sample.push.client.device.IDeviceService;
import org.sb.sample.push.client.device.impl.DeviceService;
import org.sb.sample.push.client.http.HttpPost;
import org.sb.sample.push.client.impl.NotificationServiceImpl;

public enum ServiceLocator {
	instance;

	/**
	 * TODO sb, use dependency injection
	 */
	private final IDeviceService deviceService = new DeviceService();

	/**
	 * TODO sb, use dependency injection
	 */
	private INotificationService notificationService;
	
	/**
	 * TODO sb, get the target URL from configuration of the app
	 */
	private static final String HTTP_ANDROID_GOOGLEAPIS_COM_GCM_SEND = "http://android.googleapis.com/gcm/send";

	/**
	 * TODO sb, get the key from the configuration of the app
	 */
	public static final String API_KEY = "AIzaSyAg4fBYuH361390QCL5LukBJm1qrH1yntM";


	private ServiceLocator() {
		NotificationServiceImpl notificationService1;
		try {
			notificationService1 = new NotificationServiceImpl(new HttpPost(API_KEY, HTTP_ANDROID_GOOGLEAPIS_COM_GCM_SEND));
		} catch (MalformedURLException | URISyntaxException e) {
			// TODO sb deal with the exception
			notificationService1 = null;
			e.printStackTrace();
		}
		notificationService = notificationService1;
	}

	public IDeviceService getDeviceService(){
		return deviceService;
	}
	
	/**
	 * @return may be <code>null</code> TODO sb avoid null pointer
	 */
	public INotificationService getNotificationService(){
		return notificationService;
	}

	/**
	 * Only for the tests
	 * TODO sb, to be removed when dependency injection is in place
	 */
	public void reset() {
		deviceService.reset();
	}

	/** for the tests
	 * @param notificationService
	 */
	public void setNotificationService(INotificationService notificationService) {
		this.notificationService = notificationService;
	}

}
