package task_4.custom_driver_factory;

import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class CustomDriverFactory {

    public static WebDriver getDriver(Driver driverType){
        WebDriver driver;
        switch (driverType){
            case INTERNET_EXPLORER : {
                driver = new InternetExplorerDriver();
                System.out.println("IE DRIVER CAPABILITIES:\n" + ((HasCapabilities) driver).getCapabilities());
                return driver;
            }
            case CHROME : {
                driver = new ChromeDriver();
                System.out.println("CHROME DRIVER CAPABILITIES:\n" + ((HasCapabilities) driver).getCapabilities());
                return driver;
            }
            case FIREFOX_GEKO_DRIVER: {
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability(FirefoxDriver.MARIONETTE, true);
                FirefoxBinary bin = new FirefoxBinary(new File("C:\\Program Files (x86)\\GEKO\\Mozilla Firefox\\firefox.exe"));
                driver = new FirefoxDriver(bin, new FirefoxProfile(),caps);
                System.out.println("FIRE FOX DRIVER CAPABILITIES:\n" + ((HasCapabilities) driver).getCapabilities());
                return driver;
            }
            case FIREFOX_NIGHTLY : {
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability(FirefoxDriver.MARIONETTE, true);
                FirefoxBinary bin = new FirefoxBinary(new File("C:\\Users\\Dimon\\Downloads\\firefox-53.0a1.ru.win64\\firefox_nightly\\firefox.exe"));
                driver = new FirefoxDriver(bin, new FirefoxProfile(),caps);
                System.out.println("FIRE FOX DRIVER CAPABILITIES:\n" + ((HasCapabilities) driver).getCapabilities());
                return driver;
            }
        }
        throw new RuntimeException("No driver type to start!");
    }

}
