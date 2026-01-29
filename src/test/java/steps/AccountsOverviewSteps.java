package steps;

import context.ScenarioContext;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.AccountsOverviewPage;
import pages.HomePage;
import pages.LoginPage;

public class AccountsOverviewSteps {

    private LoginPage loginPage;
    private HomePage homePage;
    private AccountsOverviewPage accountsOverviewPage;
    private ScenarioContext scenarioContext;

    public AccountsOverviewSteps(Hooks hooks, ScenarioContext scenarioContext){
        this.loginPage=hooks.getLoginPage();
        this.scenarioContext=scenarioContext;
        homePage = (HomePage) scenarioContext.getContext("HOME_PAGE");
    }

    @When("The user clicks on Account Overview page")
    public void the_user_clicks_on_page() {
        accountsOverviewPage=homePage.clickAccountOverviewLink();
    }

    @And("The user navigates to {string} page")
    public void the_user_navigates_to_page(String title) {
        Assert.assertEquals(accountsOverviewPage.getAccountsOverviewPageTitle(), title);
    }

    @Then("The new opened account is listed under account overview having account number and balance as {string} and available amount as {string}")
    public void the_new_opened_account_is_listed_under_account_overview_having_account_number_and_balance_as_and_available_amount_as(String balance, String amount) {
        String account= scenarioContext.getContext("NewAccountNum");
        Assert.assertTrue(accountsOverviewPage.getAccountsDetails(account, balance, amount));
    }
}
