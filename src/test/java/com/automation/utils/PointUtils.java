package com.automation.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.Dimension;

public class PointUtils {

    public static AppiumDriver<MobileElement> driver;

    public PointUtils(AppiumDriver<MobileElement> driver) {
        PointUtils.driver = driver;
    }

    public int getPoint(double percentage) {
        Dimension size = driver.manage().window().getSize();
        return (int) (size.width * percentage);
    }

    public int getXPoint(MobileElement element) {
        return element.getLocation().getX() + (element.getSize().getWidth() / 2);
    }

    public int getYPoint(MobileElement element) {
        return element.getLocation().getY() + (element.getSize().getHeight() / 2);
    }
}
