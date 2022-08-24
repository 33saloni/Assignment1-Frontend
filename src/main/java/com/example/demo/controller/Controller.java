package com.example.demo.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.example.demo.model.Customer;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
//@RequestMapping("/frontend")
public class Controller {
	
	Logger logger = LoggerFactory.getLogger(Controller.class);
	
		@Autowired
		private RestTemplate restTemplate;
		
		@Autowired
		private feignClient feignclient;
	
	@CircuitBreaker(name="AllCustomersTemplate",fallbackMethod="fallback_get_AllCustomers")
	@GetMapping("/customers")
	public ResponseEntity<Customer[]> get_AllCustomers() throws URISyntaxException{
		String url="http://localhost:8084/customers";
		URI uri = new URI(url);
		ResponseEntity<Customer[]> result = restTemplate.getForEntity(uri, Customer[].class);
		logger.info("Received Data from backend");
		return result;
	}
	
	@CircuitBreaker(name="AllCustomersClient",fallbackMethod="fallback_get_AllCustomers")
	@GetMapping("/feigncustomers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> customers = feignclient.getAllCustomers();
		logger.info("Received Data from backend");
//		if(customers!=null && !customers.isEmpty())
			return new ResponseEntity<>(customers,HttpStatus.OK);
//		else
//			return new ResponseEntity<>(customers,HttpStatus.NO_CONTENT);
	}
	
	public ResponseEntity<String> fallback_get_AllCustomers(Throwable ex) {
		logger.info("-----------RESPONSE FROM FALLBACK METHOD---------------");
		logger.info("/SERVICE IS DOWN");
		return new ResponseEntity<>("Service Not Available",HttpStatus.NOT_FOUND);
	}
	
}
