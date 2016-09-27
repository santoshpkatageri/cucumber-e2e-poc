# cucumber-e2e-poc
A POC for E2E Testing in Java using Cucumber 

##Functionality:
###Load LinkedIn Homepage
    *Validate Join Now is Shown
    *Validate Text box for Email, Password is shown along with SignIn Button
    *Validate Footer is shown with configured link captions
###Login With Test Username
    *Validate Login works with valid username and password
    *Validate page contains logged in user's name

##Design:
    *URL's, User credentials are picked up from app configs
    *Nice to have:
        *Utility Command/Locators
        *Component based suites
        *Priority based test cases (smoke test annotation)
