package jitendrajoshi.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".mb-3")
	static
    List<WebElement> products;

    static By addToCart = By.xpath(".//button[2]");
    static By toastMessage = By.cssSelector("#toast-container");
    static By productsBy = By.cssSelector(".mb-3");
    By cartHeader = By.cssSelector("[routerlink*='cart']");

    // get product list after waiting for elements
    public static List<WebElement> getProductList() {
        waitForElementToAppear(productsBy);
        return products;
    }

    // get product by name
    public static WebElement getProductByName(String productName) {
        return getProductList().stream()
                .filter(product -> product.findElement(By.cssSelector("b"))
                        .getText().equalsIgnoreCase(productName))
                .findFirst()
                .orElse(null);
    }

    // add product to cart
    public static void addProductToCart(String productName) {
        WebElement prod = getProductByName(productName);
        prod.findElement(addToCart).click();
        waitForElementToAppear(toastMessage);
    }

    // go to checkout page
    public CheckoutPage goToCheckout() {
        driver.findElement(cartHeader).click();
        return new CheckoutPage(driver);
    }

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client");
    }
}
