package com.application;

import com.Utility.Constants;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Set;

public class LoginAction extends BaseAction {
    private final String formName="Login";
/***
 * This methos is for selecting test cases for execution
 * @Param testcase number , configtestrunner object
 */
    public void fnExecuteTC(ConfigTestRunner configTestRunner,String tcNumber){
        switch (tcNumber) {
            case "SC001_login":
                fnValidLogin(configTestRunner);
                break;
            case "SC002_login":
                fnInValidLogin(configTestRunner);
                break;
            case "SC003_login":
                fnBlankLoginPassword(configTestRunner);
                break;
            case "SC004_login":
                fninvalidMessage(configTestRunner);
                fnforgotpassword(configTestRunner);
                break;
            case "SC006_login":
                fnDifferentBrowser(configTestRunner);
                break;
            case "SC007_login":
                fnValidLoginEnterKey(configTestRunner);
                break;
            case "SC008_login":
                fnBackButtonFunctionality(configTestRunner);
                break;
            case "SC009_login":
                fnforgotpassword(configTestRunner);
                break;
            case "SC010_login":
                fnPrivacyPolicy(configTestRunner);
                break;
            case "SC014_login":
                fnCheckLogo(configTestRunner);
                break;
            default:
                System.out.println("No Test case is consider for execution");
        }
    }

    /*
     * This method is used for login with valid details
     * @param configTestRunner
     * @return login is succesful or not
     * @Developed By: Jyoti Dhage
     */
    public boolean fnValidLogin(ConfigTestRunner configTestRunner) {
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Login To Translations Hub Application Verification For Valid Credentials"));

        boolean isPresence = false;
        String username="";
        String password="";
        if(Constants.Env.equalsIgnoreCase("Dev")){
            configTestRunner.driver.get(Constants.DEV_URL);
             username=Constants.userName_dev;
             password=Constants.password_dev;
            configTestRunner.getChildTest().log(Status.INFO, "URL use to login into the application is "+ Constants.DEV_URL);
        }else if(Constants.Env.equalsIgnoreCase("Uat")){
            configTestRunner.driver.get(Constants.UAT_URL);
            username=Constants.userName_dev;
            password=Constants.password_dev;
            configTestRunner.getChildTest().log(Status.INFO, "URL use to login into the application is "+ Constants.UAT_URL);
        }else if(Constants.Env.equalsIgnoreCase("pp")){
            configTestRunner.driver.get(Constants.PP_URL);
            username=Constants.userName_dev;
            password=Constants.password_dev;
            configTestRunner.getChildTest().log(Status.INFO, "URL use to login into the application is "+ Constants.PP_URL);
        }else
            System.out.println("No environment is selected to load.");
       //Provide login details
        fnValidLogin(configTestRunner,username,password,"Login to the application is successful","SuccessFulLoginVerification");
        return isPresence;
    }
    /***
     * @param configTestRunner
     * @return login is successful or not with invalid password
     * @Developed By: Jyoti Dhage
     */
    public void fnInValidLogin(ConfigTestRunner configTestRunner) {
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Login To Translations Hub Application Verification For InValid Credentials"));

        boolean isPresence = false;
        String username="";
        if(Constants.Env.equalsIgnoreCase("Dev")){
            configTestRunner.driver.get(Constants.DEV_URL);
            username=Constants.userName_dev;
            configTestRunner.getChildTest().log(Status.INFO, "URL use to login into the application is: "+ Constants.DEV_URL);
        }else if(Constants.Env.equalsIgnoreCase("Uat")){
            configTestRunner.driver.get(Constants.UAT_URL);
            username=Constants.userName_dev;
            configTestRunner.getChildTest().log(Status.INFO, "URL use to login into the application is : "+ Constants.UAT_URL);
        }else if(Constants.Env.equalsIgnoreCase("pp")){
            configTestRunner.driver.get(Constants.PP_URL);
            username=Constants.userName_dev;
            configTestRunner.getChildTest().log(Status.INFO, "URL use to login into the application is : "+ Constants.PP_URL);
        }else
            System.out.println("No environment is selected to load.");
        //Provide login details
        fnInValidLogin(configTestRunner,username,"invalidPwd","Login to the application is not successful for invalid username","SuccessFulLoginVerification");
    }
    /***
     * @param configTestRunner
     * @return login is succesful or not
     * @Developed By: Jyoti Dhage
     */
    public boolean fnInValidLoginPassword(ConfigTestRunner configTestRunner) {
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Login To Translations Hub Application Verification For InValid Password Credentials"));

        boolean isPresence = false;
        String username="";
        String password="";
        if(Constants.Env.equalsIgnoreCase("Dev")){
            configTestRunner.driver.get(Constants.DEV_URL);
            configTestRunner.getChildTest().log(Status.INFO, "URL use to login into the application is: "+ Constants.DEV_URL);
        }else if(Constants.Env.equalsIgnoreCase("Uat")){
            configTestRunner.driver.get(Constants.UAT_URL);
            configTestRunner.getChildTest().log(Status.INFO, "URL use to login into the application is : "+ Constants.UAT_URL);
        }else if(Constants.Env.equalsIgnoreCase("pp")){
            configTestRunner.driver.get(Constants.PP_URL);
            configTestRunner.getChildTest().log(Status.INFO, "URL use to login into the application is : "+ Constants.PP_URL);
        }else
            System.out.println("No environment is selected to load.");
        //Provide login details
        fnInValidLogin(configTestRunner,username,"invalidpwd ","Login to the application is not successful for valid username & invalid password","withInvalidPwd");
        return isPresence;
    }
    /***
     * @return login is succesful or not for blank username password
     * @Developed By: Jyoti Dhage
     */
    public void fnBlankLoginPassword(ConfigTestRunner configTestRunner) {
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Login To Translations Hub Application Verification For blank username & Password"));

        boolean isPresence = false;
        String username="";
        String password="";
        if(Constants.Env.equalsIgnoreCase("Dev")){
            configTestRunner.driver.get(Constants.DEV_URL);
            configTestRunner.getChildTest().log(Status.INFO, "URL use to login into the application is: "+ Constants.DEV_URL);
        }else if(Constants.Env.equalsIgnoreCase("Uat")){
            configTestRunner.driver.get(Constants.UAT_URL);
            configTestRunner.getChildTest().log(Status.INFO, "URL use to login into the application is : "+ Constants.UAT_URL);
        }else if(Constants.Env.equalsIgnoreCase("pp")){
            configTestRunner.driver.get(Constants.PP_URL);
            configTestRunner.getChildTest().log(Status.INFO, "URL use to login into the application is : "+ Constants.PP_URL);
        }else
            System.out.println("No environment is selected to load.");
        //Provide login details
        fnInValidLogin(configTestRunner," "," ","Login to the application is not successful for blank username & password","withBlankusernamepwd");
    }


