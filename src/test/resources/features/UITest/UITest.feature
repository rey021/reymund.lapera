Feature: Webpage Login Test

  To test the login feature of the webpage

  Scenario Outline: Test Case#1 check contact page that mandatory fields should required
    Given User is browsing the contact page of http://jupiter.cloud.planittesting.com
    When Submitting the form leaving the fields as null
    Then he should get the error message as <ERROR MESSAGE>
    Examples:
      | ERROR MESSAGE |
      | true          |

