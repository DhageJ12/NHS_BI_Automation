package com.application;

import com.Utility.Constants;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class GeneralTestCases extends BaseAction {

    public void fnUserCreation(ConfigTestRunner configTestRunner,String tcNumber) {
        switch (tcNumber){
            case "SC001_GE":
                UserManagement(configTestRunner);
                fnCreateNewUser(configTestRunner);
            break;
            case "SC002_GE":
                fnEditUser(configTestRunner);
            break;
            case "SC003_GE":
                fnDeleteUser(configTestRunner);
                break;
            case "SC004_GE":
                fnDisabledUser(configTestRunner);
                break;
            case "SC005_GE":
                fnEnabledUser(configTestRunner);
                break;
            default:
            System.out.println("No Test case is consider for execution");
    }
    }

    public void UserManagement(ConfigTestRunner configTestRunner) {
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("User Management Menu Selection Verification"));
        try{
            if(fnWaitForVisibility(getWebElement("HomePage","hamburgMenu",configTestRunner), Constants.AJAX_TIMEOUT)){
                configTestRunner.elementUtil.waitAndClick(getWebElement("HomePage","hamburgMenu",configTestRunner),Constants.AJAX_TIMEOUT);
                configTestRunner.getChildTest().log(Status.PASS,"User Click on the hamburg menu.");
                try {
                    WebElement userManagement=getWebElement("HomePage","userManagement",  configTestRunner);
                    if (fnWaitForVisibility(userManagement, Constants.AJAX_TIMEOUT)) {
                        waitAndClick(userManagement,Constants.AJAX_TIMEOUT);
                        configTestRunner.getChildTest().log(Status.PASS,"User click on user management icon.");
                        fnWaitForVisibility(getWebElement("HomePage","createNewUser",configTestRunner),Constants.AJAX_TIMEOUT);
                        configTestRunner.getChildTest().log(Status.PASS,"User management screen is opened successfully.");
                    }
                }catch (Exception e){
                    configTestRunner.getChildTest().log(Status.FAIL,"Profile option is not present in the home page menu.");
                }
            }

        }catch (Exception e){
            fnTakeScreenAshot(configTestRunner,"Fail","hamburgMenu is not present in NHS BI application home page.","HamburgMenuNotPresent");
        }

    }

    public String fnCreateNewUser(ConfigTestRunner configTestRunner){
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Create New User from the User Management page"));
        String mailId = "";
        try{
            waitAndClick(getWebElement("HomePage","createNewUser",configTestRunner),Constants.AJAX_TIMEOUT);
            configTestRunner.getChildTest().log(Status.PASS,"Create new user pop up is opened successfully.");
            fnWaitForVisibility(getWebElement("HomePage","UserManagement_FName",configTestRunner),Constants.AJAX_TIMEOUT);
            getWebElement("HomePage","UserManagement_FName",configTestRunner).sendKeys(configTestRunner.getBaseAction().getTestData().get("Fname"));
            getWebElement("HomePage","UserManagement_LName",configTestRunner).sendKeys(configTestRunner.getBaseAction().getTestData().get("Lname"));
            DateFormat dateFormat = new SimpleDateFormat("HH-mm-ss");
            String date = dateFormat.format(new Date());
            mailId = "TestMail_"+date+"@blinxsolution.com";
            getWebElement("HomePage","UserManagement_eMail",configTestRunner).sendKeys(mailId);
            configTestRunner.getChildTest().log(Status.INFO,"Email id enter is :"+mailId);
            getWebElement("HomePage","UserManagement_phone",configTestRunner).sendKeys(configTestRunner.getBaseAction().getTestData().get("MobileNumber"));
           configTestRunner.elementUtil.setFocusClick(getWebElement("HomePage","UserManagement_password",configTestRunner));
            getWebElement("HomePage","UserManagement_passwordIn",configTestRunner).sendKeys(configTestRunner.getBaseAction().getTestData().get("Password"));
            waitAndClick(getWebElement("HomePage","UserManagement_role",configTestRunner),Constants.AJAX_TIMEOUT);
            List<String> Role = new ArrayList<>(Arrays.asList(configTestRunner.getBaseAction().getTestData().get("Role").split(",")));
            for(String s: Role){
                waitAndClick(getLiWithText(s),Constants.AJAX_TIMEOUT);
            }
            waitAndClick(getWebElement("HomePage","UserManagement_role",configTestRunner),Constants.AJAX_TIMEOUT);
            configTestRunner.elementUtil.setFocusClick(getButtonWithText("Create"));
            try {
                fnWaitForVisibility(getDivWithText("Success"), 10);
                waitAndClick(getButtonWithText("OK"),Constants.AJAX_TIMEOUT);

            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                sleep(3000);
                getWebElement("HomePage","searchInputBox",configTestRunner).sendKeys(mailId);
                fnWaitForVisibility(getDivWithText(mailId),Constants.AJAX_TIMEOUT);
                configTestRunner.getChildTest().log(Status.INFO,"New user account is created successfully.");
            }catch (Exception e){

            }

        }catch (Exception e){
            configTestRunner.getChildTest().log(Status.FAIL,"Create new User window is not present in the application.");
            List<String> vaccinationType = new ArrayList<>(Arrays.asList(configTestRunner.getBaseAction().getTestData().get("VaccinationType").split(",")));
        }
        return mailId;
    }


    public void fnEditUser(ConfigTestRunner configTestRunner){
        UserManagement(configTestRunner);
        String user = fnCreateNewUser(configTestRunner);
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify the edit functionality for the user"));
        try {
            waitAndClick(getWebElement("HomePage", "moreOption", configTestRunner), Constants.AJAX_TIMEOUT);
            configTestRunner.getChildTest().log(Status.PASS,"User click on edit option from the list");
            fnWaitForVisibility(getSpanWithText(configTestRunner,"Edit"),Constants.AJAX_TIMEOUT);
            waitAndClick(getSpanWithText(configTestRunner,"Edit"),Constants.AJAX_TIMEOUT);
            try{
                fnWaitForVisibility(getDivWithText("Edit User Details"),Constants.AJAX_TIMEOUT);
                configTestRunner.getChildTest().log(Status.FAIL,"Edit User Details page is not opened successfully.");
                //click on confirm button
                waitAndClick(getWebElement("HomePage","editUserConfirm",configTestRunner),Constants.AJAX_TIMEOUT);
                try {
                    fnWaitForVisibility(getDivWithText("Success"), 10);
                    waitAndClick(getButtonWithText("OK"),Constants.AJAX_TIMEOUT);
                    configTestRunner.getChildTest().log(Status.PASS,"User detailed edited successfully.");
                    fnWaitForVisibility(getWebElement("HomePage","searchInputBox",configTestRunner),Constants.AJAX_TIMEOUT);
                    getWebElement("HomePage","searchInputBox",configTestRunner).sendKeys(user);
                    fnWaitForVisibility(getDivWithText(user),Constants.AJAX_TIMEOUT);
                }catch (Exception e){
                    configTestRunner.getChildTest().log(Status.FAIL,"User detailed not edited successfully.");
                }
            }catch (Exception e){
                configTestRunner.getChildTest().log(Status.FAIL,"More option list is not opened successfully.");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void fnDeleteUser(ConfigTestRunner configTestRunner){
        UserManagement(configTestRunner);
        String user = fnCreateNewUser(configTestRunner);
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify the delete functionality for created user"));
        try {
            waitAndClick(getWebElement("HomePage", "moreOption", configTestRunner), Constants.AJAX_TIMEOUT);
            fnWaitForVisibility(getSpanWithText(configTestRunner,"Delete"),Constants.AJAX_TIMEOUT);
            configTestRunner.getChildTest().log(Status.PASS,"User click on delete option from the list");
            waitAndClick(getSpanWithText(configTestRunner,"Delete"),Constants.AJAX_TIMEOUT);
            try{
                fnWaitForVisibility(getDivContainsWithText("Are you sure you want to delete user"),Constants.AJAX_TIMEOUT);
                configTestRunner.getChildTest().log(Status.PASS,"User delete confirmation pop up opened successfully.");
                fnTakeScreenAshot(configTestRunner,"Pass","User detailed deleted verification completed","SC003_GE_Delete");
                //click on confirm button
                waitAndClick(getWebElement("HomePage","editUserConfirm",configTestRunner),Constants.AJAX_TIMEOUT);
                try {
                    fnWaitForVisibility(getDivWithText("Success"), 10);
                    waitAndClick(getButtonWithText("OK"),Constants.AJAX_TIMEOUT);
                    try{
                        List<WebElement> ele = getWebElements(configTestRunner,"HomePage","delRecord");
                        if(ele.size()>1)
                            configTestRunner.getChildTest().log(Status.PASS,"User is deleted successfully.");
                    }catch (Exception e){
                        configTestRunner.getChildTest().log(Status.FAIL,"User is not deleted successfully.");
                    }
                }catch (Exception e){
                    configTestRunner.getChildTest().log(Status.FAIL,"User delete confirmation pop up not opened successfully.");
                }
            }catch (Exception e){
                configTestRunner.getChildTest().log(Status.FAIL,"More option list is not opened successfully.");

            }

        }catch (Exception e){

        }
    }
    public void fnDisabledUser(ConfigTestRunner configTestRunner){
        UserManagement(configTestRunner);
        String user = fnCreateNewUser(configTestRunner);
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify the disabled functionality for created user"));
        try {
            sleep(3000);
            waitAndClick(getWebElement("HomePage", "moreOption", configTestRunner), Constants.AJAX_TIMEOUT);
            fnWaitForVisibility(getSpanWithText(configTestRunner,"Disable"),Constants.AJAX_TIMEOUT);
            configTestRunner.getChildTest().log(Status.PASS,"User click on disabled option from the list");
            waitAndClick(getSpanWithText(configTestRunner,"Disable"),Constants.AJAX_TIMEOUT);

                try {
                    fnTakeScreenAshot(configTestRunner,"Pass","User disabled verification completed","SC004_GE_Disabled");
                    fnWaitForVisibility(getDivWithText("Success"), 10);
                    waitAndClick(getButtonWithText("OK"),Constants.AJAX_TIMEOUT);
                    try{
                        waitAndClick(getWebElement("HomePage", "moreOption", configTestRunner), Constants.AJAX_TIMEOUT);
                        fnWaitForVisibility(getSpanWithText(configTestRunner,"Enable"),Constants.AJAX_TIMEOUT);
                            configTestRunner.getChildTest().log(Status.PASS,"User is disabled successfully.");
                    }catch (Exception e){
                        configTestRunner.getChildTest().log(Status.FAIL,"User is not disabled successfully.");
                    }
                }catch (Exception e){
                    configTestRunner.getChildTest().log(Status.FAIL, "Disable user confirmation pop up not opened successfully.");
                }
            }catch (Exception e){
                configTestRunner.getChildTest().log(Status.FAIL,"More option list is not opened successfully.");

            }
    }

    public void fnEnabledUser(ConfigTestRunner configTestRunner){
        UserManagement(configTestRunner);
        String user = fnCreateNewUser(configTestRunner);
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify the enabled functionality for created user"));
        try {
            waitAndClick(getWebElement("HomePage", "moreOption", configTestRunner), Constants.AJAX_TIMEOUT);
            fnWaitForVisibility(getSpanWithText(configTestRunner,"Enable"),Constants.AJAX_TIMEOUT);
            configTestRunner.getChildTest().log(Status.PASS,"User click on Enable option from the list");
            waitAndClick(getSpanWithText(configTestRunner,"Enable"),Constants.AJAX_TIMEOUT);

            try {
                fnTakeScreenAshot(configTestRunner,"Pass","User enabled verification completed","SC005_GE_Disabled");
                fnWaitForVisibility(getDivWithText("Success"), 10);
                waitAndClick(getButtonWithText("OK"),Constants.AJAX_TIMEOUT);
                try{
                    waitAndClick(getWebElement("HomePage", "moreOption", configTestRunner), Constants.AJAX_TIMEOUT);
                    fnWaitForVisibility(getSpanWithText(configTestRunner,"Disable"),Constants.AJAX_TIMEOUT);
                    configTestRunner.getChildTest().log(Status.PASS,"User is Disable successfully.");
                }catch (Exception e){
                    configTestRunner.getChildTest().log(Status.FAIL,"User is not Disable successfully.");
                }
            }catch (Exception e){
                configTestRunner.getChildTest().log(Status.FAIL, "Enable user confirmation pop up not opened successfully.");
            }
        }catch (Exception e){
            configTestRunner.getChildTest().log(Status.FAIL,"More option list is not opened successfully.");

        }
    }


}
