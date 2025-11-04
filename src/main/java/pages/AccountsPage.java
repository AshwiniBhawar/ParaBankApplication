package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import utilities.ElementUtils;

public class AccountsPage {
    private WebDriver driver;
    private ElementUtils eUtil;

    private static final Logger log= LogManager.getLogger(HomePage.class);

    public AccountsPage(WebDriver driver) {
        this.driver=driver;
        eUtil= new ElementUtils(driver);
    }


}
