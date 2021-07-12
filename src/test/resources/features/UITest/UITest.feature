Feature: Webpage Login Test

  To test the login feature of the webpage and verify adding cart functionality

  Background:
    Given Given User is on the http://jupiter.cloud.planittesting.com

  @testcase1&2
  Scenario Outline: Test Case#1 validate contact form when populating or not populating required fields
    Given User is navigating the contact page
    When filling up the contact form on the following fields <FORENAME> , <SURNAME> , <EMAIL> , <TELEPHONE> , <MESSAGE>
    And clicking the submit button
    Then he should get the success <RESPONSE MESSAGE>
    Examples:
      | FORENAME     | SURNAME     | EMAIL          | TELEPHONE | MESSAGE        | RESPONSE MESSAGE                                                                       |
      |              |             |                |           |                | We welcome your feedback - but we won't get it unless you complete the form correctly. |
      | forenametest | surnametest | test@gmail.com | 3241234   | This is a test | Thanks forenametest, we appreciate your feedback.                                      |

  @testcase3
  Scenario: Test Case#3 Able to add item to cart
    Given User is navigating to shop page
    When adding item and quantity on the ff.
      | Funny Cow    | 2 |
      | Fluffy Bunny | 1 |
    Then verify item has been added to cart

  @testcase4
  Scenario: Test Case#4 buy multiple item and validate the sub-totals and sum
    Given User is navigating to shop page
    And adding the following item, and quantity
      | Item           | Price | Quantity |
      | Stuffed Frog   | 10.99 | 2        |
      | Fluffy Bunny   | 9.99  | 6        |
      | Valentine Bear | 14.99 | 3        |
    Then verify item has been added to cart with correct sub_total of each item and total cost
