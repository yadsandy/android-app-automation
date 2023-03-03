package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.SkipException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class DataReader {

    private static final List<Data> dataObjectRepo = new ArrayList<Data>();
    public static String suiteName;
    public static Map<String, String> pageObjRepoMap = new HashMap<String, String>();
    File baseDirectory = new File(".");
    Workbook workBook = null;
    org.apache.poi.ss.usermodel.Sheet generalConfigSheet = null;

    public static List<Data> getDataObjectRepo() {
        return dataObjectRepo;
    }

    public static String readProperty(String property) {
        Properties prop;
        String value = null;
        try {
            prop = new Properties();
            prop.load(new FileInputStream(new File("config.properties")));

            value = prop.getProperty(property);

            if (value == null || value.isEmpty()) {
                throw new Exception("Value not set or empty");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }

    public void setupDataSheet() throws IOException {
        String testDataPath = baseDirectory.getCanonicalPath() + "//src//main//resources//TestData//testdata";
        setDataObject(testDataPath + ".xlsx");
    }

    /**
     * Presets all test case data from the excel sheet.
     * <br> This should be only be referenced in before scenario methods.
     *
     * @author sandeep
     */
    private void setDataObject(String dataObjectFilePath) throws IOException {
        //long startTime = System.currentTimeMillis();
        File file = new File(dataObjectFilePath);
        if (file.exists() && !file.isDirectory()) {
            FileInputStream inputStream = new FileInputStream(file);
            workBook = new XSSFWorkbook(inputStream);
            int totalSheetCount = workBook.getNumberOfSheets();
            try {

                //Initializing GeneralConfig data into Global variables
                //org.apache.poi.ss.usermodel.Sheet generalConfigSheet = workBook.getSheetAt(0);
                generalConfigSheet = workBook.getSheet("GeneralConfig");

                //This function will initialize all general config variables based on the column names
                initializegeneralConfigData();
                //Initializing the test cases
                for (int sheetNumber = 1; sheetNumber < totalSheetCount; sheetNumber++) {
                    //Sheet sheet = (Sheet) workBook.getSheetAt(sheetNumber);
                    XSSFSheet sheet = (XSSFSheet) workBook.getSheetAt(sheetNumber);
                    Map<String, DataElements> dataElementsMap = getDataElements(sheet);

                    dataObjectRepo.add(new Data(sheet.getSheetName(), dataElementsMap));
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            //logger.error("Data object file not found at: " + file.getAbsolutePath());
            throw new SkipException("Data object file not found at: " + file.getAbsolutePath());
        }
    }

    /**
     * @param cell
     * @return
     * @description: This function takes a cell as an argument and returns the cell
     * value based on the type of cell value type
     */
    @SuppressWarnings("deprecation")
    public String getCellData(Cell cell) {
        String cellData = "";
        if (cell != null) {
            try {

                switch (cell.getCellType()) {

                    case STRING:
                        cellData = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        cellData = (int) cell.getNumericCellValue() + "";
                        break;
                    case BLANK:
                        cellData = "";
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /*
         * else { System.out.println("Cell is null"); }
         */
        return cellData.trim();
    }

    /**
     * @param
     * @return
     * @description: This function takes a field name as an argument and returns the
     * cell value
     */
    @SuppressWarnings("deprecation")
    public String initializegeneralConfigData() {
        String cellData = "";
        try {
            Iterator rowIterator = generalConfigSheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = (Row) rowIterator.next();
                cellData = getCellData(row.getCell(0));
                initializeGeneralConfigVariables(cellData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cellData.trim();
    }

    /**
     * @param fieldName
     * @return
     * @description: This function returns the value of each field
     */
    @SuppressWarnings("deprecation")
    public String getFieldValue(String fieldName) {
        String cellValue = "";
        try {
            Iterator rowIterator = generalConfigSheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = (Row) rowIterator.next();
                if (getCellData(row.getCell(0)).equalsIgnoreCase(fieldName))
                    cellValue = getCellData(row.getCell(1));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // System.out.println("The value of "+fieldName+" : is: "+cellValue);
        return cellValue.trim();

    }

    /**
     * @param fieldName
     * @return
     * @description: This function initializes the general config variables
     */
    public void initializeGeneralConfigVariables(String fieldName) {
        try {

            switch (fieldName) {
                case Constants.PLATFORM:
                    GlobalVars.platform = getFieldValue(fieldName);
                    break;
                case Constants.APK_FILE_NAME:
                    GlobalVars.apkFileName = getFieldValue(fieldName);
                    break;
                case Constants.DEVICE_NAME_ANDROID:
                    GlobalVars.deviceNameAndroid = getFieldValue(fieldName);
                    break;
                case Constants.PLATFORM_VERSION_ANDROID:
                    GlobalVars.platformVersionAndroid = getFieldValue(fieldName);
                    break;
                case Constants.FILE_DIR:
                    GlobalVars.fileDir = getFieldValue(fieldName);
                    break;
                case Constants.AUTOMATION_NAME:
                    GlobalVars.automationName = getFieldValue(fieldName);
                    break;
                case Constants.APP_PACKAGE:
                    GlobalVars.appPackage = getFieldValue(fieldName);
                    break;
                case Constants.APP_ACTIVITY:
                    GlobalVars.appActivity = getFieldValue(fieldName);
                    break;
                case Constants.APP_WAIT_ACTIVITY:
                    GlobalVars.appWaitPackage = getFieldValue(fieldName);
                    break;
                case Constants.OTP_AUTH_CONNECTION_URL:
                    GlobalVars.otpAuthConnectionURL = getFieldValue(fieldName);
                    break;
                case Constants.OTP_AUTH_DB_USERNAME:
                    GlobalVars.otpAuthDbUserName = getFieldValue(fieldName);
                    break;
                case Constants.OTP_AUTH_DB_PASSWORD:
                    GlobalVars.otpAuthDbPassword = getFieldValue(fieldName);
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * support method for getPageObjects <br>
     * Fetches all page elements in a page
     *
     * @param sheet takes the Sheet object returned from the workbook
     * @return return the map of page elements with element name and object of
     * pageElement for the same.
     */
    private Map<String, DataElements> getDataElements(org.apache.poi.ss.usermodel.Sheet sheet) {
        Map<String, DataElements> dataElementsMap = new HashMap<String, DataElements>();
        String testCaseName = "";
        String runStatus = "";
        String params = "";
        // Ignoring title row [0] starting from row [1]
        for (int row = 1; row <= sheet.getLastRowNum(); row++) {
            Row dataRow = sheet.getRow(row);
            // int titleValueIndex = 0;

            try {

                testCaseName = getCellData(dataRow.getCell(0));
                runStatus = getCellData(dataRow.getCell(1));
                params = getCellData(dataRow.getCell(2));

            } catch (Exception e) {
                e.printStackTrace();

            }

            dataElementsMap.put(testCaseName, new DataElements(testCaseName, runStatus, params));
        }
        return dataElementsMap;
    }

    /**
     * Fetches page locators from in-memory pageObjectRepo Improved for performance
     * considerations.
     *
     * @param testSuiteName name of the page where the element will be queried
     * @return org.openqa.selenium.By pageElement
     */
    public Map<String, DataElements> getClassData(String testSuiteName) {
        // logger.debug("Finding" + testSuiteName + "in data sheet");
        Map<String, DataElements> dataElementMap = new HashMap<String, DataElements>();
        for (Data data : dataObjectRepo) {
            if (data.getSuiteName().equalsIgnoreCase(testSuiteName)) {
                dataElementMap = data.getElementList();
            }
        }
        return dataElementMap;
    }


    public void setSuiteName(String suite_name) {
        suiteName = suite_name;
    }
}
