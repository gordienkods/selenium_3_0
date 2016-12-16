package task_17;

import core.Action;
import core.Type;
import core.UI;
import core.URL;
import custom_driver_factory.CustomDriverFactory;
import custom_driver_factory.Driver;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NoMessagesInBrowserLog {

    Action act = new Action();

    @BeforeClass
    public void beforeClass(){
        act.setDriver(CustomDriverFactory.getDriver(Driver.CHROME))
                .goTo(URL.MAIN_URL)
                .setCookie(new Cookie("remember_me", "admin%3Ac363b737ad048e411ae3fdcac0d5774841f1c4c3"))
                .setCookie(new Cookie("LCSESSID", "efste8unpirsgl263gu8tohgh2"))
                .goTo(URL.CATALOG)
                .getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @AfterClass
    public void afterClass(){
        act.getDriver().quit();
    }

    @Test
    public void links_should_open_in_separate_windows(){
        List<WebElement> allProducts = act.findElements(Type.XPATH, UI.ALL_PRODUCTS_IN_CATALOG);

        for (int i = 0; i < allProducts.size(); i++){
            act.findElements(Type.XPATH,UI.ALL_PRODUCTS_IN_CATALOG).get(i).click();
            AssertJUnit.assertTrue("There are messages in browser log!",
                    act.getDriver().manage().logs().get("browser").getAll().size() == 0);
            act.getDriver().navigate().back();
        }
    }

}
