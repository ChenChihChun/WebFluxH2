//package com.demo.config;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.lang.NonNull;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//public class ApiKeyAuthFilter extends OncePerRequestFilter {
//
//	private final String apiKeyParameterName;
//	private static final Logger log = LogManager.getLogger(ApiKeyAuthFilter.class);
//
//	public ApiKeyAuthFilter(String apiKeyParameterName) {
//		this.apiKeyParameterName = apiKeyParameterName;
//	}
//
//	@Override
//	protected void doFilterInternal(@NonNull HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		String apiKey = request.getParameter(apiKeyParameterName);
//		
//		// 000123000 是給Swagger用的 或是測試用的 不要公開出去
//		if (("000123000".equals(apiKey))) {
//			
//			SecurityContextHolder.getContext()
//					.setAuthentication(new UsernamePasswordAuthenticationToken("user", null, new ArrayList<>()));
//			String passString = "Pass," + apiKey;
//			log.debug(passString);
//		}
//		filterChain.doFilter(request, response);
//	}
//
//}
