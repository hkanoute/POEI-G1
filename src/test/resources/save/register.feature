Feature: register

  @POEI25-587
  Scenario: register
    Given Im on the login page
    When I click on Register
    Then Im redirected to the Signup page
    And I fill the first name input
    And I fill the last name input
    And I fill the Address input
    And I fill the City input
    And I fill the State input
    And I fill the Zip Code input
    And I fill the Phone input
    And I fill the SSN input
    And I fill the Username input
    And I fill the Password input
    And I fill the Confirm input
    And I clock on register
    And Im successfully registered

