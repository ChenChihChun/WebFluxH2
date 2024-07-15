package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//		http.csrf(csrf->csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//				.ignoringRequestMatchers(new OrRequestMatcher(new AntPathRequestMatcher("/v3/api-docs/**"),
//						new AntPathRequestMatcher("/swagger-ui.html"), new AntPathRequestMatcher("/swagger-ui/**")))
//				.ignoringRequestMatchers("/api/**"))
//				.authorizeHttpRequests(
//						authorize -> authorize.requestMatchers("/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**")
//								.permitAll()
//								.requestMatchers("/css/page/main.css")
//								.permitAll()
//								.requestMatchers("/h2-console/**").permitAll()
//								.requestMatchers("/api/**").authenticated())
//				.addFilterBefore(new ApiKeyAuthFilter("apiKey"), UsernamePasswordAuthenticationFilter.class);
//		
		http.csrf().disable().authorizeHttpRequests(authorize -> 
		authorize.requestMatchers("**").permitAll()).headers().frameOptions().disable();;
		return http.build();
	}
}
