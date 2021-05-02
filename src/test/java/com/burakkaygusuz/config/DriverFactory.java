package com.burakkaygusuz.config;

import com.burakkaygusuz.enums.Platforms;
import com.burakkaygusuz.exceptions.UnSupportedPlatformException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.burakkaygusuz.config.DriverType.ANDROID;
import static com.burakkaygusuz.config.DriverType.IOS;

public class DriverFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(DriverType.class);
    protected static AppiumDriver<MobileElement> driver;

    public static AppiumDriver<MobileElement> getMobileDriver(Platforms platform) {

        if (driver == null) {
            try {
                switch (platform) {
                    case ANDROID:
                        driver = ANDROID.getDriver();
                        break;
                    case IOS:
                        driver = IOS.getDriver();
                        break;
                    default:
                        throw new UnSupportedPlatformException(String.format("%s is not supported!", platform.toString()));
                }
            } catch (Exception e) {
                LOGGER.error(String.format("An unexpected error occurred while the appium driver initialized: \n %s", ExceptionUtils.getMessage(e)));
            }
        }
        return driver;
    }
}
