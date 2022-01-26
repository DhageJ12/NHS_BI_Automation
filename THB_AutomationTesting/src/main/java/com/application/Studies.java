package com.application;

import com.Utility.Constants;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Studies extends BaseAction{

    private final String formName = "Studies";
    /***
     * This method is for selecting test cases for execution
     * Parameter testcase number , configTestRunner object
     * Author:Jyoti Dhage
     * Date: 02-12-2021
     */
    public void fnExecuteTC(ConfigTestRunner configTestRunner,String tcNumber){
        switch (tcNumber) {
            case "SC001_S":
                vendorStudyTableCheck(configTestRunner);
                break;
            case "SC002_S":
                verifySearchFunctionalityStudiesPage(configTestRunner);
                break;
            case "SC003_S":
                exportTableVerification(configTestRunner);
                break;
            case "SC004_S":
                verifyColumnFilterStudyLandingPage(configTestRunner);
                break;
            case "SC005_S":
                verifySignantStudyLandingPage(configTestRunner);
                break;
            case "SC006_S":
                signantStudyTranslationPage(configTestRunner);
                break;
            case "SC007_S":
                signantStudiesLandingTable(configTestRunner);
                break;
            case "SC010_S":
                exportTableVerificationTranslationPage(configTestRunner);
                break;
            case "SC011_S":
                verifyColumnFilterTranslationPage(configTestRunner);
                break;
            case "SC012_S":
                uploadFileSignantTranslationPage(configTestRunner);
                break;
            default:
                System.out.println("No Test case is consider for execution");
        }
    }


    /*
     *Description:Verify the landing page for Signant studies
     *Author: Jyoti Dhage
     *Date: 02-12-2021
     */
    public void vendorStudyTableCheck(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner,"Studies");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify the landing page for Signant studies "));
        List<String> fields = new ArrayList<>(Arrays.asList(configTestRunner.getBaseAction().getTestCase().get("Studies_TableColumnName").split(",")));
        for(String Studies:fields){
            try{
                getSpanWithText(configTestRunner,Studies).isDisplayed();
            }catch (Exception e){
                configTestRunner.getChildTest().log(Status.FAIL,Studies +" column name is not present on the Vendor Studies home page.");
                fields.remove(Studies);
                e.printStackTrace();
            }
        }
        fnTakeScreenAshot(configTestRunner,"Pass",configTestRunner.getBaseAction().getTestCase().get("Studies_TableColumnName")+" These columns are present on the Studies home page table.","StudiesTable");
    }
    /*
     *Description:Verify the search box on the Signant studies landing page
     *Author: Jyoti Dhage
     *Date: 03-12-2021
     */
    public void verifySearchFunctionalityStudiesPage(ConfigTestRunner configTestRunner) {
        fnSelectMenu(configTestRunner,"Studies");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify the search box on the Signant studies landing page"));
        searchVendorsStudiesTranslationPage(configTestRunner,"TrialMax","studyCode","VendorStudies");
    }


    public void searchVendorsStudiesTranslationPage(ConfigTestRunner configTestRunner, String value, String columnName,String formName){
        try{
            sleep(2000);
            getWebElement(formName,"searchInputBox",configTestRunner).isDisplayed();
            getWebElement(formName,"searchInputBox",configTestRunner).clear();
            waitAndSendText(getWebElement(formName,"searchInputBox",configTestRunner), Constants.AJAX_TIMEOUT,value);
            sleep(200);
            getWebElement(formName,"searchInputBox",configTestRunner).sendKeys(Keys.ENTER);
            sleep(500);
            String country = getWebElement("Studies","studyCode",configTestRunner).getAttribute("value");
            if(country.contains(value)){
                fnTakeScreenAshot(configTestRunner,"Pass","Studies page search functionality is working fine","studiesWorkingFine");
            }else
                fnTakeScreenAshot(configTestRunner,"fail","Studies page search functionality is not working fine","studiesNotWorkingFine");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
     *Description:Verify the Export Table on the Signant studies landing page
     *Author:Jyoti Dhage
     * Date: 03/12/2021
     */
    public void exportTableVerification(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner,"Studies");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify the Export Table on the Signant studies landing page -->Excel XLS format"));
        String fileName= "Export_Excel"+configTestRunner.elementUtil.getDateAlongWithTime(configTestRunner);
        configTestRunner.elementUtil.ExportTableVerify(configTestRunner,fileName,"Excel (xlsx)","UserManagement");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify the Export Table on the Signant studies landing page -->Excel CSV format"));
        fileName= "Export_CSV"+configTestRunner.elementUtil.getDateAlongWithTime(configTestRunner);
        configTestRunner.elementUtil.ExportTableVerify(configTestRunner,fileName,"CSV","UserManagement");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify the Export Table on the Signant studies landing page -->Excel PDF format"));
        fileName= "Export_PDF"+configTestRunner.elementUtil.getDateAlongWithTime(configTestRunner);
        configTestRunner.elementUtil.ExportTableVerify(configTestRunner,fileName,"PDF","UserManagement");
    }

    /*
     *Description:Verify Signant studies team Landing Page for studies
     *Author: Jyoti Dhage
     *Date: 03-12-2021
     */
    public void verifySignantStudyLandingPage(ConfigTestRunner configTestRunner) {
        fnSelectMenu(configTestRunner,"Studies");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify Signant studies team Landing Page for studies"));
        String studyName = configTestRunner.elementUtil.columnValue("name",2).getText();
        waitAndClick(getWebElement("VendorStudies","viewButton",configTestRunner),Constants.AJAX_TIMEOUT);
        Assert.assertEquals(studyName,getWebElement("VendorStudies","studyNameTranslationPage",configTestRunner).getText());
        configTestRunner.getChildTest().log(Status.INFO,"User click on view button from the study page");
        List<String> fields = new ArrayList<>(Arrays.asList(configTestRunner.getBaseAction().getTestCase().get("Studies_TableColumnName").split(",")));
        for(String Studies:fields){
            try{
                getSpanWithText(configTestRunner,Studies).isDisplayed();
            }catch (Exception e){
                configTestRunner.getChildTest().log(Status.FAIL,Studies +" column name is not present on the Vendor Studies home page.");
                fields.remove(Studies);
                e.printStackTrace();
            }
        }
        fnTakeScreenAshot(configTestRunner,"Pass",configTestRunner.getBaseAction().getTestCase().get("Studies_TableColumnName")+" These columns are present on the Studies home page table.","StudiesTable");
    }

    /*
     *Description:Verify different column is added on the Signant studies  translation page for Signant studies
     *Author: Jyoti Dhage
     *Date: 03-12-2021
     */
    public void signantStudyTranslationPage(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner,"Studies");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify different column is added on the Signant studies  translation page for Signant studies"));
        String studyName = configTestRunner.elementUtil.columnValue("name",2).getText();
        waitAndClick(getWebElement("VendorStudies","viewButton",configTestRunner),Constants.AJAX_TIMEOUT);
        Assert.assertEquals(studyName,getWebElement("VendorStudies","studyNameTranslationPage",configTestRunner).getText());
        configTestRunner.getChildTest().log(Status.INFO,"User click on view button from the study page");
        //versionA date check
        fnEditFieldCheck(configTestRunner,"versionA",3);
        //First Review
        fnEditFieldCheck(configTestRunner,"firstReviewDate",2);
        //versionB
        fnEditFieldCheck(configTestRunner,"versionB",4);
        //versionB
        fnEditFieldCheck(configTestRunner,"secondReviewDate",4);
    }


    public void fnEditFieldCheck(ConfigTestRunner configTestRunner, String columnName,int noOfDays){
        String date1 =configTestRunner.elementUtil.getCurrentSystemDate(configTestRunner,noOfDays);
        String date = configTestRunner.elementUtil.fnDate(date1.replace("/","-"));
        String date2="";
        if(date.charAt(0)=='0')
           date= date.replace("0","");
        date1 =configTestRunner.elementUtil.fnMonth(date1.replace("/","-"))+"/"+date+"/"+configTestRunner.elementUtil.fnYear(date1.replace("/","-"));
        configTestRunner.elementUtil.getSignantStudyDatePickerField(2,columnName).click();
        configTestRunner.elementUtil.executeExtJsClick(configTestRunner.driver,getPWithText(date));
        waitAndClick(getButtonWithText("Save"),Constants.AJAX_TIMEOUT);
        sleep(2000);
        String actualDate =configTestRunner.elementUtil.getSignantStudyDatePickerField(2,columnName).getText();
        if(date1.equalsIgnoreCase(actualDate))
            configTestRunner.getChildTest().log(Status.PASS,columnName+" column is present on signant study translation landing page & it is editable.");
        else
            configTestRunner.getChildTest().log(Status.FAIL,columnName+" column is present on signant study translation landing page & it is not editable.");
    }
    /*
     *Description:verify the Export table button from signant translation landing page
     *Author:Jyoti Dhage
     * Date: 03/12/2021
     */
    public void exportTableVerificationTranslationPage(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner,"Studies");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify the Export Table on the Signant studies translation page -->Excel XLS format"));
        String studyName = configTestRunner.elementUtil.columnValue("name",2).getText();
        waitAndClick(getWebElement("VendorStudies","viewButton",configTestRunner),Constants.AJAX_TIMEOUT);
        Assert.assertEquals(studyName,getWebElement("VendorStudies","studyNameTranslationPage",configTestRunner).getText());
        configTestRunner.getChildTest().log(Status.INFO,"User click on view button from the study page");
        String fileName= "Export_Excel"+configTestRunner.elementUtil.getDateAlongWithTime(configTestRunner);
        configTestRunner.elementUtil.ExportTableVerify(configTestRunner,fileName,"Excel (xlsx)","UserManagement");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify the Export Table on the Signant studies translation page -->Excel CSV format"));
        fileName= "Export_CSV"+configTestRunner.elementUtil.getDateAlongWithTime(configTestRunner);
        configTestRunner.elementUtil.ExportTableVerify(configTestRunner,fileName,"CSV","UserManagement");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify the Export Table on the Signant studies translation page -->Excel PDF format"));
        fileName= "Export_PDF"+configTestRunner.elementUtil.getDateAlongWithTime(configTestRunner);
        configTestRunner.elementUtil.ExportTableVerify(configTestRunner,fileName,"PDF","UserManagement");
    }
    /*
     *Description:Verify upload  Files in signant translation page
     *Author:Jyoti Dhage
     * Date: 03/12/2021
     */
    public void uploadFileSignantTranslationPage(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner,"Studies");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify the Export Table on the Signant studies translation page -->Excel XLS format"));
        String studyName = configTestRunner.elementUtil.columnValue("name",2).getText();
        waitAndClick(getWebElement("VendorStudies","viewButton",configTestRunner),Constants.AJAX_TIMEOUT);
        Assert.assertEquals(studyName,getWebElement("VendorStudies","studyNameTranslationPage",configTestRunner).getText());
        configTestRunner.getChildTest().log(Status.INFO,"User click on view button from the study page");
        waitAndClick(getButtonWithText("Upload Files"),Constants.AJAX_TIMEOUT);
        configTestRunner.getChildTest().log(Status.INFO,"User click on upload files from the signant study translation page.");
        getButtonWithText("Cancel").isDisplayed();
        configTestRunner.getChildTest().log(Status.INFO,"To upload the file browse pop up is opened successfully.");
        WebElement element = getWebElement("VendorStudies","browserFile",configTestRunner);
        uploadFile(element);
        configTestRunner.getChildTest().log(Status.INFO,"Upload & Cancel button is present on signant translation page.");
        configTestRunner.elementUtil.executeExtJsClick(configTestRunner.driver,getButtonWithText("Upload"));
        fnTakeScreenAshot(configTestRunner,"pass","File is uploaded successfully file upload option from signant health page","FileUploadSuccess");
    }
    /*
     *Description:Verify Signant studies team Landing Page for studies
     *Author:Jyoti Dhage
     * Date: 03/12/2021
     */

    public void signantStudiesLandingTable(ConfigTestRunner configTestRunner) {
        fnSelectMenu(configTestRunner, "Studies");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify Signant studies team Landing Page for studies"));
        String studyName = configTestRunner.elementUtil.columnValue("name", 2).getText();
        waitAndClick(getWebElement("VendorStudies", "viewButton", configTestRunner), Constants.AJAX_TIMEOUT);
        Assert.assertEquals(studyName, getWebElement("VendorStudies", "studyNameTranslationPage", configTestRunner).getText());
        configTestRunner.getChildTest().log(Status.INFO, "User click on view button from the study page");
        List<WebElement> elements = getWebElements(configTestRunner, formName, "StudiesLanguagePage");
        configTestRunner.getChildTest().log(Status.PASS, "Status***CreateDate**Language**IRBDate**VersionA**FirstReviewDate**VersionB**SecondReviewDate**VersionOneDate**COTReceiptDate**QRGReceiptDate**SumReceiptDate**Priority**Requestor**COT**Version");
        for (int i = 0; i < elements.size() - 1; i++) {
            configTestRunner.elementUtil.getElementDataFromTable(i, "status").click();
            String status = configTestRunner.elementUtil.getElementDataFromTable(i, "status").getText();
            String createdDate = configTestRunner.elementUtil.getElementDataFromTable(i, "createdDate").getText();
            String languageName = configTestRunner.elementUtil.getElementDataFromTable(i, "country.languageName").getText();
            String irbDate = configTestRunner.elementUtil.getElementDataFromTable(i, "irbDate").getText();
            String versionA = configTestRunner.elementUtil.getElementDataFromTable(i, "versionA").getText();
            String firstReviewDate = configTestRunner.elementUtil.getElementDataFromTable(i, "firstReviewDate").getText();
            String versionB = configTestRunner.elementUtil.getElementDataFromTable(i, "versionB").getText();
            String secondReviewDate = configTestRunner.elementUtil.getElementDataFromTable(i, "secondReviewDate").getText();
            configTestRunner.elementUtil.getElementDataFromTable(i, "secondReviewDate").sendKeys(Keys.TAB);
            String versionOneDate = configTestRunner.elementUtil.getElementDataFromTable(i, "versionOneDate").getText();
            String cotDate = configTestRunner.elementUtil.getElementDataFromTable(i, "cotDate").getText();
            String qrgDate = configTestRunner.elementUtil.getElementDataFromTable(i, "qrgDate").getText();
            configTestRunner.elementUtil.getElementDataFromTable(i, "qrgDate").sendKeys(Keys.TAB);
            String sumReceiptDate = configTestRunner.elementUtil.getElementDataFromTable(i, "smDate").getText();
            configTestRunner.elementUtil.getElementDataFromTable(i, "smDate").sendKeys(Keys.TAB);
            String priority = configTestRunner.elementUtil.getElementDataFromTable(i, "priority").getText();
            configTestRunner.elementUtil.getElementDataFromTable(i, "priority").sendKeys(Keys.TAB);
            String requestor = configTestRunner.elementUtil.getElementDataFromTable(i, "requestor").getText();
            String version = configTestRunner.elementUtil.getElementDataFromTable(i, "version").getText();
            configTestRunner.elementUtil.getElementDataFromTable(i, "version").click();
            configTestRunner.elementUtil.getElementDataFromTable(i, "version").sendKeys(Keys.TAB);
            configTestRunner.elementUtil.getElementDataFromTable(i, "version").sendKeys(Keys.HOME);
            configTestRunner.getChildTest().log(Status.INFO, status + "**" + createdDate + "**" + languageName + "**" + irbDate + "**" + versionA + "**" + firstReviewDate + "**" + versionB + "**" + secondReviewDate + "**" + versionOneDate + "**" + cotDate + "**" + qrgDate + "**" + sumReceiptDate + "**" + priority + "**" + requestor + "**" + version + "**");

        }
    }

    /*
     *Description: Verify columns and filters from AG  grid for Translation hub studies language page
     *Author:Jyoti Dhage
     * Date: 03-12-2021
     */
    public void verifyColumnFilterTranslationPage(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner,"Studies");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify columns and filters from AG  grid for Translation hub studies language page"));
        String studyName = configTestRunner.elementUtil.columnValue("name", 2).getText();
        waitAndClick(getWebElement("VendorStudies", "viewButton", configTestRunner), Constants.AJAX_TIMEOUT);
        Assert.assertEquals(studyName, getWebElement("VendorStudies", "studyNameTranslationPage", configTestRunner).getText());
        configTestRunner.getChildTest().log(Status.INFO, "User click on view button from the study page");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify drag & drop functionality of column tab grid"));
        //Select & unselect columns
        waitAndClick(getSpanWithText(configTestRunner,"Columns"),Constants.AJAX_TIMEOUT);
        configTestRunner.getChildTest().log(Status.INFO,"User click on column tab from the studies translation page table ag grid");
        configTestRunner.elementUtil.fnSelectUnselectColumn(configTestRunner,"Status");
        //apply filter
        configTestRunner.elementUtil.applyFilter(configTestRunner,"Status","draft","approve","Studies","status");
        //Drag & drop in values
        waitAndClick(getSpanWithText(configTestRunner,"Columns"),Constants.AJAX_TIMEOUT);
        configTestRunner.elementUtil.dragAndDrop(configTestRunner.elementUtil.dragFromColumnTab("Status"),getWebElement(formName,"dropAreaAgGrid",configTestRunner));
        configTestRunner.elementUtil.summationBox("Status").isDisplayed();
        configTestRunner.getChildTest().log(Status.INFO,"Status column drag & drop successfully.");
        configTestRunner.elementUtil.dragAndDrop(configTestRunner.elementUtil.dragFromColumnTab("Created Date"),getWebElement(formName,"dropAreaAgGrid",configTestRunner));
        configTestRunner.elementUtil.summationBox("Created Date").isDisplayed();
        configTestRunner.getChildTest().log(Status.INFO,"Created Date column drag & drop successfully.");
        configTestRunner.elementUtil.dragAndDrop(configTestRunner.elementUtil.dragFromColumnTab("Language"),getWebElement(formName,"dropAreaAgGrid",configTestRunner));
        configTestRunner.elementUtil.summationBox("Language").isDisplayed();
        configTestRunner.getChildTest().log(Status.INFO,"Language column drag & drop successfully.");
        configTestRunner.elementUtil.fnhighlightElement(getWebElement(formName,"dropAreaAgGrid",configTestRunner));
        configTestRunner.elementUtil.scrollDownToElement(getWebElement(formName,"dropAreaAgGrid",configTestRunner));
        configTestRunner.getChildTest().log(Status.INFO,"Quote Code column drag & drop successfully.");
        fnTakeScreenAshot(configTestRunner,"Pass","Drag and Drop successfull","DragAndDrop");
    }

    /*
     *Description: Verify columns and filters from AG grid from study landing page
     *Author:Jyoti Dhage
     * Date: 03-12-2021
     */
    public void verifyColumnFilterStudyLandingPage(ConfigTestRunner configTestRunner){
        fnSelectMenu(configTestRunner,"Studies");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify columns and filters from AG  grid for Translation hub studies language page"));
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
        configTestRunner.elementUtil.dragAndDrop(configTestRunner.elementUtil.dragFromColumnTab("Date Created"),getWebElement(formName,"dropAreaAgGrid",configTestRunner));
        configTestRunner.elementUtil.summationBox("Date Created").isDisplayed();
        configTestRunner.getChildTest().log(Status.INFO,"Date Created column drag & drop successfully.");
        configTestRunner.elementUtil.dragAndDrop(configTestRunner.elementUtil.dragFromColumnTab("Sponsor Name"),getWebElement(formName,"dropAreaAgGrid",configTestRunner));
        configTestRunner.getChildTest().log(Status.INFO,"Sponsor Name column drag & drop successfully.");
        configTestRunner.elementUtil.dragAndDrop(configTestRunner.elementUtil.dragFromColumnTab("Date Created"),getWebElement(formName,"dropAreaAgGrid",configTestRunner));
        configTestRunner.elementUtil.fnhighlightElement(getWebElement(formName,"dropAreaAgGrid",configTestRunner));
        configTestRunner.elementUtil.scrollDownToElement(getWebElement(formName,"dropAreaAgGrid",configTestRunner));
        configTestRunner.getChildTest().log(Status.INFO,"Quote Code column drag & drop successfully.");
        fnTakeScreenAshot(configTestRunner,"Pass","Drag and Drop successfull","DragAndDrop");
    }



}
