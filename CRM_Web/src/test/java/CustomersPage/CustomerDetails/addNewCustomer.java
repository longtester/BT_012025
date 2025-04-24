package CustomersPage.CustomerDetails;

import Locators.Customers;
import Locators.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.time.Duration;

import java.util.List;

import static Locators.Customers.inputSearch;
import static Locators.Customers.listcountry;

public class addNewCustomer {
    static String company = "Long QA/QC";
    static String VATnumber = "123456789";
    static String phoneNumber = "0123456789";
    static String website = "https://anhtester.com/";
    static String group = "VIP";
    static String currency = "USD$";
    static String language = "Vietnamese";
    static String address = "FPT";
    static String city = "Ho Chi Minh";
    static String state = "Thu Duc";
    static String zipcode = "123456";
    static String country = "Vietnam";

    public static int getTotalAfterAdd(int element) {
        int total;
        total = element + 1;
        return total;
    }
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
        // Lấy số lượng Total Customers,Active Customers, Inactive Customers, Active Contacts, Inactive Contacts để lát so sánh
        String currentTotalCustomersBeforeAdd = driver.findElement(By.xpath(Customers.totalCustomers)).getText();
        String currentActiveCustomersBeforeAdd = driver.findElement(By.xpath(Customers.activeCustomers)).getText();
        String currentInactiveCustomersBeforeAdd = driver.findElement(By.xpath(Customers.inactiveCustomers)).getText();
        String currentActiveContactsBeforeAdd = driver.findElement(By.xpath(Customers.activeContacts)).getText();
        String currentInactiveContactsBeforeAdd = driver.findElement(By.xpath(Customers.inactiveContacts)).getText();
        // Click button New Customer
        boolean isbuttonNewCustomerEnabled = driver.findElement(By.xpath(Customers.buttonNewCustomer)).isEnabled();
        System.out.println("Check status button New Customer: " + isbuttonNewCustomerEnabled);
        driver.findElement(By.xpath(Customers.buttonNewCustomer)).click();

