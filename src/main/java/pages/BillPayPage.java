package pages;

import constants.AppConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.ElementUtils;

public class BillPayPage {
    private WebDriver driver;
    private ElementUtils eUtils;

    private static final Logger log= LogManager.getLogger(BillPayPage.class);

    public BillPayPage(WebDriver driver){
        this.driver=driver;
        eUtils=new ElementUtils(driver);
    }

    private final By billPayPageTitle=By.xpath("//h1[@class='title' and text()='Bill Payment Service']");
    private final By payeeName=By.name("payee.name");
    private final By address=By.name("payee.address.street");
    private final By city=By.name("payee.address.city");
    private final By state=By.name("payee.address.state");
    private final By zipcode=By.name("payee.address.zipCode");
    private final By phone=By.name("payee.phoneNumber");
    private final By account= By.name("payee.accountNumber");
    private final By verifyAccount=By.name("verifyAccount");
    private final By amount=By.name("amount");
    private final By fromAccount=By.xpath("//select[@name='fromAccountId']");
    private final By sendPaymentBtn=By.xpath("//input[@value='Send Payment']");
    private final By billPaySuccMsg= By.id("billpayResult");

    public String getBillPayPageTitle(){
        String title= eUtils.waitForElementText(billPayPageTitle, AppConstants.DEFAULT_MEDIUM_WAIT);
        log.info("Bill pay page title is: "+title);
        return title;
    }

    public void enterPayeeDetails(String pn, String add, String ct, String st, String zc, String ph, String payeeAcc, String verifyAcc,  String amt, String fromAcc){
        log.info("Enter the payee details: "+ pn +"-"+ add +"-"+ ct +"-"+ st +"-"+ zc +"-"+ ph +"-"+ payeeAcc +"-"+ amt +"-"+ fromAcc);
        eUtils.waitForElementVisibileAndSendText(payeeName, AppConstants.DEFAULT_MEDIUM_WAIT, pn);
        eUtils.doSendKeys(address,add);
        eUtils.doSendKeys(city,ct);
        eUtils.doSendKeys(state,st);
        eUtils.doSendKeys(zipcode,zc);
        eUtils.doSendKeys(phone,ph);
        eUtils.doSendKeys(account,payeeAcc);
        eUtils.doSendKeys(verifyAccount,verifyAcc);
        eUtils.doSendKeys(amount,amt);
        eUtils.selectElementByVisibleText(fromAccount, AppConstants.DEFAULT_SHORT_WAIT, fromAcc);
    }

    public void clickSendPaymentButton(){
        eUtils.waitForElementClick(sendPaymentBtn, AppConstants.DEFAULT_SHORT_WAIT);
    }

    public String getBillPaySuccessMsg() {
        String msg = eUtils.waitForElementText(billPaySuccMsg, AppConstants.DEFAULT_SHORT_WAIT);
        log.info("Bill pay success message: " + msg);
        return msg;
    }

}
