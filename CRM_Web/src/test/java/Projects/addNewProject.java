package Projects;

import Common.BaseTest;
import Customers.CustomersPage.addNewCustomer;
import Locators.Customers;
import Locators.Projects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class addNewProject extends BaseTest {
    public static String projectName = "5 anh em siêu nhân";
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
        for(int i =0;i<listCustomer.size();i++){
            if(listCustomer.get(i).getText().equals(addNewCustomer.company)){
                listCustomer.get(i).click();
                break;
            }
        }
        Thread.sleep(500);
        driver.findElement(By.xpath(Projects.dropdownStatus)).click();
        List<WebElement> listStatus = driver.findElements(By.xpath(Projects.dropdownlistStatus));
        for(int i =0;i<listStatus.size();i++){
            if(listStatus.get(i).getText().equals(status)){
                listStatus.get(i).click();
                break;
            }
        }
        driver.findElement(By.xpath(Projects.buttonSave)).click();
        Thread.sleep(1000);
        String alertAddProjectSuccess = driver.findElement(By.xpath(Projects.alertAddProjectSuccess)).getText();
        boolean alertAddProjectSuccessIsDisplayed = driver.findElement(By.xpath(Projects.alertAddProjectSuccess)).isDisplayed();
        if(alertAddProjectSuccess.equals("Project added successfully.") && alertAddProjectSuccessIsDisplayed == true){
            System.out.println("Add new Project successfully");
        }else{
            System.out.println("Add new Project failed");
        }
        // Verify total Projects On Hold after add new Project
        driver.findElement(By.xpath(Projects.menuProject)).click();
        int currentProjectOnHold = Integer.parseInt(currentProjectOnHoldBeforeAdd);
        int expect_ProjectOnHold = getTotalAfterAdd(currentProjectOnHold);
        String currentProjectOnHoldAfterAdd = driver.findElement(By.xpath(Projects.numberOnHold)).getText();
        if(Integer.parseInt(currentProjectOnHoldAfterAdd) == expect_ProjectOnHold){
            System.out.println("Total Projects On Hold after add new Project is correct");
        }
        else{
            System.out.println("Total Projects On Hold after add new Project is not correct");
        }
        closeDriver();
    }
}
