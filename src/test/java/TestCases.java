import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class TestCases {
    public String baseUrl = "http://localhost:3000/";
    public WebDriver driver;
    public Form form;
    public Appointments appointments;

    @Test
    @Parameters("browser")
    public void launchBrowser(String browser) {
        if (browser.equals("chrome")) {
            System.out.println("Launching Chrome browser");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } if (browser.equals("firefox")) {
            System.out.println("Launching Firefox browser");
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.get(baseUrl);
    }

    @Test (priority = 2)
    public void checkPageTitles() {
        String expectedTitle = "Administración de Pacientes";
        String actualTitle = driver.getTitle();

        assertEquals(expectedTitle,actualTitle);
        System.out.println("Page title verified");

        String expectedAppName = "APPOINTMENT MANAGEMENT";
        String actualAppName =  driver.findElement(By.xpath("//h1[@data-testid='app-name']")).getText();

        assertEquals(expectedAppName,actualAppName);
        System.out.println("App name verified");
    }

    @Test (priority = 3)
    @Parameters({"strPetName", "strOwnerName", "strDate", "strTime", "strSymptoms"})
    public void createAppointmentWithAllParams(String strPetName, String strOwnerName, String strDate, String strTime, String strSymptoms) {
        form = new Form(driver);
        form.addAppointment(strPetName, strOwnerName, strDate, strTime, strSymptoms);
        System.out.println("Appointment added successfully");
    }

    @Test (priority = 3)
    @Parameters({"strPetName", "strDate", "strTime", "strSymptoms"})
    public void createAppointmentWithMissingParams(String strPetName, String strDate, String strTime, String strSymptoms) {
        form = new Form(driver);
        form.addAppointment(strPetName, "", strDate, strTime, strSymptoms);

        String expectedAlert = "ALL FIELDS ARE REQUIRED";
        String alertText = form.getAlertText();

        assertEquals(expectedAlert,alertText);

        System.out.println("Appointment was not created due to missing fields.");
    }

    @Test (priority = 4)
    public void deleteAppointment() {
        appointments = new Appointments(driver);
        String expectedTitle = "MANAGE YOUR APPOINTMENTS";
        String dynamicTitle = appointments.dynamicTitle.getText();
        assertEquals(expectedTitle,dynamicTitle);
        System.out.println("Form list has records.");

        appointments.clickDelete();

        String emptyTitle = "THERE ARE NO APPOINTMENTS";
        dynamicTitle = appointments.dynamicTitle.getText();
        assertEquals(emptyTitle,dynamicTitle);
        System.out.println("Appointment list is empty.");
    }

    @AfterTest
    public void closeBrowser() {
        driver.close();
    }


}
