package com.automation.config;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public interface DriverOptions {
    AppiumDriver getDriverOptions(URL url, DesiredCapabilities desiredCapabilities);
}
