package com.inventory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.inventory.model.Inventory;
import com.inventory.repo.InventoryRepo;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner loadData(InventoryRepo repo) {
		return args ->{
			Inventory inventory = new Inventory();
			inventory.setSkuCode("Iphone_13");
			inventory.setQuantity(500);
			
			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("Iphone_13_red");
			inventory1.setQuantity(500);
			repo.save(inventory);
			repo.save(inventory1);
		};
	}
}
