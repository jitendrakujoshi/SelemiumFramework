package jitendrajoshi.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import jitendrajoshi.pageobject.Landingpage;
import jitendrajoshi.pageobject.ProductCatalogue;
import jitendrajoshi.pageobject.CheckoutPage;
import jitendrajoshi.pageobject.ConfirmationPage;
import rahulshettyacademy.AbstractComponents.OrderPage;
import rahulshttyacademy.testcomponent.basetest;

public class stepDefinationimpl extends basetest {

    public Landingpage landingPage;
    public ProductCatalogue productCatalogue;
    public CheckoutPage cartPage;
    public OrderPage checkoutPage;
    public ConfirmationPage confirmationPage;

    // ---------------- GIVEN ----------------
    @Given("I landed on Ecommerce Page")
    public void i_landed_on_ecommerce_page() throws IOException {
        landingPage = new Landingpage(driver);
        landingPage.goTo();
    }

    @Given("Logged in with username {string} and password {string}")
    public void logged_in_username_and_password(String email, String password) {
        productCatalogue = landingPage.loginApplication(email, password);
    }

    // ---------------- WHEN ----------------
    @When("I add product {string} to Cart")
    public void i_add_product_to_cart(String productName) {
        productCatalogue.addProductToCart(productName);
        checkoutPage = productCatalogue.goToOrderPage();
    }

    @When("Checkout {string} and submit the order")
    public void checkout_and_submit_order(String productName) {
        boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);

        checkoutPage = cartPage.goToCheckout();
        confirmationPage = checkoutPage.submitOrder();
    }

    // ---------------- THEN ----------------
    @Then("{string} message is displayed on confirmationPage")
    public void message_is_displayed_on_confirmation_page(String expectedMessage) {
        String actualMessage = confirmationPage.getConfirmationMessage();
        Assert.assertEquals(actualMessage, expectedMessage);
    }
   

}
