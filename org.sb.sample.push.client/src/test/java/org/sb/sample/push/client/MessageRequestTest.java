package org.sb.sample.push.client;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.ws.rs.ProcessingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.io.FileUtils;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.junit.Test;
import org.sb.sample.push.client.http.JSONReaderWriter;

public class MessageRequestTest {

	@Test
	public void testJsonMarshalling() {
		try {
			MessageRequest messageRequest = new MessageRequest();
			messageRequest.to = "theTo";
			messageRequest.data = new DataMessage("theMessage");
			messageRequest.collapse_key = "theCollapseKey";
			messageRequest.condition = "theCondition";
			messageRequest.delay_while_idle = true;
			messageRequest.priority = "thePriority";
			messageRequest.time_to_live = 5;
			
			byte[] theBytes = JSONReaderWriter.marshall(messageRequest);
			String line = loadResource("MessageRequestMashallTest.json");
			assertEquals(line, new String(theBytes));
		} catch (JAXBException jaxbException) {
			throw new ProcessingException("Error deserializing a SearchResults.",
					jaxbException);
		}
	}

	// TODO sb find an API that does the loading for us
	private String loadResource(String resource) {
		String line;
		try {
			InputStream expectedInputStream = getClass().getResourceAsStream(resource);
			BufferedReader reader = new BufferedReader(new InputStreamReader(expectedInputStream));
			StringBuilder out = new StringBuilder();
			boolean first = true;
			while ((line = reader.readLine()) != null) {
				if (first == false) {
				    out.append(System.lineSeparator());
				} else {
					first = false;
				}
			    out.append(line);
			}
			line = out.toString();
		} catch (IOException e) {
			throw new ProcessingException("Error deserializing a SearchResults.",
					e);
		}
		return line;
	}
}
