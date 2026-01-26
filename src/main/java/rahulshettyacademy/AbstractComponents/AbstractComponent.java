package rahulshettyacademy.AbstractComponents;

import java.time.Duration; // Correct import

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import jitendrajoshi.pageobject.CheckoutPage;
import jitendrajoshi.pageobject.cartpage;

public class AbstractComponent {

    protected WebDriver driver;
    protected static WebDriverWait wait;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        // Initialize WebDriverWait properly
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Wait for element to appear
    public static void waitForElementToAppear(By findBy) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }
    
    
    public void waitForWebElementToAppear(WebElement findBy) {
    	WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

    // Optional: wait for element to disappear
    public void waitForElementToDisappear(By findBy) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
    }
    public OrderPage goToOrderPage() {
        // Find the element on the page
        WebElement orderHeader = driver.findElement(By.cssSelector("selector-for-order-header")); // ← replace with correct selector

        // Wait until clickable (optional but safe)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(orderHeader));

        // Click the element
        orderHeader.click();

        // Return the new page object
        return new OrderPage(driver);
    }

   
    
    
    
    
}
