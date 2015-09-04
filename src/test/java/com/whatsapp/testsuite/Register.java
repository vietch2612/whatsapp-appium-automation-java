package com.whatsapp.testsuite;

import com.whatsapp.service.SetupTest;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;

/**
 * Created by viet.ch on 9/4/2015.
 */
public class Register {

    AndroidDriver driver = null;
    SetupTest setupTest = new SetupTest();

    @BeforeTest
    private void beforeTest () throws MalformedURLException {
        driver = setupTest.setUp();
    }

    @AfterTest
    private void afterTest () {
        driver.quit();
    }

}
