package Customers.CustomersPage;

import Locators.Customers;
import Locators.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class inActiveCustomer {
    public static int getTotalAfterAdd(int element) {
        int total;
        total = element + 1;
        return total;
    }
    public static int getTotalAfterDelete(int element){
        int total;
        total = element -1;
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
        // Lấy số lượng Active Customer, Inactive Customers hiện tại để lát so sánh
        String currentInactiveCustomersBeforeInactive = driver.findElement(By.xpath(Customers.inactiveCustomers)).getText();
        String currentActiveCustomersBeforeInactive = driver.findElement(By.xpath(Customers.activeCustomers)).getText();

        // Search tên Company và inactive customer
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
        int totalInactiveCustomersInt = Integer.parseInt(currentInactiveCustomersBeforeInactive);
        int totalActiveCustomersInt = Integer.parseInt(currentActiveCustomersBeforeInactive);
        List<WebElement> listToggleInactiveCustomer = driver.findElements(By.xpath(Customers.listToggleInactiveCustomer));
        listToggleInactiveCustomer.get(index).click();
        driver.navigate().refresh();
        Thread.sleep(1000);
        // Inactive Customer
        int expect_TotalInActiveCustomers = getTotalAfterAdd(totalInactiveCustomersInt);
        String numberTotalInactiveCustomers = driver.findElement(By.xpath(Customers.inactiveCustomers)).getText();
        if((Integer.parseInt(numberTotalInactiveCustomers)) == expect_TotalInActiveCustomers){
            System.out.println(+Integer.parseInt(numberTotalInactiveCustomers)+ " Total Inactive Customers after inactive customer is correct");
        }
        else{
            System.out.println("Total Inactive Customers after inactive customer is not correct");
        }
        // Active Customer
        int expect_TotalActiveCustomers = getTotalAfterDelete(totalActiveCustomersInt);
        String numberTotalActiveCustomers = driver.findElement(By.xpath(Customers.activeCustomers)).getText();
        if (expect_TotalActiveCustomers == Integer.parseInt(numberTotalActiveCustomers)){
            System.out.println(+Integer.parseInt(numberTotalActiveCustomers)+ " Total Active Customers after inactive customer is correct");
        }
        else{
            System.out.println("Total Active Customers after inactive customer is not correct");
        }

        Thread.sleep(3000);
        driver.quit();
    }
}
