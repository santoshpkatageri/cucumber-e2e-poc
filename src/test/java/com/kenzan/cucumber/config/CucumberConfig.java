package com.kenzan.cucumber.config;

import javax.annotation.PostConstruct;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.config.AbstractPollingScheduler;
import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicConfiguration;
import com.netflix.config.FixedDelayPollingScheduler;
import com.netflix.config.sources.URLConfigurationSource;

/**
 * 
 * Configuration Class to create the Web Driver
 * and load Configuration.
 * 
 */
@Configuration
public class CucumberConfig {

    @Value("${properties.url}")
    private String propertiesURL;
    
    private static final Logger logger = LoggerFactory.getLogger(CucumberConfig.class);
    
    private WebDriver webDriver;

    @Bean
    WebDriver loadChromeWebDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        webDriver = new ChromeDriver();
        logger.info("Driver Loaded");
        return webDriver;
    }

    @PostConstruct
    public void loadConfig() {
        final AbstractPollingScheduler scheduler = new FixedDelayPollingScheduler(0,1000,false);
        final DynamicConfiguration dynConfig = new DynamicConfiguration(
                new URLConfigurationSource(propertiesURL),
                scheduler);
        ConfigurationManager.loadPropertiesFromConfiguration(dynConfig);
        logger.info("Config Loaded");
    }
}
