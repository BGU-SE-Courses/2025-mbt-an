package hellocucumber;

import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class StepDefinitions {

    private OpenMoodle moodle;
    private int initialGroupSize;

    @Given("the teacher logs into Moodle as {string} with password {string}")
    public void theTeacherLogsIntoMoodleAs(String username, String password) {
        moodle = new OpenMoodle();
        moodle.initSessionAsAdmin("webdriver.chrome.driver", "../Selenium/chromedriver");
        moodle.loginAsTeacher(username, password);
    }

    @When("the teacher navigates to the course")
    public void theTeacherNavigatesToTheCourse() {
        moodle.navigateToCourse();
    }

    @When("the teacher selects the Extra-Time Group")
    public void theTeacherSelectsTheExtraTimeGroupAndFetchesTheInitialGroupSize() {
        moodle.navigateToExtraTimeGroupAndFetchSize();
        initialGroupSize = moodle.getGroupSize();
    }

    @When("the teacher removes a student from the group")
    public void theTeacherRemovesAStudentFromTheGroup() {
        moodle.removeStudentFromGroup();
    }

    @When("the teacher returns to the Groups menu")
    public void theTeacherReturnsToTheGroupsMenu() {
        moodle.returnToGroupsMenu();
    }

    @Then("the group size decreases by {int}")
    public void theGroupSizeDecreasesBy(int decrement) {
        int updatedGroupSize = moodle.getGroupSize();
        assertEquals(initialGroupSize - decrement, updatedGroupSize, "Group size did not decrease correctly.");
        moodle.closeSession();
    }
}


