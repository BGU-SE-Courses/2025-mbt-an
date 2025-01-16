Feature: Navigate Test Pages

  Scenario: Student moves to the next page in a test
    Given the student is on page 1 of the test
    When the student clicks on the "Next" button
    Then the student is on page 2 of the test
