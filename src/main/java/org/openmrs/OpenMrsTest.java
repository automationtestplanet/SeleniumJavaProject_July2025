package org.openmrs;

import org.openmrs.pageobjects.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class OpenMrsTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\DriverFiles\\chromedriver_139v.exe");
        WebDriver driver = new ChromeDriver();
        BasePage basePage = new BasePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        PatientDetailsPage patientDetailsPage = new PatientDetailsPage(driver);

        basePage.navigateToUrl("https://o2.openmrs.org/openmrs/login.htm");
        if (loginPage.getPageTitle().equals("Login")) {
            loginPage.loginToOpenMrs("Admin", "Admin123", "Registration Desk");
            if (homePage.getPageTitle().equals("Home")) {
                if (homePage.verifyModuleTile("Register a patient")) {
                    homePage.clickModuleTile("Register a patient");
                    if (homePage.verifyModulePage("Register a patient")) {
                        registrationPage.enterPatientDetails("Test, User1", "Male", "01, January, 1990", "Near Icici bank Sr Nagar, Hyderabad, Telangana, India, 500038", "9876543210", "Parent, TestUser1 Parent");
                        if (registrationPage.verifyEnteredPatientDetails("Test, User1", "Male", "01, January, 1990", "9876543210")) {
                            registrationPage.clickConfirmButon();
                            if (patientDetailsPage.verifyPatientName("Test, User1")) {
                                System.out.println(patientDetailsPage.getPatientId());
                            } else {
                                System.out.println("Patient Name is incorrect in Patient Details Page");
                            }
                        } else {
                            System.out.println("Registered details showing incorrect");
                            registrationPage.clickCancelButon();
                        }
                    } else {
                        System.out.println("Register Page is not displayed");
                    }
                } else {
                    System.out.println("Register Patient Module is not displayed");
                }
            } else {
                System.out.println("Home Page is not available, Login Failed");
            }
        } else {
            System.out.println("Login page is not available");
        }
    }
}
