package hellocucumber;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import org.junit.Assert;
public class OpenMoodle {
    protected WebDriver driver;

    public void initSessionAsAdmin(String webDriver, String path) {
        System.setProperty(webDriver, path);

        // new chrome driver object
        this.driver = new ChromeDriver();

        

        // launch website -> localhost
        driver.get("http://localhost/");

       
}

}
