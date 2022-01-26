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

public class VEndorStudies_Executor {

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
        destFile =  Constants.reportsFilePath+"VendorsStudies_"+dateFormat.format(new Date());
        File newFolder = new File(destFile);
        boolean created =  newFolder.mkdir();  //mkdir will create folder
        if(created)
            System.out.println("Folder is created !");
        else
            System.out.println("Unable to create folder");

        destDir = "VendorsStudies_Report"+dateFormat.format(new Date()) + ".html";
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

    }
    @Test
    public void SC001_verifyStudyLandingPage(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC001_VS");
    }
    @Test
    public void SC002_verifySearchBoxFunctionality(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC002_VS");
    }
    @Test
    public void SC003_verifyExportTableFunctionality(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC003_VS");
    }
    @Test
    public void SC004_verifyColumnFilter(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC004_VS");
    }

    @Test
    public void SC005_verifyTranslationPageTableCheck(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC005_VS");
    }
    @Test
    public void SC006_verifyColumn(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC006_VS");
    }
    @Test
    public void SC007_verifyActionMenuTranslationPage(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC007_VS");
    }
    @Test
    public void SC008_VerifyImportTranslatedText(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC008_VS");
    }
    @Test
    public void SC009_VerifyExportMasterText(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC009_VS");
    }

    @Test
    public void SC013_VerifySearchFunctionalityTranslationPage(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC013_VS");
    }

    @Test
    public void SC014_VerifyExportTableTranslationPage(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC014_VS");
    }

    @Test
    public void SC015_VerifyColumnFilterTranslationPage(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC015_VS");
    }

    @Test
    public void SC017_VerifyTranslationPageAssignView(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC017_VS");
    }
    @Test
    public void SC018_VerifyTranslationPageAssignViewWithTranslatorEditable(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC018_VS");
    }

    @Test
    public void SC020_VerifyLocalizeScreenReport(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC020_VS");
    }

    @Test
    public void SC021_VerifyCommentProjectTranslationPage(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC021_VS");
    }
    @Test
    public void SC022_VerifyReplyToComment(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC022_VS");
    }
    @Test
    public void SC023_VerifyViewFiles(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC023_VS");
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
