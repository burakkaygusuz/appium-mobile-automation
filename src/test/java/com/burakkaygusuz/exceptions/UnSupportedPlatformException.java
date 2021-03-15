package com.burakkaygusuz.exceptions;

public class UnSupportedPlatformException extends IllegalStateException {

    public UnSupportedPlatformException(String platformName) {
        super(String.format("Platform %s is not valid or not defined :", platformName));
    }
}
