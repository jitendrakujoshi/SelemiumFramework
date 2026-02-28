package jitendrajoshi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FluantwaitTest {

    public static void main(String[] args) {

        // Chrome driver start
        WebDriver driver = new ChromeDriver();

        // Basic Auth credentials URL me pass kiye
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");

        // Verification (page text check)
        String text = driver.findElement(By.tagName("p")).getText();

        if (text.contains("Congratulations")) {
            System.out.println("✅ Basic Auth handled successfully");
        } else {
            System.out.println("❌ Authentication failed");
        }

        // driver.quit(); // optional
    }
}
