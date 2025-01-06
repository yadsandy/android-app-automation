package tests;

import base.DriverBase;
import org.testng.annotations.Test;
import pages.MainPage;


public class MainTest extends DriverBase {

    @Test(description = "Verify the VPN connection")
    public void testVPNConnection() {
        MainPage mainPage = new MainPage();
        mainPage.connectVPN();
    }

}
