package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class Assignment1FrontendApplication {

	public static void main(String[] args) {
		SpringApplication.run(Assignment1FrontendApplication.class, args);
	}
	//@LoadBalanced
	@Bean
     RestTemplate getRestTemplate() {
		RestTemplate restTemplate=new RestTemplate();
		return restTemplate;
	}

}
