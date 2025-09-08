package org.openmrs.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FindPatientPage extends BasePage {

    @FindBy(id = "patient-search")
    WebElement patientSearchInput;
    @FindBy(xpath = "//table[@id='patient-search-results-table']/thead/tr/th/div")
    List<WebElement> searchPatientTableColumns;

    @FindBy(xpath = "//table[@id='patient-search-results-table']/tbody/tr[1]/td[1]")
    WebElement searchPatientTableFirstRecord;

    public FindPatientPage(WebDriver driver) {
        super(driver);
    }

    public void searchPatientWithName(String patientName) {
        patientSearchInput.sendKeys(patientName);
    }

    public Map<String, Integer> getSearchPatientTableColumnNamesMap() {
        Map<String, Integer> searchPatientTablecolumnsMap = new LinkedHashMap<>();
        int index = 1;
        for (WebElement eachColumnElement : searchPatientTableColumns) {
            searchPatientTablecolumnsMap.put(eachColumnElement.getText().trim(), index);
            index++;
        }
        return searchPatientTablecolumnsMap;
    }

    public int getSearchPatientTableColumnIndex(String columnName) {
        return getSearchPatientTableColumnNamesMap().get(columnName);
    }

    public String getSearchPatientTableColumnValue(String columnName) {
        return driver.findElement(By.xpath("//table[@id='patient-search-results-table']/tbody/tr/td[" + getSearchPatientTableColumnIndex(columnName) + "]")).getText().trim();
    }

    public boolean verifySearchPatientTableColumnValue(String columnName, String expectedValue) {
        return getSearchPatientTableColumnValue(columnName).equals(expectedValue);
    }

    public void clickSearchPatientTableFirstRecord(){
        searchPatientTableFirstRecord.click();
    }
}
