Feature: Registration for ParaBank application

  @register @regression
  Scenario: Validate a page title on register page for ParaBank application
   Given The user is on login page and clicks on register link
   When The user fetches the page title
   Then The page title should be "ParaBank | Register for Free Online Account Access"

  @register @regression @smoke
  Scenario Outline: Complete a registration form for ParaBank application
    Given The user is on login page and clicks on register link
    When The user fetches the registration form title
    Then form page title should be "Signing up is easy!"
    When The user enters "<firstname>" "<lastname>" "<address>" "<city>" "<state>" "<zipcode>" "<phone>" "<ssn>" "<username>" "<password>" "<confirmpassword>"
    And The user clicks on register link
    When The user fetches the registration successful message
    Then Registration should be successful with "Welcome" message and "Your account was created successfully. You are now logged in." message

  Examples:
    | firstname | lastname | address       | city   | state | zipcode | phone      | ssn       | username    | password       | confirmpassword |
    | Raj       | Thakur   | 1234 N Circle | Austin | Texas | 12345   | 9874563210 | ABC123456 | raj.thakur  | rajthakur1234  | rajthakur1234   |
    | Raj       | Kashyap  | 1234 N Circle | Austin | Texas | 12345   | 9874563210 | ABC123456 | raj.kashyap | rajkashyap1234 | rajkashyap1234  |

 @negative
  Scenario Outline: Complete a registration form using existing username for ParaBank application
    Given The user is on login page and clicks on register link
    When The user fetches the registration form title
    Then form page title should be "Signing up is easy!"
    When The user enters "<firstname>" "<lastname>" "<address>" "<city>" "<state>" "<zipcode>" "<phone>" "<ssn>" "<username>" "<password>" "<confirmpassword>"
    And The user clicks on register link
    And The user fetches username already exist error message
    Then "This username already exists." message should be displayed

    Examples:
      | firstname | lastname | address       | city   | state | zipcode | phone      | ssn       | username   | password      | confirmpassword |
      | Raj       | Kapoor   | 1234 N Circle | Austin | Texas | 12345   | 9874563210 | ABC123456 | raj.kapoor | rajkapoor1234 | rajkapoor1234   |