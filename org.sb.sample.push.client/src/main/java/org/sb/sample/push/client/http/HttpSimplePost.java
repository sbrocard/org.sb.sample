package org.sb.sample.push.client.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.bind.JAXBException;

import org.json.JSONObject;
import org.sb.sample.push.client.GCMMessageResponse;
import org.sb.sample.push.client.IMessageResponse;
import org.sb.sample.push.client.MessageRequest;

/**
 * This class is a possible alternative to the automatic use of
 * the JSON marshalling done by Jersey. 
 * It will probably be removed soon
 * @deprecated
 * @author sbrocard
 *
 */
public class HttpSimplePost {

	private final URL url;
	private final String apiKey;

	private Class<? extends IMessageResponse> messageResponseType;

	public HttpSimplePost(String apiKey, String uriString) throws MalformedURLException, URISyntaxException {
		this.apiKey = apiKey;
		String urlString = uriString;
		url = new URL(urlString);
		messageResponseType = GCMMessageResponse.class;
	}

	/**
	 * @deprecated
	 * @param args
	 */
	public void send(String... args) {
		try {
			// Prepare JSON containing the GCM message content. What to send and where to send.
			JSONObject jGcmData = new JSONObject();
			JSONObject jData = new JSONObject();
			jData.put("message", args[0].trim());
			//jData.put("msgcnt", "1");
			// Where to send GCM message.
			if (args.length > 1 && args[1] != null) {
				jGcmData.put("to", args[1].trim());
			} else {
				jGcmData.put("to", "/topics/global");
			}
			// What to send in GCM message.
			jGcmData.put("data", jData);

			sendImpl(jGcmData.toString().getBytes(), GCMMessageResponse.class);
		} catch (IOException | JAXBException e) {
			System.out.println("Unable to send GCM message.");
			System.out.println("Please ensure that API_KEY has been replaced by the server " +
					"API key, and that the device's registration token is correct (if specified).");
			e.printStackTrace();
		}
	}

	/** This method is a possible alternative to the use of Jersey to send a
	 * json payload.
	 * 
	 * It will probably be removed soon
	 * @deprecated
	 * @param payload
	 * @param resultClass
	 * @return
	 * @throws IOException
	 * @throws ProtocolException
	 * @throws JAXBException
	 */
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

	/**
	 * @deprecated
	 * @param request
	 * @return
	 */
	public IMessageResponse sendHttpRequest(MessageRequest request) {
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
