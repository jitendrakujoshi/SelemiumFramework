package jitendrajoshi;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import jitendrajoshi.data.DataReader;
import jitendrajoshi.pageobject.Landingpage;
import jitendrajoshi.pageobject.ProductCatalogue;
import rahulshttyacademy.testcomponent.basetest;

public class SubmitOrderTest extends basetest {
	public WebDriver driver;

    @Test(dataProvider = "getData", groups = {"purchase"})
    public void submitOrder(HashMap<String, String> input) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // ================= LOGIN =================
        Landingpage landingpage = new Landingpage(driver);
        landingpage.goTo();
        landingpage.loginApplication(
                input.get("email"),
                input.get("password")
        );

        // ================= PRODUCT =================
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        productCatalogue.getProductList();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

        WebElement prod = products.stream()
                .filter(p -> p.findElement(By.cssSelector("b"))
                        .getText().equalsIgnoreCase(input.get("product")))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));

        WebElement addToCartBtn = prod.findElement(By.xpath(".//button[2]"));
        js.executeScript("arguments[0].click();", addToCartBtn);

        // ================= CART =================
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));

        WebElement cartButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'Cart')]")
                )
        );
        js.executeScript("arguments[0].click();", cartButton);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection")));

        WebElement checkoutBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("li.totalRow button")
                )
        );
        js.executeScript("arguments[0].click();", checkoutBtn);

        // ================= COUNTRY SELECT (FIXED) =================
        WebElement countryInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("[placeholder='Select Country']")
                )
        );

        countryInput.click();
        countryInput.clear();
        countryInput.sendKeys("ind");   // 🔑 important for Angular

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".ta-results")));

        WebElement indiaOption = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(@class,'ta-item') and contains(.,'India')]")
                )
        );
        js.executeScript("arguments[0].click();", indiaOption);

        // ================= PLACE ORDER =================
        WebElement placeOrderBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector(".action__submit")
                )
        );
        js.executeScript("arguments[0].click();", placeOrderBtn);

    }


    // ================= DATA PROVIDER =================
    @DataProvider
    public Object[][] getData() throws IOException {

        DataReader reader = new DataReader();

        List<HashMap<String, String>> data =
                reader.getJsonDataToMap(
                        System.getProperty("user.dir")
                                + "/src/test/java/jitendrajoshi/data/purchaseorder.json"
                );

        return new Object[][]{
                {data.get(0)}
        };
    }
}
