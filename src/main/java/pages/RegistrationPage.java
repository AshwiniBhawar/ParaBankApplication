package pages;

import constants.AppConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.ElementUtils;


public class RegistrationPage {

	private WebDriver driver;
	private ElementUtils eUtil;
    private static final Logger log= LogManager.getLogger(RegistrationPage.class);

	public RegistrationPage(WebDriver driver){
		this.driver=driver;
		eUtil= new ElementUtils(driver);
	}
	
	private static final By registerFormTitleLoc=By.xpath("//h1[@class='title']");
    private static final By fNameLoc= By.id("customer.firstName");
    private static final By lNameLoc= By.id("customer.lastName");
    private static final By addressLoc= By.id("customer.address.street");
    private static final By cityLoc= By.id("customer.address.city");
    private static final By stateLoc= By.id("customer.address.state");
    private static final By zipCodeLoc= By.id("customer.address.zipCode");
    private static final By phoneNoLoc= By.id("customer.phoneNumber");
    private static final By ssnLoc= By.id("customer.ssn");
    private static final By usernameLoc= By.id("customer.username");
    private static final By passLoc= By.id("customer.password");
    private static final By confirmPassLoc= By.id("repeatedPassword");
    private static final By formRegisterBtnLoc= By.xpath("//input[@value='Register']");
    private static final By usernameErrorMsgLoc= By.xpath("//span[@id='customer.username.errors']");

	public void fillTheRegistrationForm(String fName, String lName, String address, String city, String state, String zipCode,
                                        String phone, String ssn, String username, String password, String confirmPassword) {
        log.info("fill the registration form:"+fName+" "+lName+" "+address+" "+city+" "+state+" "+zipCode+" "+phone+" "+ssn+" "+username+" "+password+" "+confirmPassword);
        eUtil.waitForElementVisibileAndSendText(fNameLoc, AppConstants.DEFAULT_SHORT_WAIT, fName);
        eUtil.doSendKeys(lNameLoc,lName);
        eUtil.doSendKeys(addressLoc,address);
        eUtil.doSendKeys(cityLoc,city);
        eUtil.doSendKeys(stateLoc,state);
        eUtil.doSendKeys(zipCodeLoc,zipCode);
        eUtil.doSendKeys(phoneNoLoc,phone);
        eUtil.doSendKeys(ssnLoc,ssn);
        eUtil.doSendKeys(usernameLoc,username);
        eUtil.doSendKeys(passLoc,password);
        eUtil.doSendKeys(confirmPassLoc,confirmPassword);
    }

    public String getRegistrationFormTitle(){
       WebElement element= eUtil.waitForElementVisibile(registerFormTitleLoc, AppConstants.DEFAULT_MEDIUM_WAIT);
       String formTitle=element.getText();
       log.info("Registration form title is :"+formTitle);
       return formTitle;
    }

    public HomePage clickRegisterLink(){
        log.info("click on form register");
        eUtil.waitForElementClick(formRegisterBtnLoc, AppConstants.DEFAULT_MEDIUM_WAIT);
        return new HomePage(driver);
    }

    public String getRegistrationPageTitle(){
       String title= eUtil.getPageTitle();
       log.info("Registration page title expected :"+title);
       return title;
    }

    public String userNameExistErrorMsg(){
        String errorMsg=eUtil.waitForElementText(usernameErrorMsgLoc, AppConstants.DEFAULT_MEDIUM_WAIT);
        log.info("Username already exist error message :"+errorMsg);
        return errorMsg;
    }
}
