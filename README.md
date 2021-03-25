# Appium Mobile Automation

[![Appium Java Client](https://img.shields.io/github/v/release/appium/java-client?label=Appium%20Java%20Client&style=for-the-badge)](https://github.com/appium/java-client/releases/latest)

This sample project demonstrates how to run appium server programmatically and test both Android and IOS devices as headless.

## Requirements

Make sure you have installed all the following prerequisites on your development machine:

| OS      | Java JDK                              | Maven                     | Node.js                           | Android Studio                           |
| ------- | ------------------------------------- | ------------------------- | --------------------------------- | ---------------------------------------- |
| Windows | ```choco install openjdk11```         | ```choco install maven``` | ```choco install nodejs```        | ```choco install androidstudio```        |
| Ubuntu  | ```sudo apt install openjdk-11-jdk``` | ```sdk install maven```   | ```sudo apt-get install nodejs``` | ```sudo apt install android-studio```    |
| macOS   | ```brew install openjdk@11```         | ```brew install maven```  | ```brew install node```           | ```brew install --cask android-studio``` |


**NOTE:** macOS and XCode are mandatory for testing the IOS Simulator using [XCUITest](https://github.com/appium/appium-xcuitest-driver/releases/latest).

- Install the appium server via npm:

```sh
npm install appium -g
```

- Install the appium doctor to verify node, Android, IOS configuration via npm:

```sh
npm install appium-doctor -g
```

- Diagnose the common configs using this below command:

```sh
appium-doctor --dev
```

## Commands

```sh
 git clone git@github.com:burakkaygusuz/appium-mobile-automation.git
 cd appium-mobile-automation
 mvn clean test
```
