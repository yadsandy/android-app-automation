package base;

import io.appium.java_client.SupportsLegacyAppManagement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import logger.Log;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utils.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase extends GlobalVars {

    public static int stepNumber = 0;
    static String driverUrl = "";
    static String className = null;
    protected Map<String, DataElements> dataElementMap = null;
    AppiumManager appiumManager = new AppiumManager();
    DataReader oDataReader = null;
    public void initGlobalVars() {
        try {
            GlobalVars.prop = new Properties();
            GlobalVars.workingDir = System.getProperty("user.dir");
            oDataReader = new DataReader();
            oDataReader.setupDataSheet();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public void initializeDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        switch (GlobalVars.platform) {
            case "android":
                String path = GlobalVars.fileDir;
                File appDir = new File(path);
                File appName = new File(appDir, GlobalVars.apkFileName);
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, GlobalVars.deviceNameAndroid);
                capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, GlobalVars.platformVersionAndroid);
                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, GlobalVars.platform);
                capabilities.setCapability(MobileCapabilityType.APP, appName.getAbsolutePath());
                capabilities.setCapability("newCommandTimeout", 50000);
                capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
                GlobalVars.driver = new AndroidDriver(appiumManager.getAppiumUrl(), capabilities);
                GlobalVars.driver.manage().timeouts().implicitlyWait(Utils.IMPLICIT_WAIT, TimeUnit.SECONDS);
        }
    }

    @BeforeSuite
    public void before() throws MalformedURLException {
        appiumManager.startAppium();
        initGlobalVars();
        initializeDriver();
    }


    @BeforeClass
    public void classDataInitializer() throws MalformedURLException {
        className = this.getClass().getSimpleName();
        oDataReader = new DataReader();
        dataElementMap = oDataReader.getClassData(className);
    }

    @BeforeMethod
    public void initializeExtentTest(Method method) throws IOException, InterruptedException {
        stepNumber = 1;
        Utils.initializeExtentReport();
        DOMConfigurator.configure("log4j.xml");
        Log.initializeLogProperties();
        Test test = method.getAnnotation(Test.class);
        Utils.initializeExtentTest(method.getName(), test.description(), this.getClass().getName());
        Log.startTestCase(method.getName());
        Utils.logStepInfo("---- Demo app Launched ----");
    }

    @AfterMethod
    public void afterMethod(ITestResult result) throws IOException, InterruptedException {

        if (result.getStatus() == ITestResult.FAILURE) {
            Utils.captureScreenshot(result);
        }
        Log.endTestCase(result.getTestName());
        ((SupportsLegacyAppManagement) GlobalVars.driver).resetApp();
        System.out.println("****************************************");
    }

    @AfterSuite
    public void tearDownSuite(ITestContext context) {
        Utils.closeExtentTest();
        driver.quit();
        appiumManager.stopAppium();
    }

}
