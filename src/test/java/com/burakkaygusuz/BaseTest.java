package com.burakkaygusuz;

import com.burakkaygusuz.config.DriverFactory;
import com.burakkaygusuz.config.DriverType;
import com.burakkaygusuz.server.ServerManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);
    protected AppiumDriver<MobileElement> driver;

    @BeforeSuite
    public void startAppiumServer() throws Exception {
        ServerManager.startServer();
        log.info(String.format("The appium server : %s is started...", ServerManager.getServerAddress()));
    }

    @BeforeClass
    public void startSession() {
        if (driver == null)
            driver = DriverFactory.getMobileDriver(DriverType.ANDROID);
    }

    @AfterClass
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
