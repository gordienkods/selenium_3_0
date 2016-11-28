package task_9;

import core.Action;
import core.Type;
import custom_driver_factory.CustomDriverFactory;
import custom_driver_factory.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CountriesOrderAndGeoZonesTes {

    Action act = new Action();

    @BeforeClass
    public void beforeClass(){
        act.setDriver(CustomDriverFactory.getDriver(Driver.CHROME))
                .goTo("http://localhost/litecart")
                .setCookie(new Cookie("remember_me", "admin%3Ad8bcbe282b74952a8f4399be154cda5247815692"))
                .setCookie(new Cookie("LCSESSID", "meha6tiu4g606btg886t0p9a16"))
                .goTo("http://localhost/litecart/admin/?app=countries&doc=countries")
                .getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @AfterClass
    public void afterClass(){
        act.getDriver().quit();
    }

//    @Test
//    public void countries_alphabet_order_tes(){
//        Integer allCountriesLines = act.findElements(By.xpath("//table[@class='dataTable']//tr[@class='row']")).size();
//        List<String> actCountriesOrder = new ArrayList<>();
//        List<String> expCountriesOrder = new ArrayList<>();
//
//        for (int i = 1; i <= allCountriesLines; i++){
//            actCountriesOrder.add(act.getText(By.xpath("//table[@class='dataTable']//tr[@class='row'][" + i + "]//td[5]")));
//            expCountriesOrder.add(act.getText(By.xpath("//table[@class='dataTable']//tr[@class='row'][" + i + "]//td[5]")));
//        }
//
//        sortByAlphabet(expCountriesOrder);
//
//        for (int i = 0; i < allCountriesLines; i++){
//            System.err.println("UNSORTED: " + actCountriesOrder.get(i) + "  SORTED: " + expCountriesOrder.get(i));
//            AssertJUnit.assertEquals(expCountriesOrder.get(i), actCountriesOrder.get(i));
//        }
//    }

    @Test
    public void zones_in_alphabet_order_test(){
        Integer allCountriesLines = act.findElements(Type.XPATH, "//table[@class='dataTable']//tr[@class='row']").size();
        List<String> actZonesOrder = new ArrayList<>();
        List<String> expZonesOrder = new ArrayList<>();

        for (int i = 1; i <= allCountriesLines; i++){
            Integer zonesCount = Integer.parseInt(act.findElement(Type.XPATH,
                                         "//table[@class='dataTable']//tr[@class='row'][" + i + "]//td[6]").getText());
            if(zonesCount > 0){
                Integer allZonesLines =
                        act.findElement(Type.XPATH, "//table[@class='dataTable']//tr[@class='row'][" + i + "]//td[5]//a")
                            .click()
                            .findElements(Type.XPATH, "//table[@id='table-zones']//tr")
                            .size();

                for (int j = 2; j < allZonesLines; j++ ){
                    actZonesOrder.add(act.findElement(Type.XPATH, "//table[@id='table-zones']//tr[" + j + "]//td[3]/input").getAttribute("value"));
                    expZonesOrder.add(act.getAttribute("value"));

                    System.err.println(act.getAttribute("value"));
                }
                System.err.println("EXP SIZE " + expZonesOrder.size());
                sortByAlphabet(expZonesOrder);
                System.err.println("EXP SIZE " + expZonesOrder.size());

                System.err.println("------");
                for (String s : actZonesOrder){
                    System.err.println(s);
                }
                System.err.println("------");

                for (int j = 0; j < expZonesOrder.size(); j++){
                    System.err.println("UNSORTED: " + actZonesOrder.get(j) + "  SORTED: " + expZonesOrder.get(j));
                    AssertJUnit.assertEquals(expZonesOrder.get(j), actZonesOrder.get(j));
                }
                act.getDriver().navigate().back();
            }

        }
    }

    private void sortByAlphabet(List<String> unsortedList){
        Collections.sort(unsortedList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }

}