    /***
     * @return verify the invalid credential message
     * @Developed By: Jyoti Dhage
     */
    public void fninvalidMessage(ConfigTestRunner configTestRunner) {
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify invalid credentials message"));
        if(Constants.Env.equalsIgnoreCase("Dev")){
            configTestRunner.driver.get(Constants.DEV_URL);
            configTestRunner.getChildTest().log(Status.INFO, "URL use to login into the application is: "+ Constants.DEV_URL);
        }else if(Constants.Env.equalsIgnoreCase("Uat")){
            configTestRunner.driver.get(Constants.UAT_URL);
            configTestRunner.getChildTest().log(Status.INFO, "URL use to login into the application is : "+ Constants.UAT_URL);
        }else if(Constants.Env.equalsIgnoreCase("pp")){
            configTestRunner.driver.get(Constants.PP_URL);
            configTestRunner.getChildTest().log(Status.INFO, "URL use to login into the application is : "+ Constants.PP_URL);
        }else
            System.out.println("No environment is selected to load.");
        //Provide login details
        fnInValidLogin(configTestRunner," "," ","Verify the invalid credential message as 'Invalid credentials","invalidMessage");
    }



    /**
     * This methos is for valid details verification
     * @param configTestRunner
     */
    public void fnValidLogin(ConfigTestRunner configTestRunner,String username,String password,String message,String screenShotName){
        //try {
            configTestRunner.getChildTest().log(Status.PASS, "Translations Hub Application is launch successfully.");
            if (getWebElement(formName,"userid", configTestRunner).isDisplayed()) {
                fnEnterCredentials(configTestRunner,username,password);
                getWebElement(formName,"loginBtn",  configTestRunner).click();
                sleep(1000);
                try {
                    Assert.assertTrue(getSpanWithText(configTestRunner,"Vendor Studies").isDisplayed());
                    fnTakeScreenAshot(configTestRunner,"Pass",message,screenShotName);
                } catch (Exception e) {
                    fnTakeScreenAshot(configTestRunner,"Fail","Login to the application is unsuccessful",screenShotName);
                    e.printStackTrace();
                }
            }
//        }catch (Exception e){
//            fnTakeScreenAshot(configTestRunner,"Fail","Translations Hub Application is not launch successfully.","URL_NotLaunch");
//        }
    }

