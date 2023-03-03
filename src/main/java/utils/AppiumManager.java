package utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.Platform;

import java.io.File;
import java.net.URL;

public class AppiumManager {
    public AppiumDriverLocalService service;

    /**
     * @return Appium service builder for Android to run appium server
     * programmatically
     */
    public void startAppium() {
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        if (Platform.getCurrent().toString().equalsIgnoreCase("MAC")) {
            builder.withIPAddress("127.0.0.1").usingAnyFreePort().withAppiumJS(new File("/usr/local/bin/appium"))
                    .usingDriverExecutable(new File("/usr/local/bin/node"))
                    .withArgument(GeneralServerFlag.BASEPATH, "/wd/hub")
                    .withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        } else if (Platform.getCurrent().toString().contains("WIN")) {
            builder.withIPAddress("127.0.0.1").usingAnyFreePort()
                    .withAppiumJS(new File("mention appium installation path here"))
                    .usingDriverExecutable(new File("mention node installation path here"))
                    .withArgument(GeneralServerFlag.BASEPATH, "/wd/hub")
                    .withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        }

        service = AppiumDriverLocalService.buildService(builder);
        service.start();
    }

    /**
     * @return Get appium url from the server
     */
    public URL getAppiumUrl() {
        return service.getUrl();
    }

    /**
     * Stop the appium server
     */
    public void stopAppium() {
        if (service != null) {
            try {
                service.stop();
                System.out.println("Appium Stopped Successfully");
            } catch (Exception e) {
                if (service.isRunning()) {
                    service.stop();
                    System.out.println("Appium Stopped Successfully This Time");
                }
            }
        }
    }
}
