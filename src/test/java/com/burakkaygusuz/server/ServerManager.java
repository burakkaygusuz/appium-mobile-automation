package com.burakkaygusuz.server;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import static com.burakkaygusuz.utils.PathUtils.getAppiumJSPath;
import static com.burakkaygusuz.utils.PathUtils.getNodeJSPath;

public class ServerManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerManager.class);
    private static AppiumDriverLocalService service;

    public static void startServer() {
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();

        try {
            try {
                serviceBuilder
                        .usingDriverExecutable(getNodeJSPath())
                        .withAppiumJS(getAppiumJSPath())
                        .withIPAddress("127.0.0.1")
                        .usingPort(4723)
                        .withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
                        .withArgument(GeneralServerFlag.LOG_LEVEL, "warn")
                        .withArgument(GeneralServerFlag.RELAXED_SECURITY);
            } catch (IOException | InterruptedException e) {
                LOGGER.error(String.format("An error occurred while building the Appium service: \n %s", ExceptionUtils.getStackTrace(e)));
            }

            HashMap<String, String> environment = new HashMap<>();
            environment.put("PATH", "/usr/local/bin:" + System.getenv("PATH")); //Path to carthage
            environment.put("ANDROID_HOME", System.getenv("ANDROID_HOME")); //Path to Android SDK
            environment.put("JAVA_HOME", System.getProperty("java.home")); // Path to JAVA_HOME

            serviceBuilder.withEnvironment(environment);
            service = AppiumDriverLocalService.buildService(serviceBuilder);
            service.start();
            LOGGER.info("Appium server has been started");
        } catch (AppiumServerHasNotBeenStartedLocallyException e) {
            LOGGER.error(String.format("Appium server has not been started as locally: \n %s", ExceptionUtils.getStackTrace(e)));
            throw e;
        }
    }

    public static URL getServerAddress() {
        return service.getUrl();
    }

    public static void stopServer() {
        try {
            Runtime.getRuntime().exec("adb -e emu kill"); // kill the headless Android emulator
        } catch (IOException e) {
            LOGGER.error(String.format("An error occurred while executing the ADB command: \n %s", ExceptionUtils.getStackTrace(e)));
        }
        service.stop();
        LOGGER.info(String.format("Appium server has been stopped.Is service running?: %s", isServiceRunning()));
    }

    public static boolean isServiceRunning() {
        return service.isRunning();
    }
}
