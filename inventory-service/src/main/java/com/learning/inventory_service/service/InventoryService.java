package com.learning.inventory_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.inventory_service.dto.InventoryRequest;
import com.learning.inventory_service.dto.InventoryResponse;
import com.learning.inventory_service.model.Inventory;
import com.learning.inventory_service.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

	private final InventoryRepository inventoryRepository;

	public String createInventory(List<InventoryRequest> requests) {
		List<Inventory> inventoryList = requests.stream().map(this::mapDtoToEntity).collect(Collectors.toList());
		inventoryRepository.saveAll(inventoryList);
		return "Invetory loaded successfully";

	}

	@Transactional(readOnly = true)
	public List<InventoryResponse> isInStock(List<String> skuCode) {
		return inventoryRepository.findBySkuCodeIn(skuCode).stream().map(inventory -> InventoryResponse.builder()
				.skuCode(inventory.getSkuCode()).isInStock(inventory.getQuantity() > 0).build()).toList();

	}

	private Inventory mapDtoToEntity(InventoryRequest request) {
		Inventory inventory = new Inventory();
		inventory.setQuantity(request.getQuantity());
		inventory.setSkuCode(request.getSkuCode());
		return inventory;
	}

}
