package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import jdk.jfr.Label;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import context.ScenarioContext;

public class LoginPageSteps {
    private LoginPage loginPage;
    private HomePage homePage;
    private ScenarioContext scenarioContext;

    public LoginPageSteps(Hooks hooks, ScenarioContext scenarioContext){
        this.loginPage=hooks.getLoginPage();
        this.scenarioContext= scenarioContext;
    }

    @Given("The user launches the application and fetches the login page title")
    public void The_user_launches_the_application_and_fetches_the_login_page_title(){
        scenarioContext.setContext("loginPageTitle", loginPage.getLoginPageTitle());
    }

    @Then("The login page title should be {string} title")
    public void The_login_page_title_should_be_title(String loginPageTitle) {
        Assert.assertEquals(scenarioContext.getContext("loginPageTitle"), loginPageTitle, "Login page title is not matching");
    }

    @Given("The user logins the application with a username {string} and a password {string}")
    public void The_user_logins_the_application_with_a_username_and_a_password_in_the_textbox(String username, String password) {
        scenarioContext.setContext("username", username);
        scenarioContext.setContext("password", password);
        homePage=loginPage.performLogin(username, password);
        scenarioContext.setContext("HOME_PAGE", homePage);
    }

    @And("The user fetches the home page title")
    public void The_user_fetches_the_home_page_title(){
        scenarioContext.setContext("homePageTitle", homePage.getHomePageTitle());
    }
    @Then("The home page title should be {string}")
    public void The_home_page_title_should_be(String homePageTitle) {
        Assert.assertEquals(scenarioContext.getContext("homePageTitle"), homePageTitle, "Home page title is not matching");
    }

    @Then("The invalid login error message should be {string}")
    public void the_invalid_login_error_message_should_be(String expectedMsg) {
        String username=scenarioContext.getContext("username");
        String password=scenarioContext.getContext("password");
        Assert.assertTrue(loginPage.getInvalidLoginMsgResult(username, password, expectedMsg));
    }

    @Then("The user successfully logged out and navigate to login page having title {string}")
    public void The_user_successfully_logged_out_and_navigate_to_login_page_title(String title){
        loginPage= homePage.clickLogOutLink();
        Assert.assertEquals(loginPage.getLoginPageTitle(), title);
    }


}
