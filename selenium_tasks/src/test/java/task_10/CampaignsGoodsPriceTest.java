package task_10;

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

public class CampaignsGoodsPriceTest {

    Action act = new Action();

    @BeforeClass
    public void beforeClass(){
        act.setDriver(CustomDriverFactory.getDriver(Driver.CHROME))
                .goTo(URL.MAIN_URL)
                .setCookie(new Cookie("remember_me", "admin%3A6729e8325050d63125fc5622cbf3b25c7c5f4d2c"))
                .setCookie(new Cookie("LCSESSID", "qh4vuvfh25psed6r990m8677s0"))
                .goTo(URL.MAIN_COSTUMER_URL)
                .getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @AfterClass
    public void afterClass(){
        act.getDriver().quit();
    }

    @Test
    public void only_one_sticker_per_product(){
        ItemDataHolder firstProductInCampaign = new ItemDataHolder();
        ItemDataHolder productDetailInfo = new ItemDataHolder();
        ItemDataHolder productExpectedInfo = new ItemDataHolder();

//        productExpectedInfo.setProductName("Yellow Duck");


        firstProductInCampaign.setMainPageProductName(act.findElement(Type.XPATH, UI.FIRST_ITEM_IN_CAMPAIGNS_GOODS_LIST + UI.CONTAINER_WITH_ITEM_NAME).getText());
        firstProductInCampaign.setOldPrice(act.findElement(Type.XPATH, UI.FIRST_ITEM_IN_CAMPAIGNS_GOODS_LIST + UI.OLD_PRICE).getText());
        firstProductInCampaign.setOldPriceColor(act.getCssValue("color"));
        firstProductInCampaign.setOldPriceTextDecoration(act.getCssValue("text-decoration"));
        firstProductInCampaign.setOldPriceFontSize(act.getCssValue("font-size"));

        firstProductInCampaign.setNewPrice(act.findElement(Type.XPATH, UI.FIRST_ITEM_IN_CAMPAIGNS_GOODS_LIST + UI.NEW_PRICE).getText());
        firstProductInCampaign.setNewPriceColor(act.getCssValue("color"));
        firstProductInCampaign.setNewPriceFontWeight(act.getCssValue("text-decoration"));
        firstProductInCampaign.setNewPriceFontSize(act.getCssValue("font-size"));

        act.findElement(Type.XPATH, UI.FIRST_ITEM_IN_CAMPAIGNS_GOODS_LIST).click();

        productDetailInfo.setProductName(act.findElement(Type.XPATH, UI.DETEIL_PAGE_HEADER).getText());
        productDetailInfo.setOldPrice(act.findElement(Type.XPATH, UI.OLD_PRICE).getText());
        productDetailInfo.setOldPriceColor(act.getCssValue("color"));
        productDetailInfo.setOldPriceTextDecoration(act.getCssValue("text-decoration"));
        productDetailInfo.setOldPriceFontSize(act.getCssValue("font-size"));

        productDetailInfo.setNewPrice(act.findElement(Type.XPATH, UI.NEW_PRICE).getText());
        productDetailInfo.setNewPriceColor(act.getCssValue("color"));
        productDetailInfo.setNewPriceFontWeight(act.getCssValue("font-weight"));
        productDetailInfo.setNewPriceFontSize(act.getCssValue("font-size"));

        System.err.println(firstProductInCampaign);
        System.err.println(productDetailInfo);
    }

}
