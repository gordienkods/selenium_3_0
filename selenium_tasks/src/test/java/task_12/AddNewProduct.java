package task_12;

import core.*;
import custom_driver_factory.CustomDriverFactory;
import custom_driver_factory.Driver;
import org.openqa.selenium.Cookie;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import static core.Type.CSS;
import static core.Type.XPATH;

public class AddNewProduct {

    Action act = new Action();

    @BeforeClass
    public void beforeClass(){
        act.setDriver(CustomDriverFactory.getDriver(Driver.CHROME))
                .goTo(URL.MAIN_URL)
                .setCookie(new Cookie("remember_me", "admin%3Ad8bcbe282b74952a8f4399be154cda5247815692"))
                .setCookie(new Cookie("LCSESSID", "meha6tiu4g606btg886t0p9a16"))
                .goTo(URL.ADMIN)
                .getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @AfterClass
    public void afterClass(){
        act.getDriver().quit();
    }

    @Test
    public void add_new_product(){
        final String PRODUCT_NAME = "smile" + System.currentTimeMillis();
        final String CODE = String.valueOf(System.currentTimeMillis());

        act.ui(XPATH, UI.CATALOG_IN_LEFT_MENU).click()
                .ui(XPATH, UI.ADD_NEW_PRODUCT_BUTTON).click()
                .ui(XPATH, UI.ENABLED_STATUS_RADIO_BUTTON).click()
                .ui(XPATH, UI.PRODUCT_NAME).sendKeysToElement(PRODUCT_NAME)
                .ui(XPATH, UI.CODE).sendKeysToElement(CODE)
                .ui(XPATH, UI.RUBBER_DUCKS_CATEGORY).click()
                .ui(XPATH, UI.PRODUCT_GROUPS_MALE).click()
                .ui(CSS, UI.QUANTITY).setAttribute("value", "100")
                .ui(XPATH, UI.UPLOAD_IMAGES_INPUT).sendKeysToElement(Tools.getAbsoluteFilePath("src\\main\\resources\\smile.jpg"))
                .ui(CSS, UI.DATE_VLID_FROM_INPUT).setAttribute("value","2016-12-05")
                .ui(CSS, UI.DATE_VLID_TO_INPUT).setAttribute("value","2016-12-15")
                .ui(XPATH, UI.INFORMATION_TAB).click()
                .ui(XPATH, UI.MANUFACTURER_SELECT).select("1")
                .ui(XPATH, UI.KEYWORDS_INPUT).sendKeysToElement("smile")
                .ui(XPATH, UI.SHORT_DESCRIPTION_INPUT).sendKeysToElement("funny smile :)")
                .ui(XPATH, UI.DESCRIPTION_FIELD).click().waitABit(1000).sendKeys("yellow funny smile :)")
                .ui(XPATH, UI.HEAD_TITLE_INPUT).sendKeysToElement("head_title_smile")
                .ui(XPATH, UI.META_DESCRIPTION_INPUT).sendKeysToElement("meta_description_smile")
                .ui(XPATH, UI.PRICES_TAB).click()
                .ui(CSS, UI.PURCHASE_PRICE_INPUT).setAttribute("value", "10")
                .ui(XPATH, UI.PURCHASE_PRICE_SELECT).select("USD")
                .ui(CSS, UI.PRICE_INPUT_USD).setAttribute("value", "1")
                .ui(CSS, UI.PRICE_INCLU_TAX_USD_INPUT).setAttribute("value", "1.50")
                .ui(CSS, UI.PRICE_INPUT_EUR).setAttribute("value", "1")
                .ui(CSS, UI.PRICE_INCLU_TAX_EUR_INPUT).setAttribute("value", "1.50")
                .ui(XPATH, UI.SAVE_BUTTON).click();

                AssertJUnit.assertTrue("Product wasn't created!",
                        act.ui(XPATH, "//*[contains(text(),'" + PRODUCT_NAME + "')]").isElementPresent());
    }


}
