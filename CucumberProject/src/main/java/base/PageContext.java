package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageContext {

    public WebDriver getDriver() {
        return driver;
    }
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
    public WebDriverWait getWait() {
        return wait;
    }
    public void setWait(WebDriverWait wait) {
        this.wait = wait;
    }
    private  WebDriver driver;
    private  WebDriverWait wait;
    
    
    


}
