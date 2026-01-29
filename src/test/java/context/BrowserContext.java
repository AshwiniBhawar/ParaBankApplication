package context;

public class BrowserContext {

    private static ThreadLocal<String> browser= new ThreadLocal<>();

    public static String getBrowser() {
        return browser.get();
    }

    public static void setBrowser(String browserName){
        browser.set(browserName);
    }

    public static void removeBrowser(){
        browser.remove();
    }
}
