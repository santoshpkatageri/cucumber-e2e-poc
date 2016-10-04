package com.kenzan.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * 
 * Cucumber Test Class. This binds the steps with
 * features. 
 * 
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"com.kenzan.cucumber.steps"})
public class CucumberTest {

}
