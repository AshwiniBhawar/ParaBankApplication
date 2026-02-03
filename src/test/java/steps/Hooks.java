package steps;

import com.aventstack.chaintest.plugins.ChainTestCucumberListener;
import driverfactory.DriverFactory;
import io.cucumber.java.*;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import context.BrowserContext;
import java.util.Properties;

public class Hooks {
    private WebDriver driver;
    private DriverFactory df;
    private Properties prop;
    private LoginPage loginPage;

    private static final Logger log= LogManager.getLogger(Hooks.class);

    @Before()
    public void setUp(Scenario scenario) {
        log.info("Before test method executed==>set up the browser");
        df= new DriverFactory();
        prop = df.initProp();
        String browserName= BrowserContext.getBrowser();
        log.info("Browser name is- "+ browserName);

        if(browserName !=null){
            log.info("Setting up property file browser value using provided parameter - "+ browserName);
            prop.setProperty("browser", browserName);
        }

        else if(prop.getProperty("browser")==null || prop.getProperty("browser").isEmpty()){
                log.info("Property file browser value is null, hence setting it to default value chrome.");
                prop.setProperty("browser", "chrome");
        }

        driver=df.initDriver(prop);
        loginPage= new LoginPage(driver);

        scenario.log("TestNg browser parameter value is- " + browserName+ " and property file broswer contains value: "+prop.get("browser"));
    }

    @After
    public void tearDown() {
        log.info("After test method executed==>close the browser");
        if(driver !=null) {
            driver.quit();
        }

        BrowserContext.removeBrowser();
        DriverFactory.tlDriver.remove();
    }

    public LoginPage getLoginPage(){
        return loginPage;
    }

    public Properties getProperties(){
        return prop;
    }

    @AfterStep
    public void captureScreenshotForFailedStep(Scenario scenario) {
        if (scenario.isFailed()) {
            log.info("After each step executed==>Capture a screenshot for failed step");
            scenario.attach(DriverFactory.getScreenshotByte(), "image/png", "Failure Screenshot");
        }
    }
}
