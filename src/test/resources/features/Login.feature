Feature: Login to the ParaBank application

  @login @smoke @regression
  Scenario: Login with valid data for ParaBank application
    Given The user launches the application and fetches the login page title
    Then The login page title should be "ParaBank | Welcome | Online Banking" title
    When The user logins the application with a username "raj.thakur04" and a password "rajthakur1234"
    And The user fetches the home page title
    Then The home page title should be "ParaBank | Accounts Overview"

