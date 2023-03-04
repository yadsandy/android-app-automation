package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import logger.Log;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;
import reporters.ExtentManager;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Utils {
    public static long IMPLICIT_WAIT = 20;
    private static ExtentTest test;
    private static ExtentReports extent;

    /* This function will initialize the ExtentReport object */
    public static void initializeExtentReport() {
        extent = ExtentManager.getReporter();
    }

    /* This function will initialize the ExtentTest object */
    public static void initializeExtentTest(String methodName, String description, String category) {
        test = extent.createTest(methodName + " | " + GlobalVars.platform, description).assignCategory(category);
        test.log(Status.INFO, "Test execution started.");
    }

    /*
     * This function will be called after every test method
     */
    public static void closeExtentTest() {
        test.getExtent().flush();
    }

    /* This function will log function level logs based on the result */
    public static void logFunctionLevelLogs(boolean result, String functionName) {
        if (result)
            Log.info(functionName + ": successful!");
        else
            Log.error(functionName + ": failed!");
    }

    /* This function will log each step of a test case */
    public static void logStepInfo(String message) throws IOException, InterruptedException {
        test.log(Status.PASS, message);
        Log.info("Message: " + message);
        Reporter.log(message);
    }

    /*
     * This function will return the formatted file name with date and time appended
     */
    public static String getFileName(String file) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        Calendar cal = Calendar.getInstance();
        String fileName = file + dateFormat.format(cal.getTime());
        return fileName;
    }

    /* capturing screenshot */
    public static void captureScreenshot(ITestResult result) throws IOException, InterruptedException {
        try {
            String screenshotName = Utils.getFileName(result.getName());

            try {
                File screenshot = ((TakesScreenshot) GlobalVars.driver).getScreenshotAs(OutputType.FILE);
                String path = ExtentManager.extentPath + "/" + screenshotName + ".png";
                File destination = new File(path);
                try {
                    FileUtils.copyFile(screenshot, destination);
                } catch (IOException e) {
                    System.out.println("Capture Failed " + e.getMessage());
                }
                test.fail(result.getThrowable().getMessage(),

                        MediaEntityBuilder.createScreenCaptureFromPath("./" + screenshotName + ".png").build());

            } catch (Exception e) {
                e.printStackTrace();
                test.fail(result.getThrowable().getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
