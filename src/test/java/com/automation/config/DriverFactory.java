package com.automation.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobilePlatform;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class DriverFactory {

    private static AppiumDriver<MobileElement> driver;

    public static AppiumDriver<MobileElement> getMobileDriver(String platformName, URL url, DesiredCapabilities capabilities) {

        if (driver == null) {
            switch (StringUtils.capitalize(platformName)) {
                case MobilePlatform.ANDROID:
                    driver = DriverManager.ANDROID.getDriverOptions(url, capabilities);
                    break;
                case MobilePlatform.IOS:
                    driver = DriverManager.IOS.getDriverOptions(url, capabilities);
                    break;
                default:
                    throw new IllegalArgumentException("Undefined OS");
            }
        }
        return driver;
    }
}
