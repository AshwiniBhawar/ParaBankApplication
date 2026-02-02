package steps;

import context.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import pages.OpenAccountPage;

import java.util.List;

public class OpenNewAccountSteps {

    private LoginPage loginPage;
    private HomePage homePage;
    private ScenarioContext scenarioContext;
    private OpenAccountPage openAccountPage;

    private static final Logger log= LogManager.getLogger(OpenNewAccountSteps.class);

    public OpenNewAccountSteps(Hooks hooks, ScenarioContext scenarioContext){
        this.loginPage=hooks.getLoginPage();
        this.scenarioContext=scenarioContext;
        homePage = (HomePage) scenarioContext.getContext("HOME_PAGE");
    }

    @When("The user clicks on the Open New Account link")
    public void the_user_clicks_on_the_open_new_account_link() {
        openAccountPage= homePage.clickOpenNewAccountLink();
    }

    @Then("The open account page title should be {string}")
    public void the_open_account_page_title_should_be(String openAccountPagetitle) {
        Assert.assertEquals(openAccountPage.getOpenAccountTitle(), openAccountPagetitle, "open new account page title mismatch");
    }

    @When("The user selects  account type as {string} and account number as {string}")
    public void the_user_selects_account_type_as_and_account_number_as(String accountType, String accountNum) {
        openAccountPage.selectAccountType(accountType);
        openAccountPage.selectAccountNumber(accountNum);
    }

    @When("The user clicks on open new account button")
    public void the_user_clicks_on_open_new_account_button() {
        openAccountPage.clickOpenNewAccountBtn();
    }

    @Then("The account is opened")
    public void the_account_is_opened() {
       String succMsg= openAccountPage.getOpenNewAccountMessage();
       String newAccountNum=succMsg.split(":")[1].trim();
       scenarioContext.setContext("NewAccountNum",newAccountNum);
       Assert.assertEquals(succMsg, "Account Opened!\n" +
               "Congratulations, your account is now open.\n" +
               "Your new account number: "+newAccountNum, "The account open success message mismatch");
    }

    @When("The user clicks on the new account number and navigate to {string} page")
    public void the_user_clicks_on_the_new_account_number_and_navigate_to_page(String string) {
        openAccountPage.clickNewAccountLink();
        Assert.assertEquals(openAccountPage.accountDetailsTitle(),"Account Details");
    }

    @And("The account details tab contains account number and account type as {string} and balance as {string} and available as {string}")
    public void the_account_details_tab_contains_account_number_and_account_type_as_and_balance_as_and_available_as(String accountType, String balance, String available) {
        String accountNum =scenarioContext.getContext("NewAccountNum");
        Assert.assertTrue(openAccountPage.getAccountDetails(accountNum, accountType, balance, available));
    }

    @Then("Select Activity Period as {string} and Type as {string} and click on go")
    public void select_activity_period_as_and_type_as_and_click_on_go(String activityPeriod, String activityType) {
        openAccountPage.selectAccountActivityDropdowns(activityPeriod, activityType);
    }


    @Then("The date shows as {string} and transaction as {string} and debit as {string} and credit as {string}")
    public void the_date_shows_as_and_transaction_as_and_debit_as_and_credit_as(String date, String transaction, String debit, String credit) {
        Assert.assertTrue(openAccountPage.getAccountActivityDetails(date, transaction, debit, credit));
    }

    @And("The user clicks on Funds Transfer Received  and navigate to {string} page")
    public void the_user_clicks_on_funds_transfer_received_and_navigate_to_page(String transactionDetailsPageTitle) {
        Assert.assertEquals(openAccountPage.clickFTLinkAndGetTDTitle(), transactionDetailsPageTitle);
    }

    @Then("The transaction details page contains transaction id and date as {string} and description as {string} and type as {string} amount as {string}")
    public void the_transaction_details_page_contains_transaction_id_and_date_as_and_description_as_and_type_as_amount_as(String date, String description, String type, String amount) {
        Assert.assertNotNull(openAccountPage.getTransactionId());
        List<String> actualList=openAccountPage.getTransactionDetails();
        List<String> expectedList= List.of(date, description, type,amount);
        Assert.assertEquals(actualList, expectedList);
    }

}
