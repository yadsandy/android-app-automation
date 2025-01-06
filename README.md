# Android App Automation Using Appium

This project defines the automation of android app using appium.

## Prerequisites for the project

1. Java and set JAVA_HOME
2. Maven and set MAVEN_HOME
3. NodeJS
4. Android Studio
5. Android SDK and set ANDROID_HOME
6. IDE (preferred: IntelliJ)
7. Appium 2.0

   `npm install -g appium`

8. Appium Drivers

   `appium driver install uiautomator2`

## Execution of Test Cases

Steps:

1. Connect real android device or open simulator with the system and update the `android` app and device details
   in [config.properties](src/test/resources/config.properties)
2. Check the [testng.xml](src/test/resources/testng.xml) and update or edit the classes and methods based on the need
3. You can run the test cases using Intellij and terminal.
    1. **Intellij ::**
        - Execute the [testng.xml](src/test/resources/testng.xml)
        - You can see the results in the reports folder based on the latest execution.
    2. **Terminal ::**

    * To run the test suite for android platform

      `mvn clean test`

