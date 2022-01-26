package com.application;

import com.Utility.ElementUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.config.Configuration;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConfigTestRunner {

    public static String destFile;
    private ConfigTestRunner configTestRunner = null;
    private com.aventstack.extentreports.ExtentTest parentTest;
    private com.aventstack.extentreports.ExtentTest childTest;
    private ExtentReports extent;
    private BaseAction baseAction;
    private LoginAction loginAction;
    private Configuration config;
    public ElementUtil elementUtil;
    public User_Management user_management;
    public VendorStudies vendorStudies;
    public Studies studies;
    public Vendor vendor;
    private GeneralTestCases generalTestCases;
    private TranslationManagement translationManagement;
    public  WebDriver driver=DriverFactory.getDriver();
    public String TestCase_Id;
    public Map<String, String> testCase = new ConcurrentHashMap<>();
    public Map<String, String> testData = new ConcurrentHashMap<>();
    boolean isExecuted = false;
    public String accountID;
    public String sheetName;

    public ConfigTestRunner(ExtentReports extent) {
        setExtent(extent);
        //setDriver(driver);
    }

    public void run(String Tcnumber) {
        baseAction = new BaseAction();
        baseAction.ReadTestData(Tcnumber);
            if (baseAction.testCase.get("Execute").equalsIgnoreCase("YES")) {
                isExecuted = true;
                String browserName = ((RemoteWebDriver) driver).getCapabilities().getBrowserName();
                parentTest = extent.createTest(baseAction.testCase.get("SC_ID") + " " + baseAction.testCase.get("Summary (functionality)") + ": Executed On Browser::" + browserName);
                intActions();
                TestCase_Id = baseAction.testCase.get("SC_ID");
                if(!Tcnumber.contains("login")) {
                    loginAction.fnValidLogin(configTestRunner);
                }
                fnExecuteTestCase(Tcnumber);
                if(baseAction.testCase.get("Summary (functionality)").contains("invalid") || baseAction.testCase.get("Summary (functionality)").contains("blank")
                || baseAction.testCase.get("Summary (functionality)").contains("Back") || baseAction.testCase.get("Summary (functionality)").contains("Forgot")
                || baseAction.testCase.get("Summary (functionality)").contains("policy"))
                    driver.close();
                else {
                    loginAction.LogOutFromApplication(configTestRunner);
                    driver.close();
                }
            } else {
                driver.close();
                if (!isExecuted) {
                    TestCase_Id = Tcnumber;
                    parentTest = getExtent().createTest(Tcnumber + " is not consider for execution");
                    parentTest.log(Status.SKIP, "Test is not consider for execution.");
                }
            }

    }

    public void fnExecuteTestCase(String tcNumber) {
        String cardName = tcNumber.split("_")[1];
        if (cardName.equals("login")) {
            loginAction.fnExecuteTC(configTestRunner,tcNumber);
        }else if(cardName.equals("UM")) {
            user_management.fnExecuteTC(configTestRunner,tcNumber);
        }else if(cardName.equals("V")) {
            vendor.fnExecuteTC(configTestRunner,tcNumber);
        }else if(cardName.equals("VS")) {
            vendorStudies.fnExecuteTC(configTestRunner,tcNumber);
        }else if(cardName.equals("S")){
            studies.fnExecuteTC(configTestRunner,tcNumber);
        }else if(cardName.equals("TM")){
            translationManagement.fnExecuteTC(configTestRunner,tcNumber);
        }
        else
            parentTest.log(Status.INFO,"No Test Case is considered for execution");

    }
    public void intActions(){
        elementUtil= new ElementUtil(driver);
        config = new Configuration();
        loginAction = new LoginAction();
        generalTestCases = new GeneralTestCases();
        user_management= new User_Management();
        vendor = new Vendor();
        vendorStudies = new VendorStudies();
        studies = new Studies();
        translationManagement = new TranslationManagement();
    }


    public String takeFullPageScreenShot(String screenShotName)  {

        JavascriptExecutor jsExec = (JavascriptExecutor)driver;

        //Returns a Long, Representing the Height of the window’s content area.
        Long windowHeight = (Long) jsExec.executeScript("return window.innerHeight;");

        //Returns a Long, Representing the Height of the complete WebPage a.k.a. HTML document.
        Long webpageHeight = (Long) jsExec.executeScript("return document.getElementsByClassName('page-container').scrollHeight;");

        //Marker to keep track of the current position of the scroll point
        //Long currentWindowScroll = Long.valueOf(0);
        //Using java's boxing feature to create a Long object from native long value.

        Long currentWindowScroll = 0L;
        String dest =getDestFile()+"\\"+screenShotName;

        do{
            //System.out.println(windowHeight + ", " + webpageHeight + ", " + currentWindowScroll);

            jsExec.executeScript("window.scrollTo(0, " + currentWindowScroll + ");");

            Actions act = new Actions(driver);
            act.pause(5000).perform();
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File(dest);
            try {
                FileUtils.copyFile(source, destination);
                byte[] bytes = new byte[(int) source.length()];
                String base64 = new String(Base64.encodeBase64(bytes), "UTF-8");
            }catch (Exception e){
                e.printStackTrace();
            }

            currentWindowScroll = currentWindowScroll + windowHeight;

        }while(currentWindowScroll <= webpageHeight);
        return dest;
    }

    public String screenShotName(String screenShotName){
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH-mm-ss");
        String fileName = screenShotName+"_"+dateFormat.format(new Date())+".png";
        capture(fileName );
        return fileName;
    }
    public String capture( String screenShotName)  {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dest = getConfigTestRunner().getDestFile() +"\\"+screenShotName;
        File destination = new File(dest);
        try {
            FileUtils.copyFile(source, destination);
            byte[] bytes = new byte[(int) source.length()];
//            String base64 = new String(Base64.encodeBase64(bytes), "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return dest;
    }

    public Map<String, String> getTestData() {
        return testData;
    }

    public void setTestData(Map<String, String> testData) {
        this.testData = testData;
    }

    public ExtentReports getExtent() {
        return extent;
    }

    public void setExtent(ExtentReports extent) {
        this.extent = extent;
    }

    public void setConfigTestRunner(ConfigTestRunner configTestRunner) {
        this.configTestRunner= configTestRunner;
    }
    public ConfigTestRunner getConfigTestRunner() {
        return configTestRunner;
    }

    public String getDestFile() {
        return destFile;
    }

    public void setDestFile(String destFile) {
        ConfigTestRunner.destFile = destFile;
    }

    public ExtentTest getParentTest() {
        return parentTest;
    }

    public void setParentTest(ExtentTest parentTest) {
        this.parentTest = parentTest;
    }

    public ExtentTest getChildTest() {
        return childTest;
    }

    public void setChildTest(ExtentTest childTest) {
        this.childTest = childTest;
    }


    public Map<String, String> getTestCase() {
        return testCase;
    }

    public BaseAction getBaseAction() {
        return baseAction;
    }

    public void setBaseAction(BaseAction baseAction) {
        this.baseAction = baseAction;
    }

    public Configuration getConfig() {
        return config;
    }

    public void setConfig(Configuration config) {
        this.config = config;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

}


