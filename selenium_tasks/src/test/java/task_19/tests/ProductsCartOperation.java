package task_19.tests;

import custom_driver_factory.CustomDriverFactory;
import custom_driver_factory.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import task_19.pages.CheckOutCartPage;
import task_19.pages.MainConsumerPage;
import task_19.pages.ProductPage;

public class ProductsCartOperation {

    private WebDriver driver;
    private ProductPage productPage;
    private CheckOutCartPage checkOutCartPage;
    private MainConsumerPage mainConsumerPage;

    public ProductsCartOperation(){
        driver = CustomDriverFactory.getDriver(Driver.CHROME);
        mainConsumerPage = new MainConsumerPage(driver);
        productPage = new ProductPage(driver);
        checkOutCartPage = new CheckOutCartPage(driver);
    }

    @AfterClass (alwaysRun = true)
    public void afterClass(){
        driver.quit();
    }

    @Test
    public void add_and_remove_product_to_cart(){
        mainConsumerPage.open().clickOnProduct("Most Popular", "Purple Duck");
        productPage.addCurrentProductToCart(3).checkoutUrlUnderChartCounter.click();
        checkOutCartPage.cleanCart();
        AssertJUnit.assertTrue(checkOutCartPage.isTexPresentOnPage("There are no items in your cart."));
    }

}
