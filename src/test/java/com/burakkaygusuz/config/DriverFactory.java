package com.burakkaygusuz.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import static com.burakkaygusuz.config.DriverType.ANDROID;
import static com.burakkaygusuz.config.DriverType.IOS;

public class DriverFactory {

    protected static AppiumDriver<MobileElement> driver;

    public static AppiumDriver<MobileElement> getMobileDriver(DriverType driverType) {

        if (driver == null) {
            switch (driverType) {
                case ANDROID:
                    driver = ANDROID.getDriver();
                    break;
                case IOS:
                    driver = IOS.getDriver();
                    break;
                default:
                    throw new IllegalArgumentException(String.format("An unexpected driver has been attempted to init: \n %s", driverType.toString()));
            }
        }
        return driver;
    }
}
