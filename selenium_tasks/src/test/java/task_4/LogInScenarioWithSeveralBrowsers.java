package task_4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.selenium.factory.SingleWebDriverPool;
import ru.stqa.selenium.factory.WebDriverPool;
import task_4.custom_driver_factory.CustomDriverFactory;
import task_4.custom_driver_factory.Driver;

import java.util.concurrent.TimeUnit;

/*Задание 4. Научитесь запускать разные браузеры
Попробуйте запустить разработанный ранее сценарий логина во всех основных браузерах, доступных для вашей операционной системы.
Windows -- запустите в Firefox (с использованием geckodriver), Chrome, Internet Explorer (или Edge).*/

public class LogInScenarioWithSeveralBrowsers {

    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
//        driver = CustomDriverFactory.getDriver(Driver.CHROME);
//        driver = CustomDriverFactory.getDriver(Driver.FIREFOX_GEKO_DRIVER);
//        driver = CustomDriverFactory.getDriver(Driver.INTERNET_EXPLORER);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void log_in(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.xpath("//*[text()='Username']/following-sibling::*//input"))
                .sendKeys("admin");
        driver.findElement(By.xpath("//*[text()='Password']/following-sibling::*//input"))
                .sendKeys("admin");
        driver.findElement(By.xpath("//button[text()='Login']"))
                .click();
    }

}
