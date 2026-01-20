Feature: Open New Account Validation for ParaBank application

  Background:
    Given The user logins the application with a username "raj.kapoor63" and a password "rajkapoor1234"

  @functional
  Scenario:Verify that user is able to transfer funds
    When The user clicks on the Transfer Funds link
    Then The transfer account page title should be "Transfer Funds"
    When The user enters  amount as "100.00"
    And The user enters from account as "14787" and to as "14787"
    And The user clicks on transfer button
    Then The transfer is successful

