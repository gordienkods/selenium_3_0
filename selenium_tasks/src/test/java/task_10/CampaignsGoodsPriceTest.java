package task_10;

import core.Action;
import core.Type;
import core.UI;
import core.URL;
import custom_driver_factory.CustomDriverFactory;
import custom_driver_factory.Driver;
import org.openqa.selenium.Cookie;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
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
        HashMap<String, String> firstProductInCampaignExpValues = initFirstProductInCampaignsExpValues();
        HashMap<String, String> productDetailInfoExpValues = initProductDetailInfoExpValues();
        HashMap<String, String> firstProductInCampaignActValues;
        HashMap<String, String> productDetailInfoActValues;


        firstProductInCampaign.setMainPageProductName(act.ui(Type.XPATH, UI.FIRST_ITEM_IN_CAMPAIGNS_GOODS_LIST + UI.CONTAINER_WITH_ITEM_NAME).getText());
        firstProductInCampaign.setOldPrice(act.ui(Type.XPATH, UI.FIRST_ITEM_IN_CAMPAIGNS_GOODS_LIST + UI.OLD_PRICE).getText());
        firstProductInCampaign.setOldPriceColor(act.getCssValue("color"));
        firstProductInCampaign.setOldPriceTextDecoration(act.getCssValue("text-decoration"));
        firstProductInCampaign.setOldPriceFontSize(act.getCssValue("font-size"));

        firstProductInCampaign.setNewPrice(act.ui(Type.XPATH, UI.FIRST_ITEM_IN_CAMPAIGNS_GOODS_LIST + UI.NEW_PRICE).getText());
        firstProductInCampaign.setNewPriceColor(act.getCssValue("color"));
        firstProductInCampaign.setNewPriceFontWeight(act.getCssValue("text-decoration"));
        firstProductInCampaign.setNewPriceFontSize(act.getCssValue("font-size"));

        act.ui(Type.XPATH, UI.FIRST_ITEM_IN_CAMPAIGNS_GOODS_LIST).click();

        productDetailInfo.setProductName(act.ui(Type.XPATH, UI.DETEIL_PAGE_HEADER).getText());
        productDetailInfo.setOldPrice(act.ui(Type.XPATH, UI.OLD_PRICE).getText());
        productDetailInfo.setOldPriceColor(act.getCssValue("color"));
        productDetailInfo.setOldPriceTextDecoration(act.getCssValue("text-decoration"));
        productDetailInfo.setOldPriceFontSize(act.getCssValue("font-size"));

        productDetailInfo.setNewPrice(act.ui(Type.XPATH, UI.NEW_PRICE).getText());
        productDetailInfo.setNewPriceColor(act.getCssValue("color"));
        productDetailInfo.setNewPriceFontWeight(act.getCssValue("font-weight"));
        productDetailInfo.setNewPriceFontSize(act.getCssValue("font-size"));

        firstProductInCampaignActValues = firstProductInCampaign.toHashMap();
        for (Map.Entry<String,String> entry : firstProductInCampaignExpValues.entrySet()){
            System.err.println("KEY: " + entry.getKey() + "\nEXP: " + entry.getValue() + "\nACT: " + firstProductInCampaignActValues.get(entry.getKey()));
            AssertJUnit.assertEquals(entry.getValue() ,firstProductInCampaignActValues.get(entry.getKey()));
        }

        productDetailInfoActValues = productDetailInfo.toHashMap();
        for (Map.Entry<String,String> entry : productDetailInfoExpValues.entrySet()){
            System.err.println("KEY: " + entry.getKey() + "\nEXP: " + entry.getValue() + "\nACT: " + productDetailInfoActValues.get(entry.getKey()));
            AssertJUnit.assertEquals(entry.getValue(), productDetailInfoActValues.get(entry.getKey()));
        }
    }

    private HashMap<String,String> initFirstProductInCampaignsExpValues(){
        HashMap<String, String> result = new HashMap<>();
        result.put("PRODUCT_NAME","Yellow Duck");
        result.put("OLD_PRICE","$20");
        result.put("NEW_PRICE","$18");
        result.put("OLD_PRICE_FONT_SIZE","14.4px");
        result.put("OLD_PRICE_TEXT_DECORATION","line-through");
        result.put("OLD_PRICE_COLOR","rgba(119, 119, 119, 1)");
        result.put("NEW_PRICE_COLOR","rgba(204, 0, 0, 1)");
        result.put("NEW_PRICE_FONT_SIZE","18px");
        result.put("NEW_PRICE_FONT_WEIGHT","none");
        return result;
    }

    private HashMap<String,String> initProductDetailInfoExpValues(){
        HashMap<String, String> result = new HashMap<>();
        result.put("PRODUCT_NAME","Yellow Duck");
        result.put("OLD_PRICE","$20");
        result.put("NEW_PRICE","$18");
        result.put("OLD_PRICE_FONT_SIZE","16px");
        result.put("OLD_PRICE_TEXT_DECORATION","line-through");
        result.put("OLD_PRICE_COLOR","rgba(102, 102, 102, 1)");
        result.put("NEW_PRICE_COLOR","rgba(204, 0, 0, 1)");
        result.put("NEW_PRICE_FONT_SIZE","22px");
        result.put("NEW_PRICE_FONT_WEIGHT","bold");
        return result;
    }

}
