package task_13;

import core.Action;
import core.Type;
import core.UI;
import core.URL;
import custom_driver_factory.CustomDriverFactory;
import custom_driver_factory.Driver;
import org.openqa.selenium.Cookie;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ProductsCartOperations {

    Action act = new Action();


    @BeforeClass
    public void beforeClass(){
        act.setDriver(CustomDriverFactory.getDriver(Driver.CHROME))
                .goTo(URL.MAIN_URL)
                .setCookie(new Cookie("remember_me", "admin%3A6729e8325050d63125fc5622cbf3b25c7c5f4d2c"))
                .setCookie(new Cookie("LCSESSID", "qh4vuvfh25psed6r990m8677s0"))
                .goTo(URL.MAIN_COSTUMER_URL)
                .getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }
    @AfterClass
    public void afterClass(){
        act.getDriver().quit();
    }

    @Test
    public void add_and_remove_product_to_cart(){
        act.ui(Type.XPATH, UI.PRODUCT_FOR_CHART_ADDING).click();

        for (Integer i = 1; i <= 3; i++) {
            act.ui(Type.XPATH, UI.ADD_TO_CART_BUTTON).click()
               .ui(Type.XPATH, UI.PRODUCTS_QUANTITY_IN_CHART).waitWhileTextNotPresentInElement(i.toString());
        }

        act.ui(Type.XPATH, UI.CHECKOUT_URL_UNDER_CHART_COUNTER).click();

        for (Integer i = 2; i >= 0; i--) {
            act.ui(Type.CSS, UI.QUANTITY).setAttribute("value", i.toString())
                    .ui(Type.XPATH, UI.UPDATE_BUTTON).click();
            if (act.isTexPresentOnPage("There are no items in your cart")){
                break;
            }
            act.ui(Type.XPATH, UI.PRODUCTS_QUANTITY_IN_TABLE).waitWhileTextNotPresentInElement(i.toString());
        }
    }

}
