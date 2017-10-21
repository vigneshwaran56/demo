package com.concretepage.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.concretepage.endpoint.ArticleEndpoint;
import com.concretepage.endpoint.UserEndpoint;

@Component

@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(ArticleEndpoint.class);
		register(UserEndpoint.class);
//		packages("org.glassfish.jersey.examples.multipart");
//		register(MultiPartFeature.class);
	}
	
}
