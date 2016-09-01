package org.sb.sample.push.client;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.junit.Test;
import org.sb.sample.push.client.http.HttpPost;
import org.sb.sample.push.client.impl.NotificationServiceImpl;

public class NotificationServiceImplTest {

	private static final String HTTP_ANDROID_GOOGLEAPIS_COM_GCM_SEND = "http://android.googleapis.com/gcm/send";
    public static final String API_KEY = "AIzaSyAg4fBYuH361390QCL5LukBJm1qrH1yntM";
    //public static final String API_KEY = "1:218774439706:android:f36173e948059ccb";
    
	@Test
	public void testSend() {
        try {
            INotificationService notificationService = new NotificationServiceImpl(new HttpPost(API_KEY, HTTP_ANDROID_GOOGLEAPIS_COM_GCM_SEND));
            MessageRequest messageRequest = new MessageRequest();
            messageRequest.data = new DataMessage("hello");
            IMessageResponse messageResponse = notificationService.sendMessageTo("APA91bE-t0FEcmwbRz9IKmGZ0wsS6NiQvnnZV0YrgnuE8-TocHurKwA_pM4wrayU0y0VqjB49wy62ik5e62HuG1syizVREnvP3lF_GpTo0rGV5AMkQHiYc_H8Cu81FDOv9OVntMzkLu4-1_MVUsN8sLpzo6By2E8Ig", messageRequest);
			
			assertTrue(messageResponse.toString().contains("success=1"));
		} catch (MalformedURLException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
    }

}
