package pages_android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.CommonFunctions;

public class WelcomePage {


	public WelcomePage(AppiumDriver driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		commonFunctions = new CommonFunctions();
	}

	// locators of Welcome page
	@FindBy(xpath = "//android.widget.Button[@content-desc='Get Started']")
	public WebElement getStarted_Lbl;
	CommonFunctions commonFunctions;

	// this method is to click on get started button
	public void clickOnGetStarted() throws Throwable {
		Assert.assertTrue(commonFunctions.waitForVisibilityOfElement(getStarted_Lbl, "started element"));
		commonFunctions.click(getStarted_Lbl, 2);
	}

}