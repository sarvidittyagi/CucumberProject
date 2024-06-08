Feature: User Authentication tests

  Background: 
    And User click on the login link

  @smoke @reg
  Scenario: Login should be success
    And User enter the username as "styagi11"
    And User enter the password as "Ty@gi1993"
    When User click on the login button
    Then Login should be success

  @smoke @reg
  Scenario: Login should not be success
    Given User enter the username as "sarvidit"
    Given User enter the password as "tyagi"
    When User click on the login button
    But Login should fail
