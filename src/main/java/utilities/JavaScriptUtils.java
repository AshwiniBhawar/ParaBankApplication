package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JavaScriptUtils {
	private WebDriver driver;
	
	public JavaScriptUtils(WebDriver driver) {
		this.driver=driver;
	}
	
	
	public void generateAlert(String msg) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("alert("+ msg +")");
	}
	

}
