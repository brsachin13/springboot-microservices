package com.learning.order_service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learning.order_service.dto.InventoryResponse;

@FeignClient(value = "inventory", url = "${inventory.url}")
public interface InventoryClient {

	@GetMapping("/api/inventory")
	List<InventoryResponse> isInstock(@RequestParam List<String> skuCode);

}
