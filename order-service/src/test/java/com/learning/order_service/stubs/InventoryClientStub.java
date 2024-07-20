package com.learning.order_service.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.client.WireMock;

public class InventoryClientStub {

	public static void stubInventoryCall() {

		WireMock.stubFor(get(urlPathEqualTo("/api/inventory")).withQueryParam("skuCode", matching("[^,]+(,[^,]+)*"))
				.willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBody(
//						"[{\"skuCode\": \"iphone_13\", \"inStock\": true}, {\"skuCode\": \"iphone_13_red\", \"inStock\": false}]")));
						"[{\"skuCode\": \"iphone_13\", \"inStock\": true}]")));

	}

}
