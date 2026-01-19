package steps;

import context.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import pages.OpenAccountPage;

public class OpenNewAccountSteps {

    private LoginPage loginPage;
    private HomePage homePage;
    private ScenarioContext scenarioContext;
    private OpenAccountPage openAccountPage;

    public OpenNewAccountSteps(Hooks hooks, ScenarioContext scenarioContext){
        this.loginPage=hooks.getLoginPage();
        this.scenarioContext=scenarioContext;
    }

    @When("The user clicks on the Open New Account link")
    public void the_user_clicks_on_the_open_new_account_link() {
        homePage = (HomePage) scenarioContext.getContext("HOME_PAGE");
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

       Assert.assertEquals(succMsg, "Account Opened!\n" +
               "Congratulations, your account is now open.\n" +
               "Your new account number: "+newAccountNum, "The account open success message mismatch");
       System.out.println(succMsg);
    }
}
