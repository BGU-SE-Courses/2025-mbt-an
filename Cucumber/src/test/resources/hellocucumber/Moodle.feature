Feature: A set of scenarios for testing the "Moodle" module

  Scenario: Removing student from extra-Time group
    Given the user logs into Moodle as "teacher" with password "#Aa123456"
    When the user navigates to the course
    And the user selects the Extra-Time Group
    And the user removes a student from the group
    And the user returns to the Groups menu
    Then the extra-time group size decreases by 1

  Scenario Outline: Student moving to the next page in a test
    Given the user logs into Moodle as "<username>" with password "<Password>"
    When the user navigates to the course
    And the user attempts a quiz in the course
    Then the user successfully moved to the next page
    Examples:
      | username | Password  |
      | user1    | #Aa123456 |
      | user2    | #Aa123456 |
