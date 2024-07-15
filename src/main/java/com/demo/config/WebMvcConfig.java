package com.demo.config;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Value("${app.swagger.server-url}")
	private String serverUrl;
	
	@Bean
	public OpenAPI initOpenAPI() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
		return new OpenAPI().info(new Info().title("Test API").description("user shoppingCar API").version("v1.0")
				.contact(new Contact().name("Peter").email("maxpeter2100@gmail.com")).license(new License()
						.name("Copyright © " + cal.get(Calendar.YEAR) + "  . All rights reserved").url("#")))
				.addServersItem(new Server().url(serverUrl))
				.addSecurityItem(new SecurityRequirement().addList("apiKey"))
				.components(new Components().addSecuritySchemes("apiKey",
						new SecurityScheme().type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.QUERY)
								.name("X-API-KEY")));
	}
}
