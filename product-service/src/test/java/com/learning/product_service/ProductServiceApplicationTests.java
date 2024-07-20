package com.learning.product_service;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mongoDBContainer.start();
	}

	@Test
	void shouldCreateProduct() throws JsonProcessingException, Exception {
		String requestBody = """
				{
				    "name": "iphone_13",
				    "description": "Mobile",
				    "price": 1200
				}
								""";
		RestAssured.given().contentType("application/json").body(requestBody).when().post("/api/product").then()
				.statusCode(201).body("id", Matchers.notNullValue()).body("name", Matchers.equalTo("iphone_13"))
				.body("description", Matchers.equalTo("Mobile")).body("price", Matchers.equalTo(1200));

	}

}
