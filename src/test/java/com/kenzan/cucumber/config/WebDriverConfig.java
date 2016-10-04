package com.kenzan.cucumber.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * Configuration Class to create the Web Driver.
 * 
 */
@Configuration
public class WebDriverConfig {
    
    @Bean
    WebDriver webDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        return new ChromeDriver();
    }
}
