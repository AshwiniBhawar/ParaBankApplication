Feature: Invalid login to the ParaBank application

  @negative
  Scenario Outline: Invalid login with valid data for ParaBank application
    Given The user launches the application and fetches the login page title
    Then The login page title should be "ParaBank | Welcome | Online Banking" title
    Given The user logins the application with a username "<username>" and a password "<password>"
    Then The invalid login error message should be "<message>"

  Examples:
    | username     | password | message                                          |  |  |
    | raj.thakur04 | test1234 | The username and password could not be verified. |  |  |
    |              | test1234 | Please enter a username and password.            |  |  |
    | raj.thakur   |          | Please enter a username and password.            |  |  |