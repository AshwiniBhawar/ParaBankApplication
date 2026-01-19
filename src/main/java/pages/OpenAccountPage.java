package pages;

import constants.AppConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.ElementUtils;

public class OpenAccountPage {
    private WebDriver driver;
    private ElementUtils eUtil;

    private static final Logger logger = LogManager.getLogger(OpenAccountPage.class);

    public OpenAccountPage(WebDriver driver) {
        this.driver = driver;
        eUtil = new ElementUtils(driver);
    }

    private final By openAccountTitle = By.xpath("//h1[text()='Open New Account']");
    private final By selectAccountTypeDropdown = By.xpath("//select[@id='type']");
    private final By selectAccountNumDropdown = By.xpath("//select[@id='fromAccountId']");
    private final By openAccountButton = By.xpath("//input[@value='Open New Account']");
    private final By openNewAccountSuccMessage=By.id("openAccountResult");

    public String getOpenAccountTitle() {
        String title=eUtil.waitForElementText(openAccountTitle, AppConstants.DEFAULT_MEDIUM_WAIT);
        return title;
    }

    public void selectAccountType(String accountTupe) {
        eUtil.selectElementByVisibleText(selectAccountTypeDropdown,AppConstants.DEFAULT_MEDIUM_WAIT,accountTupe);
    }

    public void selectAccountNumber(String accountNum) {
        eUtil.selectElementByVisibleText(selectAccountNumDropdown,AppConstants.DEFAULT_MEDIUM_WAIT,accountNum);
    }

    public void clickOpenNewAccountBtn(){
        eUtil.waitForElementClick(openAccountButton, AppConstants.DEFAULT_LONG_WAIT);
    }

    public String getOpenNewAccountMessage(){
        return eUtil.waitForElementText(openNewAccountSuccMessage, AppConstants.DEFAULT_LONG_WAIT);
    }

}

