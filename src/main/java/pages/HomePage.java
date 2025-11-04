package pages;

import constants.AppConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.ElementUtils;

public class HomePage {
    private WebDriver driver;
    private ElementUtils eUtil;

    private static final Logger log= LogManager.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {
        this.driver=driver;
        eUtil= new ElementUtils(driver);
    }

    private final static By firstMsgLoc=By.xpath("//div[@id='rightPanel']/h1");
    private final static By secondMsgLoc=By.xpath("//div[@id='rightPanel']/p");

    public String welcomeMsg(){
        return eUtil.waitForElementText(firstMsgLoc, AppConstants.DEFAULT_LONG_WAIT);
    }

    public String accountCreationMsg(){
        return eUtil.waitForElementText(secondMsgLoc, AppConstants.DEFAULT_LONG_WAIT);
    }

    public String getHomePageTitle(){
        return eUtil.getPageTitle();
    }
}
