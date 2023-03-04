package pages_android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.CommonFunctions;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SchedulePage {

    
	public SchedulePage(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        commonFunctions = new CommonFunctions();
    }
	
	// List of all the schedule page locators
    @FindBy(xpath = "//android.view.View[@content-desc='Repeat']/following-sibling::android.view.View[1]/android.view.View/android.view.View")
    public List<WebElement> getRepeatOptions_Txt;
    CommonFunctions commonFunctions;

    // This method is to check the repeat values and compare it with the actual
    // values
    public void checkRepeatValues(String values) throws Throwable {
        Set<String> set = new HashSet<String>();
        Assert.assertTrue(
                commonFunctions.waitForVisibilityOfElement(getRepeatOptions_Txt.get(0), "Repeat option not visible"));
        for (WebElement selectedService : getRepeatOptions_Txt) {
            set.add(commonFunctions.getAttributeValue(selectedService, "content-desc"));
        }
        commonFunctions.slideVerticalByCoordinate(0.7, 0.7, 0.2, 0.7);
        for (WebElement selectedService : getRepeatOptions_Txt) {
            set.add(commonFunctions.getAttributeValue(selectedService, "content-desc"));
        }
        String valuesFromUI = set.toString().replaceAll("[^a-zA-Z0-9]", " ").trim();
        Assert.assertEquals(values, valuesFromUI, "Expected values are: " + valuesFromUI);

    }

}