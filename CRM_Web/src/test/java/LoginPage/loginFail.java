package LoginPage;

import Locators.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.chrome.ChromeDriver;


import java.time.Duration;

public class loginFail {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://crm.anhtester.com/admin/authentication");
        boolean buttonLoginIsEnabled = driver.findElement(By.xpath(LoginPage.buttonLogin)).isEnabled();
        if (buttonLoginIsEnabled) {
            // User not input email and password
            driver.findElement(By.xpath(LoginPage.buttonLogin)).click();
            String alertErrorEmailRequired = driver.findElement(By.xpath(LoginPage.alertErrorEmailRequired)).getText();
            boolean alertErrorEmailRequiredIsDisplayed = driver.findElement(By.xpath(LoginPage.alertErrorEmailRequired)).isDisplayed();
            String alertErrorPasswordRequired = driver.findElement(By.xpath(LoginPage.alertErrorPasswordRequired)).getText();
            boolean alertErrorPasswordRequiredIsDisplayed = driver.findElement(By.xpath(LoginPage.alertErrorPasswordRequired)).isDisplayed();

            if (alertErrorEmailRequired.trim().equals("The Email Address field is required.") && alertErrorEmailRequiredIsDisplayed &&
                    alertErrorPasswordRequired.trim().equals("The Password field is required.") && alertErrorPasswordRequiredIsDisplayed) {
                System.out.println("Alert error email/password message required is displayed correctly");

            } else {
                System.out.println("Login button is not enabled");
            }
        }
        Thread.sleep(1000);
        // User not input email
        driver.findElement(By.xpath(LoginPage.inputPassword)).sendKeys("123456");
        driver.findElement(By.xpath(LoginPage.buttonLogin)).click();
        Thread.sleep(500);
        String alertErrorEmailRequired = driver.findElement(By.xpath(LoginPage.alertErrorMessage)).getText();
        boolean alertErrorEmailRequiredIsDisplayed = driver.findElement(By.xpath(LoginPage.alertErrorMessage)).isDisplayed();
        if (alertErrorEmailRequired.trim().equals("The Email Address field is required.") && alertErrorEmailRequiredIsDisplayed) {
            System.out.println("Alert error email message required is displayed correctly");
        } else {
            System.out.println("Alert error email message required is not displayed");
        }
        Thread.sleep(1000);
        // User not input password
        driver.findElement(By.xpath(LoginPage.inputEmail)).sendKeys("test@mail.com");
        driver.findElement(By.xpath(LoginPage.buttonLogin)).click();
        Thread.sleep(500);
        String alertErrorPasswordRequired = driver.findElement(By.xpath(LoginPage.alertErrorMessage)).getText();
        boolean alertErrorPasswordRequiredIsDisplayed = driver.findElement(By.xpath(LoginPage.alertErrorMessage)).isDisplayed();
        if (alertErrorPasswordRequired.trim().equals("The Password field is required.") && alertErrorPasswordRequiredIsDisplayed) {
            System.out.println("Alert error password message required is displayed correctly");
        } else {
            System.out.println("Alert error password message required is not displayed");
        }
        Thread.sleep(1000);
        // User input incorrect email or password
        driver.findElement(By.xpath(LoginPage.inputEmail)).sendKeys("test@mail.com");
        driver.findElement(By.xpath(LoginPage.inputPassword)).sendKeys("123");
        driver.findElement(By.xpath(LoginPage.buttonLogin)).click();
        Thread.sleep(500);
        String alertErrorMsg = driver.findElement(By.xpath(LoginPage.alertErrorMessage)).getText();
        boolean alertErrorMsgIsDisplayed = driver.findElement(By.xpath(LoginPage.alertErrorMessage)).isDisplayed();
        if (alertErrorMsg.trim().equals("Invalid email or password") && alertErrorMsgIsDisplayed) {
            System.out.println("Alert error message is displayed correctly");
        } else {
            System.out.println("Alert error message is not displayed");
        }
    driver.quit();
    }
}
