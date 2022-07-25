import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Appointments {
    WebDriver driver;

    @FindBy(xpath="//h2[@data-testid='dynamic-title']")
    WebElement dynamicTitle;

    @FindBy(xpath="//button[contains(@data-testid,'btn-delete')]")
    WebElement deleteButton;

    public Appointments(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void clickDelete() {
        deleteButton.click();
    }
}
