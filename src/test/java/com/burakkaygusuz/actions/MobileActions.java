package com.burakkaygusuz.actions;

import com.burakkaygusuz.enums.Directions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;

import java.time.Duration;

@SuppressWarnings("rawtypes")
public class MobileActions {

    private final AppiumDriver<MobileElement> driver;

    public MobileActions(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public TouchAction singleTapByElement(MobileElement element, long milliSeconds) {
        return new TouchAction(driver).tap(new TapOptions()
                .withElement(ElementOption.element(element)))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(milliSeconds)))
                .release().perform();
    }

    public TouchAction singleTapByCoordinates(int x, int y, long milliSeconds) {
        return new TouchAction(driver).tap(PointOption.point(x, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(milliSeconds)))
                .release().perform();
    }

    public TouchAction doubleTapByElement(MobileElement element, long milliSeconds) {
        return new TouchAction(driver).tap(new TapOptions().withTapsCount(2).withElement(ElementOption.element(element)))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(milliSeconds)))
                .release().perform();
    }

    public TouchAction pressByElement(MobileElement element, long seconds) {
        return new TouchAction(driver).press(ElementOption.element(element))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(seconds)))
                .release().perform();
    }

    public TouchAction pressByCoordinates(int x, int y, long seconds) {
        return new TouchAction(driver).press(PointOption.point(x, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(seconds)))
                .release().perform();
    }

    public TouchAction swipeScreen(Directions direction) {

        PointOption pointOptionStart, pointOptionEnd;
        Dimension dimension = driver.manage().window().getSize();

        pointOptionStart = PointOption.point(dimension.width / 2, dimension.height / 2);

        int edgeBorder = 10;

        switch (direction) {
            case DOWN:
                pointOptionEnd = PointOption.point(dimension.width / 2, dimension.height - edgeBorder);
                break;
            case UP:
                pointOptionEnd = PointOption.point(dimension.width / 2, edgeBorder);
                break;
            case LEFT:
                pointOptionEnd = PointOption.point(edgeBorder, dimension.height / 2);
                break;
            case RIGHT:
                pointOptionEnd = PointOption.point(dimension.width - edgeBorder, dimension.height / 2);
                break;
            default:
                throw new IllegalArgumentException("swipeScreen(): dir: '" + direction + "' not supported");
        }

        return new TouchAction(driver).press(pointOptionStart)
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(200)))
                .moveTo(pointOptionEnd)
                .release().perform();
    }

}
