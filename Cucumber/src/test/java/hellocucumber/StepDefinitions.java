package hellocucumber;

import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class StepDefinitions {

    private OpenMoodle moodle;
    private int initialGroupSize;
    private int initialPageNumber;

    @Given("the teacher logs into Moodle as {string} with password {string}")
    public void theTeacherLogsIntoMoodleAs(String username, String password) {
        moodle = new OpenMoodle();
        moodle.initSession("webdriver.chrome.driver", "../Selenium/chromedriver");
        moodle.login(username, password);
    }

    @When("the user navigates to the course")
    public void theTeacherNavigatesToTheCourse() {
        moodle.navigateToCourse();
    }

    @When("the user selects the Extra-Time Group")
    public void theTeacherSelectsTheExtraTimeGroupAndFetchesTheInitialGroupSize() {
        moodle.navigateToExtraTimeGroupAndFetchSize();
        initialGroupSize = moodle.getGroupSize();
    }

    @When("the user removes a student from the group")
    public void theTeacherRemovesAStudentFromTheGroup() {
        moodle.removeStudentFromGroup();
    }

    @When("the user returns to the Groups menu")
    public void theTeacherReturnsToTheGroupsMenu() {
        moodle.returnToGroupsMenu();
    }

    @Then("the extra-time group size decreases by {int}")
    public void theGroupSizeDecreasesBy(int decrement) {
        int updatedGroupSize = moodle.getGroupSize();
        assertEquals(initialGroupSize - decrement, updatedGroupSize, "Group size did not decrease correctly.");
        moodle.closeSession();
    }

    @Given("the student logs into Moodle as {string} with password {string}")
    public void theUserLogsIntoMoodleAs(String username, String password) {
        moodle = new OpenMoodle();
        moodle.initSession("webdriver.chrome.driver", "../Selenium/chromedriver");
        moodle.login(username, password);
    }

    @When("the user attempts a quiz in the course")
    public void theUserAttemptsQuiz() {
        initialPageNumber = moodle.currentPage();
    }

    @Then("the user successfully moves to the next page")
    public void theUserSuccessfullyMovesToTheNextPage() {
        int pageNumber = moodle.nextPage();
        assertEquals(initialPageNumber + 1, pageNumber, "failed to move the next page.");
        System.out.println("Navigation to the next page done successfully.");
        moodle.closeSession();

    }

}


