package org.sb.sample.push.client.http;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;

public class JSONReaderWriter {

	public static <T> byte[] marshall(T data) throws JAXBException
	{
		// Create a JaxBContext
		JAXBContext jc = JAXBContext.newInstance(data.getClass());

		// Create the Marshaller Object using the JaxB Context
		Marshaller marshaller = jc.createMarshaller();

		// Set the Marshaller media type to JSON or XML
		marshaller.setProperty(MarshallerProperties.MEDIA_TYPE,
				"application/json");

		// Set it to true if you need to include the JSON root element in the JSON output
		marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, false);

		// Set it to true if you need the JSON output to formatted
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		// Marshal the employee object to JSON and print the output to console
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		marshaller.marshal(data, output);
		return output.toByteArray();
	}

	public static <T> T unmarshall(InputStream inputstream, Class<T> zClass) throws JAXBException {
		// Create a JaxBContext
		JAXBContext jc = JAXBContext.newInstance(zClass);
		// Create the Unmarshaller Object using the JaxB Context
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		// Set the Unmarshaller media type to JSON or XML
		unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE,
				"application/json");
		// Set it to true if you need to include the JSON root element in the
		// JSON input
		unmarshaller
		.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, false);
		// Create the StreamSource by creating StringReader using the JSON input
		StreamSource json = new StreamSource(inputstream);

		// Getting the SearchResults pojo again from the json
		T result = unmarshaller.unmarshal(json, zClass)
				.getValue();
		return result;
	}
	
}
