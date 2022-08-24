package com.example.demo.Runner;



import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/resources/frontendTest.feature",
		glue= {"com.example.demo.StepDefinitions"},
		publish=true
		)

public class CucumberRunner {

}
