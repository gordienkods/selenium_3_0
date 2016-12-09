package task_14;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LinksOpenInSeparateWindows {

    Action act = new Action();

    @BeforeClass
    public void beforeClass(){
        act.setDriver(CustomDriverFactory.getDriver(Driver.CHROME))
                .goTo(URL.MAIN_URL)
                .setCookie(new Cookie("remember_me", "admin%3Ad8bcbe282b74952a8f4399be154cda5247815692"))
                .setCookie(new Cookie("LCSESSID", "meha6tiu4g606btg886t0p9a16"))
                .goTo(URL.ADMIN + URL.AFGHANISTAN_EDIT_COUNTRY_PAGE)
                .getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @AfterClass
    public void afterClass(){
        act.getDriver().quit();
    }

    @Test
    public void links_should_open_in_separate_windows(){
        List<String> linksToWiki = new ArrayList<>();
        linksToWiki.add(UI.LINK_CODE_ISO_3166_1_ALPHA_2);
        linksToWiki.add(UI.LINK_ISO_3166_1_ALPHA_3);
        linksToWiki.add(UI.TAX_ID_FORMAT);
        linksToWiki.add(UI.ADDRESS_FORMAT);
        linksToWiki.add(UI.POSTCODE_FORMAT);
        linksToWiki.add(UI.PHONE_COUNTRY_CODE);
        linksToWiki.add(UI.CURRENCY_CODE);

        act.setCurrentWindows();
        for (String locator : linksToWiki){
            String newWindowUrl = act.ui(Type.XPATH, locator)
                                        .click()
                                        .switchToNewWindow()
                                        .getDriver().getCurrentUrl();

            AssertJUnit.assertTrue("Url in new windows doesn't contains expected result!",
                    newWindowUrl.contains(getExpUrl(locator)));

            act.closeCurrentWindow()
                    .switchToMainWindow();
        }
    }

    private String getExpUrl(String linkLocator) {
        HashMap<String, String> result = new HashMap<>();
        result.put(UI.LINK_CODE_ISO_3166_1_ALPHA_2, "ISO_3166-1_alpha-2");
        result.put(UI.LINK_ISO_3166_1_ALPHA_3, "ISO_3166-1_alpha-3");
        result.put(UI.TAX_ID_FORMAT, "Regular_expression");
        result.put(UI.ADDRESS_FORMAT, "/address-format");
        result.put(UI.POSTCODE_FORMAT, "Regular_expression");
        result.put(UI.PHONE_COUNTRY_CODE, "/List_of_country_calling_codes");
        result.put(UI.CURRENCY_CODE, "/List_of_countries_and_capitals_with_currency_and_language");
        return result.get(linkLocator);
    }

}
