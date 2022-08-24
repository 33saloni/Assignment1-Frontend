
Feature: get values from frontend
  
   Scenario Outline:  Data retrieval from a web service
    Given  the client call GET /customers
    When the client call /customers
    Then the client receives status code of 200
    And the client receives list of customers 

