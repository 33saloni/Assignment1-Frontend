
Feature: get values from frontend
  
   Scenario Outline:  Data retrieval from a web service
    When data retrieval method is called
    Then the client receives status code and list of customers
  
