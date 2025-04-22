package LoginPage;

import Locators.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class checkLabel {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://crm.anhtester.com/admin/authentication");
        Thread.sleep(1000);
        String labelEmailAddress = driver.findElement(By.xpath(LoginPage.labelEmail)).getText();
        String labelPassword = driver.findElement(By.xpath(LoginPage.labelPassword)).getText();
        String labelLogin = driver.findElement(By.xpath(LoginPage.headerLoginPage)).getText();
        String labelRememberMe = driver.findElement(By.xpath(LoginPage.labelRememberMe)).getText();
        String labelForgotPassword = driver.findElement(By.xpath(LoginPage.linkForgotPassword)).getText();
        String linkForgotPassword = driver.findElement(By.xpath(LoginPage.linkForgotPassword)).getAttribute("href");

        if (labelEmailAddress.trim().equals("Email Address") &&
                labelPassword.trim().equals("Password") &&
                labelLogin.trim().equals("Login") && labelRememberMe.trim().equals("Remember me") && labelForgotPassword.trim().equals("Forgot Password?")
                && linkForgotPassword.contains("forgot_password")
                )
            {
                System.out.println("Login page is displayed correctly.");
            } else{
                System.out.println("Login page is not displayed correctly.");
            }
        driver.quit();
        }

    }


