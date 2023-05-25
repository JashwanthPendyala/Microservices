package com.inventory.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.repo.InventoryRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

	private final InventoryRepo repo;
	
	@Transactional(readOnly = true)
	public boolean isInStock(String skewCode) {
		
		return repo.findBySkuCode(skewCode).isPresent();
	}
}
