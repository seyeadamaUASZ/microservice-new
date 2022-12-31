package com.sid.gl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner loadDate(InventoryRepository inventoryRepository){
//		return args -> {
//			Inventory inventory =
//					new Inventory();
//			inventory.setSkucode("iphone_13");
//			inventory.setQuantity(100);
//
//			Inventory inventory1=
//					new Inventory();
//			inventory1.setQuantity(0);
//			inventory1.setSkucode("iphone_13_red");
//
//			inventoryRepository.save(inventory);
//			inventoryRepository.save(inventory1);
//		};
//		}


}