    /**
     * This methos is for valid details verification with Enter key
     * @param configTestRunner
     */
    public void fnValidLoginEnterKey(ConfigTestRunner configTestRunner,String username,String password,String message,String screenShotName){
        //try {
        configTestRunner.getChildTest().log(Status.PASS, "Translations Hub Application is launch successfully.");
        if (getWebElement(formName,"userid", configTestRunner).isDisplayed()) {
            fnEnterCredentials(configTestRunner,username,password);
            getWebElement(formName,"loginBtn",  configTestRunner).sendKeys(Keys.ENTER);
            configTestRunner.getChildTest().log(Status.PASS,"User press enter key for sign In");
            sleep(1000);
            try {
                Assert.assertTrue(getSpanWithText(configTestRunner,"Vendor Studies").isDisplayed());
                fnTakeScreenAshot(configTestRunner,"Pass",message,screenShotName);
            } catch (Exception e) {
                fnTakeScreenAshot(configTestRunner,"Fail","Login to the application is unsuccessful",screenShotName);
                e.printStackTrace();
            }
        }
//        }catch (Exception e){
//            fnTakeScreenAshot(configTestRunner,"Fail","Translations Hub Application is not launch successfully.","URL_NotLaunch");
//        }
    }

    /**
     * This methos is for invalid details verification
     * @param configTestRunner
     */
    public void fnInValidLogin(ConfigTestRunner configTestRunner,String username,String password,String message,String screenShotName){
            configTestRunner.getChildTest().log(Status.PASS, "Translations Hub Application is launch successfully.");
            if (getWebElement(formName,"userid", configTestRunner).isDisplayed()) {
                fnEnterCredentials(configTestRunner,username,password);
                getWebElement(formName,"loginBtn",  configTestRunner).click();
                getWebElement(formName,"invaliduserName",configTestRunner).isDisplayed();
                configTestRunner.getChildTest().log(Status.INFO, "After entering invalid credentials getting message as: " + getWebElement(formName,"invaliduserName",configTestRunner).getText());
                fnTakeScreenAshot(configTestRunner,"Pass",message,screenShotName);
            }
    }

    public void fnEnterCredentials(ConfigTestRunner configTestRunner,String username, String password){
            getWebElement(formName,"userid",configTestRunner).clear();
            getWebElement(formName,"userid",configTestRunner).sendKeys(username);
            configTestRunner.getChildTest().log(Status.INFO, "User name enter is: " + username);
            getWebElement(formName,"password",  configTestRunner).clear();
            getWebElement(formName,"password",  configTestRunner).sendKeys(password);
            configTestRunner.getChildTest().log(Status.INFO, "Password enter is: " + password);
    }

    /*
    This method is used for logout from the NHS BI application
     */
    public synchronized void LogOutFromApplication(ConfigTestRunner configTestRunner) {
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Logout From Translations Hub Application Verification"));
        try{
            getWebElement("HomePage","hamburgMenu",configTestRunner).isDisplayed();
                configTestRunner.elementUtil.waitAndClick(getWebElement("HomePage","hamburgMenu",configTestRunner),Constants.AJAX_TIMEOUT);
                configTestRunner.getChildTest().log(Status.PASS,"User Click on the hamburg menu.");
                try {
                        getSpanWithText(configTestRunner,"Logout").isDisplayed();
                        waitAndClick(getSpanWithText(configTestRunner,"Logout"),Constants.AJAX_TIMEOUT);
                        configTestRunner.getChildTest().log(Status.PASS,"User Click on logout button.");
                        try {
                           getWebElement(formName,"userid", configTestRunner).isDisplayed();
                           configTestRunner.getChildTest().log(Status.PASS, "User logout successfully from the Translations Hub application.");
                        }catch (Exception e){
                            fnTakeScreenAshot(configTestRunner,"fail","User is unable to logout from the Translations Hub application.","LogOutUnsuccessful");
                        }
                }catch (Exception e){
                    fnTakeScreenAshot(configTestRunner,"fail","LogOut option is not present in profile menu.","LogOut_Btn_Not_Visible");
                }

        }catch (Exception e){
            fnTakeScreenAshot(configTestRunner,"Fail","hamburgMenu is not present in NHS BI application home page.","HamburgMenuNotPresent");
        }
    }
    /*
     * This method is used to check forgot password link functionality
     * @param configTestRunner
     * @Developed By: Jyoti Dhage
     */
    public boolean fnforgotpassword(ConfigTestRunner configTestRunner) {
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Login To Translations Hub Application Verification For Valid Credentials"));

        boolean isPresence = false;
        String username="";
        if(Constants.Env.equalsIgnoreCase("Dev")){
            configTestRunner.driver.get(Constants.DEV_URL);
            username=Constants.userName_dev;
            configTestRunner.getChildTest().log(Status.INFO, "URL use to login into the application is "+ Constants.DEV_URL);
        }else if(Constants.Env.equalsIgnoreCase("Uat")){
            configTestRunner.driver.get(Constants.UAT_URL);
            username=Constants.userName_dev;
            configTestRunner.getChildTest().log(Status.INFO, "URL use to login into the application is "+ Constants.UAT_URL);
        }else if(Constants.Env.equalsIgnoreCase("pp")){
            configTestRunner.driver.get(Constants.PP_URL);
            username=Constants.userName_dev;
            configTestRunner.getChildTest().log(Status.INFO, "URL use to login into the application is "+ Constants.PP_URL);
        }else
            System.out.println("No environment is selected to load.");
        //Provide login details
        fnForgotPasswordLinkVerification(configTestRunner,username);
        return isPresence;
    }

