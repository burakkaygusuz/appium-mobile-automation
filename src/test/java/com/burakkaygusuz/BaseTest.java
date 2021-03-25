package com.burakkaygusuz;

import com.burakkaygusuz.config.DriverFactory;
import com.burakkaygusuz.enums.Platforms;
import com.burakkaygusuz.server.ServerManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

public class BaseTest {

    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);
    protected AppiumDriver<MobileElement> driver;
    protected WebDriverWait wait;

    @BeforeSuite
    public void startAppiumServer() throws Exception {
        ServerManager.startServer();
        log.info("The appium server is started...");
    }

    @BeforeTest
    public void startSession() {
        if (driver == null)
            driver = DriverFactory.getMobileDriver(Platforms.ANDROID);
        if (wait == null)
            wait = new WebDriverWait(driver, 20);
    }

    @BeforeClass
    public void beforeClass() {
        log.info(String.format("Automation Name  : %s", driver.getAutomationName()));
        log.info(String.format("Platform         : %s", driver.getPlatformName().toUpperCase()));
        log.info(String.format("Version          : %s", driver.getCapabilities().getCapability(MobileCapabilityType.PLATFORM_VERSION)));
        log.info(String.format("Device Name      : %s", driver.getCapabilities().getCapability(MobileCapabilityType.DEVICE_NAME)));
        log.info(String.format("Remote Address   : %s", driver.getRemoteAddress()));
    }

    @AfterTest
    public void endSession() {
        if (driver != null) {
            driver.closeApp();
            driver.quit();
        }
    }

    @AfterSuite(alwaysRun = true)
    public void stopAppiumServer() throws Exception {
        if (ServerManager.isServiceRunning())
            ServerManager.stopServer();
        log.info("The appium server is stopped...");
    }

}
