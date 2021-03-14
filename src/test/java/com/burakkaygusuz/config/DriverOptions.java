package com.burakkaygusuz.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileOptions;

public interface DriverOptions<T extends MobileOptions<T>> {

    AppiumDriver<MobileElement> getDriver();

    MobileOptions<T> getOptions();
}
