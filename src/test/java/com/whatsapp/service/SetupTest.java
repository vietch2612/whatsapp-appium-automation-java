package com.whatsapp.service;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by viet.ch on 9/4/2015.
 */

public class SetupTest {

    AndroidDriver driver = null;
    static String URL = "http://127.0.0.1:4723/wd/hub";

    public AndroidDriver getDriver() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName","Android Emulator");
        caps.setCapability("platform","ANDROID");
        caps.setCapability("platformVersion", "5.0");

        driver = new AndroidDriver(new URL(URL),caps);

        return driver;
    }

}
