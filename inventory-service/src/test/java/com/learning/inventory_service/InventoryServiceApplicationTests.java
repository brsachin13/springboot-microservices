package com.learning.inventory_service;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryServiceApplicationTests {

	@ServiceConnection
	static public MySQLContainer<?> mysqlContainer() {
		return new MySQLContainer<>("mysql:8.3.0").withDatabaseName("inventory_service").withUsername("root")
				.withPassword("mysql");
	}

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	@Test
	void contextLoads() {
		String requestBody = """
								[
				    {
				     "skuCode": "iphone_13",
					 "quantity": 100
				    },
				    {
				     "skuCode": "iphone_13_red",
					 "quantity": 0
				    },
				    {
				     "skuCode": "iphone_15",
					 "quantity": 10
				    },
				    {
				     "skuCode": "iphone_15_black",
					 "quantity": 0
				    }
				]
								""";

		var responseString = RestAssured.given().contentType("application/json").body(requestBody).when()
				.post("/api/inventory").then().log().all().statusCode(201).extract().body().asString();

		assertThat(responseString, Matchers.is("Invetory loaded successfully"));
	}

}
