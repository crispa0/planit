# crisaldo.paningasan
This serves as my answers for the Test Automation Exam for Planit

Answer to Question 1:
  Add more validation tests for each field (i.e. lengths, accepted characters..) both for optional and mandatory
  Validate that the data being displayed in the UI is correct based on the API or Database validation
  Try to incorporate load testing to make sure system can handle multiple items in one transaction.
  Validate computations with bigger data sets, (i.e. large amount of items)
  Include validation that user should not be able to checkout unless they are login to make sure security on their transaction.
  

Answer to Question 2:
  * Rather than each validations are in one test case, we could include more validations in one run that could be included on the flow. This would lessen the login, logout time or navigation or loading time as well for each validation/scenarios.
  
  *Since most of the methods are already included on the page objects models for each pages, we could easily reuse each methods in a single test case to navigate and manipulate elements and different actions for them in one single flow.


Answer to Question 3:
  It is ok to use BDD framework if the existing testing team is more on manual and not that technical. In that way the automation team can create the BDD framework and easily transition the manual/non tehcnical testers to use automation. It would be somewhat of an easy transition since the feature file would just look like a test case that they are used to and most of the basic coding should had been completed and covered by the initial implementation. It is also ok if the users wants to review the test cases that are being created by the testers since it is just reading an actual test case from the feature file.
