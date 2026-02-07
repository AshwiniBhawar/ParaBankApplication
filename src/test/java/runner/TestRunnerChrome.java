package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.qameta.allure.Allure;
import org.testng.annotations.*;
import context.BrowserContext;

@CucumberOptions (
    features = "src/test/resources/features",
    glue = "steps",
    tags = "@login",
    plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-report-chrome.html",
                "json:target/cucumber-reports/cucumber-report-chrome.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "com.aventstack.chaintest.plugins.ChainTestCucumberListener:"
        },
    monochrome = true,
    dryRun = false
)


public class TestRunnerChrome extends AbstractTestNGCucumberTests {

    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void setUpBrowser(@Optional("chrome") String browser){
        BrowserContext.setBrowser(browser); //pass it to hooks
    }


    @Override
    @DataProvider(parallel=true)
    public Object[][] scenarios(){
        return super.scenarios();
    }

}
