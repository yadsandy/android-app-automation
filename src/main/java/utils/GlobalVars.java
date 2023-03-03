package utils;


import io.appium.java_client.AppiumDriver;

import java.util.Properties;

//This class will have all the global variables to be used across the classes and packages
public class GlobalVars {
    public static AppiumDriver driver;
    public static Properties prop;
    public static String platform;
    public static String deviceNameAndroid;
    public static String workingDir;
    public static String platformVersionAndroid;
    public static String fileDir;
    public static String apkFileName;
    public static String automationName;
    public static String appPackage;
    public static String appActivity;
    public static String appWaitPackage;
    public static String otpAuthConnectionURL;
    public static String otpAuthDbUserName;
    public static String otpAuthDbPassword;
}
