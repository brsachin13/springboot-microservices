package com.learning.order_service.service;

//@Service
//@Log4j2
//public class OrderGrpcClient {
//
//	private ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("inventory-service", 6565).usePlaintext().build();
//
//	public InventoryResponseGrpc checkInventory(List<String> skuCode) {
//
//		InventoryServiceGrpc.InventoryServiceBlockingStub stub = InventoryServiceGrpc.newBlockingStub(managedChannel);
//
//		log.info("Channel created in order service!!!");
//
//		InventoryRequestGrpc checkInventoryRequest = InventoryRequestGrpc.newBuilder().addAllSkuCode(skuCode).build();
//
//		InventoryResponseGrpc response = stub.checkInventory(checkInventoryRequest);
//
//		log.info("Response received from the inventory service!!! {} ", response.toString());
//
//		return response;
//
//	}
//
//}
