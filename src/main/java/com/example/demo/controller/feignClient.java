package com.example.demo.controller;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Customer;

@FeignClient(name = "BACKEND")

//@FeignClient(value="feignclient",url="http://localhost:8080")

public interface feignClient {

		@GetMapping("/customers")
		public List<Customer> getAllCustomers();
}
