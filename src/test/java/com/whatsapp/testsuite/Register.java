package com.whatsapp.testsuite;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static com.whatsapp.resource_id.RegisterId.*;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by viet.ch on 9/4/2015.
 */
public class Register {

    //Url of appium server.
    //How to find? here
    //Http://3.bp.blogspot.com/-vlxM0YgXspw/UvYS6kOZDdI/AAAAAAAAAiU/3JXuNp8AOPc/s1600/Appium.jpg
    static String URL = "http://127.0.0.1:4723/wd/hub";
    //Init appium android driver, if iOS you can call IosDriver
    AndroidDriver driver;
    //Get apk app location
    File app = new File("src/resources/com.whatsapp.app.apk");

    @BeforeTest
    private void beforeTest() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Android Emulator");
        caps.setCapability("platform", "ANDROID");
        caps.setCapability("platformVersion", "5.0");
        caps.setCapability("app", app);
        driver = new AndroidDriver(new URL(URL), caps);
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
            if (driver.findElementById(TITLE_ALERT).isDisplayed()) {
                //Click on OK button
                clickOn(BTN_OK);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test(dependsOnMethods = {"checkAlertPopup"})
    public void clickOnAgree() {
        //Click to Agree and continue
        clickOn(BTN_ACCEPT_AND_CONTINUE);
    }

    @Test(dependsOnMethods = {"clickOnAgree"}, dataProvider = "countryNameList")
    public void selectCountry(String countryCode, String countryNameEnglish) throws UncheckedIOException{
        //Select country
        clickOn(BTN_SELECT_COUNTRY);

        //Search Vietnam and select
        sendKeys(TEXT_SEARCH, countryCode);
        List<WebElement> elements = driver.findElementsById(TXT_COUNTRY_NAME_ENG);
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
