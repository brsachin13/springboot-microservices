package com.learning.order_service.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.learning.order_service.client.InventoryClient;
import com.learning.order_service.dto.InventoryResponse;
import com.learning.order_service.dto.OrderLineItemsDto;
import com.learning.order_service.dto.OrderRequest;
import com.learning.order_service.model.OrderLineItems;
import com.learning.order_service.model.Orders;
import com.learning.order_service.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class OrderService {

	private final OrderRepository orderRepository;

	// Grpc Communication
//	private final OrderGrpcClient orderGrpcClient;

	// Web Client communication
	private final WebClient.Builder webClient;

	private final InventoryClient inventoryFeignClient;

	public String placeOrder(OrderRequest orderRequest) {
		Orders order = new Orders();
		order.setOrderNumber(UUID.randomUUID().toString());

		order.setOrderLineItemsList(orderRequest.getOrderLineItemsDtoList().stream().map(this::mapToDto).toList());

		List<String> skuCodeList = orderRequest.getOrderLineItemsDtoList().stream().map(OrderLineItemsDto::getSkuCode)
				.toList();

		// WebClient Call
//		InventoryResponse[] webClientResponses = webClient.build().get()
//				.uri("http://inventory-service/api/inventory",
//						uriBuilder -> uriBuilder.queryParam("skuCode", skuCodeList).build())
//				.retrieve().bodyToMono(InventoryResponse[].class).block();

//		log.info("Web Client Response : {} ", Arrays.toString(webClientResponses));

		// Feign Client

		List<InventoryResponse> feignResponse = inventoryFeignClient.isInstock(skuCodeList);

		log.info("Feign Response ========>>>> {} ", feignResponse);

		// GRPC call
//		InventoryResponseGrpc grpcResponse = orderGrpcClient.checkInventory(skuCodeList);

//		log.info("Grpc Response : {} ", grpcResponse.toString());
		/*
		 * order.setOrderLineItemsList(
		 * grpcResponse.getResponseList().stream().filter(Response::getInStock).map(
		 * Response::getSkuCode) .flatMap(skuCode ->
		 * orderRequest.getOrderLineItemsDtoList().stream() .filter(sku ->
		 * sku.getSkuCode().equals(skuCode)).map(this::mapToDto))
		 * .collect(Collectors.toList()));
		 */

//		boolean allOrdersInStock = Arrays.stream(webClientResponses).allMatch(InventoryResponse::isInStock);
		boolean allOrdersInStock = feignResponse.stream().allMatch(InventoryResponse::isInStock);

//		log.info("Grpc boolean check : {} ", grpcResponse.getResponseList().stream().allMatch(Response::getInStock));

		if (allOrdersInStock) {
			orderRepository.save(order);
			return "Order placed successfully..";
		} else {
			return "Product is out of stock!!! Please try after some time";

		}
	}

	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setId(orderLineItemsDto.getId());
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

		return orderLineItems;
	}
}
