package org.openmrs;

import org.openmrs.pageobjects.HomePage;
import org.openmrs.pageobjects.LoginPage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WelcomePageTest2 {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\DriverFiles\\chromedriver_139v.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(System.getProperty("user.dir") + "\\HTMLFiles\\Welcome.html");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        String mainWindowId = driver.getWindowHandle();
        System.out.println(mainWindowId);

        driver.findElement(By.linkText("OpenMRS in New Tab")).click();

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        System.out.println("------------Handling multiple Tabs---------------");
        Set<String> allTabIds = driver.getWindowHandles();
        System.out.println(allTabIds);

        for(String eachTabId : allTabIds){
            if(!eachTabId.equals(mainWindowId)){
                driver.switchTo().window(eachTabId);
                loginPage.loginToOpenMrs("Admin", "Admin123", "Registration Desk");
                homePage.logoutApplication();
                driver.close();
                break;
            }
        }

        driver.switchTo().window(mainWindowId);

        driver.findElement(By.linkText("OpenMRS in Seperate Window")).click();

        System.out.println("------------Handling multiple Windows---------------");
        Set<String> allWindowIds = driver.getWindowHandles();
        System.out.println(allWindowIds);

        for(String eachWinId : allWindowIds){
            if(!eachWinId.equals(mainWindowId)){
                driver.switchTo().window(eachWinId);
                driver.manage().window().maximize();
                loginPage.loginToOpenMrs("Admin", "Admin123", "Registration Desk");
                homePage.logoutApplication();
                driver.close();
                break;
            }
        }

        driver.switchTo().window(mainWindowId);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scroll(0,document.body.scrollHeight);");
        Thread.sleep(3000);
        js.executeScript("window.scroll(0,document.body.scrollLeft);");

        WebElement flipkartButton = driver.findElement(By.cssSelector("input[value='Flipkart']"));
        js.executeScript("arguments[0].scrollIntoView(true);", flipkartButton);

        WebElement phoneNumberEle = driver.findElement(By.cssSelector("input[name*='phonenumber']"));
        String phoneNumber = "9876543210";
        js.executeScript("arguments[0].scrollIntoView(true);", phoneNumberEle);
//        js.executeScript("arguments[0].value='"+phoneNumber+"'", phoneNumberEle);
//        js.executeScript("arguments[0].value=arguments[1]", phoneNumberEle, phoneNumber);
        js.executeScript("arguments[0].setAttribute('value',arguments[1])", phoneNumberEle, phoneNumber);

        WebElement emailEle = driver.findElement(By.id("email"));
        String emailStr = "test1@gmail.com";
        js.executeScript("arguments[0].scrollIntoView(true);", emailEle);
        js.executeScript("arguments[0].setAttribute('value',arguments[1])", emailEle, emailStr);

        js.executeScript("window.scroll(0,document.body.scrollLeft);");

        System.out.println("------------Handling IFrames---------------");
        driver.switchTo().frame("frame1");  // Id/Name/WebElement
//        loginPage.loginToOpenMrs("Admin", "Admin123", "Registration Desk");
        loginPage.setUserName("Admin");
        loginPage.setPassword("Admin123");
        loginPage.selectModule("Registration Desk");
        WebElement loginButton = loginPage.getLoginButton();
        js.executeScript("arguments[0].scrollIntoView(true);", loginButton);
        js.executeScript("arguments[0].click();", loginButton);
        driver.switchTo().defaultContent();

        js.executeScript("window.scroll(0,document.body.scrollLeft);");
        js.executeScript("window.alert('Hello........... Click OK button to close me')");
        Thread.sleep(3000);
        System.out.println("------------Handling Alerts---------------");
        Alert alert1 = driver.switchTo().alert();
//        alert1.accept();
        alert1.dismiss();

        Select countriesDropDown = new Select(driver.findElement(By.id("countries")));
        countriesDropDown.selectByVisibleText("China");

        System.out.println("------------Actions class---------------");
        // With the help of actions class, we can perform mouse and keyboard actions
        WebElement userNameElement = driver.findElement(By.id("userName"));

        Actions actions = new Actions(driver);
        actions.moveToElement(userNameElement).contextClick().build().perform();  // right click

        Thread.sleep(3000);

        actions.moveToElement(userNameElement).click(userNameElement).sendKeys("NewAdmin").build().perform();

        WebElement registerButton = driver.findElement(By.cssSelector("[value='Register']"));
        actions.moveToElement(registerButton).click(registerButton).build().perform();


//        WebElement fileInputEle = driver.findElement(By.cssSelector("input[type='file']"));
//        js.executeScript("arguments[0].scrollIntoView(true);", fileInputEle);
////        fileInputEle.click();
//        js.executeScript("arguments[0].click()", fileInputEle);

















    }
}
