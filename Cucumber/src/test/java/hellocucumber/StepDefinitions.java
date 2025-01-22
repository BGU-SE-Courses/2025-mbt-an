package hellocucumber;

import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class StepDefinitions {

    private OpenMoodle moodle;
    private int initialGroupSize;
    private int initialPageNumber;

    // initializing the Moodle session and Logs into Moodle with the provided username and password.
    @Given("the user logs into Moodle as {string} with password {string}")
    public void theTeacherLogsIntoMoodleAs(String username, String password) {
        moodle = new OpenMoodle();
        moodle.initSession("webdriver.chrome.driver", "../Selenium/chromedriver");
        moodle.login(username, password);
    }

    // Navigates to the specified course in Moodle.
    @When("the user navigates to the course")
    public void theTeacherNavigatesToTheCourse() {
        moodle.navigateToCourse();
    }

    // Selects the Extra-Time Group in Moodle and fetches the initial size of the group.
    @When("the user selects the Extra-Time Group")
    public void theTeacherSelectsTheExtraTimeGroupAndFetchesTheInitialGroupSize() {
        moodle.navigateToExtraTimeGroupAndFetchSize();
        initialGroupSize = moodle.getGroupSize();
    }

    // Removes a student from the Extra-Time Group in Moodle.
    @When("the user removes a student from the group")
    public void theTeacherRemovesAStudentFromTheGroup() {
        moodle.removeStudentFromGroup();
    }

    // Returns to the Groups menu in Moodle.
    @When("the user returns to the Groups menu")
    public void theTeacherReturnsToTheGroupsMenu() {
        moodle.returnToGroupsMenu();
    }

    // Validates that the size of the Extra-Time Group has decreased by 1 and the student deleted successfully.
    @Then("the extra-time group size decreases by 1")
    public void theGroupSizeDecreasesBy() {
        // Fetch the updated group size after the removal operation.
        System.out.print("The new ");
        int updatedGroupSize = moodle.getGroupSize();
        assertEquals(initialGroupSize - 1, updatedGroupSize, "Group size did not decrease correctly.");
        System.out.println("-----Student successfully deleted from the group-----");
        // Add the removed student back to the group to restore the original state.
        moodle.returnStudentBackToGroup();
        moodle.closeSession();
    }


    // Attempts a quiz in the course and records the initial page number of the quiz.
    @When("the user attempts a quiz in the course")
    public void theUserAttemptsQuiz() {
        initialPageNumber = moodle.currentPage();
    }

    // Validates that the user successfully moved to the next page of the quiz.
    @Then("the user successfully moved to the next page")
    public void theUserSuccessfullyMovesToTheNextPage() {
        // Navigate to the next page and get the current page number.
        int pageNumber = moodle.nextPage();
        assertEquals(initialPageNumber + 1, pageNumber, "failed to move the next page.");
        System.out.println("-----Student successfully moved to the next page-----");
        // Return to the previous page of the quiz to restore the original state.
        moodle.backToPreviousPage();
        moodle.closeSession();

    }

}


