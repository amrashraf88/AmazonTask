package StepDefinitions.Login;

import POM.AmazonPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;

import static StepDefinitions.Home.Hooks.driver;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class AmazonStepDefinition {

    private static final Logger logger = LoggerFactory.getLogger(AmazonStepDefinition.class);
    AmazonPage amazonPage = new AmazonPage(driver);

    @Given("the user is on the Amazon homepage")
    public void theUserIsOnTheAmazonHomepage() {
        try {
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue("User is not on the Amazon homepage", currentUrl.contains("amazon.eg"));
            logger.info("Verified user is on the Amazon homepage.");
        } catch (Exception e) {
            logger.error("Failed to verify Amazon homepage.", e);
            Assert.fail("Error: " + e.getMessage());
        }
    }

    @When("I log in with email {string} and password {string}")
    public void iLogInWithEmailAndPassword(String email, String password) {
        try {

            amazonPage.click(amazonPage.accountAndLists());
            amazonPage.sendKeys(amazonPage.emailInput(), email);
            amazonPage.click(amazonPage.continueButton());
            amazonPage.sendKeys(amazonPage.passwordInput(), password);
            amazonPage.click(amazonPage.signInButton());
            logger.info("Successfully logged in with email: " + email);
        } catch (Exception e) {
            logger.error("Login failed.", e);
            Assert.fail("Error during login: " + e.getMessage());
        }
    }

    @And("I navigate to {string}")
    public void iNavigateTo(String menu) {
        try {
            amazonPage.click(amazonPage.menuIcon());
            amazonPage.click(amazonPage.seeall());
            amazonPage.click(amazonPage.allVideoGamesLink());


                amazonPage.click(amazonPage.allVideoGamesLink1());
            driver.navigate().to("https://www.amazon.eg/-/en/gp/browse.html?node=18022560031&ref_=nav_em_vg_all_0_2_16_2");
        } catch (Exception e) {
            logger.error("Failed to navigate to menu.", e);
            Assert.fail("Error during navigation: " + e.getMessage());
        }
    }

    @And("I apply the filters for {string} and {string}")
    public void iApplyTheFiltersForAnd(String filter1, String filter2) {
        try {

            if (filter1.equalsIgnoreCase("Free Shipping")) {
                amazonPage.click(amazonPage.freeShippingFilter());
            }
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,250)"); // Scroll down

            amazonPage.newConditionFilter().click();

            logger.info("Applied filters: " + filter1 + ", " + filter2);
        } catch (Exception e) {
            logger.error("Failed to apply filters.", e);
            Assert.fail("Error during filter application: " + e.getMessage());
        }
    }

    @And("I sort by {string}")
    public void iSortBy(String sortingOption) {
        try {

            amazonPage.sortDropdown().click();
            amazonPage.sortByPriceHighToLowOption().click();


        } catch (Exception e) {
            logger.error("Unexpected error during sorting.", e);
            Assert.fail("Error during sorting: " + e.getMessage());
        }
    }

    @And("I add products below 15000 EGP to the cart")
    public void iAddProductsBelowEGPToTheCart() {
        try {
            boolean hasNextPage = true;
            while (hasNextPage) {
                // Locate all product prices on the current page
                List<WebElement> products = driver.findElements(By.xpath("//span[contains(@class, 'a-price-whole')]"));

                for (WebElement priceElement : products) {
                    try {
                        // Extract product price and parse to integer
                        String priceText = priceElement.getText().replace(",", "").trim();
                        int price = Integer.parseInt(priceText);

                        // Check if the price is below 15,000 EGP
                        if (price < 15000) {
                            // Find the "Add to Cart" button within the product container
                            WebElement productContainer = priceElement.findElement(By.xpath("ancestor::div[contains(@class, 's-result-item')]"));
                            WebElement addToCartButton = productContainer.findElement(By.xpath(".//input[@aria-label='Add to Cart']"));

                            // Scroll and click the "Add to Cart" button
                            ((JavascriptExecutor) driver).executeScript(
                                    "arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", addToCartButton);
                            addToCartButton.click();
                            logger.info("Added product with price: " + price + " EGP to the cart.");
                        }
                    } catch (NumberFormatException e) {
                        logger.warn("Failed to parse product price. Skipping this product: " + priceElement.getText(), e);
                    } catch (NoSuchElementException e) {
                        logger.warn("Add to Cart button not found for a product. Skipping.", e);
                    } catch (Exception e) {
                        logger.warn("Unexpected error while adding product to the cart.", e);
                    }
                }

                // Check for "Next Page" button and navigate
                hasNextPage = driver.findElements(By.xpath("//li[@class='a-last']/a")).size() > 0;
                if (hasNextPage) {
                    WebElement nextButton = driver.findElement(By.xpath("//li[@class='a-last']/a"));
                    ((JavascriptExecutor) driver).executeScript(
                            "arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", nextButton);
                    nextButton.click();
                    logger.info("Navigated to the next page.");
                } else {
                    logger.info("No more pages to navigate.");
                }
            }
        } catch (Exception e) {
            logger.error("Failed to add products to the cart.", e);
            Assert.fail("Error during cart addition: " + e.getMessage());
        }
    }
    @Then("I verify all products are added to the cart")
    public void iVerifyAllProductsAreAddedToTheCart() {
        try {
            // Navigate to the cart page by clicking on the cart icon
            WebElement cartIcon = driver.findElement(By.xpath("//a[@id='nav-cart']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", cartIcon);
            cartIcon.click();

            // Wait for the cart items to be visible
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-name='Active Items']")));

            // Locate all items in the cart
            List<WebElement> cartItems = driver.findElements(By.xpath("//div[@data-name='Active Items']//div[contains(@class, 'sc-list-item')]"));

            // Assert that the cart contains items
            Assert.assertFalse("No items found in the cart.", cartItems.isEmpty());
            logger.info("Verified that products are added to the cart. Total items: " + cartItems.size());

            // Log the name and price of each item in the cart
            for (WebElement item : cartItems) {
                String itemName = item.findElement(By.xpath(".//span[@class='a-truncate-full']")).getText();
                String itemPrice = item.findElement(By.xpath(".//span[@class='a-price']//span[@class='a-offscreen']")).getText();
                logger.info("Cart item: " + itemName + " | Price: " + itemPrice);
            }
        } catch (NoSuchElementException e) {
            logger.error("Element not found during cart verification.", e);
            Assert.fail("Error during cart verification: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Failed to verify cart.", e);
            Assert.fail("Error during cart verification: " + e.getMessage());
        }
    }
    @When("the user adds a new address with full name {string}, phone number {string}, street {string}, building {string}, and landmark {string}")
    public void addNewAddress(String fullName, String phoneNumber, String street, String building, String landmark) {
        amazonPage.click(amazonPage.addNewAddressButton());
        amazonPage.sendKeys(amazonPage.fullNameInput(), fullName);
        amazonPage.sendKeys(amazonPage.phoneNumberInput(), phoneNumber);
        amazonPage.sendKeys(amazonPage.streetNameInput(), street);
        amazonPage.sendKeys(amazonPage.buildingNameInput(), building);
        amazonPage.sendKeys(amazonPage.nearestLandmarkInput(), landmark);
        amazonPage.click(amazonPage.aswanOption());
        amazonPage.click(amazonPage.deliveryTimeOption());
        amazonPage.click(amazonPage.submitAddressButton());
    }
    @Then("the user proceeds to checkout")
    public void proceedToCheckout() {
        amazonPage.click(amazonPage.proceedToCheckoutButton());
    }
}