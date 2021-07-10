Feature: Webpage Login Test

  To test the login feature of the webpage

  Background:
    Given the URL to test is:
      | URL                                    |
      | http://jupiter.cloud.planittesting.com |

  Scenario Outline: Test Case#1 check contact page that mandatory fields should required
    Given <User> is browsing the contact page of http://jupiter.cloud.planittesting.com
    When Submitting the form leaving the fields <INPUT>
    Then he should get the <STATUS> message
    Examples:
      | User  | INPUT | STATUS |
      | guest | NULL  | ERROR  |
