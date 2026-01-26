package jitendrajoshi;

import org.testng.Assert;
import org.testng.annotations.Test;


import jitendrajoshi.pageobject.Landingpage;
import rahulshttyacademy.testcomponent.basetest;

public class ErrorMessage extends basetest {

    @Test
    public void Loginvalidation() {

    	
    	
        // ✅ Proper way to open application
        Landingpage landingpage = launchApplication();

        // ❌ Wrong credentials (to validate error message)
        landingpage.loginApplication("jitu@gmail.com", "Stu@12345");

        // ✅ Assertion
        Assert.assertEquals(
                landingpage.getErrorMessage(),
                "Incorrect email or password."
        );
    }
}