    /*
     * This method is used to check privacy policy link functionality
     * @param configTestRunner
     * @Developed By: Jyoti Dhage
     */
    public void fnPrivacyPolicy(ConfigTestRunner configTestRunner) {
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Login To Translations Hub Application Verification For Valid Credentials"));

        boolean isPresence = false;
        String username="";
        if(Constants.Env.equalsIgnoreCase("Dev")){
            configTestRunner.driver.get(Constants.DEV_URL);
            username=Constants.userName_dev;
            configTestRunner.getChildTest().log(Status.INFO, "URL use to login into the application is "+ Constants.DEV_URL);
        }else if(Constants.Env.equalsIgnoreCase("Uat")){
            configTestRunner.driver.get(Constants.UAT_URL);
            username=Constants.userName_dev;
            configTestRunner.getChildTest().log(Status.INFO, "URL use to login into the application is "+ Constants.UAT_URL);
        }else if(Constants.Env.equalsIgnoreCase("pp")){
            configTestRunner.driver.get(Constants.PP_URL);
            username=Constants.userName_dev;
            configTestRunner.getChildTest().log(Status.INFO, "URL use to login into the application is "+ Constants.PP_URL);
        }else
            System.out.println("No environment is selected to load.");
        //Provide login details
        VerifPrivacyPolicy(configTestRunner);
    }

    /*
     * This method is used to check logo on login page
     * @param configTestRunner
     * @Developed By: Jyoti Dhage
     */
    public void fnCheckLogo(ConfigTestRunner configTestRunner) {
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Login To Translations Hub Application Verification For Valid Credentials"));

        boolean isPresence = false;
        String username="";
        String password="";
        if(Constants.Env.equalsIgnoreCase("Dev")){
            configTestRunner.driver.get(Constants.DEV_URL);
            username=Constants.userName_dev;
            password=Constants.password_dev;
            configTestRunner.getChildTest().log(Status.INFO, "URL use to login into the application is "+ Constants.DEV_URL);
        }else if(Constants.Env.equalsIgnoreCase("Uat")){
            configTestRunner.driver.get(Constants.UAT_URL);
            username=Constants.userName_dev;
            password=Constants.password_dev;
            configTestRunner.getChildTest().log(Status.INFO, "URL use to login into the application is "+ Constants.UAT_URL);
        }else if(Constants.Env.equalsIgnoreCase("pp")){
            configTestRunner.driver.get(Constants.PP_URL);
            username=Constants.userName_dev;
            password=Constants.password_dev;
            configTestRunner.getChildTest().log(Status.INFO, "URL use to login into the application is "+ Constants.PP_URL);
        }else
            System.out.println("No environment is selected to load.");
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify Logo on login page"));
        getWebElement(formName,"blinxSolutionLogo",configTestRunner).isDisplayed();
        configTestRunner.elementUtil.fnhighlightElement(getWebElement(formName,"blinxSolutionLogo",configTestRunner));
        fnTakeScreenAshot(configTestRunner,"Pass","Logo of the application is present on login page.","LogoVerify");
        //Provide login details
        fnValidLogin(configTestRunner,username,password,"Login to the application is successful","SuccessFulLoginVerification");
        getWebElement(formName,"logo_landingPage",configTestRunner).isDisplayed();
        getWebElement(formName,"logo_landingPage",configTestRunner).isDisplayed();
        configTestRunner.elementUtil.fnhighlightElement(getWebElement(formName,"logo_landingPage",configTestRunner));
        fnTakeScreenAshot(configTestRunner,"Pass","Logo of the application is present on langing page of Translation Hub application..","LogoVerify_landingPage");
    }

