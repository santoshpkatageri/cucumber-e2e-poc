package com.kenzan.cucumber.page;


import javax.annotation.PostConstruct;

import org.apache.logging.log4j.util.Strings;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class HomePage{
    
    @Autowired
    private WebDriver webDriver;
    
    @Value("${loadHomePage.url}")
    private String homePageURL;

    @Value("${homePage.registrationForm.id}")
    String registration;
    @Value("${homePage.loginEmail.id}")
    String loginEmail;
    @Value("${homePage.loginPassword.id}")
    String loginPassword;
    @Value("${homePage.loginSubmit.id}")
    String loginSubmit;
    @Value("${homePage.footer.class}")
    String footer;
    @Value("${loggedin.name.class}")
    String userName;
    
    private WebElement emailInput;
    
    private WebElement passwordInput;
    
    private WebElement sigInButton;
    
    private static final Logger logger = LoggerFactory.getLogger(HomePage.class);

    @PostConstruct
    public void init() {
        logger.info("Initializing Page Object: " + this.getClass().getSimpleName());
        PageFactory.initElements(webDriver, this);
    }
    
    public void loadHomePage(){
        //load the configured home page
        webDriver.get(homePageURL);
    }
    
    public boolean validateJoinNow(){
        try {
            webDriver.findElement(By.id(registration));
        } catch ( NoSuchElementException exception ) {
            return false;
        }
        return true;
    }
    
    public boolean validateLoginPlaceholders(){
        try {
            getLoginPlaceholders();
        } catch ( NoSuchElementException exception ) {
            return false;
        }
        return true;
    }
    
    public boolean validateFooter(){
        try {
            webDriver.findElement(By.className(footer));
        } catch ( NoSuchElementException exception ) {
            return false;
        }
        return true;
    }
    
    public void login(final String email, final String password){
        getLoginPlaceholders();
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        sigInButton.click();
    }
    
    public boolean validateLoggedIn(){
        try {
            webDriver.findElement(By.className(footer));
        } catch ( NoSuchElementException exception ) {
            return false;
        }
        return true;
    }
    
    public String getLoggedInUsername(){
        try {
            return webDriver.findElement(By.className(userName)).getText();
        } catch ( NoSuchElementException exception ) {
            return Strings.EMPTY;
        }
    }
    
    public String getCurrentURL(){
        return webDriver.getCurrentUrl();
    }
    
    private void getLoginPlaceholders() {
        emailInput = webDriver.findElement(By.id(loginEmail));
        passwordInput = webDriver.findElement(By.id(loginPassword));
        sigInButton = webDriver.findElement(By.id(loginSubmit));
    }
}
