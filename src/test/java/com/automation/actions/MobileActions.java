package com.automation.actions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

import static com.automation.utils.ActionUtils.*;
import static com.automation.utils.PointUtils.*;

public class MobileActions {

    private final AppiumDriver<MobileElement> driver;

    public MobileActions(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public TouchAction tapByCoordinates(int x, int y) {
       return getTapAction(driver,x,y,250);
    }

    public TouchAction pressByElement(MobileElement element, long seconds) {
        return getPressAction(driver,element,seconds);
    }

    public TouchAction pressByCoordinates(int x, int y, long seconds) {
       return getPressAction(driver,x,y,seconds);
    }

    public TouchAction horizontalSwipeByPercentage(double startPercentage, double endPercentage, double anchorPercentage) {
        int anchor = getPoint(anchorPercentage);
        int startPoint = getPoint(startPercentage);
        int endPoint = getPoint(endPercentage);

       return getSwipeAction(driver,anchor,startPoint,endPoint,1000);
    }

    public TouchAction verticalSwipeByPercentages(double startPercentage, double endPercentage, double anchorPercentage) {
        int anchor = getPoint(anchorPercentage);
        int startPoint = getPoint(startPercentage);
        int endPoint = getPoint(endPercentage);

       return getSwipeAction(driver,anchor,startPoint,endPoint,1000);
    }

    public TouchAction swipeByElements(MobileElement startElement, MobileElement endElement) {
        int startX = getXPoint(startElement);
        int startY = getYPoint(startElement);

        int endX = getXPoint(endElement);
        int endY = getYPoint(endElement);

      return getSwipeAction(driver,startX,startY,endX,endY,1000);
    }
}
