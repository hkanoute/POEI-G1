Feature: Open an account

  @POEI25-583 @hselenium
  Scenario: Open an account
    Given Im logged
    When I click on open account
    Then Im redirected to the open account page
    And I finish creating my account
