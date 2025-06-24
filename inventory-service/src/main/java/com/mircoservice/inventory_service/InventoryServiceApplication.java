package com.mircoservice.inventory_service;

import com.mircoservice.inventory_service.model.Inventory;
import com.mircoservice.inventory_service.repository.InventoryRepo;
import com.mircoservice.inventory_service.service.InventoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
@EnableDiscoveryClient
@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
 	@Bean
	public CommandLineRunner commandLineRunner(InventoryRepo inventoryRepo) {
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("iphone_13");
			inventory.setQuantity(100);

			Inventory inventory2 = new Inventory();
			inventory2.setSkuCode("iphone_14");
			inventory2.setQuantity(10);
			inventoryRepo.save(inventory);
			inventoryRepo.save(inventory2);

		};
	}
}
