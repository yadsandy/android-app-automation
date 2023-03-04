# Android-Demo-App

<h3> Initial Setup: </h3>

<p> - Install and configure JDK </p>
<p> - Install and configure Maven </p>
<p> - Setup your IDE (Preferably Eclipse IDE) </p>
<p> - Import cloned repository as project </p>
<p> - Add your test case classes under `testcases` package </p>
<p> - Add your test data into `TestData.xslx` file </p>
<p> - Update `testng.xml` file for test classes </p>
<p> - Update the pom.xml file with your system version in maven-compiler-plugin for  <source>java-version</source> and <target>18</target>
<p> - Simply run `mvn clean install` command to run your test cases </p>
<p> - Check the reports in the target folder > ExtentReports<ExecutionTime> folder for the execution results and logs.


<h3> Framework Explanation :</h3>

1. /src/main/java/base : Contains classes which define the appium start and close related functions with the caps and
   initializations.
2. /src/main/java/logger: contain class to log the execution of android appium.
3. /src/main/java/pages: all the pages related to the app are mentioned here with there locators and functions.
4. /src/main/java/reporters: having report related classes and methods to generate the extent report after execution.
5. /src/main/java/utils: contains files having functions like common functions, data reader, appium service builders,
   utils.
5. /src/main/resources/Apps: it has the .apk file that we need to use for the execution.
6. /src/main/resources/Testdata: contains testdat file to read the data from the excel.
7. /src/test/java/tests : All the tests files are declare here and also we call the pages methods here to complete the
   execution.



