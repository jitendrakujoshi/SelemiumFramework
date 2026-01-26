@tag
Feature: Error validation
  I went to use this template for my feature file
 Background:
  Given I landed on Ecommerce Page
  @tag2
  Scenario Outline: Error validation with wrong credentials
Given Logged in with username "<username>" and password "<password>"
   # Then "Incorrect email or password." message is display

    Examples:
      | username       | password    |
      | jitu@gmail.com | Stu@12345  |
