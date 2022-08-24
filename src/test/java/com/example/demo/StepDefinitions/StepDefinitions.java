package com.example.demo.StepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Customer;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import  io.restassured.response.Response;
import static io.restassured.RestAssured.*;

@CucumberContextConfiguration
@SpringBootTest

public class StepDefinitions {
	private Response response;
	
	@When("data retrieval method is called")
	public void data_retrieval_method_is_called() {
	  response = when().get("http://localhost:8081/feigncustomers");
	    
	}

	@Then("the client receives status code and list of customers")
	public void the_client_receives_status_code_and_list_of_customers() {
		 int status = response.then().extract().statusCode();
		 assertEquals(200,status);
		 Customer[] customers = response.as( Customer[].class);
		 assertTrue(customers.length>0);
		
	}
}
