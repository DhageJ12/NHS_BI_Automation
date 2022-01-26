package com.application;

import com.Utility.Constants;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TranslationManagement extends BaseAction {
  private String formName = "TranslationManagement";

    /***
     * This method is for selecting test cases for execution
     * Parameter testcase number , configTestRunner object
     * Author:Jyoti Dhage
     * Date: 06-12-2021
     */
    public void fnExecuteTC(ConfigTestRunner configTestRunner,String tcNumber) {
        switch (tcNumber) {
            case "SC001_TM":
                verifyLandingPageTM(configTestRunner);
                break;
            case "SC002_TM":
                translationManagementLandingTableCheck(configTestRunner);
                break;
            case "SC003_TM":
                addUserTM(configTestRunner);
                break;
            case "SC004_TM":
                enabledDisabledVerify(configTestRunner);
                break;
            case "SC005_TM":
                editUserVerification(configTestRunner);
                break;
            case "SC006_TM":
                ExportTableExcel(configTestRunner);
                break;
            case "SC007_TM":
                ExportTableCSV(configTestRunner);
                break;
            case "SC008_TM":
                ExportTablePDF(configTestRunner);
                break;
            case "SC009_TM":
                verifyColumnFilterTranslationManagementPage(configTestRunner);
                break;
            default:
            System.out.println("No Test case is consider for execution");
        }
    }
    /*
     *Description: Verify Translator Management page
     *Author: Jyoti Dhage
     *Date: 06-12-2021
     */
    public void verifyLandingPageTM(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner,"Translator Management");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify Translator Management landing page"));
        if(getButtonWithText("Add a User").isDisplayed() && getButtonWithText("Export Table").isDisplayed())
            fnTakeScreenAshot(configTestRunner,"Pass","'Add a User' & 'Export Table' button is present on the Translation Management home page","TranslationManagementBtn");
        else
            fnTakeScreenAshot(configTestRunner,"fail","'Add a User' & 'Export Table'  button is not present on the Translation Management home page","TranslationManagementNotBtn");
    }

    /*
     *Description: Verify Translation Management table columns
     *Author: Jyoti Dhage
     *Date: 06-12-2021
     */
    public void translationManagementLandingTableCheck(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner,"Translator Management");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify Translator Management table columns"));
        List<String> fields = new ArrayList<>(Arrays.asList(configTestRunner.getBaseAction().getTestCase().get("TranslationManagementHomePage_Column").split(",")));
        for(String vendorColumnName:fields){
            try{
                getSpanWithText(configTestRunner,vendorColumnName).isDisplayed();
            }catch (Exception e){
                configTestRunner.getChildTest().log(Status.FAIL,vendorColumnName +" column name is not present on the Translation Management home page.");
                fields.remove(vendorColumnName);
                e.printStackTrace();
            }
        }
        fnTakeScreenAshot(configTestRunner,"Pass",configTestRunner.getBaseAction().getTestCase().get("TranslationManagementHomePage_Column")+" These columns are present on the Translation Management home page.","TranslationManagement_HomePage");
    }
    /*
     *Description: Verify  user is able to Add a user
     *Author: Jyoti Dhage
     *Date: 06-12-2021
     */
    public String addUserTM(ConfigTestRunner configTestRunner) {
        fnSelectMenu(configTestRunner, "Translator Management");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify  user is able to Add a user"));
        waitAndClick(getButtonWithText("Add a User"), Constants.AJAX_TIMEOUT);
        configTestRunner.getChildTest().log(Status.INFO, "User click on the Add a user button on translation management home [age.");
        boolean userDetails = configTestRunner.elementUtil.getHeaderWithDifferentNumber(4, "User Details").isDisplayed();
        boolean addNewUser = configTestRunner.elementUtil.getHeaderWithDifferentNumber(3, "Add New User").isDisplayed();
        boolean cancelBtn = getButtonContainsText("Cancel").isDisplayed();
        boolean saveBtn = getButtonContainsText("Save").isDisplayed();
        if (userDetails)
            configTestRunner.getChildTest().log(Status.PASS, "User Details is added on the Add new user screen");
        else
            configTestRunner.getChildTest().log(Status.FAIL, "User Details is not added on the Add new user screen");

        if (addNewUser)
            configTestRunner.getChildTest().log(Status.PASS, "Add New User is present on the top of the model");
        else
            configTestRunner.getChildTest().log(Status.FAIL, "Add New User is not present on the top of the model");

        if (cancelBtn && saveBtn)
            configTestRunner.getChildTest().log(Status.PASS, "Cancel button & Save button is present on the add new user model");
        else
            configTestRunner.getChildTest().log(Status.FAIL, "Cancel button & Save button is not present on the add new user model");
        String emailID = "JyotiDhage_" + configTestRunner.elementUtil.getDateAlongWithTime(configTestRunner) + "@blinxsolutions.com";
        waitAndSendText(getWebElement(formName, "addUser_EmailAddress", configTestRunner), Constants.AJAX_TIMEOUT, emailID);
        configTestRunner.getChildTest().log(Status.PASS, "User enters the email address field on Add New User Model");
        waitAndClick(getButtonWithText("Save"), Constants.AJAX_TIMEOUT);
        sleep(2000);
        if (getDivContainsWithText("Translator added successfully").isDisplayed()){
            configTestRunner.getChildTest().log(Status.PASS, "Translator added successfully in Translation Management landing page.");
        }else
            fnTakeScreenAshot(configTestRunner,"Pass","Translator is not added successfully","NotAddTranslator");
        if(searchTranslator(configTestRunner,emailID))
            fnTakeScreenAshot(configTestRunner,"Pass","Translator is added & search successfully on the translation management page.","TranslatorAddedSuccessfully");
        else
            fnTakeScreenAshot(configTestRunner,"Fail","Translator is not added & search successfully on the translation management page.","TranslatorNotAdded");
        return emailID;
    }


    public boolean searchTranslator(ConfigTestRunner configTestRunner, String value){
        boolean isAdded = false;
        try{
            sleep(2000);
            getWebElement("VendorStudies","searchInputBox",configTestRunner).isDisplayed();
            getWebElement("VendorStudies","searchInputBox",configTestRunner).clear();
            waitAndSendText(getWebElement("VendorStudies","searchInputBox",configTestRunner),Constants.AJAX_TIMEOUT,value);
            sleep(200);
            getWebElement("VendorStudies","searchInputBox",configTestRunner).sendKeys(Keys.ENTER);
            sleep(5000);
            String email = configTestRunner.elementUtil.columnValueTable("email",2).getText();
            if(email.contains(value)){
                configTestRunner.getChildTest().log(Status.PASS,"Translator is searched successfully on Translation Management page.");
                isAdded = true;
            }else
                fnTakeScreenAshot(configTestRunner,"fail","Translator is not searched successfully on Translation Management page.","translatorNotAddedSuccessfully");
        }catch (Exception e){
            e.printStackTrace();
        }
        return isAdded;
    }
    /*
     *Description: Verify  user is enabled or disabled user
     *Author: Jyoti Dhage
     *Date: 06-12-2021
     */
    public void enabledDisabledVerify(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner, "Translator Management");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Add New user for enabled & disable from translation management landing page"));
        String email = addUserTM(configTestRunner);
        searchTranslator(configTestRunner,email);
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Disabled user verification"));
        userEnabledDisabled(configTestRunner,email,"Disable User","disabledIcon");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Enabled user verification"));
        searchTranslator(configTestRunner,email);
        userEnabledDisabled(configTestRunner,email,"Enable User","enabledIcon");
    }
    /**
     *Verify Export Table -->Excel XLS format
     * param configTestRunner
     * Author: Jyoti Dhage
     * Date : 06-12-2021
     */
    public void ExportTableExcel(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner, "Translator Management");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify Export Table -->Excel XLS format"));
        String fileName= "Export_Excel"+configTestRunner.elementUtil.getDateAlongWithTime(configTestRunner);
        configTestRunner.elementUtil.ExportTableVerify(configTestRunner,fileName,"Excel (xlsx)","UserManagement");
    }
    /**
     *Verify Export Table -->CSV format
     * param configTestRunner
     * Author: Jyoti Dhage
     * Date : 06-12-2021
     */
    public void ExportTableCSV(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner, "Translator Management");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify Export Table -->Excel CSV format"));
        String fileName= "Export_CSV"+configTestRunner.elementUtil.getDateAlongWithTime(configTestRunner);
        configTestRunner.elementUtil.ExportTableVerify(configTestRunner,fileName,"CSV","UserManagement");
    }

    /**
     *Verify Export Table -->PDF format
     * param configTestRunner
     * Author: Jyoti Dhage
     * Date : 06-12-2021
     */
    public void ExportTablePDF(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner, "Translator Management");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify Export Table -->Excel PDF format"));
        String fileName= "Export_PDF"+configTestRunner.elementUtil.getDateAlongWithTime(configTestRunner);
        configTestRunner.elementUtil.ExportTableVerify(configTestRunner,fileName,"PDF","UserManagement");
    }


    /**
     *Verify User should able to edit the user
     * @param configTestRunner
     */
    public void editUserVerification(ConfigTestRunner configTestRunner) {
        fnSelectMenu(configTestRunner, "Translator Management");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Add New user for enabled & disable from translation management landing page"));
        String email = addUserTM(configTestRunner);
        searchTranslator(configTestRunner, email);
        editUser(configTestRunner,"Edit User");
        //TODO:Need to confirm from Odunayo
    }

        public void userEnabledDisabled(ConfigTestRunner configTestRunner, String email, String option, String xpathName){
        waitAndClick(configTestRunner.elementUtil.actionBtn(),Constants.AJAX_TIMEOUT);
        configTestRunner.getChildTest().log(Status.INFO,"User click on the action button");
        waitAndClick(getSpanWithText(configTestRunner,option),Constants.AJAX_TIMEOUT);
        configTestRunner.getChildTest().log(Status.INFO,"User click on the action button for :"+option);
        sleep(2000);
        searchTranslator(configTestRunner,email);
        if(getWebElement(formName,xpathName,configTestRunner).isDisplayed()){
            fnTakeScreenAshot(configTestRunner,"Pass",option+" is successfully",option+"_completed");
        }else
            fnTakeScreenAshot(configTestRunner,"fail",option+" is not successfully",option+"NotDone");
    }

    public void editUser(ConfigTestRunner configTestRunner,  String option){
        waitAndClick(configTestRunner.elementUtil.actionBtn(),Constants.AJAX_TIMEOUT);
        configTestRunner.getChildTest().log(Status.INFO,"User click on the action button");
        waitAndClick(getSpanWithText(configTestRunner,option),Constants.AJAX_TIMEOUT);
        configTestRunner.getChildTest().log(Status.INFO,"User click on the action button for :"+option);

    }

    /*
     *Description: Verify columns and filters from AG grid
     *Author:Jyoti Dhage
     * Date: 06-12-2021
     */
    public void verifyColumnFilterTranslationManagementPage(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner, "Translator Management");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify columns and filters from AG grid "));
        //Select & unselect columns
        waitAndClick(getSpanWithText(configTestRunner,"Columns"),Constants.AJAX_TIMEOUT);
        configTestRunner.getChildTest().log(Status.INFO,"User click on column tab from the translator management page table ag grid");
        configTestRunner.elementUtil.fnSelectUnselectColumn(configTestRunner,"Name");
        //apply filter
        waitAndClick(getSpanWithText(configTestRunner,"Filters"),Constants.AJAX_TIMEOUT);
        List<WebElement> elements = configTestRunner.elementUtil.getTableData("createdDate");
        configTestRunner.getChildTest().log(Status.PASS,elements.size()+" these many records are present before selecting the pivot mode");
        waitAndClick(getWebElement(formName,"createdDateFilter",configTestRunner),Constants.AJAX_TIMEOUT);
        waitAndClick(getDivWithText("(Select All)"),Constants.AJAX_TIMEOUT);
        sleep(500);
        List<WebElement> element = configTestRunner.elementUtil.getTableData("createdDate");
        if(element.size()==1){
            fnTakeScreenAshot(configTestRunner,"pass","Filter is applied successfully","Filter_Apply");
        }
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify drag & drop functionality of column tab grid"));
        //Drag & drop in values
        waitAndClick(getSpanWithText(configTestRunner,"Columns"),Constants.AJAX_TIMEOUT);
        configTestRunner.elementUtil.dragAndDrop(configTestRunner.elementUtil.dragFromColumnTab("Created Date"),getWebElement("Studies","dropAreaAgGrid",configTestRunner));
        configTestRunner.elementUtil.summationBox("Created Date").isDisplayed();
        configTestRunner.getChildTest().log(Status.INFO,"Created Date column drag & drop successfully.");
        configTestRunner.elementUtil.dragAndDrop(configTestRunner.elementUtil.dragFromColumnTab("Name"),getWebElement("Studies","dropAreaAgGrid",configTestRunner));
        configTestRunner.elementUtil.summationBox("Name").isDisplayed();
        configTestRunner.getChildTest().log(Status.INFO,"Name column drag & drop successfully.");
        configTestRunner.elementUtil.fnhighlightElement(getWebElement("Studies","dropAreaAgGrid",configTestRunner));
        configTestRunner.elementUtil.scrollDownToElement(getWebElement("Studies","dropAreaAgGrid",configTestRunner));
        configTestRunner.getChildTest().log(Status.INFO,"Quote Code column drag & drop successfully.");
        fnTakeScreenAshot(configTestRunner,"Pass","Drag and Drop successful","DragAndDrop");
    }
}
