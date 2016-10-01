package com.kenzan.cucumber.steps;

import static org.junit.Assert.assertEquals;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.kenzan.cucumber.CucumberApplication;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

@ContextConfiguration(classes = CucumberApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class LoadHomePageSteps {

    @Autowired
    private WebDriver webDriver;
    
    private static final Logger logger = LoggerFactory.getLogger(LoadHomePageSteps.class);
    
    @Given("^I load the homepage$")
    public void loadHomepage() throws Throwable {
        logger.info("Executing Load Homepage");
        webDriver.get("https://www.linkedin.com");
        logger.info("Executing Load Homepage"+webDriver.getCurrentUrl());
    }

    @Then("^I get a OK response$")
    public void homepageLoaded() throws Throwable {
        assertEquals("https://www.linkedin.com/", webDriver.getCurrentUrl());
    }
}
