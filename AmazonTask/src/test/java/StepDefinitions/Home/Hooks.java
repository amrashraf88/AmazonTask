package StepDefinitions.Home;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Hooks {

    public static WebDriver driver;


    @Before
    public void openBrowser() {
        // Set ChromeOptions for WebDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*"); // Allow cross-origin requests
        options.addArguments("--disable-blink-features=AutomationControlled"); // Prevent detection as automation
        options.addArguments("--start-maximized"); // Start browser maximized

        // Initialize WebDriver
        driver = new ChromeDriver(options);

        // Set timeouts for the driver
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Implicit wait
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS); // Page load timeout

        try {
            // Navigate to the specified URL
            driver.navigate().to("https://www.amazon.eg/");
            System.out.println("Browser opened successfully and navigated to Amazon.");
        } catch (Exception e) {
            System.err.println("Error during browser setup or navigation: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @After
    public void closeBrowser() {
        if (driver != null) {
            try {
                // Delay for visibility (optional)
                Thread.sleep(2000);

                // Quit WebDriver
                driver.quit();
                System.out.println("Browser closed successfully.");
            } catch (InterruptedException e) {
                System.err.println("Thread sleep interrupted: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Unexpected error during browser closure: " + e.getMessage());
            }
        } else {
            System.err.println("Driver is null, no browser instance to close.");
        }
    }
}