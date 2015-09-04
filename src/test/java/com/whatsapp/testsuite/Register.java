package com.whatsapp.testsuite;

import com.whatsapp.service.SetupTest;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.List;

import static com.whatsapp.resource_id.RegisterId.*;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by viet.ch on 9/4/2015.
 */
public class Register {

    SetupTest s = new SetupTest();

    AndroidDriver driver;

    @BeforeTest
    private void beforeTest() throws MalformedURLException {
        driver = s.getAndroidDriver();
    }

    //Quit driver after test
    @AfterTest
    private void afterTest() {
        driver.quit();
    }

    //Run your test case
    @Test
    public void checkAlertPopup() {
        try {
            //Check alert is displayed
            if (driver.findElementById(REG_TITLE_ALERT).isDisplayed()) {
                //Click on OK button
                clickOn(REG_BTN_OK);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test(dependsOnMethods = {"checkAlertPopup"})
    public void clickOnAgree() {
        //Click to Agree and continue
        clickOn(REG_BTN_AGREE_CONTINUE);
    }

    @Test(dependsOnMethods = {"clickOnAgree"}, dataProvider = "countryNameList")
    public void selectCountry(String countryCode, String countryNameEnglish) {
        //Select country
        clickOn(REG_BTN_SELECT_COUNTRY);

        try {
            driver.scrollTo("CountryName");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        //Search Vietnam and select
        sendKeys(REG_TEXT_SEARCH, countryCode);
        List<WebElement> elements = driver.findElementsById(REG_TXT_COUNTRY_NAME_ENG);
        for (WebElement element : elements) {
            if (element.getText().equals(countryNameEnglish)) {
                element.click();
            } else {
                assertTrue(false);
            }
        }
    }

    //Some country list
    @DataProvider(name = "countryNameList")
    public Object[][] listCountryName() {
        return new Object[][]{
                {"84", "Vietnam"},
                {"379", "Vatican City"},
                {"55", "Brazil"},
                {"52", "Mexico"}
        };
    }

    public void clickOn(String id) {
        driver.findElement(By.id(id)).click();
    }

    public void sendKeys(String id, String keys) {
        driver.findElement(By.id(id)).sendKeys(keys);
    }

}
