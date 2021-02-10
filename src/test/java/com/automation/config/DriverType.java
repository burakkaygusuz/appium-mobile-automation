package com.automation.config;

import com.automation.utils.PropertyUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.Properties;

public enum DriverType implements DriverOptions {

    ANDROID {
        @Override
        public AppiumDriver<MobileElement> getDriverOptions(URL url, DesiredCapabilities desiredCapabilities) {

            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, props.getProperty("ANDROID_DEVICE_NAME"));
            desiredCapabilities.setCapability(MobileCapabilityType.UDID, props.getProperty("ANDROID_DEVICE_ID"));
            desiredCapabilities.setCapability(MobileCapabilityType.APP, new File(props.getProperty("ANDROID_APP_PATH")).getAbsolutePath());
            desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, false);
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.IS_HEADLESS, true);
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.AVD, props.getProperty("AVD_DEVICE_NAME"));
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.SKIP_UNLOCK, true);
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.ANDROID_INSTALL_PATH, "/data/local/tmp");
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.ANDROID_INSTALL_TIMEOUT, 100000);
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, props.getProperty("APP_ACTIVITY"));
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, props.getProperty("APP_PACKAGE"));

            return new AndroidDriver<>(url, desiredCapabilities);
        }
    },

    IOS {
        @Override
        public AppiumDriver<MobileElement> getDriverOptions(URL url, DesiredCapabilities desiredCapabilities) {

            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13");
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, props.getProperty("IOS_DEVICE_NAME"));
            desiredCapabilities.setCapability(MobileCapabilityType.UDID, props.getProperty("IOS_DEVICE_ID"));
            desiredCapabilities.setCapability(MobileCapabilityType.APP, new File(props.getProperty("IOS_APP_PATH")).getAbsolutePath());
            desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, false);
            desiredCapabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, props.getProperty("BUNDLE_ID"));
            desiredCapabilities.setCapability(IOSMobileCapabilityType.LOCATION_SERVICES_ENABLED, true);
            desiredCapabilities.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, true);

            return new IOSDriver<>(url, desiredCapabilities);
        }
    };

    protected final Properties props = PropertyUtils.readPropertiesFile("src/test/resources/config.properties");

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
