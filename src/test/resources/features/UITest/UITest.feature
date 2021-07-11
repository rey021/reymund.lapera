Feature: Webpage Login Test

  To test the login feature of the webpage

  Background:
    Given Given User is on the http://jupiter.cloud.planittesting.com

  @testcase1
  Scenario Outline: Test Case#1 check contact page that mandatory fields should required
    Given User is navigating the contact page
    When leaving fields as blank
    And clicking the submit button
    Then he should get the error message: "<ERROR MESSAGE>"
    Examples:
      | ERROR MESSAGE                                                                          |
      | We welcome your feedback - but we won't get it unless you complete the form correctly. |

  @testcase2
  Scenario Outline: Test Case#2 check contact page that mandatory fields should required
    Given User is navigating the contact page
    When filling up the contact form on the following fields:
        | FORENAME | SURNAME | EMAIL          | TELEPHONE | MESSAGE        |
        | test     | test    | test@gmail.com | 63999231  | this is a test |
    And clicking the submit button
    Then he should get the success <RESPONSE MESSAGE>
    Examples:
      | RESPONSE MESSAGE                          |
      | Thanks test, we appreciate your feedback. |