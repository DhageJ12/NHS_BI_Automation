package com.application;

import com.Utility.Constants;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.security.auth.login.Configuration;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public  class BaseAction {

    protected Configuration conf;
    private ExtentReports extent;
    private  XSSFWorkbook wb;
    private  XSSFSheet sh;
    private int Rowcnt;
    private WebDriver driver= DriverFactory.getDriver();
//    File Src;
    public Map<String, String> testCase = new ConcurrentHashMap<String, String>();
    private Map<String, String> testData = new ConcurrentHashMap<String, String>();
    private boolean Exist = false;
    public com.aventstack.extentreports.ExtentTest parentTest;
    public com.aventstack.extentreports.ExtentTest childTest;
    public   String sheetName;


    public void readExcelDataFile(String FilePath) {
        InputStream is;
        FileInputStream Src=null;
        try{
            File file = new File(Constants.TestDataPath+ FilePath);
            FileInputStream Fis = new FileInputStream(file);
            wb = new XSSFWorkbook(Fis);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Map<String,String> ReadTestData(String TCID){
        readExcelDataFile(Constants.dataSheetName);
        String cardName = TCID.split("_")[1];
        if(cardName.equals("login")){
            sheetName = "Login";
        }else if(cardName.equals("UM")){
            sheetName ="UserManagement";
        }else if(cardName.equals("V")){
            sheetName ="Vendors";
        }else if(cardName.equals("VS")){
            sheetName="VendorStudies";
        }else if(cardName.equals("S")){
            sheetName="SignantStudies";
        }else if(cardName.equals("TM")){
            sheetName="TranslatorManagement";
        }
        int rowno =  RowNo(sheetName,TCID,"SC_ID");
        return TestCaseDic(rowno,sheetName);
    }
    public int RowCount(String SheetName){
        sh = wb.getSheet(SheetName);
        return sh.getLastRowNum();
    }

    public int ColCount(String SheetName){
        sh = wb.getSheet(SheetName);
        return sh.getRow(0).getLastCellNum();

    }
    public Map<String,String>  TestCaseDic(int m, String sheetName) {
        int CCnt = ColCount(sheetName);
        for (int q = 0; q < CCnt; q++) {
            String Key = getExcelDataint(sheetName,0, q).trim();

            if (Key.equals("")) {
                testCase.put(Key, " ");
            } else {
                try {
                    String Value = getExcelDataint(sheetName,m, q).trim();
                    testCase.put(Key, Value);
                }catch (Exception e){
//                    e.printStackTrace();
                }
            }
        }
        return testCase;
    }
    public void TestDataDic(int RowNo,String sheetName) {
        int Ccnt = ColCount(sheetName);
        try {
            for (int j = 0; j < Ccnt; j++) {
                String key = getExcelDataint(sheetName, 0, j).trim();
                if (key.equals("")) {
                    testData.put(key, null);
                } else {
                    try {
                        String value = getExcelDataint(sheetName, RowNo, j).trim();
                        testData.put(key, value);
                    } catch (Exception ex) {
//                        ex.printStackTrace();
                    }
                }
            }
        }catch (Exception e){
//            e.printStackTrace();
        }
    }
    String getExcelDataint(String sheetName,int RowNo, int ColNo){
        String CellValue=null;
        try {
            // int ColNo = ColumnValue(colNo);
            sh = wb.getSheet(sheetName);
            // Cell cell=sh.getRow(RowNo).getCell(ColNo);
            CellValue = sh.getRow(RowNo).getCell(ColNo).getStringCellValue();
        }catch (Exception ex){
//             ex.printStackTrace();
        }
        return CellValue;
    }
    public int RowNo(String Sheet,String RowValue,String ColValue1){
        int RCount = RowCount(Sheet);
        int Cocnt = ColumnValue(ColValue1,Sheet);
        for (int p = 1; p<=RCount; p++){
            String Value = sh.getRow(p).getCell(Cocnt).getStringCellValue().trim();
            if (RowValue.trim().equals(Value.trim())) {
                Exist = true;
                return p;
            }
        }
        if (!Exist){
            System.out.println(RowValue+" This row value is not exist.");
        }
        return -1;
    }
    public int rowValue(String RowData, int colNo){
        Rowcnt = sh.getLastRowNum();
        for (int j = 0 ; j<=Rowcnt ; j++ ) {
            try {
                String Value = sh.getRow(j).getCell(colNo).getStringCellValue().trim();
                if (RowData.trim().equals(Value.trim())) {
                    Exist = true;
                    return j;
                }
            }catch (Exception e1){
            }
        }
        if (!Exist){
            System.out.println(RowData+" This row is not exist.");
        }
        return -1;
    }
    public int ColumnValue(String ColumnName, String shName){

        sh = wb.getSheet(shName);
        int cnt = sh.getRow(0).getLastCellNum();
        for (int j = 0 ; j<=cnt ; j++ ) {

            String Value = sh.getRow(0).getCell(j).getStringCellValue().trim();
            if (ColumnName.trim().equals(Value.trim())) {
                Exist = true;
                return j;
            }
        }
        if (!Exist){
            System.out.println(ColumnName+" This column is not exist.");
        }

        return -1;
    }
    public WebElement getDivWithText(String text){
        return driver.findElement(By.xpath("//div[text()='"+text+"']"));
    }
    public WebElement getDivContainsWithText(String text){
        return driver.findElement(By.xpath("//div[contains(text(),'"+text+"')]"));
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
    public void Sleep(int wait){
        try{
            Thread.sleep(wait);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
     * @param configTestRunner
     * @param menu
     * @param menuName
     * @param formName
     * @param objectName
     */
    public void fnSelectMenu(ConfigTestRunner configTestRunner, String menuName) {
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode(menuName+"  :Page Navigation Verification"));
        sleep(1500);
        try {
            getWebElement("HomePage", "hamburgMenu", configTestRunner).isDisplayed();
                sleep(500);
                configTestRunner.elementUtil.waitAndClick(getWebElement("HomePage", "hamburgMenu", configTestRunner), Constants.AJAX_TIMEOUT);
                try {
                    configTestRunner.elementUtil.waitAndClick(getSpanWithText(configTestRunner,menuName), Constants.AJAX_TIMEOUT);
                    configTestRunner.getChildTest().log(Status.PASS, "User Click on the " + menuName + " option from the hamburg menu.");
                } catch (Exception e) {
                    configTestRunner.getChildTest().log(Status.FAIL, "Unable to click on hamburg menu from the home page.");
                }
        }catch (Exception e){
            fnTakeScreenAshot(configTestRunner,"Fail","HamburgMenu is not present on the application.","HamburgMenuNotAvailable");
            e.printStackTrace();
        }
    }

    public void fnTakeScreenAshot(ConfigTestRunner configTestRunner, String status , String Message, String screenShotName){

        if(status.equalsIgnoreCase("Pass")){
            try {
                configTestRunner.getChildTest().log(Status.PASS, Message +" "+ configTestRunner.getChildTest().addScreenCaptureFromPath(configTestRunner.screenShotName(screenShotName )));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(status.equalsIgnoreCase("Fail")){
            try {
                configTestRunner.getChildTest().log(Status.FAIL, Message+" " + configTestRunner.getChildTest().addScreenCaptureFromPath(configTestRunner.screenShotName(screenShotName )));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(status.equalsIgnoreCase("Warning")){
            try {
                configTestRunner.getChildTest().log(Status.WARNING, Message+" " + configTestRunner.getChildTest().addScreenCaptureFromPath(configTestRunner.screenShotName(screenShotName )));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(status.equalsIgnoreCase("Info")){
            try {
                configTestRunner.getChildTest().log(Status.INFO, Message+" " + configTestRunner.getChildTest().addScreenCaptureFromPath(configTestRunner.screenShotName(screenShotName )));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public WebElement getSpanWithText1(String text) {
         return driver.findElement(By.xpath("(//span[text() = '" + text + "'])[1]"));
    }
    public WebElement getPWithText(String text) {
        return driver.findElement(By.xpath("(//p[text() = '" + text + "'])"));
    }

    public WebElement getCovidPatientGroup(String patientGroup){
        return driver.findElement(By.xpath("//div[@id='graph-card']//*[text()='"+patientGroup+"']"));
    }
    public WebElement getSpanWithText(ConfigTestRunner configTestRunner,String text) {
        List<WebElement> webElement=null;
        try{
            webElement = configTestRunner.driver.findElements(By.xpath("//span[text() = '" + text + "']"));
        }catch (Exception e){
            configTestRunner.getChildTest().log(Status.FAIL,  text+" :field is not present in the application.");
        }
        //  assert webElement != null;
        if (webElement.size() >= 1) {

            return webElement.get((webElement.size() - 1));
        } else {
            return webElement.get(0);
        }
    }

    public WebElement getThOfTableHeader(ConfigTestRunner configTestRunner, String text, WebDriver driver){
        return driver.findElement(By.xpath("//th[//span[text()='"+ text +"']]"));

    }
    public WebElement getHeaderWithText(ConfigTestRunner configTestRunner,String text, WebDriver driver) {
        List<WebElement> webElement=null;
        try{
            webElement = driver.findElements(By.xpath("//h6[text() = '" + text + "']"));
        }catch (Exception e){
            configTestRunner.getChildTest().log(Status.FAIL,  text+" :field is not present in the application.");
        }
        //  assert webElement != null;
        assert webElement != null;
        if (webElement.size() >= 1) {

            return webElement.get((webElement.size() - 1));
        }else
            return webElement.get(0);

    }
    public WebElement getButtonWithText(String text) {
        return driver.findElement(By.xpath("//button[text()='"+text+"']"));
    }
    public WebElement getButtonContainsText(String text) {
        return driver.findElement(By.xpath("//button[contains(text(),'"+text+"')]"));
    }
    public WebElement getTextWithoutTag(String text) {
        return driver.findElement(By.xpath("//*[text()='"+text+"']"));
    }
    public WebElement getLabelWithText(String text) {
        return driver.findElement(By.xpath("//label[text()='"+text+"']"));
    }
    public WebElement getLiWithText(String text) {
        return driver.findElement(By.xpath("//li[text()='"+text+"']"));
    }
    public WebElement getLiContainsWithText(String text) {
        return driver.findElement(By.xpath("//li[contains(text(),'"+text+"')]"));
    }
    public WebElement getLinkText(String text) {
        return driver.findElement(By.xpath("//a[text()='"+text+"']"));
    }

    public WebElement getLinkTextContains(String text) {
        return driver.findElement(By.xpath("//a[contains(text(),'"+text+"')]"));
    }

    public void setTestData(Map<String, String> testData) {
        this.testData = testData;
    }
    public WebElement getWebElement(String formName,String name,ConfigTestRunner configTestRunner) {
        return configTestRunner.getConfig().getWebElement(configTestRunner.driver, formName, name);
    }

    public List<WebElement>  getWebElements(ConfigTestRunner configTestRunner,String formName,String name) {
        return configTestRunner.getConfig().getWebElementes(configTestRunner.driver,  formName, name);
    }
    public void sleep(int timer){
        try{
            Thread.sleep(timer);
        }catch (Exception e){

        }
    }

    public void waitAndClick(final WebElement element, int waitfor) {
        WebDriverWait wait = new WebDriverWait(driver, waitfor);
        wait.until(new ExpectedCondition<WebElement>() {
            public ExpectedCondition<WebElement> visibilityOfElement = ExpectedConditions.visibilityOf(element);
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
    public void waitAndSendText(final WebElement element, int waitfor,String value) {
        WebDriverWait wait = new WebDriverWait(driver, waitfor);
        wait.until(new ExpectedCondition<WebElement>() {
            public ExpectedCondition<WebElement> visibilityOfElement = ExpectedConditions.visibilityOf(element);
            @Override
            public WebElement apply(WebDriver driver) {
                try {
                    WebElement elementx = this.visibilityOfElement.apply(driver);
                    if (elementx == null) {
                        return null;
                    }
                    if (elementx.isDisplayed() && elementx.isEnabled())  {
                        elementx.sendKeys(value);
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
    /*
     * This method is to set data into the testdata sheet
     */
    public void setCellData(String Data, int row , int col){

        DataFormatter formatter = new DataFormatter();
        String data1 = formatter.formatCellValue(sh.getRow(row).getCell(col));
        if (!data1.isEmpty())
            sh.getRow(row).getCell(col).setCellValue("");
        if (!(Data.isEmpty())) {
            try {
                sh.getRow(row).getCell(col).setCellValue(Data);
                try {
                    File file = new File(Constants.TestDataPath + "/Test_scenarios.xlsx");
//                FileInputStream Fis = new FileInputStream(file);
                    FileOutputStream fileOut = new FileOutputStream(file);
                    wb.write(fileOut);
                    fileOut.flush();
                    fileOut.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void jsClick(WebDriver driver, String attribute, String value){
        JavascriptExecutor jsExecutor=(JavascriptExecutor) driver;
        String jsQuery = "document.querySelector(\"["+attribute+"='"+value+"']\").click()";
        jsExecutor.executeScript(jsQuery);
    }

    public void jScriptSetText(String value, String id_value){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("document.getElementById('"+id_value+"').value='"+value+"'");

    }

    /***
     * This method is for file download
     *
     */
    public boolean isFileDownloaded(String downloadPath, String fileName) {
        boolean flag = false;
        File dir = new File(downloadPath);
        File[] dir_contents = dir.listFiles();

        for (int i = 0; i < dir_contents.length; i++) {
            if(dir_contents[i].getName().contains(fileName)) {
                return flag = true;
            }
//            dir_contents[i].delete();
        }

        return flag;
    }

    public void uploadFile(WebElement element){
        String js = "arguments[0].style.height='auto';arguments[0].style.visibility='visible';";
        ((JavascriptExecutor)driver).executeScript(js,element);
        ClassLoader classLoader = getClass().getClassLoader();
        sleep(3000);
        File file = new File(System.getProperty("user.dir")+Constants.FileUploadPath);
        element.sendKeys(file.getAbsolutePath());
        sleep(1000);
    }
    public void sentMail(String fileName) {

        // Recipient's email ID needs to be mentioned.
        String to = "amol.nikam@blinxsolutions.com";
//        String to = "jadhav.jyoti79@gmail.com";
        // Sender's email ID needs to be mentioned
        String from = "jadhav.jyoti79@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";
        // Get system properties
        Properties properties = System.getProperties();
        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        // Get the Session object.// and pass
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("jadhav.jyoti79@gmail.com", "Devyanee@1234");
            }
        });
        //session.setDebug(true);
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("BlinlxSolutions Automation Execution Result");
            Multipart multipart = new MimeMultipart();

            MimeBodyPart attachmentPart = new MimeBodyPart();

            MimeBodyPart textPart = new MimeBodyPart();

            try {
//                String fileName1= zipFile1(fileName);
                File f =new File(fileName);

                attachmentPart.attachFile(f);
                textPart.setText("This is text");
                multipart.addBodyPart(textPart);
                multipart.addBodyPart(attachmentPart);

            } catch (IOException e) {

                e.printStackTrace();

            }

            message.setContent(multipart);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
    public void zipFile1(String filePath) {
        try {
            File file = new File(filePath);
            String zipFileName = file.getName().concat(".zip");

            FileOutputStream fos = new FileOutputStream(zipFileName);
            ZipOutputStream zos = new ZipOutputStream(fos);

            zos.putNextEntry(new ZipEntry(file.getName()));

            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            zos.write(bytes, 0, bytes.length);
            zos.closeEntry();
            zos.close();

        } catch (FileNotFoundException ex) {
            System.err.format("The file %s does not exist", filePath);
        } catch (IOException ex) {
            System.err.println("I/O error: " + ex);
        }
    }
    public Map<String, String> getTestCase() {
        return testCase;
    }

    public void setTestCase(Map<String, String> testCase) {
        this.testCase = testCase;
    }

    public Map<String, String> getTestData() {
        return testData;
    }
    public ExtentReports getExtent() {
        return extent;
    }

    public void setExtent(ExtentReports extent) {
        this.extent = extent;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public Configuration getConf() {
        return conf;
    }
//    public abstract String getFormName()

}
