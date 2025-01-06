package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;

import static utils.Constants.*;

public class MobileDriverService {
    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    private static AndroidDriver androidDriver;
    private AppiumDriverLocalService appiumService;

    public static AppiumDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(AndroidDriver driver) {
        MobileDriverService.driver.set(driver);
    }

    public void startAppiumService() {
        appiumService = AppiumDriverLocalService.buildService(
                new AppiumServiceBuilder()
                        .withIPAddress(APPIUM_SERVER_IP)
                        .usingPort(4723)
        );
        appiumService.start();
    }

    public void stopAppiumService() {
        appiumService.stop();
    }

    public AndroidDriver spinUpDriver() {
        startAppiumService();
        UiAutomator2Options options = new UiAutomator2Options()
                .setUdid(ANDROID_DEVICE_NAME)
                .setApp(new File(ANDROID_APP_FILE_PATH).getAbsolutePath())
                .setAppPackage(ANDROID_APP_PACKAGE).setAppActivity(ANDROID_APP_ACTIVITY)
                .setNoReset(Boolean.parseBoolean(ANDROID_NO_RESET))
                .setFullReset(Boolean.parseBoolean(ANDROID_FULL_RESET))
                .autoGrantPermissions();

        androidDriver = new AndroidDriver(appiumService.getUrl(), options);
        androidDriver.manage().timeouts().implicitlyWait(APPIUM_DRIVER_TIMEOUT);

        return androidDriver;
    }

    public void closeDriver() {
        androidDriver.close();
    }

}