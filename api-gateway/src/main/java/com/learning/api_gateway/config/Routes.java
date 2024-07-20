package com.learning.api_gateway.config;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Routes {

	@Bean
	RouterFunction<ServerResponse> productServiceRoute() {
		return GatewayRouterFunctions.route("product-service")
				.route(RequestPredicates.path("/api/product"), HandlerFunctions.http("http://localhost:8081")).build();

	}

	@Bean
	RouterFunction<ServerResponse> orderServiceRoute() {
		return GatewayRouterFunctions.route("order-service")
				.route(RequestPredicates.path("/api/order"), HandlerFunctions.http("http://localhost:8082")).build();

	}

	@Bean
	RouterFunction<ServerResponse> inventoryServiceRoute() {
		return GatewayRouterFunctions.route("inventory-service")
				.route(RequestPredicates.path("/api/inventory"), HandlerFunctions.http("http://localhost:8083"))
				.build();

	}

	@Bean
	RouterFunction<ServerResponse> discovereyServerRoute() {
		return GatewayRouterFunctions.route("discoverey-server")
				.route(RequestPredicates.path("/eureka/web"), HandlerFunctions.http("http://localhost:8761")).build();

	}

	@Bean
	RouterFunction<ServerResponse> discovereyServerStaticRoute() {
		return GatewayRouterFunctions.route("discoverey-server-static")
				.route(RequestPredicates.path("/eureka/**"), HandlerFunctions.http("http://localhost:8761")).build();

	}

}
