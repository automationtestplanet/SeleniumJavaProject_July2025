package org.openmrs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class OpenMrsTest2 {
    public static WebDriver driver;

    public static void navigateToUrl(String url) {
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public static void setUserName(String userName) {
        driver.findElement(By.id("username")).sendKeys(userName);
    }

    public static void setPassword(String password) {
        driver.findElement(By.name("password")).sendKeys(password);
    }

    public static void selectModule(String moduleName) {
        driver.findElement(By.id(moduleName)).click();
    }

    public static void clickLogin() {
        driver.findElement(By.id("loginButton")).click();
    }

    public static void loginToOpenMrs(String userName, String password, String moduleName) {
        try {
            setUserName(userName);
            setPassword(password);
            selectModule(moduleName);
            clickLogin();
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println("Exception occurred while login to the application: " + e.getMessage());
        }
    }

    public static String getPageTitle() {
        return driver.getTitle().trim();
    }

    public static boolean verifyModuleTile(String moduleName) {
        return driver.findElement(By.partialLinkText(moduleName)).isDisplayed();
    }

    public static void clickModuleTile(String moduleName) {
        driver.findElement(By.partialLinkText(moduleName)).click();
    }

    public static boolean verifyModulePage(String expectedPageName) {
        return driver.findElement(By.xpath("//h2[contains(text(),'" + expectedPageName + "')]")).isDisplayed();
    }

    public static void setPatientGivenName(String givenName) {
        driver.findElement(By.name("givenName")).sendKeys(givenName);
    }

    public static void setPatientFamilyName(String familyName) {
        driver.findElement(By.name("familyName")).sendKeys(familyName);
    }

    public static void clickNextButton() {
        driver.findElement(By.id("next-button")).click();
    }

    public static void selectGender(String genderValue) {
        Select genderDropdown = new Select(driver.findElement(By.id("gender-field")));
        genderDropdown.selectByVisibleText(genderValue);
    }

    public static void setBirthDate(String birthDate) {
        driver.findElement(By.id("birthdateDay-field")).sendKeys(birthDate);
    }

    public static void selectBirthMonth(String birthMonth) {
        Select birthMonthDropDown = new Select(driver.findElement(By.id("birthdateMonth-field")));
        birthMonthDropDown.selectByVisibleText(birthMonth);
    }

    public static void setBirthYear(String birthYear) {
        driver.findElement(By.id("birthdateYear-field")).sendKeys(birthYear);
    }

    public static void setDateOfBirth(String dateOfBirth) {
        String[] dateOfBirthArr = dateOfBirth.split(",");
        setBirthDate(dateOfBirthArr[0].trim());
        selectBirthMonth(dateOfBirthArr[1].trim());
        setBirthYear(dateOfBirthArr[2].trim());
    }

    public static void setAddress1(String address1) {
        driver.findElement(By.id("address1")).sendKeys(address1);
    }

    public static void setCity(String city) {
        driver.findElement(By.id("cityVillage")).sendKeys(city);

    }

    public static void setState(String state) {
        driver.findElement(By.id("stateProvince")).sendKeys(state);

    }

    public static void setCountry(String country) {
        driver.findElement(By.id("country")).sendKeys(country);
    }

    public static void setPostalCode(String postalCode) {
        driver.findElement(By.id("postalCode")).sendKeys(postalCode);
    }

    public static void setAddress(String address) {
        String[] addressArr = address.split(",");
        setAddress1(addressArr[0].trim());
        setCity(addressArr[1].trim());
        setState(addressArr[2].trim());
        setCountry(addressArr[3].trim());
        setPostalCode(addressArr[4].trim());
    }

    public static void setPhoneNumber(String phoneNumber) {
        driver.findElement(By.name("phoneNumber")).sendKeys(phoneNumber);
    }

    public static void selectPatientRelation(String patientRelation) {
        Select relationshipDropDown = new Select(driver.findElement(By.id("relationship_type")));
        relationshipDropDown.selectByVisibleText(patientRelation);
    }

    public static void setPatientRelationName(String relativeName) {
        driver.findElement(By.cssSelector("input[placeholder='Person Name']")).sendKeys(relativeName);
    }

    public static void setPatientRelationDetails(String relationDetails) {
        String[] relationDetailsArr = relationDetails.split(",");
        selectPatientRelation(relationDetailsArr[0].trim());
        setPatientRelationName(relationDetailsArr[1].trim());
    }

    public static String getPatientName() {
        return driver.findElement(By.xpath("//span[contains(text(),'Name:')]//parent::p")).getText().trim();
    }

    public static void setPatientName(String patientName) {
        String[] patientNameArr = patientName.split(",");
        setPatientGivenName(patientNameArr[0].trim());
        setPatientFamilyName(patientNameArr[1].trim());
    }

    public static String getPatientGender() {
        return driver.findElement(By.xpath("//span[contains(text(),'Gender:')]//parent::p")).getText().trim();
    }

    public static String getPatientDateOfBirth() {
        return driver.findElement(By.xpath("//span[contains(text(),'Birthdate:')]//parent::p")).getText().trim();
    }

    public static String getPatientPhoneNumber() {
        return driver.findElement(By.xpath("//span[contains(text(),'Phone Number:')]//parent::p")).getText().trim();
    }

    public static boolean verifyEnteredPatientDetails(String expectedPatientName, String expectedGender, String expectedDateOfBirth, String expectedPhoneNumber) {
        return getPatientName().contains(expectedPatientName) && getPatientGender().contains(expectedGender) && getPatientDateOfBirth().contains(expectedDateOfBirth) && getPatientPhoneNumber().contains(expectedPhoneNumber);
    }

    public static void clickConfirmButon() {
        driver.findElement(By.cssSelector("input[value='Confirm']")).click();
    }

    public static void clickCancelButon() {
        driver.findElement(By.id("cancelSubmission")).click();
    }

    public static String getGivenName() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement givenNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//em[contains(text(),'Given')]//preceding-sibling::span")));
        return givenNameElement.getText().trim();
    }

    public static String getFamilyName() {
        return driver.findElement(By.xpath("//em[contains(text(),'Family Name')]//preceding-sibling::span")).getText().trim();
    }

    public static boolean verifyPatientName(String patientName) {
        String[] patientNameArr = patientName.split(",");
        return getGivenName().equals(patientNameArr[0].trim()) && getFamilyName().equals(patientNameArr[1].trim());
    }

    public static String getPatientId() {
        return driver.findElement(By.xpath("//em[contains(text(),'Patient ID')]//following-sibling::span")).getText().trim();
    }

    public static void enterPatientDetails(String patientName, String patientGender, String patientDateOfBirth, String patientAddress, String patientPhoneNumber, String patientRelatives) {
        setPatientName(patientName);
        clickNextButton();
        selectGender(patientGender);
        clickNextButton();
        setDateOfBirth(patientDateOfBirth);
        clickNextButton();
        setAddress(patientAddress);
        clickNextButton();
        setPhoneNumber(patientPhoneNumber);
        clickNextButton();
        setPatientRelationDetails(patientRelatives);
        clickNextButton();
    }


    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\DriverFiles\\chromedriver_139v.exe");
        driver = new ChromeDriver();
        navigateToUrl("https://o2.openmrs.org/openmrs/login.htm");
        loginToOpenMrs("Admin", "Admin123", "Registration Desk");
        if (getPageTitle().equals("Home")) {
            System.out.println("Pass");
            if (verifyModuleTile("Register a patient")) {
                clickModuleTile("Register a patient");
                if (verifyModulePage("Register a patient")) {
                    enterPatientDetails("Test, User1", "Male", "01, January, 1990", "Near Icici bank Sr Nagar, Hyderabad, Telangana, India, 500038", "9876543210", "Parent, TestUser1 Parent");
                    if (verifyEnteredPatientDetails("Test, User1", "Male", "01, January, 1990", "9876543210")) {
                        clickConfirmButon();
                        if (verifyPatientName("Test, User1")) {
                            System.out.println(getPatientId());
                        } else {
                            System.out.println("Patient Name is incorrect in Patient Details Page");
                        }
                    } else {
                        System.out.println("Registered details showing incorrect");
                        clickCancelButon();
                    }
                } else {
                    System.out.println("Register Page is not displayed");
                }
            } else {
                System.out.println("Register Patient Module is not displayed");
            }
//            driver.close();
        } else
            System.out.println("Fail");

//        driver.findElement(By.partialLinkText("Logout")).click();
//        driver.close();


    }
}
