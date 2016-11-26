package core;

import org.openqa.selenium.*;

import java.util.List;

public class Act {

    private WebDriver driver = null;

    public Act setDriver(WebDriver driver) {
        if (driver != null) {
            this.driver = driver;
        }
        return this;
    }

    public WebDriver getDriver() {
        if (driver != null) {
            return driver;
        }
        throw new CoreException("No created driver!");
    }

    public Act goTo(String url){
        if ( !"(1920, 1080)".equals(this.driver.manage().window().getSize().toString())) {
            Dimension dimension = new Dimension(1920, 1080);
            this.driver.manage().window().setSize(dimension);
        }
        driver.get(url);
        return this;
    }

    public Act click(By by){
        findElement(by).click();
        return this;
    }

    public List<WebElement> findElements_(By by){
       return findElements(by);
    }

    public List<WebElement> findElements(By by){
       return driver.findElements(by);
    }

    public String getAttribute(By by, String attribute){
        return findElement(by).getAttribute(attribute);
    }

    public Act setCookie(Cookie cookie){
        driver.manage().addCookie(cookie);
        return this;
    }

    public WebElement findElement(By by){
        return driver.findElement(by);
    }

    public String getText(By by){
        return findElement(by).getText();
    }

    public void waitABit(Integer timeOut){
        try {
            Thread.sleep(timeOut);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }


}


