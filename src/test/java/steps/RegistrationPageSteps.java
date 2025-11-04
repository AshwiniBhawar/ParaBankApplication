package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationPage;
import utilities.RandomNumUtil;
import utils.ScenarioContext;

public class RegistrationPageSteps {
    private LoginPage loginPage;
    private ScenarioContext scenarioContext;
    private RegistrationPage regiPage;
    private HomePage homePage;

    public RegistrationPageSteps(Hooks hooks, ScenarioContext scenarioContext){
        this.loginPage= hooks.getLoginPage();
        this.scenarioContext=scenarioContext;
    }

    @Given("The user is on login page and clicks on register link")
    public void The_user_is_on_login_page_and_click_on_register_link(){
        regiPage=loginPage.clickRegisterLink();
    }

    @When("The user fetches the page title")
    public void The_user_fetches_the_page_title(){
        scenarioContext.setContext("RegistrationPageTitle",regiPage.getRegistrationPageTitle());
    }

    @Then("The page title should be {string}")
    public void The_page_title_should(String pageTitle){
        Assert.assertEquals(scenarioContext.getContext("RegistrationPageTitle"), pageTitle, "Page title does not match");
    }

    @When("The user fetches the registration form title")
    public void The_user_fetches_the_registration_form_title(){
        scenarioContext.setContext("RegistrationFormTitle",regiPage.getRegistrationFormTitle());
    }

    @Then("form page title should be {string}")
    public void form_page_title_should_be(String formTitle) {
        Assert.assertEquals(scenarioContext.getContext("RegistrationFormTitle"), formTitle);
    }

    @When("The user enters {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string}")
    public void the_user_enters(String fName, String lName, String address, String city, String state, String zipCode, String phone, String ssn, String username, String password, String confirmPass) {
        scenarioContext.setContext("username", password+RandomNumUtil.getNumber());
        String updatedUserName= scenarioContext.getContext("username");
        regiPage.fillTheRegistrationForm(fName, lName, address, city, state, zipCode, phone, ssn, updatedUserName, password, confirmPass);
    }

    @And("The user click on register link")
    public void the_user_click_on_register_link() {
        homePage=regiPage.clickRegisterLink();
    }

    @And("The user fetches the registration successful message")
    public void The_user_fetches_the_registration_successful_message() {
        scenarioContext.setContext("welcomeMsg", homePage.welcomeMsg());
        scenarioContext.setContext("accountCreationMsg", homePage.accountCreationMsg());
    }

    @Then("Registration should be successful with {string} message and {string} message")
    public void registration_should_be_successful_with_message_and_message(String firstMsg, String secondMsg) {
        String msg=scenarioContext.getContext("welcomeMsg");
        Assert.assertEquals(scenarioContext.getContext("welcomeMsg"), firstMsg +" "+scenarioContext.getContext("username"), "Welcome message is not matching");
        Assert.assertEquals(scenarioContext.getContext("accountCreationMsg"),secondMsg,  "Account creation message is not matching");
    }

    @And("The user fetches username already exist error message")
    public void the_user_fetches_username_already_exist_error_message() {
        scenarioContext.setContext("userNameExistError", regiPage.userNameExistErrorMsg());
    }

    @Then("{string} message should be displayed")
    public void message_should_be_displayed(String expectedMsg) {
        Assert.assertEquals(scenarioContext.getContext("userNameExistError"), expectedMsg);
    }
}
