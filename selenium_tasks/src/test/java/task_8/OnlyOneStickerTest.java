package task_8;

import core.Action;
import core.Type;
import custom_driver_factory.CustomDriverFactory;
import custom_driver_factory.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class OnlyOneStickerTest {

    Action act = new Action();

    @BeforeClass
    public void beforeClass(){
        act.setDriver(CustomDriverFactory.getDriver(Driver.CHROME))
                .goTo("http://localhost/litecart")
                .setCookie(new Cookie("remember_me", "admin%3A6729e8325050d63125fc5622cbf3b25c7c5f4d2c"))
                .setCookie(new Cookie("LCSESSID", "qh4vuvfh25psed6r990m8677s0"))
                .goTo("http://localhost/litecart/en/")
                .getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @AfterClass
    public void afterClass(){
        act.getDriver().quit();
    }

    @Test
    public void only_one_sticker_per_product(){

        Integer allProducts = act.findElements(Type.XPATH, "//li[@class='product column shadow hover-light']").size();

        for (int i = 1; i <= allProducts; i++ ){
            WebElement currentProduct = act.getDriver().findElement(By.xpath("(//li[@class='product column shadow hover-light'])[" + i + "]"));
            String productName = currentProduct.findElement(By.xpath(".//div[@class='name']")).getText();
            String productSticker = currentProduct.findElement(By.xpath(".//*[contains(@class,'sticker')]")).getText();
            Integer stickersCount = currentProduct.findElements(By.xpath(".//*[contains(@class,'sticker')]")).size();

            AssertJUnit.assertTrue("Product '" + productName +  "' has more than 1 sticker!", stickersCount == 1 );

            System.err.println("PRODUCT: " + productName);
            System.err.println("STICKER: " + productSticker);
            System.err.println("");
        }
    }

}
