package task_19.app;

import core.CoreException;
import core.CustomWebElement;
import core.Type;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static core.Type.CSS;
import static core.Type.XPATH;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

public class CommonPage {

    protected WebDriver driver = null;
    private CustomWebElement customWebElement = null;
    private final Integer WAIT = 15;
    private Set<String> oldWindows;
    private String mainWindowId = null;

    public CommonPage (WebDriver driver){
        this.driver = driver;
    }

    public CommonPage setDriver(WebDriver driver) {
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

    public CommonPage goTo(String url){
        if ( !"(1920, 1080)".equals(this.driver.manage().window().getSize().toString())) {
            Dimension dimension = new Dimension(1920, 1080);
            this.driver.manage().window().setSize(dimension);
        }
        driver.get(url);
        return this;
    }

    public CommonPage click(WebElement webElement){
        webElement.click();
        return this;
    }

    public CommonPage sendKeysToElement(String text){
        driver.findElement(getElementByLocatorType(customWebElement.getType(),customWebElement.getLocator())).sendKeys(text);
        return this;
    }

    public List<WebElement> findElements(Type type, String locator){
       return driver.findElements(getElementByLocatorType(type, locator));
    }

    public CommonPage setCookie(Cookie cookie){
        driver.manage().addCookie(cookie);
        return this;
    }

    public CommonPage ui(Type type, String locator){
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

    public String getAttribute(Type type, String locator, String attribute){
        return driver.findElement(getElementByLocatorType(type,
                locator)).getAttribute(attribute);
    }

    public CommonPage setAttribute(String attribute, String value){
        if (customWebElement.getType().equals(CSS)) {
            String js = "$('" + customWebElement.getLocator() + "').attr(\"" + attribute + "\", \"" + value + "\");";
            ((JavascriptExecutor) driver).executeScript(js);
            return this;
        }
        throw new CoreException("Yuo can't set value into element using xpath!");
    }

    public CommonPage setAttribute(String cssLocator, String attribute, String value){
        String js = "$('" + cssLocator + "').attr(\"" + attribute + "\", \"" + value + "\");";
        ((JavascriptExecutor) driver).executeScript(js);
        return this;
    }

    public String getCssValue(String attribute){
        return driver.findElement(getElementByLocatorType(customWebElement.getType(),
                customWebElement.getLocator())).getCssValue(attribute);
    }

    public CommonPage waitABit(Integer timeOut){
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

    public CommonPage select(String value){
        Select select = new Select(driver.findElement(
                        getElementByLocatorType(customWebElement.getType(), customWebElement.getLocator())));
        select.selectByValue(value);
        return this;
    }

    public CommonPage sendKeys(String text){
       new Actions(driver).sendKeys(text).perform();
       return this;
    }

    public CommonPage waitWhileTextNotPresentInElement(String text){
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(textToBePresentInElementLocated(
                getElementByLocatorType(customWebElement.getType(), customWebElement.getLocator()),

                text));
        return this;
    }

    public CommonPage waitWhileTextNotPresentInElement(Type type, String locator ,String text){
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(textToBePresentInElementLocated(
                getElementByLocatorType(type, locator),
                text));
        return this;
    }

    public Boolean isTexPresentOnPage(String text){
        if (findElements(XPATH,"//*[contains(text(),'" + text + "')]").size() > 0){
            return true;
        } else {
            return false;
        }
    }

    public CommonPage setCurrentWindows(){
        mainWindowId = driver.getWindowHandle();
        oldWindows = driver.getWindowHandles();
        return this;
    }

    public CommonPage switchToNewWindow(){
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(numberOfWindowsToBe(oldWindows.size() + 1));
        Iterator<String> iteratorNewWindows = driver.getWindowHandles().iterator();

        while (iteratorNewWindows.hasNext()){
            String windowId = iteratorNewWindows.next();
            if (!oldWindows.contains(windowId)){
                driver.switchTo().window(windowId);
                return this;
            }
        }
        throw new CoreException("No new window found!");
    }

    public CommonPage switchToMainWindow(){
        driver.switchTo().window(mainWindowId);
        return this;
    }

    public CommonPage closeCurrentWindow() {
        driver.close();
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


