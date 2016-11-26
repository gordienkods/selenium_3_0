package task_7;

import core.Act;
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
        String [] mainMenuHeaders = {"Appearence", "Catalog", "Countries", "Currencies", "Customers", "Geo Zones",
                                     "Languages", "Modules", "Orders", "Pages", "Reports", "Settings", "Slides", "Tax",
                                     "Translations", "Users", "vQmods"};
        String actPageHeader, expPageHeader;

        List<WebElement> mainMenuElements = act.findElements(By.xpath(UI.MAIN_MENU_ALL_MAIN_ELEMENTS));
        AssertJUnit.assertTrue("Some elements of main menu are missed!", mainMenuElements.size() == 17);
        for (String menuHeader : mainMenuHeaders){
            Integer size = act.click(By.xpath("//a//span[text()='" + menuHeader + "']")).
                    findElements(By.xpath("//a//span[text()='" + menuHeader + "']/ancestor::li//li")).size();

            for (int i = 1; i <= size; i++){
                expPageHeader = act.click(By.xpath("//a//span[text()='" + menuHeader + "']/ancestor::li//li[" + i + "]"))
                                    .getText(By.xpath("//a//span[text()='" + menuHeader + "']/ancestor::li//li[" + i + "]"));
                actPageHeader = act.findElement(By.xpath(".//*[@id='content']/h1")).getText();
                AssertJUnit.assertEquals(doMapMainMenuHeaderToPageHeader(expPageHeader), actPageHeader);
            }

            System.err.println("    MENU: " + menuHeader);
            System.err.println("SUB MENU: " + size);

        }

    }

    public String doMapMainMenuHeaderToPageHeader(String mainMenuHeader){
        switch (mainMenuHeader){
            case "Background Jobs" : {
                return "Job Modules";
            }
            case "Customer" : {
                return "Customer Modules";
            }
            case "Shipping" : {
                return "Shipping Modules";
            }
            case "Payment" : {
                return "Payment Modules";
            }
            case "Order Total" : {
                return "Order Total Modules";
            }
            case "Order Success" : {
                return "Order Success Modules";
            }
            case "Order Action" : {
                return "Order Action Modules";
            }
            case "Store Info" : {
                return "Settings";
            }
            case "Defaults" : {
                return "Settings";
            }
            case "General" : {
                return "Settings";
            }
            case "Listings" : {
                return "Settings";
            }
            case "Images" : {
                return "Settings";
            }
            case "Checkout" : {
                return "Settings";
            }
            case "Advanced" : {
                return "Settings";
            }
            case "Security" : {
                return "Settings";
            }
            case "Scan Files" : {
                return "Scan Files For Translations";
            }
            default : {
                return mainMenuHeader;
            }
        }
    }

}
