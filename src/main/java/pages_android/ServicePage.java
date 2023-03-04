package pages_android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.CommonFunctions;

import java.util.List;

public class ServicePage {

	// locators for service selection screen
	@FindBy(xpath = "//android.view.View/android.widget.ImageView")
	public List<WebElement> listOfServices;
	@FindBy(className = "android.widget.Button")
	public WebElement next_Btn;
	@FindBy(xpath = "//android.widget.ImageView[2]/android.view.View/android.view.View/android.view.View")
	public List<WebElement> bedroomQuantity_Lbl;
	@FindBy(xpath = "//android.widget.ImageView[3]/android.view.View/android.view.View/android.view.View")
	public List<WebElement> bathroomQuantity_Lbl;
	CommonFunctions commonFunctions;

	public ServicePage(AppiumDriver driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		commonFunctions = new CommonFunctions();
	}

	// Select the service you need from the list
	public void clickOnService(String service) throws InterruptedException {
		boolean serviceChecked = checkValueAvailability(service);
		if (!serviceChecked) {
			do {
				commonFunctions.verticalSwipeByPercentages();
				serviceChecked = checkValueAvailability(service);
			} while (!serviceChecked);
		}
	}

	// click on proceed button
	public void clickOnProceed() {
		commonFunctions.click(next_Btn, 2);
	}

	// Select service and click on proceed
	public void selectServiceFromScreenAndProceed(String service) throws InterruptedException {
		clickOnService(service);
		clickOnProceed();
	}

	// To check service is clicked or not
	public boolean checkValueAvailability(String value) throws InterruptedException {
		boolean check = false;
		Thread.sleep(2000);
		for (WebElement selectedService : listOfServices) {
			String getServiceText = commonFunctions.getAttributeValue(selectedService, "content-desc");
			if (getServiceText.equalsIgnoreCase(value)) {
				selectedService.click();
				System.out.println("service clicked");
				check = true;
				break;
			}
		}
		return check;
	}

	// To check the correct count of selected rooms
	public void verifySelectedRoomsCount(String qty) {
		String rooms = commonFunctions.getAttributeValue(next_Btn, "content-desc");
		Assert.assertTrue(qty.equalsIgnoreCase(rooms));
	}

	// select the room you want
	public void clickOnRoomFromSelectionScreen(String room) throws InterruptedException {
		boolean roomClicked = checkValueAvailability(room);
		Assert.assertTrue(roomClicked);
	}

	// select the quantity of the bedroom
	public void clickOnBedRoomQuantity(int qty) {
		bedroomQuantity_Lbl.get(qty - 1).click();
	}

	// select the quantity of the bathroom
	public void clickOnBathRoomWithQuantity(int qty) {
		bathroomQuantity_Lbl.get(qty - 1).click();
	}

	// To select the room and room quantity
	public void selectRoomAndQuantity(String room1, String room2, int bedRoomQty, int bathRoomQty)
			throws InterruptedException {
		clickOnRoomFromSelectionScreen(room1);
		clickOnBedRoomQuantity(bedRoomQty);
		clickOnRoomFromSelectionScreen(room2);
		clickOnBathRoomWithQuantity(bathRoomQty);

	}
}