package com.learning.product_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learning.product_service.dto.ProductRequest;
import com.learning.product_service.dto.ProductResponse;
import com.learning.product_service.model.Product;
import com.learning.product_service.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductService {

	private final ProductRepository productRepository;

	public ProductResponse createProduct(ProductRequest productRequest) {
		Product product = Product.builder().name(productRequest.getName()).description(productRequest.getDescription())
				.price(productRequest.getPrice()).build();

		productRepository.save(product);
		log.info("Product {} is saved", product.getId());
		return mapToProductResponse(product);
	}

	public List<ProductResponse> getAllProducts() {
		List<Product> products = productRepository.findAll();

		return products.stream().map(this::mapToProductResponse).toList();
	}

	private ProductResponse mapToProductResponse(Product product) {
		return ProductResponse.builder().id(product.getId()).name(product.getName())
				.description(product.getDescription()).price(product.getPrice()).build();
	}
}
