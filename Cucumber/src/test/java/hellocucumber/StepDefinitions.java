package hellocucumber;

import io.cucumber.java.en.*;

import static org.junit.jupiter.api.Assertions.*;

public class StepDefinitions {

    private String student;
    private int currentPage;

    @Given("the student {string} is in the extra-time group")
    public void theStudentIsInTheExtraTimeGroup(String studentName) {
        student = studentName;
        System.out.println(student + " is added to the extra-time group.");
    }

    @When("the teacher removes {string} from the extra-time group")
    public void theTeacherRemovesFromTheExtraTimeGroup(String studentName) {
        if (student.equals(studentName)) {
            student = null;
        }
    }

    @Then("{string} is no longer part of the extra-time group")
    public void isNoLongerPartOfTheExtraTimeGroup(String studentName) {
        assertNull(student, studentName + " should not be part of the extra-time group.");
    }

    @Given("the student is on page {int} of the test")
    public void theStudentIsOnPageOfTheTest(int pageNumber) {
        currentPage = pageNumber;
    }

    @When("the student clicks on the {string} button")
    public void theStudentClicksOnTheButton(String button) {
        if (button.equals("Next")) {
            currentPage++;
        }
    }

    @Then("the student is on the next page {int} of the test")
    public void theStudentIsOnTheNextPageOfTheTest(int expectedPage) {
        assertEquals(expectedPage, currentPage, "The student should be on the expected page.");
    }
}
