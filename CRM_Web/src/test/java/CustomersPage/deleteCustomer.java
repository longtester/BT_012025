package CustomersPage;

import Locators.Customers;
import Locators.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

public class deleteCustomer {
    public static int getToTalAfterDelete(int element){
        int total;
        total = element - 1;
        return total;
    }
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://crm.anhtester.com/admin/authentication");
        // Login
        driver.get("https://crm.anhtester.com/admin/authentication");
        driver.findElement(By.xpath(LoginPage.inputEmail)).sendKeys("admin@example.com");
        driver.findElement(By.xpath(LoginPage.inputPassword)).sendKeys("123456");
        driver.findElement(By.xpath(LoginPage.buttonLogin)).click();

        // Click Customers menu
        driver.findElement(By.xpath(Customers.menuCustomer)).click();
        // Lấy số lượng Total Customers,Active Customers, Inactive Customers, Active Contacts, Inactive Contacts hiện tại để lát so sánh
        String currentTotalCustomersBeforeAdd = driver.findElement(By.xpath(Customers.totalCustomers)).getText();
        String currentActiveCustomersBeforeAdd = driver.findElement(By.xpath(Customers.activeCustomers)).getText();
        String currentInactiveCustomersBeforeAdd = driver.findElement(By.xpath(Customers.inactiveCustomers)).getText();
        String currentActiveContactsBeforeAdd = driver.findElement(By.xpath(Customers.activeContacts)).getText();
        String currentInactiveContactsBeforeAdd = driver.findElement(By.xpath(Customers.inactiveContacts)).getText();

        // Search tên Company và xóa
        driver.findElement(By.xpath(Customers.inputSearch)).sendKeys("long");
        Thread.sleep(2000);
        int index = 0;
        List<WebElement> listCompany = driver.findElements(By.xpath(Customers.listCompany));
        for(int i =0;i<listCompany.size();i++){
            if(listCompany.get(i).getText().equals(addNewCustomer.company)){
                index = i;
                break;
            }
        }
        System.out.println("Index of company: " + index);

        Actions action = new Actions(driver);
        action.moveToElement(listCompany.get(index)).perform();
        Thread.sleep(3000);
        WebElement buttondelete = driver.findElement(By.xpath(Customers.buttonDeleteCompany));
        boolean isButtonDeleteEnabled = buttondelete.isEnabled();
        System.out.println("Check status button Delete: " + isButtonDeleteEnabled);
        buttondelete.click();

        Thread.sleep(3000);
        driver.quit();


    }
}
