Feature: Open New Account Validation for ParaBank application

  Background:
  Given The user logins the application with a username "raj.thakur04" and a password "rajthakur1234"

  @wip
  Scenario:Verify that user is able to open a new account
    When The user clicks on the Open New Account link
    Then The open account page title should be "Open New Account"
    When The user selects  account type as "CHECKING" and account number as "15564"
    And The user clicks on open new account button
    Then The account is opened