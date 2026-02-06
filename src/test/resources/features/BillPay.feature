Feature: Bill Pay validation for ParaBank application

  Background:
    Given The user logins the application with a username "raj.thakur99" and a password "rajthakur1234"

  @smoke @functional
  Scenario Outline::Verify that user is able to perform bill payment
    When The user clicks on the Bill Pay link
    Then The bill pay page title should be "Bill Payment Service"
    When The user adds the payee and accounts details "<payeeName>" "<address>" "<city>" "<state>" "<zipcode>" "<phone>" "<account>" "<verifyAcc>" "<amount>" and "<fromAcc>"
    And The user clicks on send payment button
    Then The bill payment successful message should be displayed
    Then The user successfully logged out and navigate to login page having title "ParaBank | Welcome | Online Banking"

    Examples:
      | payeeName | address         | city    | state | zipcode | phone      | account | verifyAcc | amount | fromAcc |
      | krish     | 123 Main Street | NewYork | NY    | 10001   | 1234567890 | 17007   | 17007     | 10.00  | 17118   |

    #username, password, payer and payee account details needs to be updated- These details are user specific

