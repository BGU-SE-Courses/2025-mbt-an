Feature: Managing the Extra-Time Group in Moodle

  Scenario: Remove a student from the Extra-Time Group
    Given the teacher logs into Moodle as "teacher" with password "#Aa123456"
    When the teacher navigates to the course
    And the teacher selects the Extra-Time Group
    And the teacher removes a student from the group
    And the teacher returns to the Groups menu
    Then the group size decreases by 1
