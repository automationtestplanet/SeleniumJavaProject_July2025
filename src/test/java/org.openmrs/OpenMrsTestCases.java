package org.openmrs;

import org.openmrs.pageobjects.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OpenMrsTestCases {

    @Test
    public void registerPatientTest() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\DriverFiles\\chromedriver_139v.exe");
        WebDriver driver = new ChromeDriver();
        BasePage basePage = new BasePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        PatientDetailsPage patientDetailsPage = new PatientDetailsPage(driver);

        basePage.navigateToUrl("https://o2.openmrs.org/openmrs/login.htm");
        Assert.assertEquals(loginPage.getPageTitle(), "Login", "Login page is not available");
        loginPage.loginToOpenMrs("Admin", "Admin123", "Registration Desk");
        Assert.assertEquals(loginPage.getPageTitle(), "Home", "Home Page is not available, Login Failed");
        Assert.assertTrue(homePage.verifyModuleTile("Register a patient"), "Register Patient Module is not displayed");
        homePage.clickModuleTile("Register a patient");
        Assert.assertTrue(homePage.verifyModulePage("Register a patient"), "Register Page is not displayed");
        registrationPage.enterPatientDetails("Test, User1", "Male", "01, January, 1990", "Near Icici bank Sr Nagar, Hyderabad, Telangana, India, 500038", "9876543210", "Parent, TestUser1 Parent");
        Assert.assertTrue(registrationPage.verifyEnteredPatientDetails("Test, User1", "Male", "01, January, 1990", "9876543210"), "Registered details showing incorrect");
        registrationPage.clickConfirmButon();
        Assert.assertTrue(patientDetailsPage.verifyPatientName("Test, User1"), "Patient Name is incorrect in Patient Details Page");
        System.out.println(patientDetailsPage.getPatientId());
    }

    @Test
    public void findPatientTest() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\DriverFiles\\chromedriver_139v.exe");
        WebDriver driver = new ChromeDriver();
        BasePage basePage = new BasePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        FindPatientPage findPatientPage = new FindPatientPage(driver);
        PatientDetailsPage patientDetailsPage = new PatientDetailsPage(driver);

        basePage.navigateToUrl("https://o2.openmrs.org/openmrs/login.htm");
        Assert.assertEquals(loginPage.getPageTitle(), "Login", "Login page is not available");
        loginPage.loginToOpenMrs("Admin", "Admin123", "Registration Desk");
        Assert.assertEquals(loginPage.getPageTitle(), "Home", "Home Page is not available, Login Failed");
        Assert.assertTrue(homePage.verifyModuleTile("Find Patient Record"), "Find Patient Record Module is not displayed");
        homePage.clickModuleTile("Find Patient Record");
        Assert.assertTrue(homePage.verifyModulePage("Find Patient Record"), "Find Patient Record Page is not displayed");
        findPatientPage.searchPatientWithName("Test User1");
        Assert.assertTrue(findPatientPage.verifySearchPatientTableColumnValue("Name", "Test User1"), "Find Patient record is not matching");
        findPatientPage.clickSearchPatientTableFirstRecord();
        Assert.assertTrue(patientDetailsPage.verifyPatientName("Test User1"), "Find Patient Name is not matching in Patient Details Page");
    }
}
