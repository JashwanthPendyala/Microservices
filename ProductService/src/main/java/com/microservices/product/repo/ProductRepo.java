package com.microservices.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.product.model.Product;

public interface ProductRepo extends JpaRepository<Product, String>{

}
