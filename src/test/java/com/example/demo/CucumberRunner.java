package com.example.demo;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/resources/frontendTest",
		glue= {"com.example.demo"},
		publish=true

		)

public class CucumberRunner {

}
