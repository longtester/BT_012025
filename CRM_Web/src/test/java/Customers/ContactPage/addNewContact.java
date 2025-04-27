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
import java.util.ArrayList;
import java.util.List;

public class addNewContact {
    static String firtname = "long";
    static String lastname = "tester";
    static String email = "longtester@mail.com";
    static String password = "123456";

    public static int getTotalAfterAdd(int element) {
        int total;
        total = element + 1;
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
        // Lấy số lượng Active Contacts, Inactive Contacts hiện tại để lát so sánh
        String currentActiveContactsBeforeAdd = driver.findElement(By.xpath(Customers.activeContacts)).getText();
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

        // Click button Add New Contact and not input required field
        if(driver.findElement(By.xpath(Contact.buttonNewContact)).isEnabled()){
            driver.findElement(By.xpath(Contact.buttonNewContact)).click();
            Thread.sleep(1000);
            if(driver.findElement(By.xpath(Contact.formContact)).isDisplayed()){
                driver.findElement(By.xpath(Contact.buttonSaveContact)).click();
                Thread.sleep(1000);
                // Check alert error message
                driver.findElement(By.xpath(Contact.alertErrorFirstNameRequired)).isDisplayed();
                driver.findElement(By.xpath(Contact.alertErrorLastNameRequired)).isDisplayed();
                driver.findElement(By.xpath(Contact.alertErrorEmailRequired)).isDisplayed();
                driver.findElement(By.xpath(Contact.alertErrorPasswordRequired)).isDisplayed();
            }
            else{
                System.out.println("Add new contact form is not displayed");
            }
        }
        else{
            System.out.println("Button New Contact is disabled");
        }
        driver.findElement(By.xpath(Contact.buttonClose)).click();
        Thread.sleep(1000);

        // Click button Add New Contact and input required field
        if(driver.findElement(By.xpath(Contact.buttonNewContact)).isEnabled()){
            driver.findElement(By.xpath(Contact.buttonNewContact)).click();
            Thread.sleep(1000);
            if(driver.findElement(By.xpath(Contact.formContact)).isDisplayed()){
                driver.findElement(By.id(Contact.inputFirstName)).sendKeys(firtname);
                Thread.sleep(500);
                driver.findElement(By.id(Contact.inputLastname)).sendKeys(lastname);
                Thread.sleep(500);
                driver.findElement(By.id(Contact.inputEmail)).sendKeys(email);
                Thread.sleep(500);
                driver.findElement(By.name(Contact.inputPassword)).sendKeys(password);
                Thread.sleep(500);
                driver.findElement(By.xpath(Contact.buttonSaveContact)).click();
            }
            else{
                System.out.println("Add new contact form is not displayed");
            }
        }
        else{
            System.out.println("Button New Contact is disabled");
        }
        Thread.sleep(500);
        String alertAddContactSuccess = driver.findElement(By.xpath(Contact.alertAddContactSuccess)).getText();
        boolean checkAlertAddContactSuccess = driver.findElement(By.xpath(Contact.alertAddContactSuccess)).isDisplayed();
        if (alertAddContactSuccess.equals("Contact added successfully.") && checkAlertAddContactSuccess == true) {
            System.out.println("Add new contact success");
        }else{
            System.out.println("Add new contact failed");
        }

        // Validate thông tin Contact sau khi add thành công
        Thread.sleep(2000);
        int indexEmail = 0;
        List<WebElement> listEmailContact = driver.findElements(By.xpath(Contact.listEmail));
        for(int i = 0;i<listEmailContact.size();i++){
            if(listEmailContact.get(i).getText().equals(email)){
                indexEmail = i;
                System.out.println("Email "+email+" is correct");
                break;
            }
        }
        List<WebElement> listFullNameContact = driver.findElements(By.xpath(Contact.listFullName));
        if(listFullNameContact.get(indexEmail).getText().equals(firtname+" "+lastname)){
            System.out.println("FullName "+(firtname+" "+lastname)+" is correct");
        }else{
            System.out.println("FullName "+(firtname+" "+lastname)+" is not correct");
        }

        // Validate thông tin trong Customers Summary
        // Active Contacts
        driver.navigate().back();
        int totalActiveContactInt = Integer.parseInt(currentActiveContactsBeforeAdd);
        int expect_TotalActiveContactAfterAdd = getTotalAfterAdd(totalActiveContactInt);
        String numberTotalActiveContactAfterAdd = driver.findElement(By.xpath(Customers.activeContacts)).getText();
        if(Integer.parseInt(numberTotalActiveContactAfterAdd) == expect_TotalActiveContactAfterAdd){
            System.out.println(+Integer.parseInt(numberTotalActiveContactAfterAdd)+ " Total Active Contact after add new contact is correct");
        }
        else{
            System.out.println("Total Active Contact after add new contact is not correct");
        }


        Thread.sleep(3000);
        driver.quit();


    }
}
