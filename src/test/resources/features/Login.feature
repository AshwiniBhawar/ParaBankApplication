Feature: Login to the ParaBank application

  @login @smoke @regression @functional
  Scenario: Login with valid data for ParaBank application
    Given The user launches the application and fetches the login page title
    Then The login page title should be "ParaBank | Welcome | Online Banking" title
    When The user logins the application with a username "raj.thakur19" and a password "rajthakur1234"
    And The user fetches the home page title
    Then The home page title should be "ParaBank | Accounts Overview"
    Then The user successfully logged out and navigate to login page having title "ParaBank | Welcome | Online Banking"

 #username, password details needs to be updated
