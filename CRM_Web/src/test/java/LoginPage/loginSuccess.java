package LoginPage;

import Locators.LoginPage;
import Locators.SidebarMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class loginSuccess {
    public static void login() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://crm.anhtester.com/admin/authentication");
        driver.findElement(By.xpath(LoginPage.inputEmail)).sendKeys("admin@example.com");
        driver.findElement(By.xpath(LoginPage.inputPassword)).sendKeys("123456");
        boolean buttonLoginIsEnabled = driver.findElement(By.xpath(LoginPage.buttonLogin)).isEnabled();
        if (buttonLoginIsEnabled) {
            driver.findElement(By.xpath(LoginPage.buttonLogin)).click();
            Thread.sleep(1000);
            boolean listmenuDashboardIsDisplayed = driver.findElement(By.xpath(SidebarMenu.menuDashboard)).isDisplayed();
            System.out.println("List menu Dashboard is displayed: " + listmenuDashboardIsDisplayed);
        } else {
            System.out.println("Login fail");
        }
        driver.quit();
    }

    public static void main(String[] args) throws InterruptedException {
        loginSuccess.login();
    }
}



