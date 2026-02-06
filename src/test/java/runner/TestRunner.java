package runner;

import context.BrowserContext;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@CucumberOptions (
    features = "src/test/resources/features",
    glue = "steps",
    tags = "@login",
    plugin = {
                "pretty",
                "html:target/cucumber-reports/index.html",
                "json:target/cucumber-reports/index.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "com.aventstack.chaintest.plugins.ChainTestCucumberListener:"
        },
    monochrome = true,
    dryRun = false
)


public class TestRunner extends AbstractTestNGCucumberTests {
    private static ThreadLocal<String> browserThreadLocal = new ThreadLocal<>();
    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setUpBrowser(@Optional("chrome") String browser){
        browserThreadLocal.set(browser); // Store browser name in ThreadLocal
        BrowserContext.setBrowser(browser); //pass it to hooks
    }


    @Override
    @DataProvider(parallel=false)
    public Object[][] scenarios(){
        return super.scenarios();
    }

}
