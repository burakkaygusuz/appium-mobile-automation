package com.automation.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public interface DriverOptions {
    AppiumDriver<MobileElement> getDriverOptions(URL url, DesiredCapabilities desiredCapabilities);
}
