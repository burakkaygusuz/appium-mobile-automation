package com.burakkaygusuz.tests;

import com.burakkaygusuz.screens.LoginScreen;
import com.burakkaygusuz.screens.MainScreen;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import static com.burakkaygusuz.screens.LoginScreen.getLoginScreen;
import static com.burakkaygusuz.screens.MainScreen.getMainScreen;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginScreenTest extends BaseTest {

    @Test()
    public void loginTest() {

        MainScreen mainScreen = getMainScreen(driver);
        LoginScreen loginScreen = getLoginScreen(driver);

        mainScreen.goToLoginScreen();
        loginScreen.setUsernameAndPassword("alice", "mypassword").clickLoginButton();

        MobileElement loginText = (MobileElement) wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        MobileBy.xpath("//android.widget.TextView[contains(@text,'You are logged in as')]")));

        assertThat(loginText.getText()).contains("alice");
    }
}
