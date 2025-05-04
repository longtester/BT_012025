package Projects;

import Common.BaseTest;
import Customers.CustomersPage.addNewCustomer;
import Locators.Customers;
import Locators.Projects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class addNewProject extends BaseTest {
    public static String projectName = "Test Generator";
    static String status = "On Hold";

    public static int getTotalAfterAdd(int element) {
        int total;
        total = element + 1;
        return total;
    }

    public static void main(String[] args) throws InterruptedException {
        createDriver();
        loginCRM();
        // Click Projects menu
        driver.findElement(By.xpath(Projects.menuProject)).click();
        // Lấy số lượng Projects đang On Hold
        String currentProjectOnHoldBeforeAdd = driver.findElement(By.xpath(Projects.numberOnHold)).getText();
        // Click New Project button
        driver.findElement(By.xpath(Projects.buttonNewProject)).click();
        Thread.sleep(1000);
        // Input Project Name
        driver.findElement(By.id(Projects.inputProjectName)).sendKeys(projectName);
        // Click Customer dropdown
        driver.findElement(By.xpath(Projects.dropdownCustomer)).click();
        Thread.sleep(500);

        // Input search customer
        driver.findElement(By.xpath(Projects.inputSearchCustomer)).sendKeys("long");
        Thread.sleep(1000);
        List<WebElement> listCustomer = driver.findElements(By.xpath(Projects.listSearchCustomer));
        for (int i = 0; i < listCustomer.size(); i++) {
            if (listCustomer.get(i).getText().equals(addNewCustomer.company)) {
                listCustomer.get(i).click();
                break;
            }
        }
        Thread.sleep(500);
        driver.findElement(By.xpath(Projects.dropdownStatus)).click();
        List<WebElement> listStatus = driver.findElements(By.xpath(Projects.dropdownlistStatus));
        for (int i = 0; i < listStatus.size(); i++) {
            if (listStatus.get(i).getText().equals(status)) {
                listStatus.get(i).click();
                break;
            }
        }
        driver.findElement(By.xpath(Projects.buttonSave)).click();
        Thread.sleep(1000);
        String alertAddProjectSuccess = driver.findElement(By.xpath(Projects.alertAddProjectSuccess)).getText();
        boolean alertAddProjectSuccessIsDisplayed = driver.findElement(By.xpath(Projects.alertAddProjectSuccess)).isDisplayed();
        if (alertAddProjectSuccess.equals("Project added successfully.") && alertAddProjectSuccessIsDisplayed == true) {
            System.out.println("Add new project " + projectName + " successfully");
        } else {
            System.out.println("Add new project " + projectName + " failed");
        }
        // Verify total Status Projects On Hold after add new Project
        driver.findElement(By.xpath(Projects.menuProject)).click();
        int currentProjectOnHold = Integer.parseInt(currentProjectOnHoldBeforeAdd);
        int expect_ProjectOnHold = getTotalAfterAdd(currentProjectOnHold);
        String currentProjectOnHoldAfterAdd = driver.findElement(By.xpath(Projects.numberOnHold)).getText();
        if (Integer.parseInt(currentProjectOnHoldAfterAdd) == expect_ProjectOnHold) {
            System.out.println("Total Status Projects " + status + " after add new project is correct");
        } else {
            System.out.println("Total Status Projects " + status + " after add new project is not correct");
        }
        // Verify the information project display correct in table after add new project
        driver.findElement(By.xpath(Projects.inputSearch)).sendKeys(projectName);
        Thread.sleep(1000);
        String getCurrentProjectName = driver.findElement(By.xpath(Projects.projectName)).getText();
        boolean getCurrentProjectNameIsDisplayed = driver.findElement(By.xpath(Projects.projectName)).isDisplayed();
        String getCurrentCustomerName = driver.findElement(By.xpath(Projects.customerName)).getText();
        boolean getCurrentCustomerNameIsDisplayed = driver.findElement(By.xpath(Projects.customerName)).isDisplayed();
        String getCurrentStatus = driver.findElement(By.xpath(Projects.status)).getText();
        boolean getCurrentStatusIsDisplayed = driver.findElement(By.xpath(Projects.status)).isDisplayed();
        if (getCurrentProjectNameIsDisplayed == true && getCurrentProjectName.equals(projectName)
                && getCurrentCustomerNameIsDisplayed == true && getCurrentCustomerName.equals(addNewCustomer.company)
                && getCurrentStatusIsDisplayed == true && getCurrentStatus.equals(status)) {
            System.out.println("The information of Project " + projectName + " is correct");
        } else {
            System.out.println("The information of Project " + projectName + " is not correct");
        }
        closeDriver();
    }
}
