package com.microservices.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservices.orderservice.dto.OrderLineItemsDto;
import com.microservices.orderservice.dto.OrderRequest;
import com.microservices.orderservice.model.Order;
import com.microservices.orderservice.model.OrderLineItems;
import com.microservices.orderservice.repo.OrderRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

	private final OrderRepo repo;
	public void placeOrder(OrderRequest request) {
		Order order = new Order();
		order.setOrderno(UUID.randomUUID().toString());
		List<OrderLineItems> list = request.getOrderLineItemsDtoList().stream()
				.map(OrderLineItemsDto -> mapToDto(OrderLineItemsDto)).toList();
		
		order.setOrderLineItemsList(list);
		repo.save(order);
	}

	private OrderLineItems mapToDto(OrderLineItemsDto dto) {
		OrderLineItems items = new OrderLineItems();
		items.setPrice(dto.getPrice());
		items.setQuantity(dto.getQuantity());
		items.setSkewCode(dto.getSkewCode());
		return items;
	}
}
