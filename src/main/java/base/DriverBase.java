package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import utils.TestListener;

import java.time.Duration;

@Listeners(TestListener.class)
public class DriverBase extends MobileDriverService {
    private final Logger logger = LogManager.getLogger();

    @BeforeSuite
    public void oneTimeSetup() {
        logger.debug("Test execution starts on platform: {}", "android");
    }

    @BeforeMethod
    public void openApp() {
        setDriver(spinUpDriver());
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }


    @AfterMethod
    public void closeApp() {
//        closeDriver();
        stopAppiumService();
    }
}
