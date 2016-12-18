package task_19.pages;

import core.Type;
import core.UI;
import core.URL;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import task_19.app.CommonPage;
import java.util.concurrent.TimeUnit;

public class ProductPage extends CommonPage {

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[text()='Add To Cart']")
    public WebElement addToCartButton;

    @FindBy(xpath = "//a[text()='Checkout »']")
    public WebElement checkoutUrlUnderChartCounter;

    public ProductPage open(){
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        goTo(URL.MAIN_COSTUMER_URL)
            .setCookie(new Cookie("customer_remember_me", "user%40mail.ru%3A85d49b55242827c56db6772a6e7db12389dfb713"))
            .setCookie(new Cookie("LCSESSID", "mnp3q7j2r6ipbcle7f60002oc0"))
            .goTo("http://localhost/litecart/en/rubber-ducks-c-1/purple-duck-p-5");
        return this;
    }

    public ProductPage addCurrentProductToCart(Integer count){
        for (Integer i = 1; i <= count; i++) {
            addToCartButton.click();
            waitWhileTextNotPresentInElement(Type.XPATH, UI.PRODUCTS_QUANTITY_IN_CHART, i.toString());
        }
        return this;
    }

}
