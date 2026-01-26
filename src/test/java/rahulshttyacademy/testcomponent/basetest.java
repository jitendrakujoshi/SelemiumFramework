package rahulshttyacademy.testcomponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import jitendrajoshi.pageobject.Landingpage;

public class basetest {

	protected static WebDriver driver; 
	protected WebDriverWait wait;

    // ✅ THIS METHOD WILL BE CALLED FROM CUCUMBER HOOK
    public WebDriver initializeDriver() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir")
                + "/src/main/java/rahulshettyacademy/resources/GlobalData.properties"
        );
        prop.load(fis);

        String browserName = prop.getProperty("browser");

        if (browserName.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
        }
        else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        return driver;
    }

    public Landingpage launchApplication() {
        Landingpage landingPage = new Landingpage(driver);
        landingPage.goTo();
        return landingPage;
    }

    public String getScreenshot(String methodName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        String destinationPath = System.getProperty("user.dir")
                + "/reports/" + methodName + "_" + System.currentTimeMillis() + ".png";

        FileUtils.copyFile(source, new File(destinationPath));
        return destinationPath;
    }
}
