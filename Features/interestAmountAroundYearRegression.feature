Feature: Interest Amount Around Year Regression

  @regression
  Scenario: verify navigation bar wroking in emi calculator home page
    Given user navigate carLoan calculator page
    Then verify dashbord obeject present

  @regression
  Scenario: verify the links are working in emi calculator home page
    Given user navigates to car loan homepage
    Then verify link working

  @regression
  Scenario: verify share and print button available in car loan calculator
    Given user navigates to homeLoan calculator
    When user scroll to print and share button
    Then verify Share button avalable
    And verify print button available

  @regression
  Scenario: verify all chart present in car loan calculator
    Given user navigate to carloan page
    When user enters valid data
    Then verify pi chart available
    Then verify graph chart available

  @regression
  Scenario Outline: verify all scale in Home Loan Calculator 
    Given user navigate to home loan calculator
    Then verify all scale are working
    
  @regression
  Scenario: verify emi scheme in car loan calculator
  	Given user navigate to carloan calculator 
  	Then verify emi scheme 
  	And verify emi advance and arrears button
