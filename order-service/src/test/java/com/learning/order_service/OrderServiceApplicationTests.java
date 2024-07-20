package com.learning.order_service;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.testcontainers.containers.MySQLContainer;

import com.learning.order_service.stubs.InventoryClientStub;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class OrderServiceApplicationTests {

	@ServiceConnection
	static MySQLContainer<?> mysqlContainer = (MySQLContainer<?>) new MySQLContainer("mysql:8.3.0").withDatabaseName("order_service")
			.withInitScript("init.sql").withUsername("root").withPassword("mysql");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mysqlContainer.start();
	}

	@Test
	void contextLoads() {
		String requestBodyForFailure = """
				{
				  "orderLineItemsDtoList": [
				    {
				     "skuCode": "iphone_13",
					 "price": 1200,
					 "quantity": 1
				    },
				    {
				     "skuCode": "iphone_13_red",
					 "price": 1200,
					 "quantity": 1
				    }
				  ]
				}
								""";

		String requestBodyForSuccess = """
				{
				  "orderLineItemsDtoList": [
				    {
				     "skuCode": "iphone_13",
					 "price": 1200,
					 "quantity": 1
				    }
				  ]
				}
								""";

		InventoryClientStub.stubInventoryCall();

		var responseString = RestAssured.given().contentType("application/json").body(requestBodyForSuccess).when()
				.post("/api/order").then().log().all().statusCode(201).extract().body().asString();
		assertThat(responseString, Matchers.is("Order placed successfully.."));
//		assertThat(responseString, Matchers.is("Product is out of stock!!! Please try after some time"));
	}

}
