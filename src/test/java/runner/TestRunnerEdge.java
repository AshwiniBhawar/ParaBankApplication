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
                "html:target/cucumber-reports-edge/cucumber-report-edge.html",
                "html:target/cucumber-reports-edge/cucumber-report-edge.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "com.aventstack.chaintest.plugins.ChainTestCucumberListener:"
        },
        monochrome = true,
        dryRun = false
)


public class TestRunnerEdge extends AbstractTestNGCucumberTests {

    static{
        System.setProperty("chaintest.output.dir", "target/chaintest/edge" );
        System.setProperty("allure.results.directory", "target/allure-results/edge");
    }

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
