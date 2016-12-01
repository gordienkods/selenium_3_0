package task_11;

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

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class NewUserRegistration {

    Action act = new Action();

    @BeforeClass
    public void beforeClass(){
        act.setDriver(CustomDriverFactory.getDriver(Driver.CHROME))
                .goTo("http://localhost/litecart")
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
    public void create_new_user(){
        final String email = "email" + System.currentTimeMillis() + "@mail.com";
        final String pass = "111";
        final String name = "vasya" + System.currentTimeMillis();

        act.goTo(URL.ADMIN_CUSTOMERS)
            .findElement(Type.XPATH, UI.ADD_NEW_CUSTOMER_BUTTON).click()
            .findElement(Type.XPATH, UI.EMAIL_ADDRESS_INPUT_ON_ADD_NEW_CUSTOMER_PAGE).sendKeys(email)
            .findElement(Type.XPATH, UI.FIRST_NAME_INPUT_ON_ADD_NEW_CUSTOMER_PAGE).sendKeys(name)
            .findElement(Type.XPATH, UI.PASSWORD_INPUT_ON_ADD_NEW_CUSTOMER_PAGE).sendKeys(pass)
            .findElement(Type.XPATH, UI.SAVE_BUTTON_ON_ADD_NEW_CUSTOMER_PAGE).click()
            .findElement(Type.XPATH, UI.LOG_OUT_BUTTON_IN_ADMIN).click()
            .goTo(URL.MAIN_COSTUMER_URL)
            .findElement(Type.XPATH, UI.USER_NAME_INPUT_ON_INDEX).sendKeys(email)
            .findElement(Type.XPATH, UI.PASSWORD_INPUT_ON_INDEX).sendKeys(pass)
            .findElement(Type.XPATH, UI.LOG_IN_BUTTON).click()
            .waitABit(10_000);
    }

}
