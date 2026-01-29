package pages;

import constants.AppConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.browsingcontext.CaptureScreenshotParameters;
import utilities.ElementUtils;

public class AccountsOverviewPage {

    private WebDriver driver;
    private ElementUtils eUtils;

    private static final Logger log= LogManager.getLogger(AccountsOverviewPage.class);

    public AccountsOverviewPage(WebDriver driver){
        this.driver=driver;
        eUtils= new ElementUtils(driver);
    }

    private final By accountsOverviewLink= By.xpath("");
    private final By accountOverviewTitle= By.xpath("//h1[@class='title']");

    public String getAccountsOverviewPageTitle(){
        String title=eUtils.waitForElementText(accountOverviewTitle, AppConstants.DEFAULT_SHORT_WAIT).trim();
        return title;
    }

    public boolean getAccountsDetails(String account, String balance, String available){
        By tableRowsCount=By.xpath("//table[@id='accountTable']/tbody/tr/td[1]/a");
        int size= eUtils.waitForElementPresenceAndGetSize(tableRowsCount, AppConstants.DEFAULT_SHORT_WAIT);

        for(int i=1;i<=size;i++) {
            By accountData=By.xpath("//table[@id='accountTable']/tbody/tr["+i+"]/td/a");
            String accountValue= eUtils.waitForElementText(accountData, AppConstants.DEFAULT_MEDIUM_WAIT);

            By accountRowDataCount= By.xpath("//table[@id='accountTable']/tbody/tr["+i+"]/td");
            int accountRowDataSize=eUtils.waitForElementPresenceAndGetSize(accountRowDataCount,AppConstants.DEFAULT_MEDIUM_WAIT);

            if(accountValue.equals(account)) {
                for(int j=1;j<=accountRowDataSize;j++){
                    By data= By.xpath("//table[@id='accountTable']/tbody/tr["+i+"]/td["+j+"]");
                    String rowDataValue= eUtils.waitForElementText(data, AppConstants.DEFAULT_MEDIUM_WAIT);
                    if(rowDataValue.equals(account) || rowDataValue.equals(balance) || rowDataValue.equals(available)){
                        log.info("The data at row -"+ i +" and column -"+ j + " is :"+rowDataValue);
                    }
                    else{
                        log.info("The account details are not matching");
                        return false;
                    }
                }
            }
        }
        log.info("The account details are matching");
        return true;
    }
}
