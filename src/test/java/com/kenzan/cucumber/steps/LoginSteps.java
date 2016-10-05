package com.kenzan.cucumber.steps;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import org.junit.runner.RunWith;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.kenzan.cucumber.CucumberApplication;
import com.kenzan.cucumber.page.HomePage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
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
        assertThat("Login Failed", loggedInURL, equalTo(homePage.getCurrentURL()));
    }

    @And("^I can see My name$")
    public void validateMyName() throws Throwable {
        assertThat("Logged In Username does not match", name, equalTo(homePage.getLoggedInUsername()));
        
        //Trying out the Request Filter for one single test case
        BrowserMobProxy proxy = new BrowserMobProxyServer();
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
        
        WebDriver webDriver;
        webDriver = new ChromeDriver(capabilities);
        webDriver.get("https://linkedin.com");
        proxy.stop();
        webDriver.quit();
    }

}
