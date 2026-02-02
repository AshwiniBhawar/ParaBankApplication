Feature: Open New Account Validation for ParaBank application

  Background:
  Given The user logins the application with a username "raj.thakur77" and a password "rajthakur1234"

  @functional
  Scenario:Verify that user is able to open a new account
    When The user clicks on the Open New Account link
    Then The open account page title should be "Open New Account"
    When The user selects  account type as "CHECKING" and account number as "13455"
    And The user clicks on open new account button
    Then The account is opened
    Then The user successfully logged out and navigate to login page having title "ParaBank | Welcome | Online Banking"

  @functional
  Scenario:Verify that user is able to perform end to end flow for open a new account
    When The user clicks on the Open New Account link
    Then The open account page title should be "Open New Account"
    When The user selects  account type as "CHECKING" and account number as "13455"
    And The user clicks on open new account button
    Then The account is opened
    When The user clicks on the new account number and navigate to "Account Details" page
    And The account details tab contains account number and account type as "CHECKING" and balance as "$100.00" and available as "$100.00"
    Then Select Activity Period as "All" and Type as "All" and click on go
    Then The date shows as "01-29-2026" and transaction as "Funds Transfer Received" and debit as "" and credit as "$100.00"
    And The user clicks on Funds Transfer Received  and navigate to "Transaction Details" page
    Then The transaction details page contains transaction id and date as "01-30-2026" and description as "Funds Transfer Received" and type as "Credit" amount as "$100.00"
    Then The user successfully logged out and navigate to login page having title "ParaBank | Welcome | Online Banking"


  @functional
  Scenario:Verify that a new opened account is listed under account overview
    When The user clicks on the Open New Account link
    Then The open account page title should be "Open New Account"
    When The user selects  account type as "CHECKING" and account number as "13455"
    And The user clicks on open new account button
    Then The account is opened
    When The user clicks on Account Overview page
    And The user navigates to "Accounts Overview" page
    Then The new opened account is listed under account overview having account number and balance as "$100.00" and available amount as "$100.00"
    Then The user successfully logged out and navigate to login page having title "ParaBank | Welcome | Online Banking"

#username, password, account number, amount, and transaction date details needs to be updated- These details are user specific