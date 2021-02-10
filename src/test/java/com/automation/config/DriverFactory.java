package com.automation.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

import static com.automation.config.DriverType.ANDROID;
import static com.automation.config.DriverType.IOS;

public class DriverFactory {

    private static AppiumDriver<MobileElement> driver;

    public static AppiumDriver<MobileElement> getMobileDriver(DriverType driverType, URL url, DesiredCapabilities capabilities) {

        if (driver == null) {

            switch (driverType) {
                case ANDROID:
                    driver = ANDROID.getDriverOptions(url, capabilities);
                    break;
                case IOS:
                    driver = IOS.getDriverOptions(url, capabilities);
                    break;
                default:
                    throw new IllegalArgumentException(String.format("An unexpected driver has been attempted to init: \n %s", driverType.toString()));
            }
        }
        return driver;
    }
}
