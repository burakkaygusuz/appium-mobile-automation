package com.automation;

import com.automation.config.DriverFactory;
import com.automation.config.DriverType;
import com.automation.server.ServerManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);
    private final DesiredCapabilities capabilities = new DesiredCapabilities();
    protected AppiumDriver<MobileElement> driver;

    @BeforeSuite
    public void startAppiumServer() throws Exception {
        ServerManager.startServer();
        log.info("The appium server is started...");
    }

    @BeforeClass
    public void startSession() {
        if (driver == null)
            driver = DriverFactory.getMobileDriver(DriverType.ANDROID, ServerManager.getServerAddress(), capabilities);
    }

    @AfterClass
    public void endSession() {
        if (driver != null) {
            driver.closeApp();
            driver.quit();
        }
    }

    @AfterSuite(alwaysRun = true)
    public void stopAppiumServer() {
        ServerManager.stopServer();
        log.info("The appium server is stopped...");
    }

}
