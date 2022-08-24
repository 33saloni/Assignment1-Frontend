package com.example.demo;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URISyntaxException;
//import java.util.ArrayList;
import java.util.Arrays;
//import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo.controller.Controller;
import com.example.demo.controller.feignClient;
import com.example.demo.model.Customer;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class Assignment1FrontendApplicationTests {
	
	@InjectMocks
	Controller controller;
	
	@Mock
	private feignClient feignclient;
	
	@Mock
	private RestTemplate restTemplate;

	Customer[] customers = new Customer[2];
	
	@BeforeEach
	public  void setUp(){
	 Customer Customer1 = new Customer(1, "Harry");
	 Customer Customer2 = new Customer(2, "Ron");
	
	 customers[0]=Customer1;
	 customers[1]=Customer2;
	}
	
	@Test
	void getCustomersUsingRestTemplate() throws URISyntaxException {
		
		 URI uri = new URI("http://localhost:8080/customers");
		ResponseEntity<Customer[]> list = new ResponseEntity<Customer[]>(customers,HttpStatus.OK);
		when(restTemplate.getForEntity(uri, Customer[].class)).thenReturn(list);
		assertEquals(2,controller.get_AllCustomers().getBody().length);		
	}
	
	@Test
	void getCustomersUsingFeignClient() throws URISyntaxException {
		
		when(feignclient.getAllCustomers()).thenReturn(Arrays.asList(customers));
		assertEquals(2,controller.getAllCustomers().getBody().size());
	
		
	}
	@Test
	void fallBackMethod() {
		assertEquals("Service Not Available",controller.fallback_get_AllCustomers(null).getBody());
	}

}
