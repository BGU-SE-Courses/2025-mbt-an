Feature: A set of scenarios for testing the "example" module

  Scenario: Remove a student from the Extra-Time Group
    Given the teacher logs into Moodle as "teacher" with password "#Aa123456"
    When the user navigates to the course
    And the user selects the Extra-Time Group
    And the user removes a student from the group
    And the user returns to the Groups menu
    Then the extra-time group size decreases by 1

  Scenario: User attempts a quiz and navigates to the next page
    Given the student logs into Moodle as "user2" with password "#Aa123456"
    When the user navigates to the course
    And the user attempts a quiz in the course
    Then the user successfully moves to the next page

