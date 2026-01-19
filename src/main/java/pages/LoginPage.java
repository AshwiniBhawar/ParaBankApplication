package pages;

import constants.AppConstants;
import context.ScenarioContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.ElementUtils;

public class LoginPage {
	private WebDriver driver;
	private ElementUtils eUtil;

    private static final Logger log= LogManager.getLogger(LoginPage.class);

	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eUtil= new ElementUtils(driver);
	}
	
	private final By usernameLoc=By.name("username");
	private final By passwordLoc=By.name("password");
	private final By  loginBtnLoc=By.xpath("//input[@value='Log In']");
    private final By registerLinkLoc=By.xpath("//a[text()='Register']");
    private final By invalidLoginErrorMsgLoc= By.xpath("//p[@class='error']");

    public String getLoginPageTitle(){
        return eUtil.getPageTitle();
    }

	public HomePage performLogin(String username, String password) {
		eUtil.waitForElementVisibile(usernameLoc, AppConstants.DEFAULT_MEDIUM_WAIT).sendKeys(username);
		eUtil.waitForElementVisibile(passwordLoc, AppConstants.DEFAULT_MEDIUM_WAIT).sendKeys(password);
		eUtil.doClick(loginBtnLoc);
		return new HomePage(driver);
	}

    public RegistrationPage clickRegisterLink(){
        log.info("Click register link to navigate to registration page");
        eUtil.waitForElementClick(registerLinkLoc, AppConstants.DEFAULT_SHORT_WAIT);
        return new RegistrationPage(driver);
    }

    public boolean getInvalidLoginMsgResult(String username, String password, String expectedMsg) {
        if(username==null || username.isEmpty() ){
            username="";
        }
        if(password==null || password.isEmpty()){
            password="";
        }
        String actualMsg = eUtil.waitForElementText(invalidLoginErrorMsgLoc, AppConstants.DEFAULT_SHORT_WAIT);

        log.info("Actual invalid login error message for username :" + username + " password :" + password + " is :" + actualMsg);
        log.info("Expected invalid login error message for username :" + username + " password :" + password + " is :" + expectedMsg);

        if (!username.isEmpty() && !password.isEmpty() && actualMsg.equals(expectedMsg)) {
            return true;
        } else if (username.isEmpty() && !password.isEmpty() && actualMsg.equals(expectedMsg)) {
            return true;
        } else if (!username.isEmpty() && password.isEmpty() && actualMsg.equals(expectedMsg)) {
            return true;
        } else {
            return false;
        }
    }
}
