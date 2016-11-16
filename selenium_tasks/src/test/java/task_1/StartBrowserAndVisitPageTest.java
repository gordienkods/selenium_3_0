package task_1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class StartBrowserAndVisitPageTest {

    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void first_task(){
        driver.get("http://software-testing.ru");
    }

}
