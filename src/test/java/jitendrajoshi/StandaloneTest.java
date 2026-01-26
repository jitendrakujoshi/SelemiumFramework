package jitendrajoshi;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://rahulshettyacademy.com/client");

        driver.findElement(By.id("userEmail")).sendKeys("jitu@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Stu@123456");
        driver.findElement(By.id("login")).click();

        // wait for products
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

        WebElement prod = products.stream()
                .filter(p -> p.findElement(By.cssSelector("b"))
                .getText().equalsIgnoreCase("ZARA COAT 3"))
                .findFirst()
                .orElse(null);

        if (prod != null) {

            WebElement addToCartBtn = prod.findElement(By.xpath(".//button[2]"));

            // wait till clickable
            wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));

            // JS click (most reliable)
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", addToCartBtn);

            System.out.println("Product added to cart");
        }

        // wait for toast
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));

        
        WebElement cartButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'Cart')]")
                )
        );
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", cartButton);
        
        
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        WebDriverWait wait11 = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement checkoutBtn = wait11.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("li.totalRow button")
                )
        );

        checkoutBtn.click();        
      
        
        
        
        WebElement countryInput = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("[placeholder='Select Country']")
                )
        );

        // click to focus
        countryInput.click();

        // type text
        countryInput.sendKeys("india");

        // now wait for suggestions
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

        // select India
        driver.findElement(By.xpath("//div/section/button[2]")).click();
        driver.findElement(By.xpath("//div[2]/a")).click();
        
       

        
        
    }
}
