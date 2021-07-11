Feature: Webpage Login Test

  To test the login feature of the webpage

  Background:
    Given Given User is on the http://jupiter.cloud.planittesting.com

  @testcase1.0
  Scenario Outline: Test Case#1 Not able to submit contact form when populating required fields
    Given User is navigating the contact page
    When leaving fields as blank
    And clicking the submit button
    Then he should get the error message: "<ERROR MESSAGE>"
    Examples:
      | ERROR MESSAGE                                                                          |
      | We welcome your feedback - but we won't get it unless you complete the form correctly. |

  @testcase1.1
  Scenario Outline: Test Case#1.1 Able to submit contact form when populating required fields
    Given User is navigating the contact page
    When filling up the contact form on the following fields:
        | FORENAME | SURNAME | EMAIL          | TELEPHONE | MESSAGE        |
        | test     | test    | test@gmail.com | 63999231  | this is a test |
    And clicking the submit button
    Then he should get the success <RESPONSE MESSAGE>
    Examples:
      | RESPONSE MESSAGE                          |
      | Thanks test, we appreciate your feedback. |

  @testcase3
  Scenario Outline: Test Case#3 Able to add item to cart
    Given User is navigating to shop page
    When adding the following item to cart <ITEM> < QUANTITY>
      | ITEM         | QUANTITY |
      | Funny Cow    | 2        |
      | Fluffy Bunny | 1        |
    And clicking the cart menu
    Then he should able to validate the correct item into the cart page
    Examples:
      | ITEM         | QUANTITY |
      | Funny Cow    | 2        |
      | Fluffy Bunny | 1        |