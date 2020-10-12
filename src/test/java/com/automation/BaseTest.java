package com.automation;

import com.automation.config.DriverFactory;
import com.automation.server.ServerManager;
import com.automation.utils.PropertyUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    public AppiumDriver<MobileElement> driver;
    private final Properties props = PropertyUtils.readPropertiesFile("src/test/resources/config.properties");

    //Capabilities
    private final String deviceName = props.getProperty("DEVICE_NAME");
    private final String deviceId = props.getProperty("DEVICE_ID");
    private final String appPath = props.getProperty("APP_PATH");
    private final String appActivity = props.getProperty("APP_ACTIVITY");
    private final String appPackage = props.getProperty("APP_PACKAGE");

    @BeforeSuite
    public void startAppiumServer() throws IOException, InterruptedException {
        ServerManager.startServer();
        Assert.assertTrue(ServerManager.isServiceRunning(), "The appium server is not running!");
    }

    @BeforeClass
    public void startSession() {
        final DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        capabilities.setCapability(MobileCapabilityType.UDID, deviceId);
        capabilities.setCapability(MobileCapabilityType.APP, appPath);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);

        if (driver == null) {
            driver = DriverFactory.getMobileDriver(MobilePlatform.ANDROID, ServerManager.getServerAddress(), capabilities);
            driver.installApp(appPath);
        }
    }

    @AfterClass
    public void endSession() {
        if (driver != null) {
            driver.closeApp();
            driver.removeApp(appPackage);
            driver.quit();
        }
    }

    @AfterSuite(alwaysRun = true)
    public void stopAppiumServer() {
        ServerManager.stopServer();
        Assert.assertFalse(ServerManager.isServiceRunning(), "The appium server still running!");
    }

}
