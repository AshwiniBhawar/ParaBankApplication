package steps;

import context.ScenarioContext;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.BillPayPage;
import pages.HomePage;
import pages.LoginPage;

public class BillPaySteps {
    private LoginPage loginPage;
    private ScenarioContext scenarioContext;
    private HomePage homePage;
    private BillPayPage billPayPage;

    public BillPaySteps(Hooks hooks, ScenarioContext scenarioContext){
        this.loginPage=hooks.getLoginPage();
        this.scenarioContext=scenarioContext;
        homePage = (HomePage) scenarioContext.getContext("HOME_PAGE");
    }

    @When("The user clicks on the Bill Pay link")
    public void the_user_clicks_on_the_bill_pay_link() {
        billPayPage= homePage.clickBillPayLink();
    }
    @Then("The bill pay page title should be {string}")
    public void the_bill_pay_page_title_should_be(String title) {
        Assert.assertEquals(billPayPage.getBillPayPageTitle(), title, "Bill Pay page title mismatch");
    }
    @When("The user adds the payee and accounts details {string} {string} {string} {string} {string} {string} {string} {string} {string} and {string}")
    public void the_user_adds_the_payee_and_accounts_details_and(String pn, String add, String ct, String st, String zc, String ph, String acc, String verifyAcc, String amt, String fromAcc) {
        scenarioContext.setContext("amount", amt);
        scenarioContext.setContext("payerAccount", fromAcc);
        billPayPage.enterPayeeDetails(pn, add, ct, st, zc, ph, acc, verifyAcc, amt, fromAcc);
    }
    @And("The user clicks on send payment button")
    public void the_user_clicks_on_send_payment_button() {
        billPayPage.clickSendPaymentButton();
    }
    @Then("The bill payment successful message should be displayed")
    public void the_bill_payment_successful_message_should_be_displayed() {
        String amount=scenarioContext.getContext("amount");
        String payerAccount=scenarioContext.getContext("payerAccount");
        Assert.assertEquals(billPayPage.getBillPaySuccessMsg(), "Bill Payment Complete\n" +
                "Bill Payment to krish in the amount of $"+amount+" from account "+payerAccount+" was successful.\n" +
                "See Account Activity for more details.", "Bill pay successful message mismatch");
    }

}
