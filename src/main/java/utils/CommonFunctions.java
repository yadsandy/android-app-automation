package utils;

import org.openqa.selenium.By;

import static base.MobileDriverService.getDriver;

public class CommonFunctions {

    public void clickOnElement(By by) {
        getDriver().findElement(by).click();
    }


}
