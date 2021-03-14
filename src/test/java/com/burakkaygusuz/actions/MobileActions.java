package com.burakkaygusuz.actions;

import com.burakkaygusuz.utils.PointUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

import static com.burakkaygusuz.utils.ActionUtils.*;

@SuppressWarnings("rawtypes")
public class MobileActions {

    private final AppiumDriver<MobileElement> driver;
    private final PointUtils pointUtils;

    public MobileActions(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        pointUtils = new PointUtils(driver);
    }

    public TouchAction tapByCoordinates(int x, int y) {
        return getTapAction(driver, x, y, 250);
    }

    public TouchAction pressByElement(MobileElement element, long seconds) {
        return getPressAction(driver, element, seconds);
    }

    public TouchAction pressByCoordinates(int x, int y, long seconds) {
        return getPressAction(driver, x, y, seconds);
    }

    public TouchAction horizontalSwipeByPercentage(double startPercentage, double endPercentage, double anchorPercentage) {
        int anchor = pointUtils.getPoint(anchorPercentage);
        int startPoint = pointUtils.getPoint(startPercentage);
        int endPoint = pointUtils.getPoint(endPercentage);

        return getSwipeAction(driver, anchor, startPoint, endPoint, 1000);
    }

    public TouchAction verticalSwipeByPercentages(double startPercentage, double endPercentage, double anchorPercentage) {
        int anchor = pointUtils.getPoint(anchorPercentage);
        int startPoint = pointUtils.getPoint(startPercentage);
        int endPoint = pointUtils.getPoint(endPercentage);

        return getSwipeAction(driver, anchor, startPoint, endPoint, 1000);
    }

    public TouchAction swipeByElements(MobileElement startElement, MobileElement endElement) {
        int startX = pointUtils.getXPoint(startElement);
        int startY = pointUtils.getYPoint(startElement);

        int endX = pointUtils.getXPoint(endElement);
        int endY = pointUtils.getYPoint(endElement);

        return getSwipeAction(driver, startX, startY, endX, endY, 1000);
    }
}
