package com.application;

import com.Utility.Constants;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class VendorStudies extends BaseAction{
    private final String formName = "VendorStudies";
    /***
     * This method is for selecting test cases for execution
     * Parameter testcase number , configTestRunner object
     * Author:Jyoti Dhage
     * Date: 25-11-2021
     */
    public void fnExecuteTC(ConfigTestRunner configTestRunner,String tcNumber){
        switch (tcNumber) {
            case "SC001_VS":
                vendorStudyTableCheck(configTestRunner);
                break;
            case "SC002_VS":
                verifySearchVendorStudiesFunctionality(configTestRunner);
                break;
            case "SC003_VS":
                exportTableVerification(configTestRunner);
                break;
            case "SC004_VS":
                verifyColumnFilter(configTestRunner);
                break;
            case "SC005_VS":
                translationPageTableCheck(configTestRunner);
                break;
            case "SC006_VS":
                translationPageColumnVerify(configTestRunner);
                break;
            case "SC007_VS":
                verifyActionColumnFromTranslationPage(configTestRunner);
                break;
            case "SC008_VS":
                verifyImportTranslatedTextFromTranslationPage(configTestRunner);
                break;
            case "SC009_VS":
                verifyExportMasterTextFromTranslationPage(configTestRunner);
                break;
            case "SC013_VS":
                verifySearchFunctionalityTranslationPage(configTestRunner);
                break;
            case "SC014_VS":
                exportTableVerificationTranslationPage(configTestRunner);
                break;
            case "SC015_VS":
                verifyColumnFilterTranslationPage(configTestRunner);
                break;
            case "SC017_VS":
                verifyTranslationPageAssignForm(configTestRunner);
                break;
            case "SC018_VS":
                verifyAssignFieldEditableOnAssignForm(configTestRunner);
                break;
            case "SC020_VS":
                VerifyLocaliseScreenReport(configTestRunner);
                break;
            case "SC021_VS":
                VerifyCommentProjectTranslationPage(configTestRunner);
                break;
            case "SC022_VS":
                verifyUserReplyOnComment(configTestRunner);
                break;
            case "SC023_VS":
                verifyViewFilesTranslationPage(configTestRunner);
                break;
            case "SC024_VS":
                verifyProjectMenu(configTestRunner);
                break;
            default:
                System.out.println("No Test case is consider for execution");
        }
    }
    /*
     *Description:  verify the landing page for vendor study
     *Author: Jyoti Dhage
     *Date: 26-11-2021
     */
    public void vendorStudyTableCheck(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner,"Vendor Studies");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode(" verify the landing page for vendor study"));
        List<String> fields = new ArrayList<>(Arrays.asList(configTestRunner.getBaseAction().getTestCase().get("VendorStudy_Tablecolumn").split(",")));
        for(String vendorStudy:fields){
            try{
                getSpanWithText(configTestRunner,vendorStudy).isDisplayed();
            }catch (Exception e){
                configTestRunner.getChildTest().log(Status.FAIL,vendorStudy +" column name is not present on the Vendor Studies home page.");
                fields.remove(vendorStudy);
                e.printStackTrace();
            }
        }
        fnTakeScreenAshot(configTestRunner,"Pass",configTestRunner.getBaseAction().getTestCase().get("VendorStudy_Tablecolumn")+" These columns are present on the Vendor Studies home page table.","VendorStudies");
    }

    /*
    *Description:verify the search box on the vendor study landing page
    *Author:Jyoti Dhage
    * Date: 26/11/2021
    */
    public void verifySearchVendorStudiesFunctionality(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner,"Vendor Studies");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("verify the search box on the vendor study landing page "));
        searchVendors(configTestRunner,"TrialMax");
        fnTakeScreenAshot(configTestRunner,"pass","Search functionality working fine","SearchNotWorking");
    }

    public void searchVendors(ConfigTestRunner configTestRunner, String value){
        try{
            sleep(2000);
            getWebElement(formName,"searchInputBox",configTestRunner).isDisplayed();
            getWebElement(formName,"searchInputBox",configTestRunner).clear();
            waitAndSendText(getWebElement(formName,"searchInputBox",configTestRunner), Constants.AJAX_TIMEOUT,value);
            sleep(200);
            getWebElement(formName,"searchInputBox",configTestRunner).sendKeys(Keys.ENTER);
            sleep(500);
            String code = configTestRunner.elementUtil.columnValueTable("studyCode",2).getText();
            if(code.contains(value)){
                configTestRunner.getChildTest().log(Status.PASS,"Vendor studies search functionality is working fine.");
            }else
                fnTakeScreenAshot(configTestRunner,"fail","Vendor studies functionality is not working fine","vendorstudiesnotWorkingFine");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void searchVendorsStudiesTranslationPage(ConfigTestRunner configTestRunner, String value){
        try{
            sleep(2000);
            getWebElement(formName,"searchInputBox",configTestRunner).isDisplayed();
            getWebElement(formName,"searchInputBox",configTestRunner).clear();
            waitAndSendText(getWebElement(formName,"searchInputBox",configTestRunner), Constants.AJAX_TIMEOUT,value);
            sleep(200);
            getWebElement(formName,"searchInputBox",configTestRunner).sendKeys(Keys.ENTER);
            sleep(500);
            String country = configTestRunner.elementUtil.columnValueTable("country.languageName",2).getText();
            if(country.contains(value)){
                fnTakeScreenAshot(configTestRunner,"Pass","Vendor studies translation language search functionality is working fine","vendorstudiesWorkingFine");
            }else
                fnTakeScreenAshot(configTestRunner,"fail","Vendor studies translation language search functionality is not working fine","vendorstudiesnotWorkingFine");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
     *Description:verify export table functionality for excel , csv, pdf
     *Author:Jyoti Dhage
     * Date: 26/11/2021
     */
    public void exportTableVerification(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner,"Vendor Studies");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify Vendor Studies Export Table -->Excel XLS format"));
        String fileName= "Export_Excel"+configTestRunner.elementUtil.getDateAlongWithTime(configTestRunner);
        configTestRunner.elementUtil.ExportTableVerify(configTestRunner,fileName,"Excel (xlsx)","UserManagement");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify Vendor Studies Export Table -->Excel CSV format"));
         fileName= "Export_CSV"+configTestRunner.elementUtil.getDateAlongWithTime(configTestRunner);
        configTestRunner.elementUtil.ExportTableVerify(configTestRunner,fileName,"CSV","UserManagement");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify Vendor Studies Export Table -->Excel PDF format"));
         fileName= "Export_PDF"+configTestRunner.elementUtil.getDateAlongWithTime(configTestRunner);
        configTestRunner.elementUtil.ExportTableVerify(configTestRunner,fileName,"PDF","UserManagement");
    }
    /*
     *Description:Verify columns and filters from AG grid
     *Author:Jyoti Dhage
     * Date: 26/11/2021
     */
    public void verifyColumnFilter(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner,"Vendor Studies");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("verify columns and filters from AG grid"));
        //Select & unselect columns
        waitAndClick(getSpanWithText(configTestRunner,"Columns"),Constants.AJAX_TIMEOUT);
        configTestRunner.getChildTest().log(Status.INFO,"User click on column tab from the studies translation page table ag grid");
        //apply filter
        configTestRunner.elementUtil.applyFilter(configTestRunner,"Sponsor Name","SH","only","Studies","sponsorName");
        //apply column
        //Select & unselect columns
        waitAndClick(getSpanWithText(configTestRunner,"Columns"),Constants.AJAX_TIMEOUT);
        configTestRunner.getChildTest().log(Status.INFO,"User click on column tab from the studies translation page table ag grid");
        configTestRunner.elementUtil.fnSelectUnselectColumn(configTestRunner,"Date Created");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify drag & drop functionality of column tab grid"));
        //Drag & drop in values
        configTestRunner.elementUtil.dragAndDrop(configTestRunner.elementUtil.dragFromColumnTab("Date Created"),getWebElement("Studies","dropAreaAgGrid",configTestRunner));
        configTestRunner.elementUtil.summationBox("Date Created").isDisplayed();
        configTestRunner.getChildTest().log(Status.INFO,"Date Created column drag & drop successfully.");
        configTestRunner.elementUtil.dragAndDrop(configTestRunner.elementUtil.dragFromColumnTab("Sponsor Name"),getWebElement("Studies","dropAreaAgGrid",configTestRunner));
        configTestRunner.getChildTest().log(Status.INFO,"Sponsor Name column drag & drop successfully.");
        configTestRunner.elementUtil.dragAndDrop(configTestRunner.elementUtil.dragFromColumnTab("Study Name"),getWebElement("Studies","dropAreaAgGrid",configTestRunner));
        configTestRunner.elementUtil.fnhighlightElement(getWebElement("Studies","dropAreaAgGrid",configTestRunner));
        configTestRunner.elementUtil.scrollDownToElement(getWebElement("Studies","dropAreaAgGrid",configTestRunner));
        configTestRunner.getChildTest().log(Status.INFO,"Quote Code column drag & drop successfully.");
        fnTakeScreenAshot(configTestRunner,"Pass","Drag and Drop successfull","DragAndDrop");
    }
    /*
     *Description:verify vendor study translation page for vendor studies
     *Author: Jyoti Dhage
     *Date: 29-11-2021
     */
    public void translationPageTableCheck(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner,"Vendor Studies");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify vendor study translation page for vendor studies "));
        String studyName = getWebElement(formName,"studyNameFirstRecord",configTestRunner).getText();
        waitAndClick(getWebElement(formName,"viewButton",configTestRunner),Constants.AJAX_TIMEOUT);
        Assert.assertEquals(studyName,getWebElement(formName,"studyNameTranslationPage",configTestRunner).getText());
        List<String> fields = new ArrayList<>(Arrays.asList(configTestRunner.getBaseAction().getTestCase().get("VendorStudy_Tablecolumn").split(",")));
        for(String vendorStudy:fields){
            try{
                getSpanWithText(configTestRunner,vendorStudy).isDisplayed();
            }catch (Exception e){
                configTestRunner.getChildTest().log(Status.FAIL,vendorStudy +" column name is not present on the Vendor Studies home page.");
                fields.remove(vendorStudy);
                e.printStackTrace();
            }
        }
        fnTakeScreenAshot(configTestRunner,"Pass",configTestRunner.getBaseAction().getTestCase().get("VendorStudy_Tablecolumn")+" These columns are present on the Vendor Studies home page table.","VendorStudies");
    }


    /*
     *Description:Verify different column is added on the vendor study translation page for vendor studies
     *Author: Jyoti Dhage
     *Date: 01-12-2021
     */
    public void translationPageColumnVerify(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner,"Vendor Studies");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify different column is added on the vendor study translation page for vendor studies"));
        waitAndClick(getWebElement(formName,"viewButton",configTestRunner),Constants.AJAX_TIMEOUT);
        //date check
        configTestRunner.elementUtil.columnValue("versionA",2).sendKeys("Date");
        String date =configTestRunner.elementUtil.columnValue("versionA",2).getText();
        if(date.equalsIgnoreCase("Date"))
            configTestRunner.getChildTest().log(Status.FAIL,"Version A column is not present on translation landing page & it is not readonly.");
        else
            configTestRunner.getChildTest().log(Status.PASS,"Version A column is present on translation landing page & it is readonly.");
        //First Review Date check
        configTestRunner.elementUtil.columnValue("firstReviewDate",2).sendKeys("firstDate");
        String firstReviewDate =configTestRunner.elementUtil.columnValue("firstReviewDate",2).getText();
        if(firstReviewDate.equalsIgnoreCase("firstDate"))
            configTestRunner.getChildTest().log(Status.FAIL,"First Review Date column is not present on translation landing page & it is not readonly.");
        else
            configTestRunner.getChildTest().log(Status.PASS,"First Review Date column is present on translation landing page & it is readonly.");
        //Version B check

        configTestRunner.elementUtil.columnValue("versionB",2).sendKeys("versionB");
        String versionB =configTestRunner.elementUtil.columnValue("versionB",2).getText();
        if(versionB.equalsIgnoreCase("versionB"))
            configTestRunner.getChildTest().log(Status.FAIL,"Version B column is not present on translation landing page & it is not readonly.");
        else
            configTestRunner.getChildTest().log(Status.PASS,"Version B column is present on translation landing page & it is readonly.");

        //Second Review Date check
        configTestRunner.elementUtil.columnValue("secondReviewDate",2).sendKeys("secondDate");
        String secondReviewDate =configTestRunner.elementUtil.columnValue("secondReviewDate",2).getText();
        if(secondReviewDate.equalsIgnoreCase("secondDate"))
            configTestRunner.getChildTest().log(Status.FAIL,"Second Review Date column is not present on translation landing page & it is not readonly.");
        else
            configTestRunner.getChildTest().log(Status.PASS,"Second Review Date column is present on translation landing page & it is readonly.");
    }

    /*
     *Description:Verify different options from  3 dots from action columns from vendor study translation page
     *Author: Jyoti Dhage
     *Date: 01-12-2021
     */
    public void verifyActionColumnFromTranslationPage(ConfigTestRunner configTestRunner) {
        fnSelectMenu(configTestRunner, "Vendor Studies");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify different options from  3 dots from action columns from vendor study translation page"));
        waitAndClick(getWebElement(formName, "viewButton", configTestRunner), Constants.AJAX_TIMEOUT);
        List<WebElement> webElements = configTestRunner.elementUtil.getTableData("status");
        for(int i=0;i<webElements.size();i++){
            String status = configTestRunner.elementUtil.getElementDataFromTable(i,"status").getText();
            if(!status.equalsIgnoreCase("Approved")){
                waitAndClick(configTestRunner.elementUtil.getThreeDotForRowInTranslationPage(i,"status"),Constants.AJAX_TIMEOUT);
                List<String> fields = new ArrayList<>(Arrays.asList(configTestRunner.getBaseAction().getTestCase().get("TranslationPage_ActionValue").split(",")));
                for(String vendorStudy:fields){
                    try{
                        getSpanWithText(configTestRunner,vendorStudy).isDisplayed();
                    }catch (Exception e){
                        configTestRunner.getChildTest().log(Status.FAIL,vendorStudy +" column name is not present on the Vendor Studies home page.");
                        fields.remove(vendorStudy);
                        e.printStackTrace();
                    }
                }
                fnTakeScreenAshot(configTestRunner,"Pass",configTestRunner.getBaseAction().getTestCase().get("TranslationPage_ActionValue")+" These columns are present on the Vendor Studies home page table.","VendorStudies");
            }
        }
    }

    /*
     *Description:Verify Import Translated text
     *Author: Jyoti Dhage
     *Date: 01-12-2021
     */
    public void verifyImportTranslatedTextFromTranslationPage(ConfigTestRunner configTestRunner) {
        fnSelectMenu(configTestRunner, "Vendor Studies");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify Import Translated text"));
        String studyName = getWebElement(formName,"studyNameFirstRecord",configTestRunner).getText();
        waitAndClick(getWebElement(formName,"viewButton",configTestRunner),Constants.AJAX_TIMEOUT);
        Assert.assertEquals(studyName,getWebElement(formName,"studyNameTranslationPage",configTestRunner).getText());
        configTestRunner.getChildTest().log(Status.INFO,"User click on view button from the study table.");
        List<WebElement> webElements = configTestRunner.elementUtil.getTableData("status");
        configTestRunner.getChildTest().log(Status.INFO,studyName+" Study is opened successfully.");
        for(int i=0;i<webElements.size();i++){
            String status = configTestRunner.elementUtil.getElementDataFromTable(i,"status").getText();
            if(!status.equalsIgnoreCase("Approved")){
                waitAndClick(configTestRunner.elementUtil.getThreeDotForRowInTranslationPage(i,"status"),Constants.AJAX_TIMEOUT);
                getSpanWithText(configTestRunner,"Import Translated Text").isDisplayed();
//                getSpanWithText(configTestRunner,"Import Translated Text").sendKeys(Keys.ENTER);
                configTestRunner.elementUtil.executeExtJsClick(configTestRunner.driver,getSpanWithText(configTestRunner,"Import Translated Text"));
                configTestRunner.getChildTest().log(Status.INFO,"Import Translated Text is selected.");
                WebElement element = getWebElement(formName,"browserFile",configTestRunner);
                uploadFile(element);
                configTestRunner.elementUtil.executeExtJsClick(configTestRunner.driver,getButtonWithText("Upload"));
                fnTakeScreenAshot(configTestRunner,"pass","File is uploaded successfully for import translation text option","FileUploadSuccess");
                getDivWithText("Upload Successful, changes should be visible shortly.").isDisplayed();
                configTestRunner.elementUtil.setFocusdblClick(getSpanWithText(configTestRunner,"Import Translated Text"));
                break;
            }
        }
    }


    /*
     *Description:Verify Import Translated text
     *Author: Jyoti Dhage
     *Date: 01-12-2021
     */
    public void verifyExportMasterTextFromTranslationPage(ConfigTestRunner configTestRunner) {
        fnSelectMenu(configTestRunner, "Vendor Studies");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify Export Master Text"));
        String studyName = getWebElement(formName,"studyNameFirstRecord",configTestRunner).getText();
        waitAndClick(getWebElement(formName,"viewButton",configTestRunner),Constants.AJAX_TIMEOUT);
        Assert.assertEquals(studyName,getWebElement(formName,"studyNameTranslationPage",configTestRunner).getText());
        configTestRunner.getChildTest().log(Status.INFO,"User click on view button from the study table.");
        List<WebElement> webElements = configTestRunner.elementUtil.getTableData("status");
        configTestRunner.getChildTest().log(Status.INFO,studyName+" Study is opened successfully.");
        for(int i=0;i<webElements.size();i++){
            String status = configTestRunner.elementUtil.getElementDataFromTable(i,"status").getText();
            if(!status.equalsIgnoreCase("Approved")){
                waitAndClick(configTestRunner.elementUtil.getThreeDotForRowInTranslationPage(i,"status"),Constants.AJAX_TIMEOUT);
                getSpanWithText(configTestRunner,"Export Master Text").isDisplayed();
//                getSpanWithText(configTestRunner,"Import Translated Text").sendKeys(Keys.ENTER);
                configTestRunner.elementUtil.executeExtJsClick(configTestRunner.driver,getSpanWithText(configTestRunner,"Export Master Text"));
                configTestRunner.getChildTest().log(Status.INFO,"Export Master Text is selected.");
                if(isFileDownloaded(Constants.FileDownLoadPath,studyName))
                    fnTakeScreenAshot(configTestRunner,"pass","File is downloaded successfully for Export Master Text option","fileDownloadSuccessful");
                else
                    fnTakeScreenAshot(configTestRunner,"fail","File is not downloaded successfully for Export Master Text option","fileNotDownloaded");
                configTestRunner.elementUtil.executeExtJsClick(configTestRunner.driver,getSpanWithText(configTestRunner,"Export Master Text"));
                break;
            }
        }
    }


    /*
     *Description:verify the Export table button on vendor study translation page
     *Author:Jyoti Dhage
     * Date: 01-12-2021
     */
    public void exportTableVerificationTranslationPage(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner,"Vendor Studies");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify the Export table button on vendor study translation page -->Excel XLS format"));
        String fileName= "Export_Excel"+configTestRunner.elementUtil.getDateAlongWithTime(configTestRunner);
        configTestRunner.elementUtil.ExportTableVerify(configTestRunner,fileName,"Excel (xlsx)","UserManagement");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify the Export table button on vendor study translation page -->Excel CSV format"));
        fileName= "Export_CSV"+configTestRunner.elementUtil.getDateAlongWithTime(configTestRunner);
        configTestRunner.elementUtil.ExportTableVerify(configTestRunner,fileName,"CSV","UserManagement");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify the Export table button on vendor study translation page -->Excel PDF format"));
        fileName= "Export_PDF"+configTestRunner.elementUtil.getDateAlongWithTime(configTestRunner);
        configTestRunner.elementUtil.ExportTableVerify(configTestRunner,fileName,"PDF","UserManagement");
    }
    /*
     *Description:Verify the search box on the vendor study translation page
     *Author: Jyoti Dhage
     *Date: 01-12-2021
     */
    public void verifySearchFunctionalityTranslationPage(ConfigTestRunner configTestRunner) {
        fnSelectMenu(configTestRunner, "Vendor Studies");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify the search box on the vendor study translation page"));
        String studyName = getWebElement(formName, "studyNameFirstRecord", configTestRunner).getText();
        waitAndClick(getWebElement(formName, "viewButton", configTestRunner), Constants.AJAX_TIMEOUT);
        Assert.assertEquals(studyName, getWebElement(formName, "studyNameTranslationPage", configTestRunner).getText());
        configTestRunner.getChildTest().log(Status.INFO, "User click on view button from the study table.");
        searchVendorsStudiesTranslationPage(configTestRunner,"German");
    }
    /*
     *Description: Verify columns and filters from AG grid
     *Author:Jyoti Dhage
     * Date: 01-12-2021
     */
    public void verifyColumnFilterTranslationPage(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner, "Vendor Studies");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify columns and filters from AG grid "));
        String studyName = getWebElement(formName, "studyNameFirstRecord", configTestRunner).getText();
        waitAndClick(getWebElement(formName, "viewButton", configTestRunner), Constants.AJAX_TIMEOUT);
        Assert.assertEquals(studyName, getWebElement(formName, "studyNameTranslationPage", configTestRunner).getText());
        //Select & unselect columns
        waitAndClick(getSpanWithText(configTestRunner,"Columns"),Constants.AJAX_TIMEOUT);
        configTestRunner.getChildTest().log(Status.INFO,"User click on column tab from the studies translation page table ag grid");
        configTestRunner.elementUtil.fnSelectUnselectColumn(configTestRunner,"Status");
        //apply filter
        configTestRunner.elementUtil.applyFilter(configTestRunner,"Status","draft","approve","Studies","status");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify drag & drop functionality of column tab grid"));
        //Drag & drop in values
        waitAndClick(getSpanWithText(configTestRunner,"Columns"),Constants.AJAX_TIMEOUT);
        configTestRunner.elementUtil.dragAndDrop(configTestRunner.elementUtil.dragFromColumnTab("Status"),getWebElement("Studies","dropAreaAgGrid",configTestRunner));
        configTestRunner.elementUtil.summationBox("Status").isDisplayed();
        configTestRunner.getChildTest().log(Status.INFO,"Status column drag & drop successfully.");
        configTestRunner.elementUtil.dragAndDrop(configTestRunner.elementUtil.dragFromColumnTab("Created Date"),getWebElement("Studies","dropAreaAgGrid",configTestRunner));
        configTestRunner.elementUtil.summationBox("Created Date").isDisplayed();
        configTestRunner.getChildTest().log(Status.INFO,"Created Date column drag & drop successfully.");
        configTestRunner.elementUtil.dragAndDrop(configTestRunner.elementUtil.dragFromColumnTab("Language"),getWebElement("Studies","dropAreaAgGrid",configTestRunner));
        configTestRunner.elementUtil.summationBox("Language").isDisplayed();
        configTestRunner.getChildTest().log(Status.INFO,"Language column drag & drop successfully.");
        configTestRunner.elementUtil.fnhighlightElement(getWebElement("Studies","dropAreaAgGrid",configTestRunner));
        configTestRunner.elementUtil.scrollDownToElement(getWebElement("Studies","dropAreaAgGrid",configTestRunner));
        configTestRunner.getChildTest().log(Status.INFO,"Quote Code column drag & drop successfully.");
        fnTakeScreenAshot(configTestRunner,"Pass","Drag and Drop successfull","DragAndDrop");
    }
    /*
     *Description: Verify the translation view page
     *Author:Jyoti Dhage
     * Date: 06-12-2021
     */
    public void verifyTranslationPageAssignForm(ConfigTestRunner configTestRunner) {
        fnSelectMenu(configTestRunner, "Vendor Studies");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify columns and filters from AG grid "));
        String studyName = getWebElement(formName, "studyNameFirstRecord", configTestRunner).getText();
        waitAndClick(getWebElement(formName, "viewButton", configTestRunner), Constants.AJAX_TIMEOUT);
        Assert.assertEquals(studyName, getWebElement(formName, "studyNameTranslationPage", configTestRunner).getText());
        //user click on the assign button
        waitAndClick(getWebElement(formName, "assignBtn", configTestRunner), Constants.AJAX_TIMEOUT);
        //Vendor Study Table
        List<String> fields = new ArrayList<>(Arrays.asList(configTestRunner.getBaseAction().getTestCase().get("VendorStudy_Tablecolumn").split(",")));
        for(String vendorStudy:fields){
            try{
                getSpanWithText(configTestRunner,vendorStudy).isDisplayed();
            }catch (Exception e){
                configTestRunner.getChildTest().log(Status.FAIL,vendorStudy +" column name is not present on the Vendor Studies Translation Form page");
                fields.remove(vendorStudy);
                e.printStackTrace();
            }
        }
        fnTakeScreenAshot(configTestRunner,"Pass",configTestRunner.getBaseAction().getTestCase().get("VendorStudy_Tablecolumn")+" These columns are present on the Vendor Studies home page table.","VendorStudies");
        configTestRunner.elementUtil.getElementDataFromTable(0, "translator");
        List<WebElement> webElements = configTestRunner.elementUtil.getTableData("translator");
        configTestRunner.getChildTest().log(Status.INFO, studyName + " Study is opened successfully.");
        for (int i = 0; i < webElements.size(); i++) {
            String translator = configTestRunner.elementUtil.getElementDataFromTable(i, "translator").getText();
            if (!translator.equalsIgnoreCase("Translation Not Required") || translator.equalsIgnoreCase("Select an Option") ) {
             //   configTestRunner.elementUtil.fnSelectDropDownValue(configTestRunner.elementUtil.getElementDataFromTable(i,"translator"),configTestRunner.getBaseAction().getTestCase().get(""));
                waitAndClick(configTestRunner.elementUtil.getElementDataFromTable(i,"translator"),Constants.AJAX_TIMEOUT);
                waitAndClick(configTestRunner.elementUtil.getTextWithoutTag(configTestRunner.getBaseAction().getTestCase().get("TranslatorName")),Constants.AJAX_TIMEOUT);
                sleep(3000);
                translator = configTestRunner.elementUtil.getElementDataFromTable(i, "translator").getText();
                if(translator.contains(configTestRunner.getBaseAction().getTestCase().get("TranslatorName"))){
                    fnTakeScreenAshot(configTestRunner,"Pass","Translation is editable for the user","TranslationNotPresent");
                }
                else
                    fnTakeScreenAshot(configTestRunner,"fail","Translation is not editable for the user","TranslationNotPresent");
                break;
            }
            getWebElement(formName,"pagination",configTestRunner).isDisplayed();
            configTestRunner.getChildTest().log(Status.PASS,"In translation view page is editable to the user with pagination");
        }
    }
    /*
     *Description: Verify the translation view page
     *Author:Jyoti Dhage
     * Date: 07-12-2021
     */
    public void verifyAssignFieldEditableOnAssignForm(ConfigTestRunner configTestRunner) {
        fnSelectMenu(configTestRunner, "Vendor Studies");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify columns and filters from AG grid "));
        String studyName = getWebElement(formName, "studyNameFirstRecord", configTestRunner).getText();
        waitAndClick(getWebElement(formName, "viewButton", configTestRunner), Constants.AJAX_TIMEOUT);
        Assert.assertEquals(studyName, getWebElement(formName, "studyNameTranslationPage", configTestRunner).getText());
        configTestRunner.getChildTest().log(Status.INFO, studyName + " Study is opened successfully.");
        waitAndClick(getWebElement(formName, "assignBtn", configTestRunner), Constants.AJAX_TIMEOUT);
        List<WebElement> webElements = configTestRunner.elementUtil.getTableData("translator");
        configTestRunner.getChildTest().log(Status.PASS,"User click on the assign button from study translation page.");
        for (int i = 0; i < webElements.size(); i++) {
            String translator = configTestRunner.elementUtil.getElementDataFromTable(i, "translator").getText();
            if (!translator.equalsIgnoreCase("Translation Not Required") || translator.equalsIgnoreCase("Select an Option") ) {
                //   configTestRunner.elementUtil.fnSelectDropDownValue(configTestRunner.elementUtil.getElementDataFromTable(i,"translator"),configTestRunner.getBaseAction().getTestCase().get(""));
                waitAndClick(configTestRunner.elementUtil.getElementDataFromTable(i,"translator"),Constants.AJAX_TIMEOUT);
                waitAndClick(configTestRunner.elementUtil.getTextWithoutTag(configTestRunner.getBaseAction().getTestCase().get("TranslatorName")),Constants.AJAX_TIMEOUT);
                sleep(3000);
                translator = configTestRunner.elementUtil.getElementDataFromTable(i, "translator").getText();
                if(translator.contains(configTestRunner.getBaseAction().getTestCase().get("TranslatorName"))){
                    fnTakeScreenAshot(configTestRunner,"Pass","Translation view page is present & Translation is editable for the user","TranslationNotPresent");
                }
                else
                    fnTakeScreenAshot(configTestRunner,"fail","Translation view page is present & Translation is not editable for the user","TranslationNotPresent");
                break;
            }
        }
    }
    /*
    *Verify Localised Screen Report on project translation page
    * Author:Jyoti Dhage
    * Date: 12-07-2021
     */
    public void VerifyLocaliseScreenReport(ConfigTestRunner configTestRunner) {
        fnSelectMenu(configTestRunner, "Vendor Studies");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify Localised Screen Report on project translation page"));
        String studyName = getWebElement(formName, "studyNameFirstRecord", configTestRunner).getText();
        waitAndClick(getWebElement(formName, "viewButton", configTestRunner), Constants.AJAX_TIMEOUT);
        Assert.assertEquals(studyName, getWebElement(formName, "studyNameTranslationPage", configTestRunner).getText());
        configTestRunner.getChildTest().log(Status.INFO, studyName + " Study is opened successfully.");
        waitAndClick(getWebElement(formName, "assignBtn", configTestRunner), Constants.AJAX_TIMEOUT);
        configTestRunner.getChildTest().log(Status.INFO,"Translation page should get opened successfully.");
        waitAndClick(getSpanWithText1("View"),Constants.AJAX_TIMEOUT);
        getButtonWithText("Localised Screen Report").isDisplayed();
        configTestRunner.getChildTest().log(Status.PASS,"Localised Screen Report is opened on project translation page");
        waitAndClick(getButtonWithText("Localised Screen Report"),Constants.AJAX_TIMEOUT);
        sleep(2000);
        if(isFileDownloaded(Constants.FileDownLoadPath,"Permissions test cases.docx"))
            configTestRunner.getChildTest().log(Status.PASS,"Localised screen report is downloaded from the project translation page.");
        else
            configTestRunner.getChildTest().log(Status.FAIL,"Localised screen report is not downloaded from the project translation page.");
    }

    /*
     *Verify make a comment on project translation page
     * Author:Jyoti Dhage
     * Date: 12-07-2021
     */
    public void VerifyCommentProjectTranslationPage(ConfigTestRunner configTestRunner) {
        fnSelectMenu(configTestRunner, "Vendor Studies");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify make a comment on project translation page"));
        String studyName = getWebElement(formName, "studyNameFirstRecord", configTestRunner).getText();
        waitAndClick(getWebElement(formName, "viewButton", configTestRunner), Constants.AJAX_TIMEOUT);
        Assert.assertEquals(studyName, getWebElement(formName, "studyNameTranslationPage", configTestRunner).getText());
        configTestRunner.getChildTest().log(Status.INFO, studyName + " Study is opened successfully.");
        waitAndClick(getWebElement(formName, "assignBtn", configTestRunner), Constants.AJAX_TIMEOUT);
        configTestRunner.getChildTest().log(Status.INFO,"Translation page should get opened successfully.");
        waitAndClick(getSpanWithText1("View"),Constants.AJAX_TIMEOUT);
        waitAndClick(getButtonWithText("Cancel"),Constants.AJAX_TIMEOUT);
        configTestRunner.getChildTest().log(Status.PASS,"Cancel button is present on the screen to cancel the action.");
        String comment= "CommentAddedByAutomationScript"+configTestRunner.elementUtil.getDateAlongWithTime(configTestRunner);
        waitAndSendText(getWebElement(formName,"commentField",configTestRunner),Constants.AJAX_TIMEOUT,comment);
        waitAndClick(getButtonWithText("Submit"),Constants.AJAX_TIMEOUT);
        if(getDivWithText("Comment successful.").isDisplayed() && configTestRunner.elementUtil.replyButtonCommentBox(comment).isDisplayed()){
            configTestRunner.getChildTest().log(Status.PASS,"Comment is added successfully");
        }else
            configTestRunner.getChildTest().log(Status.FAIL,"Comment is not added successfully.");
        //who make the comment & the timestamp of the comment
        String name =configTestRunner.elementUtil.commentNameDateTime(comment,3).getText();
        System.out.println(name+ " has log the comment in the system");
        String date =configTestRunner.elementUtil.commentNameDateTime(comment,2).getText();
        String time =configTestRunner.elementUtil.commentNameDateTime(comment,1).getText();
        System.out.println(name+ " has log the comment in the system on :"+ date +" and time is :"+time);

    }
    /*
     *Verify user comments reply should be track on project translation page
     * Author:Jyoti Dhage
     * Date: 12-10-2021
     */
    public void verifyUserReplyOnComment(ConfigTestRunner configTestRunner) {
        fnSelectMenu(configTestRunner, "Vendor Studies");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify user can reply on project translation page"));
        String studyName = getWebElement(formName, "studyNameFirstRecord", configTestRunner).getText();
        waitAndClick(getWebElement(formName, "viewButton", configTestRunner), Constants.AJAX_TIMEOUT);
        Assert.assertEquals(studyName, getWebElement(formName, "studyNameTranslationPage", configTestRunner).getText());
        configTestRunner.getChildTest().log(Status.INFO, studyName + " Study is opened successfully.");
        waitAndClick(getWebElement(formName, "assignBtn", configTestRunner), Constants.AJAX_TIMEOUT);
        configTestRunner.getChildTest().log(Status.INFO,"Translation page should get opened successfully.");
        waitAndClick(getSpanWithText1("View"),Constants.AJAX_TIMEOUT);
        String comment= "CommentAddedByAutomationScript"+configTestRunner.elementUtil.getDateAlongWithTime(configTestRunner);
        waitAndSendText(getWebElement(formName,"commentField",configTestRunner),Constants.AJAX_TIMEOUT,comment);
        waitAndClick(getButtonWithText("Submit"),Constants.AJAX_TIMEOUT);
        if(getDivWithText("Comment successful.").isDisplayed() && configTestRunner.elementUtil.replyButtonCommentBox(comment).isDisplayed()){
            configTestRunner.getChildTest().log(Status.PASS,"Comment is added successfully");
        }else
            configTestRunner.getChildTest().log(Status.FAIL,"Comment is not added successfully.");
        waitAndClick(configTestRunner.elementUtil.replyButtonCommentBox(comment),Constants.AJAX_TIMEOUT);
        String name =configTestRunner.elementUtil.commentNameDateTime(comment,3).getText();
        WebElement replyinputBox= configTestRunner.elementUtil.replyToComment(name);
        replyinputBox.isDisplayed();
        Random random = new Random();
        int no = random.nextInt();
        String replyComment ="Reply through automation script_"+no;
        waitAndSendText(replyinputBox,Constants.AJAX_TIMEOUT,replyComment);
         name =configTestRunner.elementUtil.commentNameDateTime(comment,3).getText();
        System.out.println(name+ " has reply to the comment in the system");
        String date =configTestRunner.elementUtil.commentNameDateTime(comment,2).getText();
        String time =configTestRunner.elementUtil.commentNameDateTime(comment,1).getText();
        System.out.println(name+ " has reply to the comment in the system on :"+ date +" and time is :"+time);
    }
    /*
     *Verify user comments reply should be track on project translation page
     * Author:Jyoti Dhage
     * Date: 12-10-2021
     */
    public void verifyViewFilesTranslationPage(ConfigTestRunner configTestRunner) {
        fnSelectMenu(configTestRunner, "Vendor Studies");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify user can view the Files on project translation page"));
        String studyName = getWebElement(formName, "studyNameFirstRecord", configTestRunner).getText();
        waitAndClick(getWebElement(formName, "viewButton", configTestRunner), Constants.AJAX_TIMEOUT);
        Assert.assertEquals(studyName, getWebElement(formName, "studyNameTranslationPage", configTestRunner).getText());
        configTestRunner.getChildTest().log(Status.INFO, studyName + " Study is opened successfully.");
        waitAndClick(getWebElement(formName, "assignBtn", configTestRunner), Constants.AJAX_TIMEOUT);
        waitAndClick(getSpanWithText1("View"),Constants.AJAX_TIMEOUT);
        getButtonWithText("View Files").isDisplayed();
        waitAndClick(getButtonWithText("View Files"),Constants.AJAX_TIMEOUT);
        List<WebElement> elements = getWebElements(configTestRunner,formName,"viewFiles");
        for(WebElement fileName : elements){
            configTestRunner.getChildTest().log(Status.PASS,"On view files screen contain files :"+fileName.getText());
        }
        waitAndClick(getButtonWithText("Close"),Constants.AJAX_TIMEOUT);
    }

    /*
     *Verify Verify the user is able to view the project menu with different menu
     * Author:Jyoti Dhage
     * Date: 12-10-2021
     */
    public void verifyProjectMenu(ConfigTestRunner configTestRunner) {
        fnSelectMenu(configTestRunner, "Vendor Studies");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify the user is able to view the project menu with different menu"));
        String studyName = getWebElement(formName, "studyNameFirstRecord", configTestRunner).getText();
        waitAndClick(getWebElement(formName, "viewButton", configTestRunner), Constants.AJAX_TIMEOUT);
        Assert.assertEquals(studyName, getWebElement(formName, "studyNameTranslationPage", configTestRunner).getText());
        configTestRunner.getChildTest().log(Status.INFO, studyName + " Study is opened successfully.");
        waitAndClick(getWebElement(formName, "assignBtn", configTestRunner), Constants.AJAX_TIMEOUT);
        waitAndClick(getWebElement(formName,"completeBtn",configTestRunner),Constants.AJAX_TIMEOUT);
        configTestRunner.getChildTest().log(Status.PASS,"User click on complete button");
        waitAndClick(getWebElement(formName,"projectMenu_PageTranslationPage",configTestRunner),Constants.AJAX_TIMEOUT);
        List<String> fields = new ArrayList<>(Arrays.asList(configTestRunner.getBaseAction().getTestCase().get("projectTranslationPageMenu").split(",")));
        for(String projectMenu:fields){
            try{
                configTestRunner.elementUtil.projectMenu_TranslationPage(projectMenu).isDisplayed();
            }catch (Exception e){
                configTestRunner.getChildTest().log(Status.FAIL,projectMenu +" this menu is present on the project translation page.");
                fields.remove(projectMenu);
                e.printStackTrace();
            }
        }
    }


}
