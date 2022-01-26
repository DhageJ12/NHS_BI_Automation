package com.application;

import com.Utility.Constants;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class User_Management extends BaseAction {
    private final String formName ="UserManagement";

    /***
     * This method is for selecting test cases for execution
     * Parameter testcase number , configTestRunner object
     * Author:Jyoti Dhage
     * Date: 25-11-2021
     */
    public void fnExecuteTC(ConfigTestRunner configTestRunner,String tcNumber){
        switch (tcNumber) {
            case "SC001_UM":
                userManagementTableCheck(configTestRunner);
                break;
            case "SC002_UM":
                addUserVerify(configTestRunner);
                break;
            case "SC003_UM":
                enabledDisabledVerify(configTestRunner);
                break;
            case "SC004_UM":
                deleteUserVerify(configTestRunner);
                break;
            case "SC005_UM":
                searchFunctionalityVerify(configTestRunner);
                break;
            case "SC006_UM":
                ExportTableExcel(configTestRunner);
                break;
            case "SC007_UM":
                ExportTableCSV(configTestRunner);
                break;
            case "SC008_UM":
                ExportTablePDF(configTestRunner);
                break;
            case "SC009_UM":
                sideColumnAGTable(configTestRunner);
                break;
            case "SC010_UM":
                applyFilter(configTestRunner);
                break;

            default:
                System.out.println("No Test case is consider for execution");
        }
    }

    /**
     *SC001:Verify different column on user management page
     * @param configTestRunner
     */
    public void userManagementTableCheck(ConfigTestRunner configTestRunner){
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify User management Screen different columns"));
        fnSelectMenu(configTestRunner,"User Management");
        List<String> fields = new ArrayList<>(Arrays.asList(configTestRunner.getBaseAction().getTestCase().get("UserMangTable_columnName").split(",")));
        for(String userMang_columnName:fields){
            try{
                getSpanWithText(configTestRunner,userMang_columnName).isDisplayed();
            }catch (Exception e){
                configTestRunner.getChildTest().log(Status.FAIL,userMang_columnName +" column name is not present on the user management card home page.");
                fields.remove(userMang_columnName);
                e.printStackTrace();
            }
        }
        fnTakeScreenAshot(configTestRunner,"Pass",configTestRunner.getBaseAction().getTestCase().get("UserMangTable_columnName")+" These columns are present on the user management home page.","UserMangColumnName");
    }
    /**
     *SC002:Verify User should get created in user management screen
     * @param configTestRunner
     */
    public void addUserVerify(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner,"User Management");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify Add User functionality for user management page"));
        String email = addUser(configTestRunner);
        searchAddedUser(configTestRunner,email);
    }
    /**
     *SC003:Verify User should get enabled & disabled from user management screen
     * @param configTestRunner
     */
    public void enabledDisabledVerify(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner,"User Management");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Add New user for enabled & disable"));
        String email = addUser(configTestRunner);
        searchAddedUser(configTestRunner,email);
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Disabled user verification"));
        userEnabledDisabled(configTestRunner,email,"Disable User","disabledIcon");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Enabled user verification"));
        searchAddedUser(configTestRunner,email);
        userEnabledDisabled(configTestRunner,email,"Enable User","enabledIcon");
    }
    /**
     *SC002:Verify User should get created in user management screen
     * @param configTestRunner
     */
    public void searchFunctionalityVerify(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner,"User Management");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify the table Search field "));
        String email = addUser(configTestRunner);
        searchAddedUser(configTestRunner,email);
        fnTakeScreenAshot(configTestRunner,"Pass","Search functionality is working fine ","UserAddedSuccessfully");

    }
    /**
     *SC003:Verify User should get enabled & disabled from user management screen
     * @param configTestRunner
     */
    public void deleteUserVerify(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner,"User Management");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Add New user for enabled & disable"));
        String email = addUser(configTestRunner);
        searchAddedUser(configTestRunner,email);
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Delete user verification"));
        deleteUser(configTestRunner,email,"Delete User");
    }

    /**
     *SC006:Verify Export Table -->Excel XLS format
     * @param configTestRunner
     */
    public void ExportTableExcel(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner,"User Management");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify Export Table -->Excel XLS format"));
        String fileName= "Export_Excel"+configTestRunner.elementUtil.getDateAlongWithTime(configTestRunner);
        configTestRunner.elementUtil.ExportTableVerify(configTestRunner,fileName,"Excel (xlsx)",formName);
    }
    /**
     *SC007:Verify Export Table -->CSV format
     * @param configTestRunner
     */
    public void ExportTableCSV(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner,"User Management");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify Export Table -->Excel CSV format"));
        String fileName= "Export_CSV"+configTestRunner.elementUtil.getDateAlongWithTime(configTestRunner);
        configTestRunner.elementUtil.ExportTableVerify(configTestRunner,fileName,"CSV",fileName);
    }

    /**
     *SC008:Verify Export Table -->PDF format
     * @param configTestRunner
     */
    public void ExportTablePDF(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner,"User Management");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify Export Table -->Excel PDF format"));
        String fileName= "Export_PDF"+configTestRunner.elementUtil.getDateAlongWithTime(configTestRunner);
        configTestRunner.elementUtil.ExportTableVerify(configTestRunner,fileName,"PDF",formName);
    }

    public void userEnabledDisabled(ConfigTestRunner configTestRunner, String email, String option, String xpathName){
        waitAndClick(configTestRunner.elementUtil.actionBtn(),Constants.AJAX_TIMEOUT);
        configTestRunner.getChildTest().log(Status.INFO,"User click on the action button");
        waitAndClick(getSpanWithText(configTestRunner,option),Constants.AJAX_TIMEOUT);
        configTestRunner.getChildTest().log(Status.INFO,"User click on the action button for :"+option);
        sleep(2000);
        searchAddedUser(configTestRunner,email);
        if(getWebElement(formName,xpathName,configTestRunner).isDisplayed()){
            fnTakeScreenAshot(configTestRunner,"Pass",option+" is successfully",option+"_completed");
        }else
            fnTakeScreenAshot(configTestRunner,"fail",option+" is not successfully",option+"NotDone");
    }
    public void deleteUser(ConfigTestRunner configTestRunner, String email, String option){
        waitAndClick(configTestRunner.elementUtil.actionBtn(),Constants.AJAX_TIMEOUT);
        configTestRunner.getChildTest().log(Status.INFO,"User click on the action button");
        waitAndClick(getSpanWithText(configTestRunner,option),Constants.AJAX_TIMEOUT);
        configTestRunner.getChildTest().log(Status.INFO,"User click on the action button for :"+option);
        sleep(1000);
        getWebElement(formName,"searchInputBox",configTestRunner).isDisplayed();
        getWebElement(formName,"searchInputBox",configTestRunner).clear();
        waitAndSendText(getWebElement(formName,"searchInputBox",configTestRunner),Constants.AJAX_TIMEOUT,email);
        List<WebElement> elements = getWebElements(configTestRunner,formName,"agGridTable");
        if(elements.size()==1){
            fnTakeScreenAshot(configTestRunner,"Pass","User is deleted successfully","UserDelete");
        }else
            fnTakeScreenAshot(configTestRunner,"fail","User is not deleted successfully","UserNotDelete");

    }
    public String addUser(ConfigTestRunner configTestRunner){
        getButtonContainsText("Add a User").isDisplayed();
        waitAndClick(getButtonContainsText("Add a User"), Constants.AJAX_TIMEOUT);
        Random random = new Random();
        String email = "jyoti.dhage_"+random.nextInt(50)+"@blinxsolution.com";
        waitAndSendText(getWebElement(formName,"addUser_EmailAddress",configTestRunner),Constants.AJAX_TIMEOUT,email);
        configTestRunner.getChildTest().log(Status.INFO,"User click on Add user button");
        configTestRunner.getChildTest().log(Status.INFO,"User enter email address as :"+email);
        waitAndClick(getWebElement(formName,"addUser_Vendor",configTestRunner),Constants.AJAX_TIMEOUT);
        waitAndClick(getLiWithText(configTestRunner.getBaseAction().getTestCase().get("Vendor")),Constants.AJAX_TIMEOUT);
        configTestRunner.getChildTest().log(Status.INFO,"User select vendor as :"+configTestRunner.getBaseAction().getTestCase().get("Vendor"));
        waitAndClick(getSpanWithText(configTestRunner,"Admin"),Constants.AJAX_TIMEOUT);
        configTestRunner.getChildTest().log(Status.INFO,"User check the Admin checkbox.");
        fnTakeScreenAshot(configTestRunner,"Pass","User added details","AddUserScreen");
        waitAndClick(getButtonWithText("Save"), Constants.AJAX_TIMEOUT);
        return email;
    }
    public void searchAddedUser(ConfigTestRunner configTestRunner, String value){
        try{
            sleep(1000);
            getWebElement(formName,"searchInputBox",configTestRunner).isDisplayed();
            getWebElement(formName,"searchInputBox",configTestRunner).clear();
            waitAndSendText(getWebElement(formName,"searchInputBox",configTestRunner),Constants.AJAX_TIMEOUT,value);
            String email = configTestRunner.elementUtil.columnValueTable("email",2).getText();
            if(email.contains(value)){
//                fnTakeScreenAshot(configTestRunner,"Pass","User added successfully in user management screen","UserAddedSuccessfully");
                configTestRunner.getChildTest().log(Status.PASS,"User added successfully in user management screen");
            }else
                fnTakeScreenAshot(configTestRunner,"fail","User is not added successfully in user management screen","UserNotAddedSuccessfully");
        }catch (Exception e){
            e.printStackTrace();
        }
    }



        public void sideColumnAGTable(ConfigTestRunner configTestRunner){
            configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify Table Side Columns AG grid"));
            fnSelectMenu(configTestRunner,"User Management");
            waitAndClick(getSpanWithText(configTestRunner,"Columns"),Constants.AJAX_TIMEOUT);
            List<WebElement> elements = getWebElements(configTestRunner,formName,"agGridTable");
            configTestRunner.getChildTest().log(Status.PASS,elements.size()+" these many records are present befor selecting the pivot mode");
            waitAndClick(getDivWithText("Pivot Mode"),Constants.AJAX_TIMEOUT);
            try{
                List<WebElement> element = getWebElements(configTestRunner,formName,"agGridTable");
            }catch (Exception e){
                fnTakeScreenAshot(configTestRunner,"pass","Pivot mode is selected successfully & no record is present on the screen","Pivote");
                e.printStackTrace();
            }
            List<WebElement> sideColumnName = getWebElements(configTestRunner,formName,"sideTableColumn");
            String columnName ="";
            for (int i=0;i<sideColumnName.size();i++){
                columnName = columnName+"|"+sideColumnName.get(i).getText();
            }
            configTestRunner.getChildTest().log(Status.PASS,columnName+" this many columns are present on the side table.");
        }

    public void applyFilter(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner,"User Management");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify Table Filter & Side Filters AG grid"));
        waitAndClick(getSpanWithText(configTestRunner,"Filters"),Constants.AJAX_TIMEOUT);
        List<WebElement> elements = getWebElements(configTestRunner,formName,"agGridTable");
        configTestRunner.getChildTest().log(Status.PASS,elements.size()+" these many records are present before selecting the pivot mode");
        waitAndClick(getWebElement(formName,"createdDateFilter",configTestRunner),Constants.AJAX_TIMEOUT);
        waitAndClick(getDivWithText("(Select All)"),Constants.AJAX_TIMEOUT);
        sleep(500);
        List<WebElement> element = getWebElements(configTestRunner,formName,"agGridTable");
        if(element.size()==1){
            fnTakeScreenAshot(configTestRunner,"pass","Filter is applied successfully","Filter_Apply");
        }
        List<WebElement> sideColumnName = getWebElements(configTestRunner,formName,"sideTableColumn");
        String columnName ="";
        for (int i=0;i<sideColumnName.size();i++){
            columnName = columnName+"|"+sideColumnName.get(i).getText();
        }
        configTestRunner.getChildTest().log(Status.PASS,columnName+" this many columns are present on the side table.");
    }

}
