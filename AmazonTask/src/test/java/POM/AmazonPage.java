package POM;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AmazonPage {
    WebDriver driver;
    Actions action;
    WebDriverWait wait;

    // Constructor
    public AmazonPage(WebDriver driver) {
        this.driver = driver;
        this.action = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators and Element Getters
    public WebElement accountAndLists() {
        return driver.findElement(By.xpath("//*[@id=\"nav-signin-tooltip\"]/a"));
    }

    public WebElement emailInput() {
        return driver.findElement(By.xpath("//input[@id='ap_email']"));
    }

    public WebElement continueButton() {
        return driver.findElement(By.xpath("//input[@id='continue']"));
    }

    public WebElement passwordInput() {
        return driver.findElement(By.xpath("//input[@id='ap_password']"));
    }

    public WebElement signInButton() {
        return driver.findElement(By.xpath("//input[@id='signInSubmit']"));
    }

    public WebElement menuIcon() {
        return driver.findElement(By.xpath("//i[@class='hm-icon nav-sprite']"));
    }
public WebElement seeall(){
        return driver.findElement(By.xpath("//*[@id=\"hmenu-content\"]/ul[1]/li[14]/a[1]"));
}
    public WebElement allVideoGamesLink() {
        return driver.findElement(By.xpath("//*[@id=\"hmenu-content\"]/ul[1]/ul/li[11]/a"));
    }
    public WebElement allVideoGamesLink1() {
        return driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/ul[32]/li[2]/div"));
    }
    public WebElement freeShippingFilter() {
        return driver.findElement(By.xpath("//*[@id=\"s-refinements\"]/div[2]/ul/li/span/a/div[1]/label"));
    }

    public WebElement newConditionFilter() {
        return driver.findElement(By.xpath("//*[@id=\"p_n_condition-type/28071525031\"]"));
    }


    public WebElement sortDropdown() {
        return driver.findElement(By.xpath("//*[@id=\"a-autoid-0\"]/span"));
    }

    public WebElement sortByPriceHighToLowOption() {
        return driver.findElement(By.xpath(" //*[@id=\"s-result-sort-select_1\"]"));
    }

    public WebElement cartIcon() {
        return driver.findElement(By.xpath("//span[@class='nav-cart-icon nav-sprite']"));
    }

    public WebElement productPrices(){
        return driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[2]/div/div/span/div/div/div/div[2]/div/div/div[3]/div[1]/div/div[1]/div[1]/div[1]/a/span/span[2]/span[2]"));
    }
    public WebElement addNewAddressButton() {
        return driver.findElement(By.xpath("//a[@id='add-new-address-popover-link']"));
    }

    public WebElement fullNameInput() {
        return driver.findElement(By.xpath("//input[@id='address-ui-widgets-enterAddressFullName']"));
    }

    public WebElement phoneNumberInput() {
        return driver.findElement(By.xpath("//input[@id='address-ui-widgets-enterAddressPhoneNumber']"));
    }

    public WebElement streetNameInput() {
        return driver.findElement(By.xpath("//input[@id='address-ui-widgets-enterAddressLine1']"));
    }

    public WebElement buildingNameInput() {
        return driver.findElement(By.xpath("//input[@id='address-ui-widgets-enter-building-name-or-number']"));
    }

    public WebElement cityInput() {
        return driver.findElement(By.xpath("//input[@id='address-ui-widgets-enterAddressCity']"));
    }

    public WebElement aswanOption() {
        return driver.findElement(By.xpath("//li[contains(text(),'Aswan')]"));
    }

    public WebElement nearestLandmarkInput() {
        return driver.findElement(By.xpath("//input[@id='address-ui-widgets-landmark']"));
    }

    public WebElement deliveryTimeOption() {
        return driver.findElement(By.xpath("//input[@id='address-ui-widgets-addr-details-res-radio-input']"));
    }

    public WebElement submitAddressButton() {
        return driver.findElement(By.xpath("//input[@aria-labelledby='address-ui-widgets-form-submit-button-announce']"));
    }

    // Locators and Element Getters for Checkout
    public WebElement proceedToCheckoutButton() {
        return driver.findElement(By.xpath("//input[@name='proceedToRetailCheckout']"));
    }

    public WebElement noThanksButton() {
        return driver.findElement(By.xpath("//a[@id='prime-interstitial-nothanks-button']"));
    }

    // Helper Methods
    public void click(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to click element: " + e.getMessage());
        }
    }

    public void sendKeys(WebElement element, String text) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send keys to element: " + e.getMessage());
        }
    }

    public void hover(WebElement element) {
        try {
            action.moveToElement(element).perform();
        } catch (Exception e) {
            throw new RuntimeException("Failed to hover over element: " + e.getMessage());
        }
    }

    public boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    // Add more helper methods as needed (e.g., validations)
}