Feature: Update Contact Info Validation for ParaBank application

  Background:
    Given The user logins the application with a username "raj.kashyap22" and a password "rajkashyap1234"

  @functional
  Scenario Outline::Verify that user is able to update the contact info
    When The user clicks on the Update Contact Info link
    Then The update contact info title should be "Update Profile"
    When The user adds the contact details "<firstname>" "<lastname>" "<address>" "<city>" "<state>" "<zipcode>" and "<phone>"
    And The user clicks on update profile button
    Then The profile updated successfully message should be displayed
    Then The user successfully logged out and navigate to login page having title "ParaBank | Welcome | Online Banking"

  Examples:
    | firstname | lastname | address         | city    | state | zipcode | phone      |
    | raj       | thakur   | 123 Main Street | NewYork | NY    | 10001   | 1234567890 |

#username, password needs to be updated