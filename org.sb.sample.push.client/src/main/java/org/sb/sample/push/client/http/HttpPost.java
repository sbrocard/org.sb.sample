package org.sb.sample.push.client.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;

import org.glassfish.jersey.logging.LoggingFeature;
import org.sb.sample.push.client.GCMMessageResponse;
import org.sb.sample.push.client.IMessageResponse;
import org.sb.sample.push.client.MessageRequest;
import org.sb.sample.push.client.rest.ClientBuilderInstance;

public class HttpPost {

	private final URL url;
	private URI uri;
	private final String apiKey;

	private Class<? extends IMessageResponse> messageResponseType;

	public HttpPost(String apiKey) throws MalformedURLException, URISyntaxException {
		this(apiKey, "http://android.googleapis.com/gcm/send");
	}

	public HttpPost(String apiKey, String uriString) throws MalformedURLException, URISyntaxException {
		this.apiKey = apiKey;
//		String urlString = "https://android.googleapis.com/gcm/send";
		String urlString = uriString;
		uri = new URI(urlString);
		url = new URL(urlString);
		messageResponseType = GCMMessageResponse.class;
	}

	
	public <T> T sendImpl(byte[] payload, Class<T> resultClass)
			throws IOException, ProtocolException, JAXBException {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Authorization", "key=" + apiKey);
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);

		// Send GCM message content.
		OutputStream outputStream = conn.getOutputStream();
		outputStream.write(payload);

		// Read GCM response.
		return JSONReaderWriter.unmarshall(conn.getInputStream(), resultClass);
	}
	
	public IMessageResponse sendHttpRequest(MessageRequest request) {
		if (true) {
			Logger logger = Logger.getLogger(getClass().getName());

			Feature feature = new LoggingFeature(logger, Level.INFO, null, null);
			final Client client = ClientBuilderInstance.INSTANCE.build();
			client.register(feature);
			final WebTarget target = client.target(uri);

			return target
					.request(MediaType.APPLICATION_JSON_TYPE)
					.header(HttpHeaders.AUTHORIZATION, "key=" + apiKey)
					.post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE), messageResponseType);
		} else {
			try {
				IMessageResponse searchResults = sendImpl(JSONReaderWriter.marshall(request), messageResponseType);
				return searchResults;
			} catch (IOException | JAXBException e) {
				// TODO sb, gerer les exception
				e.printStackTrace();
			}
			return null;
		}
	}


}
