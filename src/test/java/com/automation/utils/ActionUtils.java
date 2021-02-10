package com.automation.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

import java.time.Duration;

@SuppressWarnings("rawtypes")
public class ActionUtils {

    public static TouchAction getSwipeAction(AppiumDriver<MobileElement> driver, int startX, int startY, int endX, int endY, int milliSeconds) {
        return new TouchAction(driver)
                .press(getPoint(startX, startY))
                .waitAction(getWaitMillis(milliSeconds))
                .moveTo(getPoint(endX, endY))
                .release().perform();
    }

    public static TouchAction getSwipeAction(AppiumDriver<MobileElement> driver, int anchor, int startPoint, int endPoint, int milliSeconds) {
        return new TouchAction(driver)
                .press(getPoint(anchor, startPoint))
                .waitAction(getWaitMillis(milliSeconds))
                .moveTo(getPoint(anchor, endPoint))
                .release().perform();
    }

    public static TouchAction getPressAction(AppiumDriver<MobileElement> driver, int x, int y, long seconds) {
        return new TouchAction(driver)
                .press(getPoint(x, y))
                .waitAction(getWaitSeconds(seconds))
                .release().perform();
    }

    public static TouchAction getPressAction(AppiumDriver<MobileElement> driver, MobileElement element, long seconds) {
        return new TouchAction(driver)
                .press(getElement(element))
                .waitAction(getWaitSeconds(seconds))
                .release().perform();
    }

    public static TouchAction getTapAction(AppiumDriver<MobileElement> driver, int x, int y, int milliseconds) {
        return new TouchAction(driver)
                .tap(getPoint(x, y))
                .waitAction(getWaitMillis(milliseconds))
                .perform();
    }

    private static WaitOptions getWaitMillis(int milliSeconds) {
        return WaitOptions.waitOptions(Duration.ofMillis(milliSeconds));
    }

    private static WaitOptions getWaitSeconds(long seconds) {
        return WaitOptions.waitOptions(Duration.ofSeconds(seconds));
    }

    private static PointOption getPoint(int x, int y) {
        return PointOption.point(x, y);
    }

    private static ElementOption getElement(MobileElement element) {
        return ElementOption.element(element);
    }
}
