package com.automation.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public enum AppiumDriverManager implements AppiumDriverOptions {

    ANDROID {
        @Override
        public AppiumDriver getDriverOptions(URL url, DesiredCapabilities desiredCapabilities) {
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11");
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.SKIP_UNLOCK, true);
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.ANDROID_INSTALL_PATH, "/data/local/tmp");
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.ANDROID_INSTALL_TIMEOUT, 100000);

            return new AndroidDriver<MobileElement>(url, desiredCapabilities);
        }
    },

    IOS {
        @Override
        public AppiumDriver getDriverOptions(URL url, DesiredCapabilities desiredCapabilities) {
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13");
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
            desiredCapabilities.setCapability(IOSMobileCapabilityType.LOCATION_SERVICES_ENABLED, true);
            desiredCapabilities.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, true);

            return new IOSDriver<MobileElement>(url, desiredCapabilities);
        }
    };

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
