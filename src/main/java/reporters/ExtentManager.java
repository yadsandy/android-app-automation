package reporters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {

    public static final String EXTENT_REPORTS = "ExtentReports";
    public static String extentpath = System.getProperty("user.dir") + "/target";
    public static String extentHtmlFile = EXTENT_REPORTS + ".html";
    public static String extentScreenShot = "screenshot.png";
    private static ExtentReports extent = null;

    public synchronized static ExtentReports getReporter() {
        if (extent == null) {

            String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
            extentpath = extentpath + "//" + EXTENT_REPORTS + timeStamp;
            File file = new File(extentpath);
            if (!file.exists()) {
                boolean bool = file.mkdir();
                if (bool) {
                    System.out.println("Directory created successfully");
                } else {
                    System.out.println("Sorry couldn't create specified directory");
                }
            }

            extentHtmlFile = file.getAbsolutePath() + "//" + extentHtmlFile;
            extentScreenShot = file.getAbsolutePath() + "//" + extentScreenShot;
            ExtentSparkReporter html = new ExtentSparkReporter(extentHtmlFile);

            extent = new ExtentReports();
            extent.attachReporter(html);
        }
        return extent;
    }

}
