package driverfactory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	
	public OptionsManager(Properties prop){
		this.prop=prop;
	}
	
	public ChromeOptions getChromeOptions() {
		co=new ChromeOptions();
        co.addArguments("--guest");
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			co.addArguments("--headless=new");
		}
		
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			co.addArguments("--incognito");
		}
        if(Boolean.parseBoolean(prop.getProperty("remote"))) {
            co.setCapability("browserName", "chrome");
//            co.addArguments(
//                    "--no-sandbox",
//                    "--disable-dev-shm-usage",
//                    "--disable-gpu",
//                    "--window-size=1920,1080"
//            );

        }
		return co;
	}
			
	public FirefoxOptions getFirefoxOptions() {
		fo=new FirefoxOptions();
        fo.addArguments("--guest");
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
            fo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
            fo.addArguments("--incognito");
		}
        if(Boolean.parseBoolean(prop.getProperty("remote"))) {
            fo.setCapability("browserName", "firefox");
            fo.addArguments(
                    "--no-sandbox",
                    "--disable-dev-shm-usage",
                    "--disable-gpu",
                    "--window-size=1920,1080"
            );
        }
		return fo;
	}	
	
	public EdgeOptions getEdgeOptions() {
		eo=new EdgeOptions();
        eo.addArguments("--guest");
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
            eo.addArguments("--headless=new");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
            eo.addArguments("--inprivate");
		}
        if(Boolean.parseBoolean(prop.getProperty("remote"))) {
            eo.setCapability("browserName", "edge");
            eo.addArguments(
                    "--no-sandbox",
                    "--disable-dev-shm-usage",
                    "--disable-gpu",
                    "--window-size=1920,1080"
            );
        }
		return eo;
	}

}
