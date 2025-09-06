package org.openmrs.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PatientDetailsPage extends BasePage {

    @FindBy(xpath = "//em[contains(text(),'Family Name')]//preceding-sibling::span")
    private WebElement familyNameLabel;
    @FindBy(xpath = "//em[contains(text(),'Patient ID')]//following-sibling::span")
    private WebElement patientIdLabel;

    public PatientDetailsPage(WebDriver driver) {
        super(driver);
    }

    public String getGivenName() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement givenNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//em[contains(text(),'Given')]//preceding-sibling::span")));
        return givenNameElement.getText().trim();
    }

    public String getFamilyName() {
        return familyNameLabel.getText().trim();
    }

    public boolean verifyPatientName(String patientName) {
        String[] patientNameArr = patientName.split(",");
        return getGivenName().equals(patientNameArr[0].trim()) && getFamilyName().equals(patientNameArr[1].trim());
    }

    public String getPatientId() {
        return patientIdLabel.getText().trim();
    }
}
