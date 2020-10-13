package com.automation;

import com.automation.config.DriverFactory;
import com.automation.server.ServerManager;
import com.automation.utils.PropertyUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);
    public AppiumDriver<MobileElement> driver;
    private final DesiredCapabilities capabilities = new DesiredCapabilities();
    private final Properties props = PropertyUtils.readPropertiesFile("src/test/resources/config.properties");

    @BeforeSuite
    public void startAppiumServer() throws IOException, InterruptedException {
        ServerManager.startServer();
        Assert.assertTrue(ServerManager.isServiceRunning(), "The appium server is not running!");
        log.info("The appium server is started...");
    }

    @BeforeClass
    public void startSession() {
        if (driver == null)
            driver = DriverFactory.getMobileDriver(ANDROID, ServerManager.getServerAddress(), capabilities);
    }

    @AfterClass
    public void endSession() {
        if (driver != null) {
            driver.closeApp();
            driver.removeApp(props.getProperty("APP_PACKAGE"));
            driver.quit();
        }
    }

    @AfterSuite(alwaysRun = true)
    public void stopAppiumServer() {
        ServerManager.stopServer();
        Assert.assertFalse(ServerManager.isServiceRunning(), "The appium server still running!");
        log.info("The appium server is stopped...");
    }

}
