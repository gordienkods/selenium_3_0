package task_19.pages;

import core.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import task_19.app.CommonPage;
import java.util.concurrent.TimeUnit;

public class MainConsumerPage extends CommonPage {

    public  MainConsumerPage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public MainConsumerPage open(){
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        goTo(URL.MAIN_COSTUMER_URL)
                .setCookie(new Cookie("customer_remember_me", "user%40mail.ru%3A85d49b55242827c56db6772a6e7db12389dfb713"))
                .setCookie(new Cookie("LCSESSID", "mnp3q7j2r6ipbcle7f60002oc0"))
                .goTo(URL.MAIN_COSTUMER_URL);
        return this;
    }

    public MainConsumerPage clickOnProduct(String category, String productName){
        driver.findElement(By.xpath("//h3[text()='" + category + "']/following-sibling::div//a[@title='" + productName + "']/ancestor::li"))
                .click();
        return this;
    }

}
