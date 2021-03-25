package com.burakkaygusuz.screens;

import com.burakkaygusuz.actions.MobileActions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class LoginScreen {

    private MobileActions actions;

    public LoginScreen(AppiumDriver<MobileElement> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
        actions = new MobileActions(driver);
    }

    public static LoginScreen getLoginScreen(AppiumDriver<MobileElement> driver) {
        return new LoginScreen(driver);
    }

    @AndroidFindBy(accessibility = "username")
    private MobileElement username;

    @AndroidFindBy(accessibility = "password")
    private MobileElement password;

    @AndroidFindBy(accessibility = "loginBtn")
    private MobileElement loginButton;

    public LoginScreen setUsernameAndPassword(String user, String pass) {
        username.sendKeys(user);
        password.sendKeys(pass);
        return this;
    }

    public LoginScreen clickLoginButton() {
        actions.pressByCoordinates(540,760,1);
        return this;
    }
}
