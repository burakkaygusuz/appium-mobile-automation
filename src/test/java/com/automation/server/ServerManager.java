package com.automation.server;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import static com.automation.utils.PathUtils.getJSPath;
import static com.automation.utils.PathUtils.getNodePath;

public class ServerManager {

    private static final AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
    private static AppiumDriverLocalService service;

    public static void startServer() throws IOException, InterruptedException {
        serviceBuilder
                .usingDriverExecutable(new File(getNodePath()))
                .withAppiumJS(new File(getJSPath()))
                .usingPort(4723)
                .withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
                .withArgument(GeneralServerFlag.LOG_LEVEL, "info")
                .withArgument(GeneralServerFlag.RELAXED_SECURITY);

        HashMap<String, String> environment = new HashMap<>();
        environment.put("PATH", "/usr/local/bin:" + System.getenv("PATH")); //Path to carthage
        environment.put("ANDROID_HOME", System.getenv("ANDROID_HOME")); //Path to Android SDK
        environment.put("JAVA_HOME", System.getProperty("java.home")); // Path to JAVA_HOME

        serviceBuilder.withEnvironment(environment);

        service = AppiumDriverLocalService.buildService(serviceBuilder);
        service.start();
        service.clearOutPutStreams();
    }

    public static URL getServerAddress() {
        return service.getUrl();
    }

    public static void stopServer() {
        service.stop();
    }

    public static boolean isServiceRunning() {
        return service.isRunning();
    }
}