    public void VerifPrivacyPolicy(ConfigTestRunner configTestRunner){
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify privacy policy opens in another tab"));
        String LoginPageHandle = configTestRunner.driver.getWindowHandle();
        getLinkText("Privacy Policy").isDisplayed();
        waitAndClick( getLinkText("Privacy Policy"),Constants.AJAX_TIMEOUT);
        configTestRunner.getChildTest().log(Status.PASS,"User click on the Privacy Policy link");
        ArrayList<String> privacyPolicyLink = new ArrayList<>(configTestRunner.driver.getWindowHandles());
        configTestRunner.driver.switchTo().window(privacyPolicyLink.get(1));
        if(configTestRunner.driver.getCurrentUrl().contains("privacy-policy"))
            fnTakeScreenAshot(configTestRunner,"Pass","Privacy Policy link is opened in new tab in browser after click on privacy policy link","PrivacyLink");
        configTestRunner.driver.switchTo().window(LoginPageHandle);
    }

    /*
     * This method is to check forgot password of username field
     */

    public void fnForgotPasswordLinkVerification(ConfigTestRunner configTestRunner,String userName){
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Forgot Password Link Verification in Translations Hub Login Page"));
        //try{
            getLinkText("Forgot Password?").isDisplayed();
            waitAndClick( getLinkText("Forgot Password?"),Constants.AJAX_TIMEOUT);
            configTestRunner.getChildTest().log(Status.PASS,"User click on the Forgot Password link");
            //try{
                getWebElement(formName,"forgotPasswordInput",configTestRunner).isDisplayed();
                getWebElement(formName,"forgotPasswordInput",configTestRunner).sendKeys(userName);
                configTestRunner.getChildTest().log(Status.PASS,"User enter email id is: "+userName);
                //try{
                    //Request mail sent notification
                    waitAndClick(getButtonWithText("Send Mail"),Constants.AJAX_TIMEOUT);
                     //try{
                         getWebElement(formName,"requestSentMsg",configTestRunner).isDisplayed();
                         fnTakeScreenAshot(configTestRunner,"warning","Reset password request has been sent successfully & password reset screen is display not reseting the password through the script.","RequestSentSuccessfullyforPasswordReset");
//                     }catch (Exception e){
//                         fnTakeScreenAshot(configTestRunner,"fail","Reset password request has not sent successfully.","RequestSentUnSuccessfully");
//                     }
                     //back button functionality
//                     try{
//                        waitAndClick(getLinkText(" Back to login "),Constants.AJAX_TIMEOUT);
//                        getWebElement(formName,"userid",configTestRunner).isDisplayed();
//                         configTestRunner.getChildTest().log(Status.PASS,"Back button functionality is working successfully from forgot password page");
//                     }catch (Exception e){
//                         fnTakeScreenAshot(configTestRunner,"fail","User is not successfully navigated to login page.","BackBtnFunctionalityNotWorking");
//                     }
//                }catch (Exception e){
//                    fnTakeScreenAshot(configTestRunner,"fail","Request Email button is not present on the forgot password page","RequestEmailBtn_NotPresent");
//                }
//            }catch (Exception e){
//                fnTakeScreenAshot(configTestRunner,"fail","On Forgot password page enter email text box is not present","EnterEmailTextBox_NotPresent");
//            }
//        }catch (Exception e){
//            fnTakeScreenAshot(configTestRunner,"fail","User is not able to see the Forgot Password link on the login page","NoForgotPassword_Line");
//        }
    }
    /*
     * This method is used for login with valid details
     * @param configTestRunner
     * @return login is succesful or not
     * @Developed By: Jyoti Dhage
     */
    public boolean fnValidLoginEnterKey(ConfigTestRunner configTestRunner) {
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Login To Translations Hub Application Verification For Valid Credentials with Enter Key functionality"));

        boolean isPresence = false;
        String username="";
        String password="";
        if(Constants.Env.equalsIgnoreCase("Dev")){
            configTestRunner.driver.get(Constants.DEV_URL);
            username=Constants.userName_dev;
            password=Constants.password_dev;
            configTestRunner.getChildTest().log(Status.INFO, "URL use to login into the application is "+ Constants.DEV_URL);
        }else if(Constants.Env.equalsIgnoreCase("Uat")){
            configTestRunner.driver.get(Constants.UAT_URL);
            username=Constants.userName_dev;
            password=Constants.password_dev;
            configTestRunner.getChildTest().log(Status.INFO, "URL use to login into the application is "+ Constants.UAT_URL);
        }else if(Constants.Env.equalsIgnoreCase("pp")){
            configTestRunner.driver.get(Constants.PP_URL);
            username=Constants.userName_dev;
            password=Constants.password_dev;
            configTestRunner.getChildTest().log(Status.INFO, "URL use to login into the application is "+ Constants.PP_URL);
        }else
            System.out.println("No environment is selected to load.");
        //Provide login details
        fnValidLoginEnterKey(configTestRunner,username,password,"Login to the application is successful","SuccessFulLoginVerification");
        return isPresence;
    }


