Feature: Open New Account Validation for ParaBank application

  Background:
    Given The user logins the application with a username "raj.thakur71" and a password "rajthakur1234"

  @functional
  Scenario:Verify that user is able to transfer funds
    When The user clicks on the Transfer Funds link
    Then The transfer account page title should be "Transfer Funds"
    When The user enters  amount as "100.00"
    And The user enters from account as "15009" and to as "15120"
    And The user clicks on transfer button
    Then The transfer is successful
    Then The user successfully logged out and navigate to login page having title "ParaBank | Welcome | Online Banking"
