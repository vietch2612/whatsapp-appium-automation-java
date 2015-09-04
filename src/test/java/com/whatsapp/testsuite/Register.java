package com.whatsapp.testsuite;

import com.whatsapp.resource_id.RegisterId;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by viet.ch on 9/4/2015.
 */
public class Register {

    //Init appium android driver, if iOS you can call IosDriver
    AndroidDriver driver;

    //Url of appium server.
    //How to find? here
    //Http://3.bp.blogspot.com/-vlxM0YgXspw/UvYS6kOZDdI/AAAAAAAAAiU/3JXuNp8AOPc/s1600/Appium.jpg
    static String URL = "http://127.0.0.1:4723/wd/hub";

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
    public void testRegister() {

        //Get alert element
        WebElement alertTitle = driver.findElement(By.id(RegisterId.TITLE_ALERT));

        //Check alert is displayed
        if (alertTitle.isDisplayed()) {

            //Click on OK button
            System.out.println("Click on OK");
            WebElement btnOK = driver.findElement(By.id(RegisterId.BTN_OK));
            btnOK.click();

            //Click to Agree and continue
            System.out.println("Click on Agree");
            WebElement btnAgree = driver.findElement(By.id(RegisterId.BTN_ACCEPT_AND_CONTINUE));
            btnAgree.click();

            //Select country: Vietnam
            System.out.println("Click on Country");
            WebElement btnCountry = driver.findElement(By.id(RegisterId.BTN_SELECT_COUNTRY));
            btnCountry.click();


            //Search textbox SEARCH, sendkeys: Vietnam and Select
            System.out.println("Select Vietnam");
            driver.findElementById(RegisterId.TEXT_SEARCH).sendKeys("Vietnam");
            List<WebElement> elements = driver.findElements(By.id(RegisterId.TXT_COUNTRY_NAME_ENG));
            for (WebElement element : elements) {
                if (element.getText().equals("Vietnam")) {
                    element.click();
                } else {
                    assertTrue(false);
                }
            }

        }
    }
}
