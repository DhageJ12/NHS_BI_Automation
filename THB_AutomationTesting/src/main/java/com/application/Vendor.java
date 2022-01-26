package com.application;

import com.Utility.Constants;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.Keys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Vendor extends BaseAction{

    private final String formName="Vendor";


    /***
     * This method is for selecting test cases for execution
     * Parameter testcase number , configTestRunner object
     * Author:Jyoti Dhage
     * Date: 25-11-2021
     */
    public void fnExecuteTC(ConfigTestRunner configTestRunner,String tcNumber){
        switch (tcNumber) {
            case "SC001_V":
                verifyLandingPage(configTestRunner);
                break;
            case "SC002_V":
                vendorsTableCheck(configTestRunner);
                break;
            case "SC003_V":
                addVendors(configTestRunner);
                break;
            case "SC004_V":
                ExportTableExcel(configTestRunner);
                break;
            case "SC005_V":
                ExportTableCSV(configTestRunner);
                break;
            case "SC006_V":
                ExportTablePDF(configTestRunner);
                break;
            default:
                System.out.println("No Test case is consider for execution");
        }
    }
    /*
    *Description: Verify_Vendors landing page
    *Author: Jyoti Dhage
    *Date: 26-11-2021
    */
    public void verifyLandingPage(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner,"Vendors");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify_Vendors landing page"));
        if(getButtonWithText("Add a Vendor").isDisplayed() && getButtonWithText("Export Table").isDisplayed())
            fnTakeScreenAshot(configTestRunner,"Pass","'Add a Vendor' & 'Export Table' button is present on the Vendors home page","VendorBtn");
        else
            fnTakeScreenAshot(configTestRunner,"fail","'Add a Vendor' & 'Export Table'  button is not present on the vendors home page","VendorNotPresent");
        }
    /*
     *Description: Verify_Vendors table columns
     *Author: Jyoti Dhage
     *Date: 26-11-2021
     */
    public void vendorsTableCheck(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner,"Vendors");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify_Vendors table columns"));
        List<String> fields = new ArrayList<>(Arrays.asList(configTestRunner.getBaseAction().getTestCase().get("VendorsHomePage_TableColumn").split(",")));
        for(String vendorColumnName:fields){
            try{
                getSpanWithText(configTestRunner,vendorColumnName).isDisplayed();
            }catch (Exception e){
                configTestRunner.getChildTest().log(Status.FAIL,vendorColumnName +" column name is not present on the Vendors home page.");
                fields.remove(vendorColumnName);
                e.printStackTrace();
            }
        }
        fnTakeScreenAshot(configTestRunner,"Pass",configTestRunner.getBaseAction().getTestCase().get("VendorsHomePage_TableColumn")+" These columns are present on the Vendors home page.","UserMangColumnName");
    }
    /*
     *Description: Verify_Vendors table columns
     *Author: Jyoti Dhage
     *Date: 26-11-2021
     */
    public void addVendors(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner,"Vendors");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify_Vendors table columns"));
        waitAndClick(getButtonWithText("Add a Vendor"), Constants.AJAX_TIMEOUT);
        configTestRunner.getChildTest().log(Status.PASS,"User click on Add Vendors button");
        String vendorName =configTestRunner.getBaseAction().getTestCase().get("vendorName")+"_"+configTestRunner.elementUtil.getDateAlongWithTime(configTestRunner);
        waitAndSendText(getWebElement(formName,"vendorName",configTestRunner),Constants.AJAX_TIMEOUT,vendorName);
        configTestRunner.getChildTest().log(Status.PASS,"User enter vendor name as "+vendorName);
        Random random = new Random();
        int code = random.nextInt(200);
        waitAndSendText(getWebElement(formName,"vendorCode",configTestRunner),Constants.AJAX_TIMEOUT,Integer.toString(code));
        configTestRunner.getChildTest().log(Status.PASS,"User enter vendor name as "+configTestRunner.getBaseAction().getTestCase().get("vendorCode"));
        waitAndSendText(getWebElement(formName,"contactName",configTestRunner),Constants.AJAX_TIMEOUT,configTestRunner.getBaseAction().getTestCase().get("contactName"));
        configTestRunner.getChildTest().log(Status.PASS,"Contact name enter is "+configTestRunner.getBaseAction().getTestCase().get("contactName"));
        waitAndSendText(getWebElement(formName,"contactNumber",configTestRunner),Constants.AJAX_TIMEOUT,configTestRunner.getBaseAction().getTestCase().get("contactNumber"));
        configTestRunner.getChildTest().log(Status.PASS,"Contact number enter is "+configTestRunner.getBaseAction().getTestCase().get("contactNumber"));
        waitAndClick(getButtonWithText("Save"),Constants.AJAX_TIMEOUT);
        searchVendors(configTestRunner,Integer.toString(code));

    }

    public void searchVendors(ConfigTestRunner configTestRunner, String value){
        try{
            sleep(2000);
            getWebElement("UserManagement","searchInputBox",configTestRunner).isDisplayed();
            getWebElement("UserManagement","searchInputBox",configTestRunner).clear();
            waitAndSendText(getWebElement("UserManagement","searchInputBox",configTestRunner),Constants.AJAX_TIMEOUT,value);
            sleep(200);
            getWebElement("UserManagement","searchInputBox",configTestRunner).sendKeys(Keys.ENTER);
            sleep(5000);
            String email = configTestRunner.elementUtil.columnValueTable("code",2).getText();
            if(email.contains(value)){
                configTestRunner.getChildTest().log(Status.PASS,"Vendor is added successfully to vendor");
            }else
                fnTakeScreenAshot(configTestRunner,"fail","Vendor is not added successfully","vendorNotAddedSuccessfully");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     *SC004:Verify Export Table -->Excel XLS format
     * @param configTestRunner
     */
    public void ExportTableExcel(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner,"Vendors");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify Export Table -->Excel XLS format"));
        String fileName= "Export_Excel"+configTestRunner.elementUtil.getDateAlongWithTime(configTestRunner);
        configTestRunner.elementUtil.ExportTableVerify(configTestRunner,fileName,"Excel (xlsx)","UserManagement");
    }
    /**
     *SC005:Verify Export Table -->CSV format
     * @param configTestRunner
     */
    public void ExportTableCSV(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner,"Vendors");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify Export Table -->Excel CSV format"));
        String fileName= "Export_CSV"+configTestRunner.elementUtil.getDateAlongWithTime(configTestRunner);
        configTestRunner.elementUtil.ExportTableVerify(configTestRunner,fileName,"CSV","UserManagement");
    }

    /**
     *SC006:Verify Export Table -->PDF format
     * param configTestRunner
     */
    public void ExportTablePDF(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner,"Vendors");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify Export Table -->Excel PDF format"));
        String fileName= "Export_PDF"+configTestRunner.elementUtil.getDateAlongWithTime(configTestRunner);
        configTestRunner.elementUtil.ExportTableVerify(configTestRunner,fileName,"PDF","UserManagement");
    }


}
