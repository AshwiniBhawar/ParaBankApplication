Feature: Invalid login to the ParaBank application

  @negative
  Scenario Outline: Invalid login with valid data for ParaBank application
    Given The user launches the application and fetches the login page title
    Then The login page title should be "ParaBank | Welcome | Online Banking" title
    When The user enters a username "<username>" and a password "<password>" in the textbox
    Then The invalid login error message should be "<message>"

  Examples:
    | username    | password | message                                             |  |  |
    | raj.thakur  | test1234 | An internal error has occurred and has been logged. |  |  |
    |             | test1234 | Please enter a username and password.               |  |  |
    | krish.warun | test1234 | An internal error has occurred and has been logged. |  |  |
    | raj.thakur  |          | Please enter a username and password.               |  |  |