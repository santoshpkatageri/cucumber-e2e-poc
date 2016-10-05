package com.kenzan.cucumber.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * Configuration Class to create the Web Driver.
 * 
 */
@Configuration
public class WebDriverConfig {
    
    private static final Logger logger = LoggerFactory.getLogger(WebDriverConfig.class);
    
    private WebDriver webDriver;
    @Bean
    WebDriver webDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        webDriver = new ChromeDriver();
        return webDriver;
    }
    
}
