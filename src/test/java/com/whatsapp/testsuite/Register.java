package com.whatsapp.testsuite;

import com.whatsapp.service.SetupTest;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by viet.ch on 9/4/2015.
 */
public class Register {

    AndroidDriver driver = null;
    static String URL = "http://127.0.0.1:4723/wd/hub";
//    SetupTest setupTest = new SetupTest();

//    File classpathRoot = new File(System.getProperty("user.dir"));
//    File appDir = new File("src/resources");
    File app = new File("src/resources/com.whatsapp.app.apk");

    @BeforeTest
    private void beforeTest () throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName","Android Emulator");
        caps.setCapability("platform","ANDROID");
        caps.setCapability("platformVersion", "5.0");
        caps.setCapability("app",app);

        driver = new AndroidDriver(new URL(URL),caps);
    }

    @AfterTest
    private void afterTest () {
        driver.quit();
    }

    @Test
    public void testRegister() {
        System.out.println("Completed");
    }

}
