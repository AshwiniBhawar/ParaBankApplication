package steps;

import context.ScenarioContext;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import pages.UpdateContactInfoPage;

public class UpdateContactInfoSteps {
    private LoginPage loginPage;
    private HomePage homePage;
    private UpdateContactInfoPage updateContactInfoPage;
    private ScenarioContext scenarioContext;

    public UpdateContactInfoSteps(Hooks hooks, ScenarioContext scenarioContext){
        this.loginPage=hooks.getLoginPage();
        this.scenarioContext=scenarioContext;
        homePage = (HomePage) scenarioContext.getContext("HOME_PAGE");
    }

    @When("The user clicks on the Update Contact Info link")
    public void the_user_clicks_on_the_update_contact_info_link() {
        updateContactInfoPage=homePage.clickUpdateContactInfoLink();
    }

    @Then("The update contact info title should be {string}")
    public void the_update_contact_info_title_should_be(String title) {
        Assert.assertEquals(updateContactInfoPage.getUpdateProfileTitle(), title);
    }

    @When("The user adds the contact details {string} {string} {string} {string} {string} {string} and {string}")
    public void the_user_adds_the_contact_details_and(String fn, String ln, String add, String ct, String st, String zc, String ph) {
        updateContactInfoPage.enterTheContactDetails(fn, ln, add, ct, st, zc, ph);
    }

    @And("The user clicks on update profile button")
    public void the_user_clicks_on_update_profile_button() {
        updateContactInfoPage.clickUpdateContactButton();
    }

    @Then("The profile updated successfully message should be displayed")
    public void the_profile_updated_successfully_message_should_be_displayed() {
        System.out.print(updateContactInfoPage.updateProfileSuccMsg());
        Assert.assertEquals(updateContactInfoPage.updateProfileSuccMsg(), "Profile Updated\n" +
                "Your updated address and phone number have been added to the system.","Update profile succuss msg mismatch");
    }
}
