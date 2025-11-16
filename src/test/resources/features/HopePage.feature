Feature: Account Overview Validation for ParaBank application

  @wip
  Scenario:Validate the Account Services on Account Overview page
    Given The user logins the application with a username "raj.kashyap" and a password "rajkashyap232"
    When The user fetches the list of headers under Account Services
    Then The headers list is should be
      | Open New Account    |
      | Accounts Overview   |
      | Transfer Funds      |
      | Bill Pay            |
      | Find Transactions   |
      | Update Contact Info |
      | Request Loan        |
      | Log Out             |
