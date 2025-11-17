package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends BasePage {

    WebDriver driver;

    public SearchPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Dynamic locator for product name
    private By productName(String product) {
        return By.xpath("//a[text()='" + product + "']");
    }

    // Check if product is present in results
    public boolean isProductExist(String product) {
        try {
            return driver.findElement(productName(product)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
