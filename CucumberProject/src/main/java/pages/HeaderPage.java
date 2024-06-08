package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import base.PageContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class HeaderPage {
    
    PageContext context;
    By loginButtonLocator = By.xpath("//span[contains(.,'Login')]");
    By searchTextBoxLocator = By.cssSelector("input[type='search']");
    By searchOptLocator = By.cssSelector("span.mat-option-text");
    By snackBarLocator = By.tagName("snack-bar-container");
    By noOfBooksLocator = By.cssSelector("#mat-badge-content-0");
    
    
    public HeaderPage( PageContext context) {
      this.context = context;
    }

    @And("User click on the login link")
    public void userClicksOnTheLoginLink() {
        context.getDriver().findElement(loginButtonLocator).click();
    }
    
    @Given("user search for a {string}")
    public void userSearchForA(String bookname) {
        WebElement search =  context.getDriver().findElement(searchTextBoxLocator);
        context.getWait().until(ExpectedConditions.visibilityOf(search));
        search.sendKeys(bookname);
        WebElement searchOption =
                context.getDriver().findElement(searchOptLocator);
        WebElement options =
                context.getWait().until(ExpectedConditions.visibilityOf(searchOption));
        options.click();
    }
    
    @Then("the cart badge should get updated")
    public void theCartBadgeShouldGetUpdated() {
        WebElement snackBar = context.getDriver().findElement(snackBarLocator);
        context.getWait().until(ExpectedConditions.visibilityOf(snackBar));
        context.getWait().until(ExpectedConditions.invisibilityOf(snackBar));
        String text =
                context.getDriver().findElement(noOfBooksLocator).getText();
        System.out.println("No.of books in cart: "+text);
        Assert.assertEquals(Integer.parseInt(text) > 0, true);
    }
}
