package com.automation.config;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public interface AppiumDriverOptions {
    AppiumDriver getDriverOptions(URL url, DesiredCapabilities desiredCapabilities);
}
