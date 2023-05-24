package com.microservices.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservices.product.dto.ProductRequest;
import com.microservices.product.dto.ProductResponse;
import com.microservices.product.model.Product;
import com.microservices.product.repo.ProductRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
	
	private final ProductRepo repo;

	public void createProduct(ProductRequest request) {
		Product product = Product.builder()
							.name(request.getName())
							.description(request.getDescription())
							.price(request.getPrice()).build();
		
		repo.save(product);
		log.info("Product {} is Saved",product.getId());
	}

	public List<ProductResponse> getAllProducts() {
		List<Product> products = repo.findAll();
		return products.stream().map(product->mapToProductResponse(product)).toList();
		
	}

	private ProductResponse mapToProductResponse(Product product) {
		// TODO Auto-generated method stub
		
		return ProductResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.build();
	}
}
