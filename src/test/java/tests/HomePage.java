package tests;

import org.testng.annotations.Test;
import base.TestBase;
import pages_android.SchedulePage;
import pages_android.ServicePage;
import pages_android.WelcomePage;
import utils.Utils;

import java.lang.reflect.Method;
public class HomePage extends TestBase {

    @Test(enabled = true, description = "This method to verify the repeatable options on schedule screen")
    public void validSchedulerOptions(Method method) throws Throwable {
        WelcomePage welcomePage = new WelcomePage(driver);
        ServicePage servicePage = new ServicePage(driver);
        SchedulePage schedulePage = new SchedulePage(driver);
        String service = dataElementMap.get(method.getName()).getParams().trim().split(",")[0];
        String bedroom = dataElementMap.get(method.getName()).getParams().trim().split(",")[1];
        String bathroom = dataElementMap.get(method.getName()).getParams().trim().split(",")[2];
        String qtyOfBedroom = dataElementMap.get(method.getName()).getParams().trim().split(",")[3];
        String qtyOfBathroom = dataElementMap.get(method.getName()).getParams().trim().split(",")[4];
        String totalQty = dataElementMap.get(method.getName()).getParams().trim().split(",")[5];
        String repeatValues = dataElementMap.get(method.getName()).getParams().trim().split(",")[6];

        welcomePage.clickOnGetStarted();
        Utils.logStepInfo("successfully click on get started");

        servicePage.selectServiceFromScreenAndProceed(service);
        Utils.logStepInfo("service is successfully selected");

        servicePage.selectRoomAndQuantiy(bedroom, bathroom, Integer.parseInt(qtyOfBedroom),
                Integer.parseInt(qtyOfBathroom));
        Utils.logStepInfo("rooms and quantity are selected is successfully selected");

        servicePage.verifySelectedRoomsCount(totalQty);
        Utils.logStepInfo("total quantity is verified");

        servicePage.clickOnProceed();
        Utils.logStepInfo("clicked on proceed option.");

        schedulePage.checkRepeatValues(repeatValues);
        Utils.logStepInfo("repeated values verified successfully.");

    }

}
