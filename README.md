# Android-Demo-App

Initial Setup:
Install and configure JDK
Install and configure Maven
Setup your IDE (Preferably Eclipse IDE)
Import cloned repository as project
Add your test case classes under `testcases` package
Add your test data into `TestData.xslx` file
Update `testng.xml` file for test classes
Simply run `mvn clean install` command to run your test cases


Framework Explanation :

1. /src/main/java/base : Contains classes which define the appium start and close related functions with the caps and initializations.
2. /src/main/java/logger: contain class to log the execution of android appium.
3. /src/main/java/pages: all the pages related to the app are mentioned here with there locators and functions.
4. /src/main/java/reporters: having report related classes and methods to generate the extent report after execution.
5. /src/main/java/utils: contains files having functions like common functions, data reader, appium service builders, utils.
5. /src/main/resources/Apps: it has the .apk file that we need to use for the execution.
6. /src/main/resources/Testdata: contains testdat file to read the data from the excel.
7. /src/test/java/tests : All the tests files are declare here and also we call the pages methods here to complete the execution.



