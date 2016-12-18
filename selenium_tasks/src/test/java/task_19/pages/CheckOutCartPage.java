package task_19.pages;

import core.Type;
import core.UI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.AssertJUnit;
import task_19.app.CommonPage;

public class CheckOutCartPage extends CommonPage {

    public CheckOutCartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//button[text()='Update']")
    public WebElement updateButton;

    public CheckOutCartPage cleanCart(){
        Integer productCounter = Integer.parseInt(getAttribute(Type.CSS, UI.QUANTITY,"value"));
        for (Integer i = (productCounter - 1); i >= 0; i--) {
            setAttribute(UI.QUANTITY,"value", i.toString());
            updateButton.click();
            if (isTexPresentOnPage("There are no items in your cart")){
                break;
            }
            waitWhileTextNotPresentInElement(Type.XPATH, UI.PRODUCTS_QUANTITY_IN_TABLE, i.toString());
        }
        AssertJUnit.assertTrue(isTexPresentOnPage("There are no items in your cart."));
        return this;
    }

}

