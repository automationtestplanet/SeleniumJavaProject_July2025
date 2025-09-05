package org.openmrs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class WelcomePageTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\DriverFiles\\chromedriver_139v.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(System.getProperty("user.dir") + "\\HTMLFiles\\Welcome.html");

        WebElement userNameElement = driver.findElement(By.id("userName"));
        userNameElement.sendKeys("Admin");
        userNameElement.clear();
        userNameElement.sendKeys("NewAdmin");

        driver.findElement(By.name("Password")).sendKeys("Admin123");

        Select countriesDropDown = new Select(driver.findElement(By.id("countries")));
        countriesDropDown.selectByIndex(2);
        countriesDropDown.selectByVisibleText("China");
        countriesDropDown.selectByValue("AUS");

        List<WebElement> allOptions = countriesDropDown.getOptions();
//        allOptions.forEach(eachOption-> System.out.println(eachOption.getText()));

        for (WebElement eachOption : allOptions) {
            System.out.println(eachOption.getText());
        }

        WebElement maleRadioButton = driver.findElement(By.id("male"));
        if (!maleRadioButton.isSelected()) {
            maleRadioButton.click();
        }

        WebElement termsCheckbox = driver.findElement(By.className("checkbox"));
        if (!termsCheckbox.isSelected()) {
            termsCheckbox.click();
        }

        driver.findElement(By.cssSelector("[value='Register']")).click();


    }
}
