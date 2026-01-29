package pages;

import constants.AppConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.ElementUtils;

public class UpdateContactInfoPage {
    private WebDriver driver;
    private ElementUtils eUtils;

    private static final Logger log = LogManager.getLogger(UpdateContactInfoPage.class);

    public UpdateContactInfoPage(WebDriver driver) {
        this.driver = driver;
        eUtils = new ElementUtils(driver);
    }

    private final By updateContactPageTitle= By.xpath("//h1[@class='title' and text()='Update Profile']");
    private final By firstName= By.id("customer.firstName");
    private final By lastName= By.id("customer.lastName");
    private final By address= By.id("customer.address.street");
    private final By city= By.id("customer.address.city");
    private final By state= By.id("customer.address.state");
    private final By zipCode= By.id("customer.address.zipCode");
    private final By phone= By.id("customer.phoneNumber");
    private final By updateProfileBtn= By.xpath("//input[@value='Update Profile']");
    private final By updateProfileSuccMsg=By.id("updateProfileResult");

    public String getUpdateProfileTitle(){
        String title= eUtils.waitForElementText(updateContactPageTitle,AppConstants.DEFAULT_MEDIUM_WAIT);
        log.info("The update contcat info page title is: "+title);
        return title;
    }

    public void enterTheContactDetails(String fn, String ln, String add, String ct, String st, String zc, String ph){
        eUtils.waitForElementVisibileAndSendText(firstName, AppConstants.DEFAULT_MEDIUM_WAIT, fn);
        eUtils.doSendKeys(lastName, ln);
        eUtils.doSendKeys(address, add);
        eUtils.doSendKeys(city, ct);
        eUtils.doSendKeys(state, st);
        eUtils.doSendKeys(zipCode, zc);
        eUtils.doSendKeys(phone, ph);
    }

    public void clickUpdateContactButton(){
        eUtils.waitForElementClick(updateProfileBtn, AppConstants.DEFAULT_MEDIUM_WAIT);
    }

    public String updateProfileSuccMsg(){
        String msg= eUtils.waitForElementText(updateProfileSuccMsg,AppConstants.DEFAULT_MEDIUM_WAIT);
        log.info("The update profile succ msg is: "+msg);
        return msg;
    }

}
