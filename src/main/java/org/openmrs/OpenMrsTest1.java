package org.openmrs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class OpenMrsTest1 {
    public static void main(String[] args) throws InterruptedException {
//        System.setProperty("webdriver.chrome.driver","C:\\JavaProjects\\CoreJava_July2025\\IntelliJ\\OpenMrsSeleniumJavaMavenProject\\DriverFiles\\chromedriver_139v.exe");
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\DriverFiles\\chromedriver_139v.exe");

//        ChromeDriver driver = new ChromeDriver();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

//        driver.navigate().to("https://o2.openmrs.org/openmrs/login.htm");
//        Thread.sleep(2000);
//        driver.navigate().back();
//        Thread.sleep(2000);
//        driver.navigate().forward();
//        Thread.sleep(2000);
//        driver.navigate().refresh();

        driver.get("https://o2.openmrs.org/openmrs/login.htm");
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());
//        System.out.println(driver.getPageSource());

        By id = By.id("username");
        WebElement userNameElement = driver.findElement(id);

        userNameElement.sendKeys("Admin");
        System.out.println(userNameElement.getAttribute("id"));
        System.out.println(userNameElement.getAttribute("class"));
        System.out.println(userNameElement.getAttribute("name"));
        System.out.println(userNameElement.getAttribute("type"));
        System.out.println(userNameElement.getAttribute("placeholder"));
        System.out.println(userNameElement.getTagName());
        System.out.println(userNameElement.isEnabled());
        System.out.println(userNameElement.isDisplayed());
        driver.findElement(By.name("password")).sendKeys("Admin123");
        driver.findElement(By.id("Registration Desk")).click();
        driver.findElement(By.id("loginButton")).click();
        Thread.sleep(3000);
        if (driver.getTitle().trim().equals("Home")) {
            System.out.println("Pass");
//            driver.findElement(By.partialLinkText("Logout")).click();
            Thread.sleep(3000);

            WebElement moduleTile = driver.findElement(By.partialLinkText("Register a patient"));
            if (moduleTile.isDisplayed()) {
                moduleTile.click();
                if (driver.findElement(By.xpath("//h2[contains(text(),'Register a patient')]")).isDisplayed()) {
                    driver.findElement(By.name("givenName")).sendKeys("Test");
                    driver.findElement(By.name("familyName")).sendKeys("User1");
                    WebElement nextButton = driver.findElement(By.id("next-button"));
                    nextButton.click();

                    Select genderDropdown = new Select(driver.findElement(By.id("gender-field")));
                    genderDropdown.selectByVisibleText("Male");
                    nextButton.click();

                    driver.findElement(By.id("birthdateDay-field")).sendKeys("01");
                    Select birthMonthDropDown = new Select(driver.findElement(By.id("birthdateMonth-field")));
                    birthMonthDropDown.selectByVisibleText("January");
                    driver.findElement(By.id("birthdateYear-field")).sendKeys("1990");
                    nextButton.click();

                    driver.findElement(By.id("address1")).sendKeys("Newar Icici bank, Sr Nagar");
                    driver.findElement(By.id("cityVillage")).sendKeys("Hyderabad");
                    driver.findElement(By.id("stateProvince")).sendKeys("Telangana");
                    driver.findElement(By.id("country")).sendKeys("India");
                    driver.findElement(By.id("postalCode")).sendKeys("500038");
                    nextButton.click();

                    driver.findElement(By.name("phoneNumber")).sendKeys("9876543210");
                    nextButton.click();

                    Select relationshipDropDown = new Select(driver.findElement(By.id("relationship_type")));
                    relationshipDropDown.selectByVisibleText("Parent");
                    driver.findElement(By.cssSelector("input[placeholder='Person Name']")).sendKeys("TestUser1 Parent");
                    nextButton.click();

                    String actualName = driver.findElement(By.xpath("//span[contains(text(),'Name:')]//parent::p")).getText().trim();
                    System.out.println(actualName);

                    String actualGender = driver.findElement(By.xpath("//span[contains(text(),'Gender:')]//parent::p")).getText().trim();
                    System.out.println(actualGender);

                    String actualDateOfBirth = driver.findElement(By.xpath("//span[contains(text(),'Birthdate:')]//parent::p")).getText().trim();
                    System.out.println(actualDateOfBirth);

                    String actualPhoneNumber = driver.findElement(By.xpath("//span[contains(text(),'Phone Number:')]//parent::p")).getText().trim();
                    System.out.println(actualPhoneNumber);

                    if (actualName.contains("Test, User1") && actualGender.contains("Male") && actualDateOfBirth.contains("01, January, 1990") && actualPhoneNumber.contains("9876543210")) {
                        driver.findElement(By.cssSelector("input[value='Confirm']")).click();

                        WebDriverWait wait = new WebDriverWait(driver, 20);
                        WebElement givenNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//em[contains(text(),'Given')]//preceding-sibling::span")));

                        String givenName = givenNameElement.getText().trim();
                        String familyName = driver.findElement(By.xpath("//em[contains(text(),'Family Name')]//preceding-sibling::span")).getText().trim();

                        if (givenName.equals("Test") && familyName.equals("User1")) {
                            String patientId = driver.findElement(By.xpath("//em[contains(text(),'Patient ID')]//following-sibling::span")).getText().trim();
                            System.out.println(patientId);
                        } else {
                            System.out.println("Patient Name is incorrect in Patient Details Page");
                        }

                    } else {
                        System.out.println("Registered details showing incorrect");
                        driver.findElement(By.id("cancelSubmission")).click();
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


//        driver.close();


    }
}
