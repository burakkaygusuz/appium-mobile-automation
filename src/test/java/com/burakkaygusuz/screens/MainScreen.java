package com.burakkaygusuz.screens;

import com.burakkaygusuz.actions.MobileActions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class MainScreen {

    private MobileActions actions;

    public MainScreen(AppiumDriver<MobileElement> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        actions = new MobileActions(driver);
    }

    public static MainScreen getMainScreen(AppiumDriver<MobileElement> driver) {
        return new MainScreen(driver);
    }

    @AndroidFindBy(accessibility = "Login Screen")
    private MobileElement loginScreenButton;

    public MainScreen goToLoginScreen() {
        actions.pressByElement(loginScreenButton,1);
        return this;
    }
}
