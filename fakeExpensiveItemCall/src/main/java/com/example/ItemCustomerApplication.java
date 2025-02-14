package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
@RestController
public class ItemCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemCustomerApplication.class, args);
	}

	@GetMapping("/api/getItem")
	public Map<String, String> getItem(@RequestParam String id) throws InterruptedException {
		Thread.sleep(4000);
		return Map.of("itemId", id, "name", "dummyitem");
	}

}
