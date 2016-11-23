package task_7;

import core.Act;
import core.Locator;
import core.UI;
import custom_driver_factory.CustomDriverFactory;
import custom_driver_factory.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AllAdminMenusCheck {

    Act act = new Act();

    @BeforeClass
    public void beforeClass(){
        act.setDriver(CustomDriverFactory.getDriver(Driver.CHROME))
                .goTo("http://localhost/litecart")
                .setCookie(new Cookie("remember_me", "admin%3Ad8bcbe282b74952a8f4399be154cda5247815692"))
                .setCookie(new Cookie("LCSESSID", "meha6tiu4g606btg886t0p9a16"))
                .goTo("http://localhost/litecart/admin")
                .getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void afterClass(){
        act.getDriver().quit();
    }

    @Test
    public void check_all_elements_sub_elements_in_main_menu(){

        String [] mainMenuText = {"Appearence", "Catalog", "Countries"};
        List<WebElement> mainMenuElements = act.findElements(By.xpath(UI.MAIN_MENU_ALL_MAIN_ELEMENTS));
        AssertJUnit.assertTrue("Some elements of main menu are missed!", mainMenuElements.size() == 17);

        for (String text : mainMenuText){

            Integer size = act.click(By.xpath("//span[text()='" + text + "']"))
                    .findElements(By.xpath("//li[@class='selected']//li")).size();
            for (int i = 1; i <= size; i++){
                act.click(By.xpath("//span[text()='" + text + "']/li[1]"))
            }

            System.err.println("    MENU: " + text);
            System.err.println("SUB MENU: " + size);

        }

    }

}
