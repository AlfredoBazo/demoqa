Feature: this is an example

  Scenario Outline: Checking URL. First row passes, second one fails.
    When user opens the requested webpage
    Then url should be "<urlExpected>"

    Examples:
      | urlExpected                                 |
      | https://demoqa.com/automation-practice-form |


  Scenario: Register users randomly
    When user opens the requested webpage
    Then url should be "https://demoqa.com/automation-practice-form"
    When register a new user randomly
