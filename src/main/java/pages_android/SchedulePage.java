package pages_android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.CommonFunctions;
import utils.GlobalVars;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SchedulePage {

    // List of all the schedule page locators
    @FindBy(xpath = "//android.view.View[@content-desc='Repeat']/following-sibling::android.view.View[1]/android.view.View/android.view.View")
    public List<WebElement> getRepeatOptions_Txt;
    CommonFunctions commonFunctions;

    public SchedulePage(AppiumDriver driver) {
        driver = GlobalVars.driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        commonFunctions = new CommonFunctions();
    }

    // This method is to check the repeat values and compare it with the actual
    // values
    public void checkRepeatValues(String values) throws Throwable {
        Set<String> set = new HashSet<String>();
        for (WebElement selectedService : getRepeatOptions_Txt) {
            set.add(commonFunctions.getAttributeValue(selectedService, "content-desc"));
        }
        commonFunctions.slideByCordinate(900, 1400, 300, 1400);
        for (WebElement selectedService : getRepeatOptions_Txt) {
            set.add(commonFunctions.getAttributeValue(selectedService, "content-desc"));
        }
        for (String string : set) {
            Assert.assertTrue(values.contains(string));
        }

    }

}