package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import io.r2dbc.spi.ConnectionFactory;

@SpringBootApplication
@ComponentScan
public class HelloWorldApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HelloWorldApplication.class);
	}

	
	public static void main(String[] args) {
		SpringApplication.run(HelloWorldApplication.class, args);
	}
	
	  @Bean
	  ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

	    ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
	    initializer.setConnectionFactory(connectionFactory);
	    ClassPathResource resource = new ClassPathResource("schema.sql");
	    if (!resource.exists()) {
	        throw new RuntimeException("Cannot find 'schema.sql'");
	    }
	    initializer.setDatabasePopulator(new ResourceDatabasePopulator(resource));

	    return initializer;
	  }
}
