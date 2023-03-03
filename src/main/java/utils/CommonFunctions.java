package utils;

import base.TestBase;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import logger.Log;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class CommonFunctions extends TestBase {

    /* Function to click element without any wait */
    public boolean click(WebElement element, int i) {
        boolean isElementClicked = false;
        WebDriverWait wait = null;
        try {
            wait = new WebDriverWait(GlobalVars.driver, Duration.ofSeconds(i));
            switch (GlobalVars.platform) {
                case "android":
                case "ios":
                    wait.until(ExpectedConditions.visibilityOf(element));
                    element.click();
                    isElementClicked = true;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + GlobalVars.platform);
            }
            Utils.logFunctionLevelLogs(isElementClicked, "clickElement");
        } catch (Exception e) {
            isElementClicked = false;
            Log.error("Exception occurred in clickElement method: " + e.getMessage());
        } finally {
            Utils.captureScreenshotNew();
        }
        return isElementClicked;
    }

    // function is to check the visibility
    public boolean waitForVisibilityOfElement(WebElement element, String locator) throws Throwable {
        boolean iswaitForVisibilityOfElement = false;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            iswaitForVisibilityOfElement = true;
            Utils.logFunctionLevelLogs(iswaitForVisibilityOfElement, "waitForVisibilityOfElement");
            return true;
        } catch (Exception e) {
            Assert.assertTrue(iswaitForVisibilityOfElement, " Element " + locator + " is not visible");
            return false;
        } finally {
            Utils.captureScreenshotNew();
        }
    }

    public String getAttributeValue(WebElement element, String attribute) {
        String value = null;
        try {
            value = element.getAttribute(attribute);
            if (value != null) {
                System.out.println(value);
            }
        } catch (Exception e) {
        }
        return value;
    }

    /**
     * This function is used for slide using co-ordinates
     */
    public boolean slideByCordinate(int x1, int y1, int x2, int y2) throws Throwable {
        Dimension size = driver.manage().window().getSize();
        int height = size.getHeight();
        y1 = (int) (height * 0.7);
        y2 = (int) (height * 0.2);
        try {
            new TouchAction((PerformsTouchActions) GlobalVars.driver).press(PointOption.point(x1, y1))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(x2, y1))
                    .release().perform();

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    /**
     * This function is used for vertical scroll
     */

    public void verticalSwipeByPercentages() {
        Dimension size = driver.manage().window().getSize();
        int width = size.getWidth();
        int height = size.getHeight();
        int startx = width / 2;
        int starty = (int) (height * 0.9);
        int endx = width / 2;
        int endy = (int) (height * 0.2);
        new TouchAction((PerformsTouchActions) driver).press(PointOption.point(startx, starty))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(endx, endy))
                .release().perform();
    }

}
