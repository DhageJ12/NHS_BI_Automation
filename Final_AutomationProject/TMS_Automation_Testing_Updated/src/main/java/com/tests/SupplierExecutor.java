package com.tests;

import com.Utility.Constants;
import com.Utility.VideoRecorder_utility;
import com.application.ConfigTestRunner;
import com.application.DriverFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SupplierExecutor {

    public static String destFile;
    public ExtentTest test;
    public ExtentHtmlReporter htmlReports;   //to generate an html file
    private static ExtentReports extent;
    private ConfigTestRunner configTestRunnerLocal;

    @BeforeClass
    public void extentReportConfig(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH-mm-ss");
//        destFile = System.getProperty("user.dir")+ Constants.reportsFilePath+"BlinxSolutions_"+dateFormat.format(new Date());
        destFile =  Constants.reportsFilePath+"TMS_Supplier_TestScenario"+dateFormat.format(new Date());
        File newFolder = new File(destFile);
        boolean created =  newFolder.mkdir();  //mkdir will create folder
        if(created)
            System.out.println("Folder is created !");
        else
            System.out.println("Unable to create folder");

        String destDir = "TMS_Supplier_Report" + dateFormat.format(new Date()) + ".html";
        htmlReports = new ExtentHtmlReporter(destFile + "\\" + destDir);  //to generate an html file
        extent = new ExtentReports();
        extent.attachReporter(htmlReports);
        extent.setReportUsesManualConfiguration(true);
        setExtent(extent);
        htmlReports.config().setReportName("TMS BI Application");
        htmlReports.config().setTheme(Theme.STANDARD);
        htmlReports.config().setDocumentTitle("TMS Test Result");
    }
    @BeforeMethod
    // @Parameters("browser")
    public void initializeBrowser() {
        DriverFactory driverFactory = new DriverFactory();
        WebDriver driver = driverFactory.startBrowser("chrome");
//        driver.get(Constants.URL);
    }


    @Test
    public void SC001_SU(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC001_SU");
    }

    @Test
    public void SC011_SU() {
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC011_SU");
    }
    @Test
    public void SC012_SU() {
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC012_SU");
    }
    @Test
    public void SC013_SU() {
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC013_SU");
    }
    @Test
    public void SC014_SU() {
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC014_SU");
    }
    @Test
    public void SC016_SU() {
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC016_SU");
    }
    @Test
    public void SC017_SU() {
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC017_SU");
    }
    @Test
    public void SC018_SU() {
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC018_SU");
    }
    @Test
    public void SC019_SU() {
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC019_SU");
    }
    @Test
    public void SC021_SU() throws Exception {
        VideoRecorder_utility.startRecord("SC021_SU",destFile);
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC021_SU");
        VideoRecorder_utility.stopRecord();
    }

    @AfterMethod
    public void fnCloseBrowser(ITestResult testResult) {
//        testResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation()
        String cardName = configTestRunnerLocal.TestCase_Id.split("_")[1];
        if (testResult.getStatus() == ITestResult.FAILURE) {
            String name = "Test_Fail_ScreenShot";
            try {
                getConfigTestRunnerLocal().getChildTest().log(Status.FAIL, "Test is failed" + getConfigTestRunnerLocal().getChildTest().addScreenCaptureFromPath(getConfigTestRunnerLocal().screenShotName(name)));
            } catch (Exception exc) {
                exc.printStackTrace();
            }
            getConfigTestRunnerLocal().getBaseAction().setCellData("Fail", getConfigTestRunnerLocal().getBaseAction().rowValue(getConfigTestRunnerLocal().getBaseAction().getTestCase().get("SC_ID"), getConfigTestRunnerLocal().getBaseAction().ColumnValue("SC_ID", getConfigTestRunnerLocal().getBaseAction().sheetName)), getConfigTestRunnerLocal().getBaseAction().ColumnValue("Status", getConfigTestRunnerLocal().getBaseAction().sheetName));
        } else if (testResult.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, testResult.getThrowable());
            getConfigTestRunnerLocal().getBaseAction().setCellData("Skip", getConfigTestRunnerLocal().getBaseAction().rowValue(getConfigTestRunnerLocal().getBaseAction().getTestCase().get("SC_ID"), getConfigTestRunnerLocal().getBaseAction().ColumnValue("SC_ID", getConfigTestRunnerLocal().getBaseAction().sheetName)), getConfigTestRunnerLocal().getBaseAction().ColumnValue("Status", getConfigTestRunnerLocal().getBaseAction().sheetName));
        } else if (testResult.getStatus() == ITestResult.SUCCESS) {
            getConfigTestRunnerLocal().getBaseAction().setCellData("Pass", getConfigTestRunnerLocal().getBaseAction().rowValue(getConfigTestRunnerLocal().getBaseAction().getTestCase().get("SC_ID"), getConfigTestRunnerLocal().getBaseAction().ColumnValue("SC_ID",getConfigTestRunnerLocal().getBaseAction().sheetName)), getConfigTestRunnerLocal().getBaseAction().ColumnValue("Status", getConfigTestRunnerLocal().getBaseAction().sheetName));
        }

    }

    @AfterClass
    public void End_SetUp(){
        getConfigTestRunnerLocal().getExtent().flush();
        if(getConfigTestRunnerLocal().driver!=null) {
            getConfigTestRunnerLocal().driver.quit();
        }

    }

    public ConfigTestRunner getConfigTestRunnerLocal() {
        return configTestRunnerLocal;
    }

    public void setConfigTestRunnerLocal(ConfigTestRunner configTestRunnerLocal) {
        this.configTestRunnerLocal = configTestRunnerLocal;
    }

    public ExtentReports getExtent() {
        return extent;
    }

    public void setExtent(ExtentReports extent) {
        SupplierExecutor.extent = extent;
    }
}
