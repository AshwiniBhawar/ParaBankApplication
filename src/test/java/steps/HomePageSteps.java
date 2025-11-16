package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import context.ScenarioContext;
import java.util.List;

public class HomePageSteps {
    private LoginPage loginPage;
    private HomePage homePage;
    private ScenarioContext scenarioContext;

     public HomePageSteps(Hooks hooks, ScenarioContext scenarioContext){
         this.loginPage=hooks.getLoginPage();
         this.scenarioContext=scenarioContext;
     }

    @Given("The user logins the application with a username {string} and a password {string}")
    public void The_user_enters_a_username_and_a_password_in_the_textbox(String username, String password) {
        homePage=loginPage.performLogin(username, password);
    }

    @When("The user fetches the list of headers under Account Services")
    public void The_user_fetches_the_list_of_headers_under_Account_Services() {
      List<String> headersList=homePage.getAccountServicesHeadersList();
      scenarioContext.setContext("headersList",headersList);
    }

    @Then("The headers list is should be")
    public void The_headers_list_is_should_be(DataTable data) {
         List<String> actualHeaders=scenarioContext.getContext("headersList");
         List<String> expectedHeaders = data.asList();
         System.out.println("Actual headers list: " +actualHeaders);
         System.out.println("Expected headers list: " +expectedHeaders);
         for(int i=0;i<actualHeaders.size();i++){
             Assert.assertEquals(actualHeaders.get(i), expectedHeaders.get(i), "Header mismatch at index " + i);
         }
    }
}
