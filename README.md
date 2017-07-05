# cucumber-e2e-poc
A POC for E2E Testing in Java using Cucumber 

##Functionality:
###Load LinkedIn Homepage
    * Validate Join Now is Shown
    * Validate Text box for Email, Password is shown along with SignIn Button
    * Validate Footer is shown with configured link captions
###Login With Test Username
    * Validate Login works with valid username and password
    * Validate page contains logged in user's name

##Design:
    * Properties:
        * URL, Credentials, Class/ID are configured in properties
        * Separate properties for each environments
        * Properties can be loaded using maven command line arguments from a http service
    * Page Objects/Components
    * Priority based test scenarios

##Execution:
    * Install Maven:
        http://maven.apache.org/install.html or http://brewformulas.org/Maven
    * Clone the Repo
    * Execute Tests:
        * To Execute all the tests with default properties
            mvn clean test
        * To Execute all the tests using dev properties
            mvn clean test -Dspring.profiles.active=dev
        * To Execute only critical tests
            mvn clean test -Dcucumber.options="--tags @Critical"
        * To Execute only non critical tests
            mvn clean test -Dcucumber.options="--tags ~@Critical"
        * To Execute critical tests using stage properties
            mvn clean test -Dcucumber.options="--tags @Critical" -Dspring.profiles.active=stage
        * To Execute tests with properties loaded from http service
            mvn clean test -Darchaius.configurationSource.additionalUrls=https://raw.githubusercontent.com/kenzanmedia/cucumber-e2e-poc/develop/src/test/resources/application.properties?token=AT5lWdlLeEPZESGOvz768VKttMeihNDGks5YEmc8wA%3D%3D

 ## LICENSE
Copyright 2017 Kenzan, LLC <http://kenzan.com>
 
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
 
    http://www.apache.org/licenses/LICENSE-2.0
 
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.