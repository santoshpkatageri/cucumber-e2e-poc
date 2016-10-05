package com.kenzan.cucumber.config;


import org.junit.After;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.filters.RequestFilter;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;

/**
 * 
 * Configuration Class to create the Web Driver.
 * 
 */
@Configuration
public class WebDriverConfig {
    
    private static final Logger logger = LoggerFactory.getLogger(WebDriverConfig.class);
    
    private BrowserMobProxy proxy = new BrowserMobProxyServer();
    private WebDriver webDriver;
    @Bean
    WebDriver webDriver() {
        
        
        proxy.start(8081);
        
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
        
        proxy.addRequestFilter(new RequestFilter() {
            @Override
            public HttpResponse filterRequest(HttpRequest request, HttpMessageContents contents, HttpMessageInfo messageInfo) {

                logger.info("Requests: "+request.getUri());
                return null;
            }
        });
        
        
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        webDriver = new ChromeDriver(capabilities);
        return webDriver;
    }
    
    @After
    public void close() {
       logger.info("Stopping Proxy and Closing WebDriver");
       proxy.stop();
       webDriver.quit();
    }
}
