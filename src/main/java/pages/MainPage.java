package pages;

import org.openqa.selenium.By;
import utils.CommonFunctions;

import static utils.Constants.ANDROID_APP_PACKAGE;

public class MainPage extends CommonFunctions {

    private static final String connectButton = ANDROID_APP_PACKAGE + ":id/btn_connect_desc";


    private void clickConnect() {
        clickOnElement(By.id(connectButton));

    }

    public void connectVPN() {
        clickConnect();
    }

}
