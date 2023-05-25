package com.microservices.orderservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.orderservice.model.Order;

public interface OrderRepo extends JpaRepository<Order, Long>{

}