    /*
     * This method is used for back button functionality
     * @param configTestRunner
     * @return login is succesful or not
     * @Developed By: Jyoti Dhage
     */
    public boolean fnBackButtonFunctionality(ConfigTestRunner configTestRunner) {
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify the login page by pressing ‘Back button’ of the browser. It should not allow you to enter into the system once you log out."));
        boolean isPresence = false;
        String username="";
        String password="";
        String url="";
        if(Constants.Env.equalsIgnoreCase("Dev")){
            configTestRunner.driver.get(Constants.DEV_URL);
            url=Constants.DEV_URL;
            username=Constants.userName_dev;
            password=Constants.password_dev;
            configTestRunner.getChildTest().log(Status.INFO, "URL use to login into the application is "+ Constants.DEV_URL);
        }else if(Constants.Env.equalsIgnoreCase("Uat")){
            configTestRunner.driver.get(Constants.UAT_URL);
            url=Constants.UAT_URL;
            username=Constants.userName_dev;
            password=Constants.password_dev;
            configTestRunner.getChildTest().log(Status.INFO, "URL use to login into the application is "+ Constants.UAT_URL);
        }else if(Constants.Env.equalsIgnoreCase("pp")){
            configTestRunner.driver.get(Constants.PP_URL);
            url=Constants.PP_URL;
            username=Constants.userName_dev;
            password=Constants.password_dev;
            configTestRunner.getChildTest().log(Status.INFO, "URL use to login into the application is "+ Constants.PP_URL);
        }else
            System.out.println("No environment is selected to load.");
        //Provide login details
        fnEnterCredentials(configTestRunner,username,password);
        configTestRunner.driver.navigate().back();
        if(!(configTestRunner.driver.getCurrentUrl().equals(url))){
            fnTakeScreenAshot(configTestRunner,"pass","Back button is press successfully & user is able to logout successful","");
        }
        configTestRunner.getChildTest().log(Status.PASS,"User click on back button from the browser");
        return isPresence;
    }

    /*
     * Verify if the login page allows to log in simultaneously with different credentials in a different browser
     * @param configTestRunner
     * @Developed By: Jyoti Dhage
     */

    public void fnDifferentBrowser(ConfigTestRunner configTestRunner){
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify if the login page allows to log in simultaneously with different credentials in a different browser."));
        configTestRunner.driver.get(Constants.UAT_URL);
        fnValidLoginEnterKey(configTestRunner,Constants.userName_dev,Constants.password_dev,"Login to the application is successful","SuccessFulLoginVerification");
        DriverFactory obj = new DriverFactory();
        WebDriver driver =obj.startBrowser("firefox");
        driver.get(Constants.UAT_URL);
//        fnValidLoginEnterKey(configTestRunner,Constants.driverPath,Constants.vendor_Password,"Login to the application is successful","SuccessFulLoginVerification");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(Constants.vendor);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(Constants.vendor_Password);
        driver.findElement(By.xpath("//button[text()='Log In']")).click();
        driver.findElement(By.xpath("//span[text()='Vendor Studies']")).isDisplayed();
        fnTakeScreenAshot(configTestRunner,"Pass","Login to the application is successful in edge browser","EdgeBrowser");
        driver.close();
    }
}

