##"# UITest_PlanitTestingExam" 

### 1. What other possible scenario’s would you suggest for testing the Jupiter Toys application?
- a scenario to validate functionality on removing added item in the cart.
- a scenario were to check the "Empty Cart" button from the cart page
- a scenario to verify if the total item added into the cart are correct as expected.
- a scenario to verify the checkout functionality from the cart page.
- a scenario to check "log in users credential" functionality.

### 2. Jupiter Toys is expected to grow and expand its offering into books, tech, and modern art. We are expecting the of tests will grow to a very large number.

#### What approaches could you used to reduce overall execution time?
- my recommendation is to use a "data driven automation framework" so that the scalability can be managed and reduced headache when maintaining the scripts, you can use a tools such as Selenium or Tosca commanders.
#### How will your framework cater for this?
- this can be cater by creating a package for Automation framework only by using Maven, and the actual implementation of the test cases can be created separately.

## 3. Describe when to use a BDD approach to automation and when NOT to use BDD.
- BDD approach is better for a small team or small solution, as this framework can easily manageable.
- BDD should not be use when it is a large-scale solution that has more than hundreds of test cases to be cover, and required multiple collaborators, TDD is a good approach for this case especially when test coverage required.

================================================

###Notes: runner --> UITest_Runner
####Testcases Tag: testcase1&2, testcase3, testcase4
