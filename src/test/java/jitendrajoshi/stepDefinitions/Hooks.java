package jitendrajoshi.stepDefinitions;

import java.io.IOException;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import rahulshttyacademy.testcomponent.basetest;

public class Hooks extends basetest {

	  @Before
	    public void beforeScenario() throws IOException {
	        driver = initializeDriver();
	        System.out.println("✅ DRIVER INITIALIZED");
	    }

	    @After
	    public void afterScenario() {
	        if (driver != null) {
	            driver.quit();
	            System.out.println("✅ DRIVER QUIT");
	        }
    }
}
