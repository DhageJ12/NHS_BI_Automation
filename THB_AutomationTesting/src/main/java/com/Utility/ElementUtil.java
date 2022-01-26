package com.Utility;

import com.application.BaseAction;
import com.application.ConfigTestRunner;
import com.aventstack.extentreports.Status;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ElementUtil extends BaseAction {
    private final WebDriver driver;

    public  ElementUtil(WebDriver driver){
        this.driver=driver;
    }

    public void waitAndClick(final WebElement element, int waitfor) {
        WebDriverWait wait = new WebDriverWait(driver, waitfor);
        wait.until(new ExpectedCondition<WebElement>() {
            public final ExpectedCondition<WebElement> visibilityOfElement = ExpectedConditions.visibilityOf(element);
            @Override
            public WebElement apply(WebDriver driver) {
                try {
                    WebElement elementx = this.visibilityOfElement.apply(driver);
                    if (elementx == null) {
                        return null;
                    }
                    if (elementx.isDisplayed() && elementx.isEnabled())  {
                        elementx.click();
                        return elementx;
                    } else {
                        return null;
                    }
                } catch (WebDriverException e) {
                    return null;
                }
            }
        });
    }

    public boolean fnWaitForVisibility(WebElement element , int waitFor){
        WebDriverWait wait = new WebDriverWait(driver,waitFor);
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.isDisplayed();
    }
    public boolean fnWaitForInVisibility(WebElement element , int waitFor){
        WebDriverWait wait = new WebDriverWait(driver,waitFor);
        wait.until(ExpectedConditions.invisibilityOf(element));
        return element.isDisplayed();
    }
    public void setFocusEnter(WebElement element, String value) {
        Actions action = new Actions(driver);
        action.moveToElement(element);
        action.sendKeys(value);
        action.sendKeys(Keys.DOWN);
        action.sendKeys(Keys.ENTER);
        action.build().perform();
    }
    public void moveToElement(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.build().perform();
    }
    public void contextClickAction(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element);
        action.contextClick(element);
        action.build().perform();
    }

    public void sendKeysEnter(WebElement element){
        Actions action = new Actions(driver);
        action.moveToElement(element);
        action.click();
        action.sendKeys(Keys.ENTER);
        action.build().perform();
    }

    public void setFocusdblClick(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element);
        action.doubleClick();
        action.build().perform();
    }
    public void setFocusClick(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element);
        action.click();
        action.build().perform();
    }
   public void dragAndDrop(WebElement fromElement, WebElement toElement){
        Actions builder = new Actions(driver);
        builder.dragAndDrop(fromElement,toElement)
       .build().perform();
   }

    public void dragAndDropToOffset(WebElement element,int x , int y){
        Actions builder = new Actions(driver);
        builder.moveToElement(element)
                .dragAndDropBy(element,x,y)
                .build()
                .perform();
    }
    public void executeExtJsClick(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public WebElement columnValue(String columnName,int i){
        return driver.findElement(By.xpath("(//div[contains(@col-id,'"+columnName+"')])["+i+"]"));
    }

    public WebElement columnValueTable(String columnName,int i){
        return driver.findElement(By.xpath("(//div[@col-id='"+columnName+"'])["+i+"]"));
    }
    public WebElement actionBtn(){
        return driver.findElement(By.xpath("(//div[@col-id='actions']//button)"));
    }
    public void fnSelectDropDownValue(WebElement element, String value){
        Select drop = new Select(element);
        drop.selectByVisibleText(value);
    }
    public WebElement LoadReport(String name){
        return driver.findElement(By.xpath("//p[contains(text(),'"+name+"')]"));
    }
    public String fnDate(String Date){
        String[] str1 = Date.split("-");return str1[0];
    }
    public  String fnMonth(String Date){
        String[] str1 = Date.split("-");return str1[1];
    }
    public static String fnYear(String Date){
        String[] str1 = Date.split("-");return str1[2];
    }
    public String fnMonthNo(String month){
        String monthNo=null;
        if(month.equalsIgnoreCase("Jan"))
            monthNo="01";
        else if(month.equalsIgnoreCase("Feb"))
            monthNo="02";
        else if(month.equalsIgnoreCase("Mar"))
            monthNo="03";
        else if(month.equalsIgnoreCase("Apr"))
            monthNo="04";
        else if(month.equalsIgnoreCase("May"))
            monthNo="05";
        else if(month.equalsIgnoreCase("Jun"))
            monthNo="06";
        else if(month.equalsIgnoreCase("Jul"))
            monthNo="07";
        else if(month.equalsIgnoreCase("Aug"))
            monthNo="08";
        else if(month.equalsIgnoreCase("Sep"))
            monthNo="09";
        else if(month.equalsIgnoreCase("Oct"))
            monthNo="10";
        else if(month.equalsIgnoreCase("Nov"))
            monthNo="11";
        else if(month.equalsIgnoreCase("Dec"))
            monthNo="12";
        else
            System.out.println("No Moth is there to select");
        return monthNo;

    }
    public String fnMonthValue(String val){
        String month=null;
        if(val.length()<2){
            month="0"+val;
        }else
            month=val;
        return month;

    }


    public Date fnConvertToDate(String value){
        Date date = null;
        try {
            SimpleDateFormat formDate = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
             date = formDate.parse(value);
        }catch (Exception e){

        }
        return date;
    }
    public String fnParseNumber(String text){
        return text.replaceAll("[^0-9]","");
    }

    public void fnhighlightElement(WebElement element){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", element);
//        jsExecutor.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
//        jsExecutor.executeScript("arguments[0].setAttribute('style','border: solid 2px while');", element);
    }

    public void scrollDownToElement(WebElement element){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }
    public void jsClick(WebDriver driver, String attribute, String value){
        JavascriptExecutor jsExecutor=(JavascriptExecutor) driver;
        String jsQuery = "document.querySelector(\"["+attribute+"='"+value+"']\").click()";
        jsExecutor.executeScript(jsQuery);
    }

    /*
     *@Description:This is used to check file is uploaded
     *@Author:Jyoti Dhage
     *@Attribute: Webelement
     */
    public boolean isFileDownloaded(String fileName) {
        boolean flag = false;
        File dir = new File(Constants.FileDownLoadPath);
        File[] dir_contents = dir.listFiles();
        int index=0;

        for (int i = 0; i < dir_contents.length; i++) {
            if (fileName.contains(dir_contents[i].getName())) {
                flag = true;
                break;
            }
        }
        return flag;
    }
    public boolean deleteDownLoadedFile(String fileName){
        boolean flag = false;
        File dir = new File(Constants.FileDownLoadPath);
        File[] dir_contents = dir.listFiles();
        for (int i = 0; i < dir_contents.length; i++) {
            if(fileName.contains(dir_contents[i].getName())) {
                dir_contents[i].delete();
                flag=true;
                break;
            }
        }
        return flag;
    }

    public String getCurrentSystemDate(ConfigTestRunner configTestRunner , int noOfDays){
        DateFormat dateFormat = new SimpleDateFormat("dd/M/yyyy");
        Calendar c = Calendar.getInstance();
//        dateFormat.format(new Date());
        c.setTime(new Date());
        c.add(Calendar.DATE,noOfDays);
        return dateFormat.format(c.getTime());
    }

    public String getDateAlongWithTime(ConfigTestRunner configTestRunner){
        DateFormat dateFormat = new SimpleDateFormat("dd_M_yyyy_HH_mm");
        return dateFormat.format(new Date());
    }
    public void fileUpload(ConfigTestRunner configTestRunner,WebElement element){
        sleep(2000);
//        configTestRunner.elementUtil.executeExtJsClick(driver,element);
        try {
            element.click();
        }catch (Exception e){
            e.printStackTrace();
        }
        String filePath = System.getProperty("user.dir")+Constants.FileUploadPath;
        StringSelection fileSelection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(fileSelection,null);
        try{
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);

            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void ExportTableVerify(ConfigTestRunner configTestRunner,String reportName, String format,String formName){
        getButtonWithText("Export Table").isDisplayed();
        waitAndClick(getButtonWithText("Export Table"),Constants.AJAX_TIMEOUT);
        waitAndSendText(getWebElement(formName,"fileName",configTestRunner),Constants.AJAX_TIMEOUT,reportName);
        waitAndClick(getWebElement(formName,"fileFormatDropdown",configTestRunner),Constants.AJAX_TIMEOUT);
        waitAndClick(getLiWithText(format),Constants.AJAX_TIMEOUT);
        if(!(format.contains("PDF")))
            waitAndClick(getButtonWithText("Export"),Constants.AJAX_TIMEOUT);
        else
            waitAndClick(getSpanWithText(configTestRunner,"Export"),Constants.AJAX_TIMEOUT);
        if(format.contains("Excel"))
            reportName = reportName+".xlsx";
        else if(format.contains("CSV"))
            reportName = reportName+".csv";
        else if(format.contains("PDF"))
            reportName = reportName+".pdf";
        sleep(2000);
        if(isFileDownloaded(Constants.FileDownLoadPath,reportName))
            configTestRunner.getChildTest().log(Status.PASS,reportName+ " is downloaded successfully in "+format+ " format");
        else {
            configTestRunner.getChildTest().log(Status.FAIL, reportName + " is not downloaded successfully in " + format + " format");
        }
        waitAndClick(getButtonWithText("Cancel"),Constants.AJAX_TIMEOUT);
    }

    public WebElement columnTabField(String fieldName){
        return driver.findElement(By.xpath("//span[text()='"+fieldName+"' and @ref='eLabel']"));
    }
    public WebElement vendorStudiesColumnField(String fieldName){
        return driver.findElement(By.xpath("//span[text()='"+fieldName+"' and @ref='eText']"));
    }

    public List<WebElement> getTableData(String columnName){
        return driver.findElements(By.xpath("//div[@col-id='"+columnName+"']"));
    }

    public WebElement getElementDataFromTable(int rowid, String columnName){
        return driver.findElement(By.xpath("//div[@row-index='"+rowid+"']//div[@col-id='"+columnName+"']"));
    }

    public WebElement getHeaderWithDifferentNumber(int headerNumber, String value){
        return driver.findElement(By.xpath("//h"+headerNumber+"[text()='"+value+"']"));
    }
    public WebElement getThreeDotForRowInTranslationPage(int rowid, String columnName){
        return driver.findElement(By.xpath("//div[@row-index='"+rowid+"']//span[text()='...']"));
    }
    public WebElement getSignantStudyTable(int rowNO, String columnName){
        return driver.findElement(By.xpath("(//div[@col-id='"+columnName+"'])["+rowNO+"]//input"));
    }
    public WebElement getSignantStudyDatePickerField(int rowNO, String columnName){
        return driver.findElement(By.xpath("(//div[@col-id='"+columnName+"'])["+rowNO+"]//p[1]"));
    }
    public void fnSelectUnselectColumn(ConfigTestRunner configTestRunner,String columnName){
        waitAndClick(configTestRunner.elementUtil.columnTabField(columnName),Constants.AJAX_TIMEOUT);
        try{
            configTestRunner.elementUtil.vendorStudiesColumnField(columnName).isDisplayed();
            configTestRunner.getChildTest().log(Status.FAIL,columnName+" field is selected in column filter hence is it not display in AG grid.");
        }catch (Exception e){
            configTestRunner.getChildTest().log(Status.PASS,columnName+" field is not selected in column filter hence is it not display in AG grid.");
        }
        waitAndClick(configTestRunner.elementUtil.columnTabField(columnName),Constants.AJAX_TIMEOUT);
        try{
            configTestRunner.elementUtil.vendorStudiesColumnField(columnName).isDisplayed();
            configTestRunner.getChildTest().log(Status.PASS,columnName+" field is selected in column filter hence is it display in AG grid.");
        }catch (Exception e){
            configTestRunner.getChildTest().log(Status.FAIL,columnName+" field is not selected in column filter hence is it not display in AG grid.");
        }
    }
    public WebElement filterColumn(String columnName){
        return driver.findElement(By.xpath("//span[contains(@class,'filter') and text()='"+columnName+"']"));
    }
    public void applyFilter(ConfigTestRunner configTestRunner,String columnName,String filterFrom,String filterTo,String formName,String rowValue){

        //select filter
        waitAndClick(getSpanWithText(configTestRunner,"Filters"),Constants.AJAX_TIMEOUT);
        List<WebElement> elements = configTestRunner.elementUtil.getTableData(rowValue);
        configTestRunner.getChildTest().log(Status.PASS,elements.size()+" these many records are present before applying filter");
//       waitAndClick(getSpanWithText(configTestRunner,"Status"),Constants.AJAX_TIMEOUT);
        waitAndClick(configTestRunner.elementUtil.filterColumn(columnName),Constants.AJAX_TIMEOUT);
        waitAndSendText(getWebElement(formName,"firstFilterInputBox",configTestRunner),Constants.AJAX_TIMEOUT,filterFrom);
        configTestRunner.getChildTest().log(Status.PASS,"User select value in input box as: "+filterFrom);
        waitAndClick(getDivWithText("OR"),Constants.AJAX_TIMEOUT);
        waitAndSendText(getWebElement(formName,"secondFilterInputBox",configTestRunner),Constants.AJAX_TIMEOUT,filterTo);
        configTestRunner.getChildTest().log(Status.PASS,"User select value in input box as: "+filterTo);
        sleep(1000);
        elements  = configTestRunner.elementUtil.getTableData(rowValue);
        fnTakeScreenAshot(configTestRunner,"Pass",elements.size()+" these many records are present after applying filter","Filterapply");
    }

    public WebElement dragFromColumnTab(String columnName){
        return driver.findElement(By.xpath("//span[text()='"+columnName+"' and @ref='eLabel']/preceding-sibling::span[contains(@class,'drag-handle')]"));
    }
    public WebElement summationBox(String value){
        return driver.findElement(By.xpath("//span[text()='sum("+value+")']"));
    }
    public WebElement replyButtonCommentBox(String commentText){
        return driver.findElement(By.xpath("//span[text()='"+commentText+"']/following::span[text()='Reply'][1]"));
    }
    public WebElement commentNameDateTime(String commentText,int no){
        return driver.findElement(By.xpath("(//span[text()='"+commentText+"'])/preceding::span["+no+"]"));
    }
    public WebElement replyToComment(String name){
        return driver.findElement(By.xpath("//input[contains(@placeholder,'Replying to: "+name+"')]"));
    }
    public WebElement projectMenu_TranslationPage(String menuName){
        return driver.findElement(By.xpath("//div[@class='projects-menu__item-container']//p[text()='Vaccination Diary']"));
    }
}

