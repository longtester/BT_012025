package LoginPage;

import Common.BaseTest;
import Locators.LoginPage;
import Locators.SidebarMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class loginSuccess extends BaseTest {
    public static void main(String[] args) throws InterruptedException {
        createDriver();
        loginCRM();
        Thread.sleep(1000);
        boolean menuIsDisplayed = driver.findElement(By.xpath(SidebarMenu.menuDashboard)).isDisplayed();
        if(menuIsDisplayed == true){
            System.out.println("Menu Dashboard is displayed");
        }else{
            System.out.println("Menu Dashboard is not displayed");
        }
        closeDriver();
    }

}