        Thread.sleep(1000);
        driver.findElement(By.id(Customers.input_company)).sendKeys(company);
        driver.findElement(By.id(Customers.input_vatnumber)).sendKeys(VATnumber);
        driver.findElement(By.id(Customers.input_phonenumber)).sendKeys(phoneNumber);
        driver.findElement(By.id(Customers.input_webiste)).sendKeys(website);
        driver.findElement(By.xpath(Customers.dropdown_group)).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(Customers.input_searchgroup)).sendKeys("v");
        Thread.sleep(1000);
        List<WebElement> listGroup = driver.findElements(By.xpath(Customers.listgroup));
        for (int i = 0; i < listGroup.size(); i++) {
            if (listGroup.get(i).getText().equals(group)) {
                listGroup.get(i).click();
                break;
            }
        }
        driver.findElement(By.xpath(Customers.dropdown_group)).click();// Click again to close the dropdown
        driver.findElement(By.xpath(Customers.dropdown_currency)).click();
        Thread.sleep(1000);
        List<WebElement> listCurrency = driver.findElements(By.xpath(Customers.listcurrency));
        for (int i = 0; i < listCurrency.size(); i++) {
            if (listCurrency.get(i).getText().equals(currency)) {
                listCurrency.get(i).click();
                break;
            }
        }
        driver.findElement(By.xpath(Customers.dropdown_language)).click();
        Thread.sleep(1000);
        List<WebElement> listLanguage = driver.findElements(By.xpath(Customers.listlanguage));
        for (int i = 0; i < listLanguage.size(); i++) {
            if (listLanguage.get(i).getText().equals(language)) {
                listLanguage.get(i).click();
                break;
            }
        }
        driver.findElement(By.id(Customers.input_address)).sendKeys(address);
        driver.findElement(By.id(Customers.input_city)).sendKeys(city);
        driver.findElement(By.id(Customers.input_state)).sendKeys(state);
        driver.findElement(By.id(Customers.input_zipcode)).sendKeys(zipcode);
        driver.findElement(By.xpath(Customers.select_country)).click();

        driver.findElement(By.xpath(Customers.input_searchcountry)).sendKeys(country);
        Thread.sleep(1000);
        List<WebElement> listCountry = driver.findElements(By.xpath(listcountry));
        for (int i = 0; i < listCountry.size(); i++) {
            if (listCountry.get(i).getText().equals(country)) {
                listCountry.get(i).click();
                break;
            }
        }
        driver.findElement(By.xpath(Customers.buttonSave)).click();
        Thread.sleep(1000);
        String msgAddCustomerSuccess = driver.findElement(By.xpath(Customers.alertAddCustomerSuccess)).getText();

        // Validate thông tin Customer sau khi add thành công
        String valueCompany = driver.findElement(By.id(Customers.input_company)).getAttribute("value");
        String valueVATnumber = driver.findElement(By.id(Customers.input_vatnumber)).getAttribute("value");
        String valuePhoneNumber = driver.findElement(By.id(Customers.input_phonenumber)).getAttribute("value");
        String valueWebsite = driver.findElement(By.id(Customers.input_webiste)).getAttribute("value");
        String valueGroup = driver.findElement(By.xpath(Customers.dropdown_group)).getText();
        String valueCurrency = driver.findElement(By.xpath(Customers.dropdown_currency)).getText();
        String valueLanguage = driver.findElement(By.xpath(Customers.dropdown_language)).getText();
        String valueAddress = driver.findElement(By.id(Customers.input_address)).getAttribute("value");
        String valueCity = driver.findElement(By.id(Customers.input_city)).getAttribute("value");
        String valueState = driver.findElement(By.id(Customers.input_state)).getAttribute("value");
        String valueZipcode = driver.findElement(By.id(Customers.input_zipcode)).getAttribute("value");
        String valueCountry = driver.findElement(By.xpath(Customers.select_country)).getText();

        if (msgAddCustomerSuccess.equals("Customer added successfully.")) {
            System.out.println("Add new customer success");
        } else {
            System.out.println("Add new customer failed");
        }
        if (valueCompany.equals(company) && valueVATnumber.equals(VATnumber) && valuePhoneNumber.equals(phoneNumber)
                && valueWebsite.equals(website) && valueGroup.equals(group) && valueCurrency.equals(currency.replace("$", ""))
                && valueLanguage.equals(language) && valueAddress.equals(address) && valueCity.equals(city)
                && valueState.equals(state) && valueZipcode.equals(zipcode) && valueCountry.equals(country)) {
            System.out.println("The information of " + company + " on Customer Details is correct");
        } else {
            System.out.println("Some information of " + company + " on Customer Details is not correct");
        }

        // Validate thông tin trong Customers Summary
        // Total Customers
        driver.findElement(By.xpath(Customers.menuCustomer)).click();
        Thread.sleep(1000);
        int totalCustomersInt = Integer.parseInt(currentTotalCustomersBeforeAdd);// Ép kiểu String về int cho biến totalCustomers truước khi cộng
        int totalCustomersAfterAdd = getTotalAfterAdd(totalCustomersInt);
        String numberTotalCustomerAfterAdd = driver.findElement(By.xpath(Customers.totalCustomers)).getText();


        if(totalCustomersAfterAdd == Integer.parseInt(numberTotalCustomerAfterAdd)){
            System.out.println(+Integer.parseInt(numberTotalCustomerAfterAdd)+ " Total Customers after add new customer is correct");
        }else{
            System.out.println("Total Customer after add new customer is not correct");
        }

        // Active Customers
        int totalActiveCustomersInt = Integer.parseInt(currentActiveCustomersBeforeAdd);
        int totalActiveCustomersAfterAdd = getTotalAfterAdd(totalActiveCustomersInt);
        String numberTotalActiveCustomersAfterAdd = driver.findElement(By.xpath(Customers.activeCustomers)).getText();
        if (totalActiveCustomersAfterAdd == Integer.parseInt(numberTotalActiveCustomersAfterAdd)){
            System.out.println(+Integer.parseInt(numberTotalActiveCustomersAfterAdd)+ " Active Customers after add new customer is correct");
        }
        else{
            System.out.println("Active Customers after add new customer is not correct");
        }

        // Inactive Customers
        int totalInactiveCustomersInt = Integer.parseInt(currentInactiveCustomersBeforeAdd);
        String numberTotalInactiveCustomersAfterAdd = driver.findElement(By.xpath(Customers.inactiveCustomers)).getText();
        if((Integer.parseInt(numberTotalInactiveCustomersAfterAdd)) == totalInactiveCustomersInt){
            System.out.println(+Integer.parseInt(numberTotalInactiveCustomersAfterAdd)+ " Inactive Customers after add new customer is correct");
        }
        else{
            System.out.println("Inactive Customers after add new customer is not correct");
        }

        // Active Contacts
        int totalActiveContactsInt = Integer.parseInt(currentActiveContactsBeforeAdd);
        String numberTotalActiveContactsAfterAdd = driver.findElement(By.xpath(Customers.activeContacts)).getText();
        if(totalActiveContactsInt == Integer.parseInt(numberTotalActiveContactsAfterAdd)){
            System.out.println(+Integer.parseInt(numberTotalActiveContactsAfterAdd)+ " Active Contacts after add new customer is correct");
        }
        else{
            System.out.println("Active Contacts after add new customer is not correct");
        }

        // Inactive Contacts
        int totalInactiveContactsInt = Integer.parseInt(currentInactiveContactsBeforeAdd);
        String numberTotalInactiveContactsAfterAdd = driver.findElement(By.xpath(Customers.inactiveContacts)).getText();
        if(totalInactiveContactsInt == Integer.parseInt(numberTotalInactiveContactsAfterAdd)){
            System.out.println(+Integer.parseInt(numberTotalInactiveContactsAfterAdd)+ " Inactive Contacts after add new customer is correct");
        }
        else{
            System.out.println("Inactive Contacts after add new customer is not correct");
        }
        // Validate thông tin Customer đã có trong table chưa
        driver.findElement(By.xpath(inputSearch)).sendKeys("long");
        Thread.sleep(1000);
        List<WebElement> listCompany = driver.findElements(By.xpath(Customers.listCompany));
        for (int i = 0; i < listCompany.size(); i++) {
            if (listCompany.get(i).getText().equals(company)) {
               listCompany.get(i).click();
               break;
            }
        }

        Thread.sleep(3000);
        driver.quit();
    }
}
