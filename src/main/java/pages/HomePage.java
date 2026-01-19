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
    private ElementUtils eUtil;

    private static final Logger log= LogManager.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {
        this.driver=driver;
        eUtil= new ElementUtils(driver);
    }

    private final static By firstMsgLoc=By.xpath("//div[@id='rightPanel']/h1");
    private final static By secondMsgLoc=By.xpath("//div[@id='rightPanel']/p");
    private final static By headersList= By.xpath("//div[@id='leftPanel']//ul//a");
    private final By openNewAccountLink=By.xpath("//a[text()='Open New Account']");

    public String welcomeMsg(){
        return eUtil.waitForElementText(firstMsgLoc, AppConstants.DEFAULT_LONG_WAIT);
    }

    public String accountCreationMsg(){
        return eUtil.waitForElementText(secondMsgLoc, AppConstants.DEFAULT_LONG_WAIT);
    }

    public String getHomePageTitle(){
        return eUtil.getPageTitle();
    }

    public List<String> getAccountServicesHeadersList(){
        List<String> headers= new ArrayList<String>();
        List<WebElement> eleList= eUtil.waitForElementsVisibile(headersList, AppConstants.DEFAULT_LONG_WAIT);
        for(WebElement e:eleList){
            headers.add(e.getText());
        }
        return headers;
    }

    public OpenAccountPage clickOpenNewAccountLink(){
        eUtil.waitForElementClick(openNewAccountLink,AppConstants.DEFAULT_MEDIUM_WAIT);
        return new OpenAccountPage(driver);
    }
}
