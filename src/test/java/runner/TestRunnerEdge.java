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
                "html:target/cucumber-reports/cucumber-report-edge.html",
                "json:target/cucumber-reports/cucumber-report-edge.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "com.aventstack.chaintest.plugins.ChainTestCucumberListener:"
        },
        monochrome = true,
        dryRun = false
)


public class TestRunnerEdge extends AbstractTestNGCucumberTests {

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setUpBrowser(@Optional("edge") String browser){
        BrowserContext.setBrowser(browser); //pass it to hooks
    }


    @Override
    @DataProvider(parallel=false)
    public Object[][] scenarios(){
        return super.scenarios();
    }

}
