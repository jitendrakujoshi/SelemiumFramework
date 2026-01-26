package rahulshettyacademy.AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import jitendrajoshi.pageobject.CheckoutPage;
import jitendrajoshi.pageobject.ConfirmationPage;

import java.time.Duration;

public class OrderPage {

    WebDriver driver;
    WebDriverWait wait;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Wait for order page to load
    public void waitForOrderPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".order-summary") // यह selector तुम्हारे site के हिसाब से बदलो
        ));
    }

    // Check if order is present in order history
    public boolean isOrderPresent(String productName) {
        // Assuming order names are in elements with css ".order-item h3"
        return driver.findElements(By.cssSelector(".order-item h3"))
                     .stream()
                     .anyMatch(ele -> ele.getText().equalsIgnoreCase(productName));
    }

	public Boolean verifyProductDisplay(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public CheckoutPage goToCheckout() {
		// TODO Auto-generated method stub
		return null;
	}

	public ConfirmationPage submitOrder() {
		// TODO Auto-generated method stub
		return null;
	}
}
