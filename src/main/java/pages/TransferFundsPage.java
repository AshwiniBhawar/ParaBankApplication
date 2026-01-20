package pages;

import constants.AppConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.ElementUtils;

public class TransferFundsPage {

    private WebDriver driver;
    private ElementUtils eUtils;

    private static final Logger log= LogManager.getLogger(TransferFundsPage.class);

    public TransferFundsPage(WebDriver driver){
        this.driver=driver;
        eUtils= new ElementUtils(driver);
    }

    private final By transferFundTitle= By.xpath("//h1[@class='title' and normalize-space()='Transfer Funds']");
    private final By enterAmount= By.id("amount");
    private final By fromAccount= By.id("fromAccountId");
    private final By toAccount= By.id("toAccountId");
    private final By transferBtn=By.xpath("//input[@type='submit']");
    private final By transferSuccMsg= By.id("showResult");

    public String getTransferFundsTitle(){
        return eUtils.waitForElementText(transferFundTitle, AppConstants.DEFAULT_MEDIUM_WAIT);
    }

    public void enterAmount(String amount){
        eUtils.waitForElementVisibileAndSendText(enterAmount, AppConstants.DEFAULT_MEDIUM_WAIT, amount);
    }

    public void enterAccountFromAndTo(String fromAcc, String toAcc){
        eUtils.selectElementByValue(fromAccount, AppConstants.DEFAULT_MEDIUM_WAIT, fromAcc);
        eUtils.selectElementByValue(toAccount, AppConstants.DEFAULT_MEDIUM_WAIT, toAcc);
    }

    public void clickTransferBtn(){
        eUtils.waitForElementClick(transferBtn, AppConstants.DEFAULT_MEDIUM_WAIT);
    }

    public String transferSuccMsg(){
        return eUtils.waitForElementText(transferSuccMsg, AppConstants.DEFAULT_MEDIUM_WAIT);
    }

}
