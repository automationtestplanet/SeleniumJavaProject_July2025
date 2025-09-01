package org.openmrs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {
    public static void main(String[] args) throws InterruptedException {
//        System.setProperty("webdriver.chrome.driver","C:\\JavaProjects\\CoreJava_July2025\\IntelliJ\\OpenMrsSeleniumJavaMavenProject\\DriverFiles\\chromedriver_139v.exe");
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\DriverFiles\\chromedriver_139v.exe");

//        ChromeDriver driver = new ChromeDriver();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

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
            driver.findElement(By.partialLinkText("Logout")).click();
            Thread.sleep(3000);
            driver.close();
        } else
            System.out.println("Fail");


//        driver.close();


    }
}
