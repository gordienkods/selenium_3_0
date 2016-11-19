package task_3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LogInScenario {

    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
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
