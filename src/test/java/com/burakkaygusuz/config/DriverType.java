package com.burakkaygusuz.config;

import com.burakkaygusuz.server.ServerManager;
import com.burakkaygusuz.utils.PropertyUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidOptions;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSOptions;
import io.appium.java_client.remote.*;

import java.io.File;
import java.util.Properties;

public enum DriverType implements DriverOptions {

    ANDROID {
        @Override
        public AppiumDriver<MobileElement> getDriver() {
            return new AndroidDriver<>(ServerManager.getServerAddress(), getOptions());
        }

        @Override
        public AndroidOptions getOptions() {
            AndroidOptions androidOptions = new AndroidOptions();

            androidOptions.setPlatformName(MobilePlatform.ANDROID);
            androidOptions.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
            androidOptions.setPlatformVersion("10");
            androidOptions.setApp(new File(props.getProperty("ANDROID_APP_PATH")).getAbsolutePath());
            androidOptions.setDeviceName(props.getProperty("ANDROID_DEVICE_NAME"));
            androidOptions.setUdid(props.getProperty("ANDROID_DEVICE_ID"));
            androidOptions.setNoReset(false);
            androidOptions.setCapability(AndroidMobileCapabilityType.IS_HEADLESS, true);
            androidOptions.setCapability(AndroidMobileCapabilityType.AVD, props.getProperty("AVD_DEVICE_NAME"));
            androidOptions.setCapability(AndroidMobileCapabilityType.SKIP_UNLOCK, true);
            androidOptions.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
            androidOptions.setCapability(AndroidMobileCapabilityType.ANDROID_INSTALL_PATH, "/data/local/tmp");
            androidOptions.setCapability(AndroidMobileCapabilityType.ANDROID_INSTALL_TIMEOUT, 100000);
            androidOptions.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, props.getProperty("APP_ACTIVITY"));
            androidOptions.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, props.getProperty("APP_PACKAGE"));

            return androidOptions;
        }
    },

    IOS {
        @Override
        public AppiumDriver<MobileElement> getDriver() {
            return new IOSDriver<>(ServerManager.getServerAddress(), getOptions());
        }

        @Override
        public MobileOptions getOptions() {
            IOSOptions iosOptions = new IOSOptions();

            iosOptions.setPlatformName(MobilePlatform.IOS);
            iosOptions.setAutomationName(AutomationName.IOS_XCUI_TEST);
            iosOptions.setPlatformName("13");
            iosOptions.setApp(new File(props.getProperty("IOS_APP_PATH")).getAbsolutePath());
            iosOptions.setDeviceName(props.getProperty("IOS_DEVICE_NAME"));
            iosOptions.setUdid(props.getProperty("IOS_DEVICE_ID"));
            iosOptions.setNoReset(false);
            iosOptions.setCapability(IOSMobileCapabilityType.BUNDLE_ID, props.getProperty("BUNDLE_ID"));
            iosOptions.setCapability(IOSMobileCapabilityType.LOCATION_SERVICES_ENABLED, true);
            iosOptions.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, true);

            return iosOptions;
        }
    };

    private static final Properties props = PropertyUtils.readPropertiesFile("src/test/resources/config.properties");

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
