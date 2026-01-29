Feature: Open New Account Validation for ParaBank application

  Background:
    Given The user logins the application with a username "raj.thakur25" and a password "rajthakur1234"

  @functional
  Scenario:Verify that user is able to transfer funds
    When The user clicks on the Transfer Funds link
    Then The transfer account page title should be "Transfer Funds"
    When The user enters  amount as "100.00"
    And The user enters from account as "15009" and to as "15120"
    And The user clicks on transfer button
    Then The transfer is successful
    Then The user successfully logged out and navigate to login page having title "ParaBank | Welcome | Online Banking"

  @wip
  Scenario:Verify that user is able to transfer funds and validate account balance(debit and credit)
    Given The user go to the account overview page and captures the payee "14010" and payer "14232" balance details
    When The user clicks on the Transfer Funds link
    Then The transfer account page title should be "Transfer Funds"
    When The user enters  amount as "10.00"
    And The user enters from account as "14010" and to as "14232"
    And The user clicks on transfer button
    Then The transfer is successful
    When The user clicks on Account Overview page
    And The user navigates to "Accounts Overview" page
    Then Validates the payers account is debited and payee account is credited as per the transaction
    Then The user successfully logged out and navigate to login page having title "ParaBank | Welcome | Online Banking"

#username, password, account number(to and from), and amount details needs to be updated- These details are user specific