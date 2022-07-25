import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Form {
    WebDriver driver;

    @FindBy(xpath="//input[@data-testid='pet']")
    WebElement petName;

    @FindBy(xpath="//input[@data-testid='owner']")
    WebElement ownerName;

    @FindBy(xpath="//input[@data-testid='date']")
    WebElement dateField;

    @FindBy(xpath="//input[@data-testid='time']")
    WebElement timeField;

    @FindBy(xpath="//textarea[@data-testid='symptoms']")
    WebElement symptomsTextArea;

    @FindBy(xpath="//button[@data-testid='btn-submit']")
    WebElement addAppointmentButton;

    public Form(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setPetName(String strPetName) {
        petName.sendKeys(strPetName);
    }

    public void setOwnerName(String strOwnerName) {
        ownerName.sendKeys(strOwnerName);
    }

    public void setDateField(String strDate) {
        dateField.sendKeys(strDate);
    }

    public void setTimeField(String strTime) {
        timeField.sendKeys(strTime);
    }

    public void setSymptomsTextArea(String strSymptoms) {
        symptomsTextArea.sendKeys(strSymptoms);
    }

    public void clickSubmit() {
        addAppointmentButton.click();
    }

    /**
     * @param strPetName
     * @param strOwnerName
     * @param strDate
     * @param strTime
     * @param strSymptoms
     */
    public void addAppointment(String strPetName, String strOwnerName, String strDate, String strTime, String strSymptoms) {
        this.setPetName(strPetName);
        this.setOwnerName(strOwnerName);
        this.setDateField(strDate);
        this.setTimeField(strTime);
        this.setSymptomsTextArea(strSymptoms);
        this.clickSubmit();
    }

    public String getAlertText() {
        return driver.findElement(By.xpath("//p[@data-testid='alert']")).getText();
    }


}
