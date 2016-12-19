package task_7;

import core.Action;
import core.Type;
import core.UI;
import core.URL;
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

public class AllAdminMenuCheck {

    Action act = new Action();

    @BeforeClass
    public void beforeClass(){
        act.setDriver(CustomDriverFactory.getDriver(Driver.CHROME))
                .goTo(URL.ADMIN)
                .getDriver().manage().deleteAllCookies();
        act.setCookie(new Cookie("remember_me", "admin%3Ae15e89d601724eb060830c711804950d1dae3218"))
                .setCookie(new Cookie("LCSESSID", "rd8pl80fjde6f2k8vjc7p0r947"))
                .goTo(URL.ADMIN)
                .getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }
    @AfterClass
    public void afterClass(){
        act.getDriver().quit();
    }

    @Test
    public void check_all_elements_sub_elements_in_main_menu(){
        String actPageHeader, expPageHeader;

        Integer mainMenuElements = act.getDriver().findElements(By.xpath(UI.MAIN_MENU_ALL_MAIN_ELEMENTS)).size();
        AssertJUnit.assertTrue("Some elements of main menu are missed!", mainMenuElements == 17);
        for (int i = 0; i < mainMenuElements; i++){
            WebElement mainMenuElement = act.getDriver().findElements(By.xpath(UI.MAIN_MENU_ALL_MAIN_ELEMENTS)).get(i);
            System.err.println("    MENU: " + mainMenuElement.getText());
            mainMenuElement.click();

            Integer subMenuElements = act.getDriver().findElements(By.xpath(UI.MAIN_MENU_ALL_MAIN_ELEMENTS)).get(i)
                                                     .findElements(By.xpath(UI.MAIN_MENU_ALL_MAIN_ELEMENTS + UI.SUB_ELEMENTS_IN_LEFT_MENNU)).size();
            for (int j = 0; j < subMenuElements; j++){
                WebElement subMenuElement = act.getDriver().findElements(By.xpath(UI.MAIN_MENU_ALL_MAIN_ELEMENTS)).get(i)
                                                           .findElements(By.xpath(UI.MAIN_MENU_ALL_MAIN_ELEMENTS + UI.SUB_ELEMENTS_IN_LEFT_MENNU)).get(j);
                expPageHeader = subMenuElement.getText();
                subMenuElement.click();
                actPageHeader = act.ui(Type.XPATH, ".//*[@id='content']/h1").getText();
//                AssertJUnit.assertEquals(doMapMainMenuHeaderToPageHeader(expPageHeader), actPageHeader);
                System.err.println(" SUB MENU: " + expPageHeader);
            }
        }

    }

}
