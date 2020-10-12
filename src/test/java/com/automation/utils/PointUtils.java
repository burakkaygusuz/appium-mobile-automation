package com.automation.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.Dimension;

public class PointUtils {

    public static AppiumDriver<MobileElement> driver;

    public PointUtils(AppiumDriver<MobileElement> driver) {
        PointUtils.driver = driver;
    }

    public static int getPoint(double percentage) {
        Dimension size = driver.manage().window().getSize();
        return (int) (size.width * percentage);
    }

    public static int getXPoint(MobileElement element) {
        return element.getLocation().getX() + (element.getSize().getWidth() / 2);
    }

    public static int getYPoint(MobileElement element) {
        return element.getLocation().getY() + (element.getSize().getHeight() / 2);
    }
}
