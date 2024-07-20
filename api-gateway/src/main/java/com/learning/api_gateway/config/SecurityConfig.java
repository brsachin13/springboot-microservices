package com.learning.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableWebFluxSecurity
@EnableWebSecurity
public class SecurityConfig {

//	@Bean
//	public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception {
//
//		return http.csrf(csrf -> csrf.disable())
//				.authorizeExchange(
//						exchange -> exchange.pathMatchers("/eureka/**").permitAll().anyExchange().authenticated())
//				.oauth2ResourceServer(server -> server.jwt(jwt -> jwt
//						.jwkSetUri("http://localhost:8095/realms/Microservices/protocol/openid-connect/certs")))
//				.build();
//	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(
						requests -> requests.requestMatchers("/eureka/**").permitAll().anyRequest().authenticated())
				.oauth2ResourceServer(server -> server.jwt(jwt -> jwt
						.jwkSetUri("http://localhost:8095/realms/Microservices/protocol/openid-connect/certs")))
				.build();
	}

}
