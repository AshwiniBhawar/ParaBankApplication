package steps;

import pages.AccountsPage;
import pages.LoginPage;
import utils.ScenarioContext;

public class AccountsPageSteps {
    private LoginPage loginPage;
    private AccountsPage accountPage;
    private ScenarioContext scenarioContext;

     public AccountsPageSteps(Hooks hooks, ScenarioContext scenarioContext){
         this.loginPage=hooks.getLoginPage();
         this.scenarioContext=scenarioContext;
     }
}
