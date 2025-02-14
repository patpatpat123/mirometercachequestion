package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
@RestController
public class ExpensiveCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpensiveCustomerApplication.class, args);
	}

	@GetMapping("/api/getCustomer")
	public Map<String, String> getCustomer(@RequestParam String id) throws InterruptedException {
		Thread.sleep(5000);
		return Map.of("customerID", id, "customerName", "dummycustomer");
	}

}
