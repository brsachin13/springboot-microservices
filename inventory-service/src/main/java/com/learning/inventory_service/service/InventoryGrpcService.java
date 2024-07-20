package com.learning.inventory_service.service;

//@GrpcService
//@RequiredArgsConstructor
//public class InventoryGrpcService extends InventoryServiceImplBase {
//
//	private final InventoryService inventoryService;
//
//	@Override
//	public void checkInventory(InventoryRequestGrpc request, StreamObserver<InventoryResponseGrpc> responseObserver) {
//		List<String> skuCode = request.getSkuCodeList();
//
//		List<InventoryResponse> inStock = inventoryService.isInStock(skuCode);
//
//		// Simulate checking inventory
//		List<Response> response = inStock.stream()
//				.map(res -> Response.newBuilder().setSkuCode(res.getSkuCode()).setInStock(res.isInStock()).build())
//				.toList();
//
//		responseObserver.onNext(InventoryResponseGrpc.newBuilder().addAllResponse(response).build());
//		responseObserver.onCompleted();
//	}
//}
