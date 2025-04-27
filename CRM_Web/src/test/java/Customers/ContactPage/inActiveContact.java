package Customers.ContactPage;

import Customers.CustomersPage.addNewCustomer;
import Locators.Contact;
import Locators.Customers;
import Locators.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

public class inActiveContact {
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
        // Lấy số lượng Inactive Contacts hiện tại để lát so sánh
        String currentInactiveContactsBeforeAdd = driver.findElement(By.xpath(Customers.inactiveContacts)).getText();

        // Search tên Company và add new contact
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
        Thread.sleep(1000);
        List<WebElement> listButtonContactCompany = driver.findElements(By.xpath(Customers.listbuttonContactCompany));
        // Click button Contact dựa trên index của Company
        listButtonContactCompany.get(index).click();
        Thread.sleep(1000);
        if(driver.findElement(By.xpath(Contact.headerContact)).isDisplayed()){
            System.out.println("Navigated to Contact page successfully");
        }
        else{
            System.out.println("Navigated to Contact page failed");
        }
        driver.findElement(By.xpath(Contact.toggleInactiveContact)).click();
        if(driver.findElement(By.xpath(Contact.toggleInactiveContact)).isSelected() == true){
            System.out.println("Toggle button inactive contact is on");
        }else{
            System.out.println("Toggle button inactive contact is off");
        }
        driver.navigate().back();
        // Validate thông tin trong Customers Summary
        // InActive Contacts: Hiện tại Total inactive contact = 0 nên phải check điều kiện !=0 rồi đi so sánh
        int totalInactiveContactsInt = Integer.parseInt(currentInactiveContactsBeforeAdd);
        if(totalInactiveContactsInt !=0){
            int expect_TotalInActiveContact = getTotalAfterDelete(totalInactiveContactsInt);
            String numberTotalInactiveContacts = driver.findElement(By.xpath(Customers.inactiveContacts)).getText();
            if(Integer.parseInt(numberTotalInactiveContacts) == expect_TotalInActiveContact){
                System.out.println(+Integer.parseInt(numberTotalInactiveContacts)+ " Total Inactive Contacts after inactive contact is correct");
            }else{
                System.out.println("Total Inactive Contacts after inactive contact is not correct");
            }
        }else{
            System.out.println("Curent Total Inactive Contacts is < 0");
        }

        Thread.sleep(3000);
        driver.quit();
    }
}
