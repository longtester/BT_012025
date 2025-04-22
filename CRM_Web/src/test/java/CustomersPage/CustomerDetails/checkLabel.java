package CustomersPage.CustomerDetails;

import Locators.Customers;
import Locators.LoginPage;
import LoginPage.loginSuccess;
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
        // Login
        driver.get("https://crm.anhtester.com/admin/authentication");
        driver.findElement(By.xpath(LoginPage.inputEmail)).sendKeys("admin@example.com");
        driver.findElement(By.xpath(LoginPage.inputPassword)).sendKeys("123456");
        driver.findElement(By.xpath(LoginPage.buttonLogin)).click();

        // Click Customers menu
        driver.findElement(By.xpath(Customers.menuCustomer)).click();
        // Click button New Customer
        boolean isbuttonNewCustomerEnabled = driver.findElement(By.xpath(Customers.buttonNewCustomer)).isEnabled();
        System.out.println("Check status button New Customer: " + isbuttonNewCustomerEnabled);
        driver.findElement(By.xpath(Customers.buttonNewCustomer)).click();

        Thread.sleep(1000);
        String labelCustomerDetail = driver.findElement(By.xpath(Customers.customer_details)).getText();
        String labelCompany = driver.findElement(By.xpath(Customers.company)).getText().replace("*", "");
        String labelVAT = driver.findElement(By.xpath(Customers.vat_number)).getText();
        String labelPhone = driver.findElement(By.xpath(Customers.phone_number)).getText();
        String labelWebsite = driver.findElement(By.xpath(Customers.website)).getText();
        String labelGroups = driver.findElement(By.xpath(Customers.group)).getText();
        String labelCurrency = driver.findElement(By.xpath(Customers.currency)).getText();
        String labelLanguage = driver.findElement(By.xpath(Customers.default_language)).getText();
        String labelAddress = driver.findElement(By.xpath(Customers.address)).getText();
        String labelCity = driver.findElement(By.xpath(Customers.city1)).getText();
        String labelState = driver.findElement(By.xpath(Customers.state1)).getText();
        String labelZipcode = driver.findElement(By.xpath(Customers.zipcode1)).getText();
        String labelCountry = driver.findElement(By.xpath(Customers.country1)).getText();

        if (labelCustomerDetail.trim().equals("Customer Details") && labelCompany.trim().equals("Company")
                && labelVAT.equals("VAT Number") && labelPhone.equals("Phone")
                && labelWebsite.equals("Website") && labelGroups.equals("Groups")
                && labelCurrency.equals("Currency") && labelLanguage.equals("Default Language")
                && labelAddress.equals("Address") && labelCity.equals("City")
                && labelState.equals("State") && labelZipcode.equals("Zip Code")
                && labelCountry.equals("Country")) {
            System.out.println("All labels are correct");
        } else {
            System.out.println("Some labels are incorrect");
        }
        driver.quit();
    }
}

