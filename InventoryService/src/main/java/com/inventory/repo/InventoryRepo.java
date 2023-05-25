package com.inventory.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.model.Inventory;

public interface InventoryRepo  extends JpaRepository<Inventory, Long>{

	Optional<Inventory> findBySkuCode(String skuCode);
	
}
