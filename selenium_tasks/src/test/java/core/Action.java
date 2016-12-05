package core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import static core.Type.CSS;

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

    public Action sendKeysToElement(String text){
        driver.findElement(getElementByLocatorType(customWebElement.getType(),customWebElement.getLocator())).sendKeys(text);
        return this;
    }

    public List<WebElement> findElements(Type type, String locator){
       return driver.findElements(getElementByLocatorType(type, locator));
    }

    public Action setCookie(Cookie cookie){
        driver.manage().addCookie(cookie);
        return this;
    }

    public Action ui(Type type, String locator){
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

    public Action setAttribute(String attribute, String value){
        if (customWebElement.getType().equals(CSS)) {
            String js = "$('" + customWebElement.getLocator() + "').attr(\"" + attribute + "\", \"" + value + "\");";
            ((JavascriptExecutor) driver).executeScript(js);
            return this;
        }
        throw new CoreException("Yuo can't set value into element using xpath!");
    }

    public String getCssValue(String attribute){
        return driver.findElement(getElementByLocatorType(customWebElement.getType(),
                customWebElement.getLocator())).getCssValue(attribute);
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

    public Action select(String value){
        Select select = new Select(driver.findElement(
                        getElementByLocatorType(customWebElement.getType(), customWebElement.getLocator())));
        select.selectByValue(value);
        return this;
    }

    public Action sendKeys(String text){
       new Actions(driver).sendKeys(text).perform();
       return this;
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


