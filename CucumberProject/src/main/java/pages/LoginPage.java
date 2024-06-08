package pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import base.PageContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginPage {

    PageContext context;
    By userNameLocator = By.cssSelector("input[formcontrolname='username']");
    By passwordLocator = By.cssSelector("input[formcontrolname='password']");
    By loginLocator = By.cssSelector("(//span[text()='Login'])[last()]/..");
    By loginSuccessMsgLocator = By.cssSelector("//button[contains(@class,'mat-focus-indicator mat-menu-trigger')]//span[1]");
    By loginErrorMsgLocator = By.cssSelector("mat-error[role='alert']");
    
    public LoginPage(PageContext context) {
        this.context = context;        
    }

    @Given("User enter the username as {string}")
    public LoginPage userEnterTheUsernameAs(String username) {
        context.getDriver().findElement(userNameLocator).
        sendKeys(username);
        return this;
    }

    @Given("User enter the password as {string}")
    public LoginPage userEnterThePasswordAs(String password) {
        context.getDriver().findElement(passwordLocator).
        sendKeys(password);
        return this;
    }

    @When("User click on the login button")
    public HomePage userClickOnTheLoginButton() {
        context.getDriver().findElement(loginLocator)
        .click();
        return new HomePage(context);
    }

    @Then("Login should be success")
    public HomePage loginShouldBeSuccess() {
        WebElement userEle = context.getDriver().findElement(loginSuccessMsgLocator);
        context.getWait().until(ExpectedConditions.visibilityOf(userEle));
        assertEquals(userEle.isDisplayed(), true);
        return new HomePage(context);
    }

    @When("Login should fail")
    public LoginPage loginShouldFail() {
        String text =
                context.getDriver().findElement(loginErrorMsgLocator).getText();
        Assert.assertEquals(text.trim(), "Username or Password is incorrect.");
        return this;
    }

    @Given("user login into the application with {string} and {string}")
    public void userLoginIntoTheApplicationWithAnd(String uname, String pass) {
        new HeaderPage(context)
        .userClicksOnTheLoginLink();
        this.userEnterTheUsernameAs(uname)
        .userEnterThePasswordAs(pass)
        .userClickOnTheLoginButton();
        this.loginShouldBeSuccess();
        /*
         * this.userEnterTheUsernameAs(uname);
         * this.userEnterThePasswordAs(pass);
         * this.userClickOnTheLoginButton();
         */
    }

}
