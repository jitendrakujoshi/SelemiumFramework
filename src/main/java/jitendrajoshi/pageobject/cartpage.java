package jitendrajoshi.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.AbstractComponents.OrderPage;

public class cartpage {
	
	static WebDriver driver;
	WebDriverWait wait;
	
	 public cartpage(WebDriver driver) {
	        this.driver = driver;
	    }
	
	
	
	public void waitForElementToAppear(By findBy)
	{
    wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	
	public static CheckoutPage goToCheckout()
	{
		CheckoutPage.click();
		return  new CheckoutPage(driver);
	}
	
	
	
	
	
	
	

}
