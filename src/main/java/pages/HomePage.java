package pages;

import constants.AppConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.ElementUtils;
import java.util.ArrayList;
import java.util.List;

public class HomePage {
    private WebDriver driver;
    private ElementUtils eUtils;

    private static final Logger log= LogManager.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {
        this.driver=driver;
        eUtils= new ElementUtils(driver);
    }

    private final static By firstMsgLoc=By.xpath("//div[@id='rightPanel']/h1");
    private final static By secondMsgLoc=By.xpath("//div[@id='rightPanel']/p");
    private final static By headersList= By.xpath("//div[@id='leftPanel']//ul//a");
    private final By openNewAccountLink=By.xpath("//a[text()='Open New Account']");
    private final By transferFundsLink= By.xpath("//a[text()='Transfer Funds']");
    private final By accountsOverviewLink= By.xpath("//a[text()='Accounts Overview']");
    private final By clickLogOutLink= By.xpath("//a[text()='Log Out']");
    private final By updateContactInfoLink= By.xpath("//a[text()='Update Contact Info']");
    private final By billPayLink= By.xpath("//a[text()='Bill Pay']");

    public String welcomeMsg(){
        return eUtils.waitForElementText(firstMsgLoc, AppConstants.DEFAULT_LONG_WAIT);
    }

    public String accountCreationMsg(){
        return eUtils.waitForElementText(secondMsgLoc, AppConstants.DEFAULT_LONG_WAIT);
    }

    public String getHomePageTitle(){
        return eUtils.getPageTitle();
    }

    public List<String> getAccountServicesHeadersList(){
        List<String> headers= new ArrayList<String>();
        List<WebElement> eleList= eUtils.waitForElementsVisibile(headersList, AppConstants.DEFAULT_LONG_WAIT);
        for(WebElement e:eleList){
            headers.add(e.getText());
        }
        return headers;
    }

    public OpenAccountPage clickOpenNewAccountLink(){
        eUtils.waitForElementClick(openNewAccountLink,AppConstants.DEFAULT_MEDIUM_WAIT);
        return new OpenAccountPage(driver);
    }

    public TransferFundsPage clickTransferFundsLink(){
        eUtils.waitForElementClick(transferFundsLink,AppConstants.DEFAULT_MEDIUM_WAIT);
        return new TransferFundsPage(driver);
    }

    public AccountsOverviewPage clickAccountOverviewLink(){
        eUtils.waitForElementClick(accountsOverviewLink,AppConstants.DEFAULT_MEDIUM_WAIT);
        return new AccountsOverviewPage(driver);
    }

    public LoginPage clickLogOutLink(){
        eUtils.waitForElementClick(clickLogOutLink,AppConstants.DEFAULT_LONG_WAIT);
        return new LoginPage(driver);
    }

    public UpdateContactInfoPage clickUpdateContactInfoLink(){
        eUtils.waitForElementClick(updateContactInfoLink,AppConstants.DEFAULT_MEDIUM_WAIT);
        return new UpdateContactInfoPage(driver);
    }
    public BillPayPage clickBillPayLink(){
        eUtils.waitForElementClick(billPayLink,AppConstants.DEFAULT_MEDIUM_WAIT);
        return new BillPayPage(driver);
    }



}
