package hellocucumber;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OpenMoodle {
    protected WebDriver driver;
    private WebDriverWait wait;

    public void initSession(String webDriver, String path) {
        System.setProperty(webDriver, path);
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        driver.get("http://localhost/Moodle");
        driver.manage().window().setPosition(new Point(0, 0));
        System.out.println("--------------- INITIALIZING MOODLE TEST - OPENING WEBPAGE ---------------");
    }

    public void login(String username, String password) {
        try {
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/nav/div/div[2]/div/div/span")));
            loginButton.click();
            System.out.println("Login button clicked.");

            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            usernameField.sendKeys(username);
            System.out.println("Entered username.");

            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            passwordField.sendKeys(password);
            System.out.println("Entered password.");

            WebElement loginSubmitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginbtn")));
            loginSubmitButton.click();
            System.out.println("Login submitted.");
        } catch (Exception e) {
            System.err.println("Error during login: " + e.getMessage());
            e.printStackTrace();
        }
    }



    public void navigateToCourse() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/nav/div/div[1]/nav/ul/li[3]/a"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/section/div/aside/section/div/div/div[1]/div[2]/div/div/div[1]/div/div/div/div/a/div"))).click();
    }

    public void navigateToExtraTimeGroupAndFetchSize() {
        try {

            // Step 1: Click on "Participants"
            WebElement participantsMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[4]/div/div[2]/nav/ul/li[3]/a")));
            participantsMenu.click();
            System.out.println("Participants menu opened.");

            // Step 2: Click on "Enrolled Users" to open the menu
            WebElement enrolledUsersMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[4]/div/div[3]/div/section/div/div[1]/div/div[1]/nav/div/div")));
            enrolledUsersMenu.click();
            System.out.println("Enrolled Users menu opened.");

            // Step 3: Click on "Groups" in the menu
            WebElement groupsOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[4]/div/div[3]/div/section/div/div[1]/div/div[1]/nav/div/ul/li[2]/ul/li[2]")));
            groupsOption.click();
            System.out.println("Groups option selected.");

            // Step 4: Click on the "Extra-Time Group"
            WebElement extraTimeGroup = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[4]/div/div[3]/div/section/div/form/div/div/div[1]/div[1]/select/option")));
            extraTimeGroup.click();
            System.out.println("Extra-Time Group selected.");

            // Fetch group size immediately after selecting the group
            fetchAndLogGroupSize();
        } catch (Exception e) {
            System.err.println("Error navigating to Extra-Time Group: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void fetchAndLogGroupSize() {
        try {
            WebElement groupSizeElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("/html/body/div[2]/div[4]/div/div[3]/div/section/div/form/div/div/div[2]/div[1]/label/span[2]")
            ));
            String groupText = groupSizeElement.getText().trim();
        } catch (Exception e) {
            System.err.println("Error fetching group size: " + e.getMessage());
        }
    }

    public int getGroupSize() {
        try {

            WebElement groupSizeElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("/html/body/div[2]/div[4]/div/div[3]/div/section/div/form/div/div/div[2]/div[1]/label/span[2]")
            ));
            String groupText = groupSizeElement.getText().trim();

            if (groupText.contains("(") && groupText.contains(")")) {
                int startIndex = groupText.indexOf('(') + 1;
                int endIndex = groupText.indexOf(')');
                String groupSizeStr = groupText.substring(startIndex, endIndex);
                int groupSize = Integer.parseInt(groupSizeStr);
                System.out.println("Group size: " + groupSize);
                return groupSize;
            } else {
                throw new IllegalStateException("Unexpected group text format: " + groupText);
            }
        } catch (Exception e) {
            System.err.println("Error fetching group size: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    public void returnToGroupsMenu() {
        WebElement backToGroupsButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div[2]/div[4]/div/div[3]/div/section/div/div/form/div/table/tbody/tr[2]/td/input")
        ));
        backToGroupsButton.click();
        System.out.println("Returned to Groups menu.");
    }




    public void removeStudentFromGroup() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='showaddmembersform']"))).click();
        WebElement userInGroup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[4]/div/div[3]/div/section/div/div/form/div/table/tbody/tr[1]/td[1]/div/select/optgroup/option[1]")));
        userInGroup.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='remove']"))).click();
    }

    public int currentPage() {
        try {
            // Step 1: Click on test
            WebElement test = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("/html/body/div[2]/div[4]/div/div[3]/div/section/div/div/div/ul/li[2]/div/div[2]/ul/li/div/div[2]/div[2]/div/div/a")
            ));
            test.click();
            System.out.println("test opened");

            // Step 2: Handle quiz attempt options
            WebElement attemptButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("/html/body/div[2]/div[4]/div/div[2]/div/section/div[2]/div[1]/div/div/form/button")
            ));
            attemptButton.click();
            System.out.println("Attempt quiz button clicked");

            // Step 3: Get the current page number from the URL
            System.out.println("Fetching current page number from URL...");
            String currentUrl = driver.getCurrentUrl();
            int currentPage = extractPageNumberFromUrl(currentUrl);
            System.out.println("Current Page number: " + currentPage);
            if (currentPage == -1) {
                throw new IllegalStateException("Failed to determine the current page from the URL: " + currentUrl);
            }
            return currentPage;

        } catch (Exception e) {
            System.err.println("Error during quiz attempt: " + e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    public int nextPage() {
        try {
            // Step 1: Click on Next Page button
            WebElement nextPage = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("/html/body/div[2]/div[5]/div/div[2]/div/section/div[2]/form/div/div[2]/input")
            ));
            nextPage.click();
            System.out.println("Next Page button clicked");

            // Step 2: Verify navigation to the next page
            System.out.println("Fetching new page number from URL...");
            String newUrl = driver.getCurrentUrl();
            int newPage = extractPageNumberFromUrl(newUrl);
            System.out.println("New Page number: " + newPage);


            // Step 3: Click on Previous Page button
            WebElement previousPage = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("/html/body/div[2]/div[5]/div/div[2]/div/section/div[2]/form/div/div[2]/input[1]")
            ));
            previousPage.click();
            System.out.println("navigated back to the previous page.");

            return newPage;

        } catch (Exception e) {
            System.err.println("Error during quiz attempt: " + e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }




    // Helper method to extract page number from URL
    private int extractPageNumberFromUrl(String url) {
        try {
            if (url.contains("page=")) {
                // Extract page number from "page=" parameter
                String pageParam = url.split("page=")[1].split("&")[0];
                return Integer.parseInt(pageParam);
            } else if (url.matches(".*\\d+.*")) {
                return 0;
            }
        } catch (Exception e) {
            System.err.println("Error extracting page number from URL: " + e.getMessage());
        }
        return -1; // Return -1 if page number is not found
    }



    public void closeSession() {
        if (driver != null) {
            driver.quit();
        }
    }
}
