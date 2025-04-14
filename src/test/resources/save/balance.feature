Feature: View Balance
  @this
  Scenario: Verify my account balance
    Given Im logged
    When I click on my account
    Then I see that my balance is right
    And I see that i have at least one transaction
