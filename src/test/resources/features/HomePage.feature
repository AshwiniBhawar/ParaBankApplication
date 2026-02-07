Feature: Account Overview Validation for ParaBank application

  Background:
    Given The user logins the application with a username "raj.thakur71" and a password "rajthakur1234"

  @smoke @functional
  Scenario:Validate the Account Services on Account Overview page
    When The user fetches the list of headers under Account Services
    Then The headers list should be
      | Open New Account    |
      | Accounts Overview   |
      | Transfer Funds      |
      | Bill Pay            |
      | Find Transactions   |
      | Update Contact Info |
      | Request Loan        |
      | Log Out             |
    Then The user successfully logged out and navigate to login page having title "ParaBank | Welcome | Online Banking"

#username, password details needs to be updated