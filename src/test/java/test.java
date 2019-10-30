import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class test {
    WebDriver driver = new ChromeDriver();

    @Test
    public void testSnap() throws IOException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("https://snapengage-qa.appspot.com/signin?to=hub");
        driver.manage().window().maximize();

        // Find Email field and enter email ID
        WebElement Email = driver.findElement(By.xpath("//*[@id='email' and @type='text']"));
        Email.sendKeys("pedroalmodovar@test.com");

        // Find Password field and enter password
        WebElement Password = driver.findElement(By.xpath("//*[@id='password' and @type='password']"));
        Password.sendKeys("1q2w3e");

        // Click on Sign In button
        driver.findElements(By.name("Submit")).get(0).click();

        // Assertion
        String URL = driver.getCurrentUrl();
        String expectedUrl = "https://snapengage-qa.appspot.com/hub";
        Assert.assertEquals(URL, expectedUrl);
        // Console log
        System.out.println("========================");
        if (URL.equalsIgnoreCase(expectedUrl))
        {
            System.out.println("Test case passed.");
        }
        else
        {
            System.out.println("Test case failed.");
        }
        System.out.println("========================");

        // Wait for the home page to load
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='intro-box']")));

        // And finally take the screenshot
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("/home/rashmitha/error.png"));
        // Tearing down the browser
        driver.close();
        driver.quit();
    }
}
