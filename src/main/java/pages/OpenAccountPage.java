package pages;

import constants.AppConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.ElementUtils;

import java.util.ArrayList;
import java.util.List;

public class OpenAccountPage {
    private WebDriver driver;
    private ElementUtils eUtil;

    private static final Logger log = LogManager.getLogger(OpenAccountPage.class);

    public OpenAccountPage(WebDriver driver) {
        this.driver = driver;
        eUtil = new ElementUtils(driver);
    }

    private final By openAccountTitle = By.xpath("//h1[text()='Open New Account']");
    private final By selectAccountTypeDropdown = By.xpath("//select[@id='type']");
    private final By selectAccountNumDropdown = By.xpath("//select[@id='fromAccountId']");
    private final By openAccountButton = By.xpath("//input[@value='Open New Account']");
    private final By openNewAccountSuccMessage = By.id("openAccountResult");
    private final By accountDetailsTitle = By.xpath("//h1[@class='title' and text()='Account Details']");
    private final By accountDetailsTableSize = By.xpath("//div[@id='accountDetails']/table//tr");
    private final By newAccountLink= By.id("newAccountId");
    private final By selectActivityPeriodDetails= By.xpath("//select[@id='month']");
    private final By selectActivityTypeDetails= By.xpath("//select[@id='transactionType']");
    private final By clickGoButton= By.xpath("//input[@type='submit']");
    private final By fundTransferLink= By.xpath("//table[@id='transactionTable']/tbody//td/a");
    private final By transactionDetailsPageTitle= By.xpath("//h1[@class='title']");
    private final By transactionDetailsTableSize= By.xpath("//table/tbody/tr");
    private final By headers= By.xpath("//table[@id='transactionTable']/thead/tr/th");

    public String getOpenAccountTitle() {
        String title = eUtil.waitForElementText(openAccountTitle, AppConstants.DEFAULT_MEDIUM_WAIT);
        log.info("The open account title is :" + title);
        return title;
    }

    public void selectAccountType(String accountType) {
        eUtil.selectElementByVisibleText(selectAccountTypeDropdown, AppConstants.DEFAULT_MEDIUM_WAIT, accountType);
    }

    public void selectAccountNumber(String accountNum) {
        eUtil.selectElementByVisibleText(selectAccountNumDropdown, AppConstants.DEFAULT_LONG_WAIT, accountNum);
    }

    public void clickOpenNewAccountBtn() {
        eUtil.waitForElementClick(openAccountButton, AppConstants.DEFAULT_LONG_WAIT);
    }

    public String getOpenNewAccountMessage() {
        String msg = eUtil.waitForElementText(openNewAccountSuccMessage, AppConstants.DEFAULT_LONG_WAIT);
        log.info("The open new account message is :" + msg);
        return msg;
    }

    public void clickNewAccountLink(){
        eUtil.waitForElementClick(newAccountLink, AppConstants.DEFAULT_MEDIUM_WAIT);
    }

    public String accountDetailsTitle() {
        String title = eUtil.waitForElementText(accountDetailsTitle, AppConstants.DEFAULT_MEDIUM_WAIT);
        log.info("The account details title is :" + title);
        return title;
    }

    public boolean getAccountDetails(String accountNum, String accountType, String balance, String available) {
        int size = eUtil.waitForElementPresenceAndGetSize(accountDetailsTableSize, AppConstants.DEFAULT_MEDIUM_WAIT);
        log.info("The size of elements in the table:" + size);
        for (int i = 0; i < size; i++) {
            By rowValueLoc = By.xpath("//div[@id='accountDetails']/table/tbody/tr["+(i+1)+"]/td[2]");
            String rowValue = eUtil.waitForElementText(rowValueLoc, AppConstants.DEFAULT_MEDIUM_WAIT);
            log.info("Account Num:" + accountNum + "======" + "Account Type:" + accountType + "======" + "Balance:"
                    + balance + "======" + "Available:" + available);
            if (rowValue.equals(accountNum) || rowValue.equals(accountType) || rowValue.equals(balance) || rowValue.equals(available)) {
                log.info("The element value at-"+(i+1)+" is :"+ rowValue);
            }
            else {
                log.info("The elements values are not correct");
                return false;
            }
        }
        log.info("The elements values are correct");
        return true;
    }

    public void selectAccountActivityDropdowns(String activityPeriod, String type){
            eUtil.selectElementByVisibleText(selectActivityPeriodDetails, AppConstants.DEFAULT_MEDIUM_WAIT, activityPeriod);
            eUtil.selectElementByVisibleText(selectActivityTypeDetails, AppConstants.DEFAULT_MEDIUM_WAIT, type);
            eUtil.waitForElementClick(clickGoButton, AppConstants.DEFAULT_SHORT_WAIT);
    }

    public boolean getAccountActivityDetails(String date, String transaction, String debit, String credit){
    int headersSize= eUtil.waitForElementPresenceAndGetSize(headers, AppConstants.DEFAULT_LONG_WAIT);
    log.info("The size of headers:" + headersSize);
    log.info("Date:" + date + "======" + "Transaction:" + transaction + "======" + "Credit:"
                + credit + "======" + "Debit:" + debit);
    for(int i=0;i<headersSize;i++){
        By tableData= By.xpath("//table[@id='transactionTable']/tbody/tr/td["+(i+1)+"]");
        String tableDataValue=eUtil.waitForElementText(tableData,AppConstants.DEFAULT_MEDIUM_WAIT);
        if(tableDataValue.equals(date) || tableDataValue.equals(transaction) || tableDataValue.equals(credit) ||tableDataValue.equals(debit)){
            log.info("The element value "+(i+1)+" is correct"+ tableDataValue);
        }
        else{
            log.info("The element value "+(i+1)+" is not correct"+ tableDataValue);
            return false;
        }
    }
        return true;
    }

    public String clickFTLinkAndGetTDTitle(){
        eUtil.waitForElementClick(fundTransferLink, AppConstants.DEFAULT_SHORT_WAIT);
        return eUtil.waitForElementText(transactionDetailsPageTitle, AppConstants.DEFAULT_MEDIUM_WAIT);
    }

    public List<String> getTransactionDetails(){
        List<String> newList= new ArrayList<>();
        int size = eUtil.waitForElementPresenceAndGetSize(transactionDetailsTableSize, AppConstants.DEFAULT_MEDIUM_WAIT);
        log.info("The size of elements in the table:" + size);
        for (int i = 2; i < size; i++) {
            By rowValueLoc = By.xpath("//table/tbody/tr["+i+"]/td[2]");
            String rowValue = eUtil.getElementText(rowValueLoc);
            log.info("The value at "+i+" is :"+rowValue);
            newList.add(rowValue);
        }
        return newList;
    }

    public String getTransactionId(){
        By rowValueLoc = By.xpath("//table/tbody/tr[1]/td[2]");
        String rowValue = eUtil.getElementText(rowValueLoc);
        log.info("The transaction id value is:" + rowValue);
        return rowValue;
    }
}
