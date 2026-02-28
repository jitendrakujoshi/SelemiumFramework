package jitendrajoshi.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

//This is my landing page
public class Landingpage extends  AbstractComponent {

	WebDriver driver;
	public Landingpage(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver,this);
	
	}

	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalogue loginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		return null;
	}
	
	public String getErrorMessage() {
         waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	
	
	public void goTo()
	{
        driver.get("https://rahulshettyacademy.com/client");

	}

	public static void incrementQuantity(int int1) {
		// TODO Auto-generated method stub
		
	}

	public static void addToCart() {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
	
	
	
	
	
	
}
