package com.kenzan.cucumber.steps;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.kenzan.cucumber.CucumberApplication;
import com.kenzan.cucumber.page.HomePage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

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
        assertEquals(loggedInURL, homePage.getCurrentURL());
    }

    @And("^I can see My name$")
    public void validateMyName() throws Throwable {
        assertEquals(name, homePage.getLoggedInUsername());
    }

}
