package driverfactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import error.AppErrors;
import exceptions.BrowserException;
import exceptions.FrameworkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;

	/**
	 * This method is initializing driver on the basis of browser
	 * @param browserName
	 * @return driver instance
	 */
    private static final Logger log= LogManager.getLogger(DriverFactory.class);
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {
        log.info("initialize the driver");
		String browserName=prop.getProperty("browser");
        log.info("Browser value is: "+browserName);

        optionsManager= new OptionsManager(prop);
        boolean remoteExec=Boolean.parseBoolean(prop.getProperty("remote"));

		switch(browserName.toLowerCase().trim()) {
		case "chrome":
            if(remoteExec) {
                init_remoteDriver("chrome");
            }
            else {
                tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
            }
            break;

		case "firefox":
            if(remoteExec) {
                init_remoteDriver("firefox");
            }
            else {
                tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
            }
			break;
			
		case "edge":
            if(remoteExec) {
                init_remoteDriver("edge");
            }
            else {
                tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
            }
			break;
		default:
            log.error("=========Invalid Broswer Passed :"+browserName+"==========");
			throw new BrowserException(AppErrors.INVALID_BROWSER);
		}
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().get(prop.getProperty("baseurl"));
		return getDriver();
	}

    /**
     * this is used to get the local copy of the driver any time
     * @return
     */
    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    /**
     * initialize the remote driver
     */
    private void init_remoteDriver(String browserName) {
        try {
            switch (browserName.trim().toLowerCase()) {
                case "chrome":
                    tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
                    break;
                case "firefox":
                    tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFirefoxOptions()));
                    break;
                case "edge":
                    tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));
                    break;
                default:
                    log.error("=========Invalid Broswer Passed :" + browserName + "==========");
                    throw new BrowserException(AppErrors.INVALID_BROWSER);
            }
        }
        catch(MalformedURLException e){
            throw new BrowserException(AppErrors.INVALID_BROWSER);
        }
    }

	/**
	 * This method is initializing the prop with properties file..
	 * 
	 * @return prop instance
	 */
	public Properties initProp() {
        log.info("Initialize the properties");
		prop= new Properties();
		FileInputStream fip=null;
		String envName=System.getProperty("env");
		try {
		if(envName==null || envName.isEmpty()) {
            envName="qa";
			fip = new FileInputStream("./src/test/resources/config/config.qa.properties");
		}
		else {
			switch (envName.toLowerCase().trim()) {
			case "qa":
				fip = new FileInputStream("./src/test/resources/config/config.qa.properties");
				break;
			case "dev":
				fip = new FileInputStream("./src/test/resources/config/config.dev.properties");
				break;
			case "uat":
				fip = new FileInputStream("./src/test/resources/config/config.uat.properties");
				break;
			case "prod":
				fip = new FileInputStream("./src/test/resources/config/config.prod.properties");
				break;
			default:
				throw new FrameworkException("========Invalid Environment=========");
			}
		}
			
		} catch (FileNotFoundException e) {
			throw new FrameworkException("File not found for "+envName+" env");
		}
		try {
			prop.load(fip);
		} catch (IOException e) {
			throw new FrameworkException("Properties file is not successfully loaded");
		}
        log.info("Env value is: " + envName);
		return prop;
	}

    /**
     * takescreenshot logic
     * @return
     */
    public static File getScreenshotFile() {
        TakesScreenshot ts = (TakesScreenshot) getDriver();
        return ts.getScreenshotAs(OutputType.FILE);
    }

    public static String getScreenshotBase64() {
        TakesScreenshot ts = (TakesScreenshot) getDriver();
        return ts.getScreenshotAs(OutputType.BASE64);
    }

    public static byte[] getScreenshotByte() {
        TakesScreenshot ts = (TakesScreenshot) getDriver();
        return ts.getScreenshotAs(OutputType.BYTES);
    }
}
