package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.*;
import context.BrowserContext;

@CucumberOptions (
    features = "src/test/resources/features",
    glue = "steps",
    tags = "@login",
    plugin = {
                "pretty",
                "html:target/cucumber-reports-chrome/cucumber-report-chrome.html",
                "html:target/cucumber-reports-chrome/cucumber-report-chrome.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "com.aventstack.chaintest.plugins.ChainTestCucumberListener:"
        },
    monochrome = true,
    dryRun = false
)


public class TestRunnerChrome extends AbstractTestNGCucumberTests {

    static{
        System.setProperty("chaintest.output.dir", "target/chaintest/chrome" );
        System.setProperty("allure.results.directory", "target/allure-results/chrome");
    }

    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void setUpBrowser(@Optional("chrome") String browser){
        BrowserContext.setBrowser(browser); //pass it to hooks
    }


    @Override
    @DataProvider(parallel=false)
    public Object[][] scenarios(){
        return super.scenarios();
    }

}
