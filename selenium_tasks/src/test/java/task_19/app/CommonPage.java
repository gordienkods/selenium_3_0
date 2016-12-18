package task_19.app;

import core.CoreException;
import core.Type;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import static core.Type.XPATH;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

public class CommonPage {

    protected WebDriver driver = null;
    private final Integer WAIT = 15;

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

    public String getAttribute(Type type, String locator, String attribute){
        return driver.findElement(getElementByLocatorType(type,
                locator)).getAttribute(attribute);
    }

    public CommonPage setAttribute(String cssLocator, String attribute, String value){
        String js = "$('" + cssLocator + "').attr(\"" + attribute + "\", \"" + value + "\");";
        ((JavascriptExecutor) driver).executeScript(js);
        return this;
    }

    public CommonPage waitWhileTextNotPresentInElement(Type type, String locator ,String text){
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(textToBePresentInElementLocated(
                getElementByLocatorType(type, locator),
                text));
        return this;
    }

    public List<WebElement> findElements(Type type, String locator){
        return driver.findElements(getElementByLocatorType(type, locator));
    }

    public CommonPage waitABit(Integer timeOut){
        try {
            Thread.sleep(timeOut);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return this;
    }

    public CommonPage goTo(String url){
        if ( !"(1920, 1080)".equals(this.driver.manage().window().getSize().toString())) {
            Dimension dimension = new Dimension(1920, 1080);
            this.driver.manage().window().setSize(dimension);
        }
        driver.get(url);
        return this;
    }

    public Boolean isTexPresentOnPage(String text){
        if (findElements(XPATH,"//*[contains(text(),'" + text + "')]").size() > 0){
            return true;
        } else {
            return false;
        }
    }

    public CommonPage setCookie(Cookie cookie){
        driver.manage().addCookie(cookie);
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


