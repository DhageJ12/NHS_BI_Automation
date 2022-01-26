package com.tests;

import com.Utility.Constants;
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

public class Vendor_Executor {

    public static String destFile;
    private static String destDir;
    public ExtentTest test;
    public ExtentHtmlReporter htmlReports;   //to generate an html file
    private static ExtentReports extent;
    private ConfigTestRunner configTestRunnerLocal;
    private WebDriver driver;

    @BeforeClass
    public void extentReportConfig(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH-mm-ss");
//        destFile = System.getProperty("user.dir")+ Constants.reportsFilePath+"BlinxSolutions_"+dateFormat.format(new Date());
        destFile =  Constants.reportsFilePath+"Vendors_"+dateFormat.format(new Date());
        File newFolder = new File(destFile);
        boolean created =  newFolder.mkdir();  //mkdir will create folder
        if(created)
            System.out.println("Folder is created !");
        else
            System.out.println("Unable to create folder");

        destDir = "Vendors_Report"+dateFormat.format(new Date()) + ".html";
        htmlReports = new ExtentHtmlReporter(destFile + "\\" +destDir);  //to generate an html file
        extent = new ExtentReports();
        extent.attachReporter(htmlReports);
        extent.setReportUsesManualConfiguration(true);
        setExtent(extent);
        htmlReports.config().setReportName("Translation Hub Application");
        htmlReports.config().setTheme(Theme.STANDARD);
        htmlReports.config().setDocumentTitle("Translation Hub Test Result");
    }
    @BeforeMethod
    @Parameters("browser")
    public void initializeBrowser() {
        DriverFactory driverFactory = new DriverFactory();
        driver = driverFactory.startBrowser("chrome");
//        driver.get(Constants.URL);
    }
    @Test
    public void SC001_verifyLandingPage(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC001_V");
    }
    @Test
    public void SC002_verifyVendorTable(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC002_V");
    }
    @Test
    public void SC003_verifyAddVendor(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC003_V");
    }
    @Test
    public void SC004_verifyExportExcel(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC004_V");
    }
    @Test
    public void SC005_verifyExportCSV(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC005_V");
    }
    @Test
    public void SC006_verifyExportPDF(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC006_V");
    }
    @AfterMethod
    public void fnCloseBrowser(ITestResult testResult) {
        String cardName = configTestRunnerLocal.TestCase_Id.split("_")[1];
        if (testResult.getStatus() == ITestResult.FAILURE) {
            String name = "Test_Fail_ScreenShot"+testResult.getMethod().getMethodName();
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
        this.extent = extent;
    }

}
