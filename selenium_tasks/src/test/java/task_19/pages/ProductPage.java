package task_19.pages;

import core.Type;
import core.UI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import task_19.app.CommonPage;

public class ProductPage extends CommonPage {

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[text()='Add To Cart']")
    public WebElement addToCartButton;

    @FindBy(xpath = "//a[text()='Checkout »']")
    public WebElement checkoutUrlUnderChartCounter;

    public ProductPage addCurrentProductToCart(Integer count){
        for (Integer i = 1; i <= count; i++) {
            addToCartButton.click();
            waitWhileTextNotPresentInElement(Type.XPATH, UI.PRODUCTS_QUANTITY_IN_CHART, i.toString());
        }
        return this;
    }

}
