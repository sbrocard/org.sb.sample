package org.sb.sample.push.webapp.rest;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.glassfish.hk2.api.Factory;
import org.sb.sample.push.client.http.HttpPost;

public class TestHttpPostFactory implements Factory<HttpPost> {

	public static URI baseUri;
	
	public TestHttpPostFactory() {
	}

	@Override
	public HttpPost provide() {
		try {
			return new HttpPost("The_API_KEY", baseUri + "gcm/send");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; // TODO sb, don't return null, return a mock ?
	}

	@Override
	public void dispose(HttpPost t) {
	}
}