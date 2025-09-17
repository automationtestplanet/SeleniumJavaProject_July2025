package org.openmrs;

import org.openmrs.pageobjects.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class OpenMrsBaseTest {

    public WebDriver driver;
    public BasePage basePage;
    public LoginPage loginPage;
    public HomePage homePage;
    public RegistrationPage registrationPage;
    public PatientDetailsPage patientDetailsPage;
    public FindPatientPage findPatientPage;
    public VisitsPage visitsPage;
    public AttachmentsPage attachmentsPage;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuit() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+TestProperties.getTestProperty("chrome.driver.path"));

    }

    @BeforeTest(alwaysRun = true)
    public void beforeTest() {
        driver = new ChromeDriver();
        basePage = new BasePage(driver);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        registrationPage = new RegistrationPage(driver);
        patientDetailsPage = new PatientDetailsPage(driver);
        findPatientPage = new FindPatientPage(driver);
        visitsPage = new VisitsPage(driver);
        attachmentsPage = new AttachmentsPage(driver);
    }

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        basePage.navigateToUrl(TestProperties.getTestProperty("openmrs.qa"));
        Assert.assertEquals(loginPage.getPageTitle(), "Login", "Login page is not available");
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        loginPage.loginToOpenMrs(TestProperties.getTestProperty("openmrs.user.name"), TestProperties.getTestProperty("openmrs.password"), "Registration Desk");
        Assert.assertEquals(loginPage.getPageTitle(), "Home", "Home Page is not available, Login Failed");
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        homePage.logoutApplication();
        Assert.assertEquals(loginPage.getPageTitle(), "Login", "Login page is not available");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.close();
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        driver = null;
        basePage = null;
        loginPage = null;
        homePage = null;
        registrationPage = null;
        patientDetailsPage = null;
        findPatientPage = null;
        visitsPage = null;
        attachmentsPage = null;
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuit() {

    }
}
