package com.kenzan.cucumber.page;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

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

/**
 * 
 * Class for HomePage. This class provides access to components of
 * HomePage.
 * 
 */
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
    
    @PreDestroy
    public void close() {
        if ( webDriver != null ) {
            logger.info("Closing Web Driver");
            webDriver.quit();
        }
    }
    
    public void loadHomePage(){
        //load the configured home page
        webDriver.get(homePageURL);
    }
    
    public boolean validateJoinNow(){
        try {
            webDriver.findElement(By.id(registration));
        } catch ( NoSuchElementException exception ) {
            logger.error("Registration Form is missing in home page", exception);
            return false;
        }
        return true;
    }
    
    public boolean validateLoginPlaceholders(){
        try {
            getLoginPlaceholders();
        } catch ( NoSuchElementException exception ) {
            logger.error("Login Place holders are missing in home page", exception);
            return false;
        }
        return true;
    }
    
    public boolean validateFooter(){
        try {
            webDriver.findElement(By.className(footer));
        } catch ( NoSuchElementException exception ) {
            logger.error("Footer is missing in home page", exception);
            return false;
        }
        return true;
    }
    
    public void login(final String email, final String password){
        loadHomePage();
        try {
            getLoginPlaceholders();
            emailInput.sendKeys(email);
            passwordInput.sendKeys(password);
            sigInButton.click();
        } catch ( Exception exception ) {
            logger.error("Login Failed: ", exception);
        }
    }
    
    public String getLoggedInUsername(){
        try {
            return webDriver.findElement(By.className(userName)).getText();
        } catch ( NoSuchElementException exception ) {
            logger.error("Login Failed or Username details are not shown", exception);
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
