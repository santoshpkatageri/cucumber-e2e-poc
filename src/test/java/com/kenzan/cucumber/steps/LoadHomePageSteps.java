package com.kenzan.cucumber.steps;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.kenzan.cucumber.CucumberApplication;
import com.kenzan.cucumber.page.HomePage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

/**
 * 
 * Class to provide steps for loading home page.
 * 
 */
@ContextConfiguration(classes = CucumberApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class LoadHomePageSteps {

    @Autowired
    public HomePage homePage;

    @Value("${environment}")
    String environment;

    private static final Logger logger = LoggerFactory.getLogger(LoadHomePageSteps.class);
    
    @Given("^I load the homepage$")
    public void loadHomepage() throws Throwable {
        logger.info("Execution Enviroment: "+environment);
        homePage.loadHomePage();
        logger.info("Load Homepage Executed: "+homePage.getCurrentURL());
    }

    @And("^I see Join Now$")
    public void validateJoinNow() throws Throwable {
        assertThat("Registration is not found", true, equalTo(homePage.validateJoinNow()));
    }

    @And("^I can see Login Placeholders$")
    public void validateLoginPlaceholders() throws Throwable {
        assertThat("Login form elements are not found", true, equalTo(homePage.validateLoginPlaceholders()));
    }

    @And("^I can see Footer is shown$")
    public void validateFooter() throws Throwable {
        assertThat("Footer is not found", true, equalTo(homePage.validateFooter()));
    }
}
