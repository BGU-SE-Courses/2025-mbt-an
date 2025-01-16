Feature: Manage Extra-Time Group

  Scenario: Remove a student from the extra-time group
    Given the student "John Doe" is in the extra-time group
    When the teacher removes "John Doe" from the extra-time group
    Then "John Doe" is no longer part of the extra-time group
