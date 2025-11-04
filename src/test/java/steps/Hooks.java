package steps;

import driverfactory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utils.BrowserContext;
import java.util.Properties;

public class Hooks {
    private WebDriver driver;
    private DriverFactory df;
    private Properties prop;
    private LoginPage loginPage;

    private static final Logger log= LogManager.getLogger(Hooks.class);

    @Before()
    public void setUp() {
        log.info("Before test method executed==>set up the browser");
        df= new DriverFactory();
        prop = df.initProp();
        if(prop.getProperty("browser")==null || prop.getProperty("browser").isEmpty()){
            String browserName= BrowserContext.getBrowser();
            if(browserName!=null){
                prop.setProperty("browser", browserName);
            }
        }
        driver=df.initDriver(prop);
        loginPage= new LoginPage(driver);
    }

    @After
    public void tearDown() {
        log.info("After test method executed==>close the browser");
        if(driver !=null) {
            driver.quit();
        }
    }

    public LoginPage getLoginPage(){
        return loginPage;
    }

    public Properties getProperties(){
        return prop;
    }

    @AfterStep
    public void captureScreenshotForFailedStep(Scenario scenario) {
        log.info("After each step executed==>Capture a screenshot for failed step");
        if (scenario.isFailed()) {
            scenario.attach(DriverFactory.getScreenshotByte(), "image/png", "Failure Screenshot");
        }
    }
}
