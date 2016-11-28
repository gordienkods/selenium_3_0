package core;

import org.openqa.selenium.*;
import java.util.List;

public class Action {

    private WebDriver driver = null;
    private CustomWebElement customWebElement = null;

    public Action setDriver(WebDriver driver) {
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

    public Action goTo(String url){
        if ( !"(1920, 1080)".equals(this.driver.manage().window().getSize().toString())) {
            Dimension dimension = new Dimension(1920, 1080);
            this.driver.manage().window().setSize(dimension);
        }
        driver.get(url);
        return this;
    }

    public Action click(){
        driver.findElement(getElementByLocatorType(customWebElement.getType(),customWebElement.getLocator())).click();
        return this;
    }


    public List<WebElement> findElements(Type type, String locator){
       return driver.findElements(getElementByLocatorType(type, locator));
    }

    public Action setCookie(Cookie cookie){
        driver.manage().addCookie(cookie);
        return this;
    }

    public Action findElement(Type type, String locator){
        customWebElement = new CustomWebElement();
        customWebElement.setType(type);
        customWebElement.setLocator(locator);
        return this;
    }

    public String getText(){
        return driver.findElement(getElementByLocatorType(customWebElement.getType(), customWebElement.getLocator())).getText();
    }

    public String getAttribute(String attribute){
        return driver.findElement(getElementByLocatorType(customWebElement.getType(),
                customWebElement.getLocator())).getAttribute(attribute);
    }

    public Action waitABit(Integer timeOut){
        try {
            Thread.sleep(timeOut);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return this;
    }

    public Boolean isElementPresent(){
        if (findElements(customWebElement.getType(), customWebElement.getLocator()).size() > 0){
            return true;
        } else {
            return false;
        }
    }

    private By getElementByLocatorType(Type type, String locator){
        switch (type){
            case XPATH: {
                return By.xpath(locator);
            }
            case CSS: {
                return By.cssSelector(locator);
            }
            default: {
                throw new CoreException("No case for locator type!");
            }
        }
    }

}


