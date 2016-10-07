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
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

/**
 * 
 * Class to provide steps for validating login.
 * 
 */
@ContextConfiguration(classes = CucumberApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class LoginSteps {

    @Autowired
    public HomePage homePage;

    @Value("${login.username}")
    String email;
    @Value("${login.password}")
    String password;
    @Value("${loggedin.name}")
    String name;

    @Value("${loggedin.url}")
    private String loggedInURL;
    
    private static final Logger logger = LoggerFactory.getLogger(LoginSteps.class);
    
    @Given("^I login$")
    public void login() throws Throwable {
        homePage.login(email, password);
        logger.info("Login Executed: "+homePage.getCurrentURL());
    }

    @And("^I can be logged in$")
    public void validateLoggedIn() throws Throwable {
        assertThat("Login Failed", homePage.getCurrentURL(), equalTo(loggedInURL));
    }

    @And("^I can see My name$")
    public void validateMyName() throws Throwable {
        DynamicStringProperty myprop = DynamicPropertyFactory.getInstance().getStringProperty("loadHomePage.url", "NOT FOUND");
        logger.info("Login Executed, My Name: "+myprop.get());
        assertThat("Logged In Username does not match", homePage.getLoggedInUsername(), equalTo(name));
    }

}
