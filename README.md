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
<p> - Simply run `mvn clean install` command  or testng.xml file to run your test cases </p>
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

<h4> Issue while cloning </h4>
Incase if you are cloning it on Intellij IDE, you might issues related to testng library like :
<img width="635" alt="Screenshot 39" src="https://user-images.githubusercontent.com/6880146/222898387-7842cac7-d3a2-47b6-858e-477e50b2cd8c.png">

In such case you just need to right click on your project > click ok open module structure > Click libraries from project settings > click on + icon > slect 'from maven' option >  search org.testng > select the org.testng version which is already there in your machine> select annotation chekbox as well and click on ok > <img width="1031" alt="Screenshot 40" src="https://user-images.githubusercontent.com/6880146/222898603-42078b86-eccb-470d-be0c-c94ae072a5ec.png">
click apply and click ok.

Next you need to do is to click on File > Invalidate caches > select clear file system cache and clear VCS logs options > Invalidate and restart.
<img width="496" alt="Screenshot 41" src="https://user-images.githubusercontent.com/6880146/222898614-10274f1d-eafb-45b0-9bb5-d141e8af6114.png">

Now try again by running the testng.xml file.



