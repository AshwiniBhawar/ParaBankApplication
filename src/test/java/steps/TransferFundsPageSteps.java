package steps;

import context.ScenarioContext;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import pages.TransferFundsPage;

public class TransferFundsPageSteps {

    private LoginPage loginPage;
    private HomePage homePage;
    private ScenarioContext scenarioContext;
    private TransferFundsPage transferFundsPage;

    public TransferFundsPageSteps(Hooks hooks, ScenarioContext scenarioContext){
        this.loginPage=hooks.getLoginPage();
        this.scenarioContext= scenarioContext;
        homePage=scenarioContext.getContext("HOME_PAGE");
    }

    @Given("The user go to the account overview page and captures the payee {string} and payer {string} balance details")
    public void the_user_go_to_the_account_overview_page_and_captures_the_payee_and_payer_balance_details(String payeeAccount, String payerAccount) {

    }

    @When("The user clicks on the Transfer Funds link")
    public void the_user_clicks_on_the_transfer_funds_link() {
        transferFundsPage= homePage.clickTransferFundsLink();
    }

    @Then("The transfer account page title should be {string}")
    public void the_transfer_account_page_title_should_be(String transferFundTitle) {
        Assert.assertEquals(transferFundsPage.getTransferFundsTitle(), transferFundTitle, "transfer funds title mismatch");
    }

    @When("The user enters  amount as {string}")
    public void the_user_enters_amount_as(String amount) {
        scenarioContext.setContext("Amount", amount);
        transferFundsPage.enterAmount(amount);
    }

    @And("The user enters from account as {string} and to as {string}")
    public void the_user_enters_from_account_as_and_to_as(String fromAccount, String toAccount) {
        scenarioContext.setContext("FromAmount", fromAccount);
        scenarioContext.setContext("ToAmount", toAccount);
        transferFundsPage.enterAccountFromAndTo(fromAccount, toAccount);
    }

    @And("The user clicks on transfer button")
    public void the_user_clicks_on_transfer_button() {
        transferFundsPage.clickTransferBtn();
    }

    @Then("The transfer is successful")
    public void the_transfer_is_successful() {
        String msg= transferFundsPage.transferSuccMsg();
        String amount= scenarioContext.getContext("Amount");
        String fromAcc= scenarioContext.getContext("FromAmount");
        String toAcc= scenarioContext.getContext("ToAmount");
        Assert.assertEquals(msg, "Transfer Complete!\n" +
                "$"+amount+" has been transferred from account #"+fromAcc+" to account #"+toAcc+".\n" +
                "See Account Activity for more details.", "The transfer funds success message mismatch");
    }

    @Then("Validates the payers account is debited and payee account is credited as per the transaction")
    public void validates_the_payers_account_is_debited_and_payee_account_is_credited_as_per_the_transaction() {

    }

}
