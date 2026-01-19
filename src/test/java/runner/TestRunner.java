package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.*;
import context.BrowserContext;

@CucumberOptions (
    features = "src/test/resources/features",
    glue = "steps",
    tags = "@wip",
    plugin = {
                "pretty",
                "html:target/CucumberReport/cucumber-report.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "com.aventstack.chaintest.plugins.ChainTestCucumberListener:"
        },
    monochrome = true,
    dryRun = false
)
public class TestRunner extends AbstractTestNGCucumberTests {

    private static ThreadLocal<String> tlBrowser= new ThreadLocal<>();

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setUpBrowser(@Optional("chrome") String browser){
        tlBrowser.set(browser);  //store browser name in ThreadLocal
        BrowserContext.setBrowser(browser); //pass it to hooks
    }

    @DataProvider(parallel=true)
    @Override
    public Object[][] scenarios(){
        return super.scenarios();
    }
}
