package com.learning.inventory_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.learning.inventory_service.dto.InventoryRequest;
import com.learning.inventory_service.dto.InventoryResponse;
import com.learning.inventory_service.service.InventoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Log4j2
public class InventoryController {

	private final InventoryService inventoryService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String createInventory(@RequestBody List<InventoryRequest> request) {
		return inventoryService.createInventory(request);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) throws InterruptedException {
//		log.info("Sleeping ....");
//		Thread.sleep(Duration.ofSeconds(10));
//		log.info("Waking up....");
		return inventoryService.isInStock(skuCode);
	}

}
