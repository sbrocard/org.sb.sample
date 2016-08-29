package org.sb.sample.push.client.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.ext.ContextResolver;

import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;

public enum ClientBuilderInstance {
	INSTANCE;
	
	private ClientBuilder clientBuilder;

	ClientBuilderInstance () {
		final Map<String, String> namespacePrefixMapper = new HashMap<String, String>();
		namespacePrefixMapper.put("http://www.w3.org/2001/XMLSchema-instance", "xsi");
		 
		final MoxyJsonConfig moxyJsonConfig = new MoxyJsonConfig()
		            .setNamespacePrefixMapper(namespacePrefixMapper)
		            .setNamespaceSeparator(':');
		 
		final ContextResolver<MoxyJsonConfig> jsonConfigResolver = moxyJsonConfig.resolver();
		
		clientBuilder = ClientBuilder.newBuilder()
		        // The line below that registers MOXy feature can be
		        // omitted if FEATURE_AUTO_DISCOVERY_DISABLE is
		        // not disabled.
		        .register(MoxyJsonFeature.class)
		        .register(jsonConfigResolver)
//		        .register(MyMoxyJSONProvider.class)
		        ;
	}
	
	public Client build() {
		return clientBuilder.build();
	}

}
