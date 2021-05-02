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

public enum DriverType {

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
            androidOptions.setApp(new File(PROPS.getProperty("android.app.path")).getAbsolutePath());
            androidOptions.setDeviceName(PROPS.getProperty("android.device.name"));
            androidOptions.setUdid(PROPS.getProperty("android.device.id"));
            androidOptions.setNoReset(false);
            androidOptions.setCapability(AndroidMobileCapabilityType.IS_HEADLESS, true);
            androidOptions.setCapability(AndroidMobileCapabilityType.AVD, PROPS.getProperty("avd.device.name"));
            androidOptions.setCapability(AndroidMobileCapabilityType.SKIP_UNLOCK, true);
            androidOptions.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
            androidOptions.setCapability(AndroidMobileCapabilityType.ANDROID_INSTALL_PATH, "/data/local/tmp");
            androidOptions.setCapability(AndroidMobileCapabilityType.ANDROID_INSTALL_TIMEOUT, 100000);
            androidOptions.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, PROPS.getProperty("app.activity"));
            androidOptions.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, PROPS.getProperty("app.package"));

            return androidOptions;
        }
    },

    IOS {
        @Override
        public AppiumDriver<MobileElement> getDriver() {
            return new IOSDriver<>(ServerManager.getServerAddress(), getOptions());
        }

        @Override
        public IOSOptions getOptions() {
            IOSOptions iosOptions = new IOSOptions();

            iosOptions.setPlatformName(MobilePlatform.IOS);
            iosOptions.setAutomationName(AutomationName.IOS_XCUI_TEST);
            iosOptions.setPlatformVersion("14.4");
            iosOptions.setApp(new File(PROPS.getProperty("ios.app.path")).getAbsolutePath());
            iosOptions.setDeviceName(PROPS.getProperty("ios.device.name"));
            iosOptions.setUdid(PROPS.getProperty("ios.device.id"));
            iosOptions.setNoReset(false);
            iosOptions.setCapability(IOSMobileCapabilityType.BUNDLE_ID, PROPS.getProperty("bundle.id"));
            iosOptions.setCapability(IOSMobileCapabilityType.LOCATION_SERVICES_ENABLED, true);
            iosOptions.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, true);

            return iosOptions;
        }
    };

    private static final Properties PROPS = PropertyUtils.readPropertiesFile("src/test/resources/app.properties");

    public abstract AppiumDriver<MobileElement> getDriver();
    public abstract MobileOptions<?> getOptions();

}
