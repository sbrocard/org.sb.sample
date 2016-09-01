package org.sb.sample.push.client.http;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.logging.LoggingFeature;
import org.sb.sample.push.client.GCMMessageResponse;
import org.sb.sample.push.client.IMessageResponse;
import org.sb.sample.push.client.MessageRequest;
import org.sb.sample.push.client.rest.ClientBuilderInstance;

public class HttpPost {

	private final URI uri;
	private final String apiKey;
	private final Class<? extends IMessageResponse> messageResponseType;

	public HttpPost(String apiKey, String uriString) throws MalformedURLException, URISyntaxException {
		this.apiKey = apiKey;
		String urlString = uriString;
		uri = new URI(urlString);
		messageResponseType = GCMMessageResponse.class;
	}

	public IMessageResponse sendHttpRequest(MessageRequest request) {
		Logger logger = Logger.getLogger(getClass().getName());

		Feature feature = new LoggingFeature(logger, Level.INFO, null, null);
		final Client client = ClientBuilderInstance.INSTANCE.build();
		client.register(feature);
		final WebTarget target = client.target(uri);

		return target
				.request(MediaType.APPLICATION_JSON_TYPE)
				.header(HttpHeaders.AUTHORIZATION, "key=" + apiKey)
				.post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE), messageResponseType);
	}

}
