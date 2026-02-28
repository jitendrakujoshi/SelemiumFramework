package jitendrajoshi.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[placeholder='Select Country']")
    WebElement country;

    @FindBy(css = ".action__submit")
    WebElement submit;

    // Select country
    public void selectCountry(String countryName) {
        country.sendKeys(countryName);
        driver.findElement(
            org.openqa.selenium.By.xpath("//button[contains(@class,'ta-item')][2]")
        ).click();
    }

    // Submit order
    public ConfirmationPage submitOrder() {
        submit.click();
        return new ConfirmationPage(driver);
    }
}
