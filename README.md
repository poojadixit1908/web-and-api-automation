# Web and API testing

This project solves the QA challenge for web and api testing. 

### Frameworks and patterns used
 * Selenium Java webdriver
 * Cucumber BDD
 * Page objects pattern for creating java page classes corresponding to website page
 * maven as compile, packaging and build tool
 * Reporting via maven surefire plugin
 * Jackson for De-serialization of JSON responses to Java
 
### OS and IDE used
This project is built and tested on Mac OS Catalina and IntelliJ Idea IDE
 
### Project Dependencies 
 * Java 8
 * Maven 3.6.3
 * Selenium 3.141.59
 * Cucumber 1.2.5
 * RestAssured 4.3.0
 * maven surefire plugin 2.22.2
 * webdrivermanager - 4.1.0 (https://github.com/bonigarcia/webdrivermanager)

### Cloning the project 
```markdown
git clone https://github.com/poojadixit1908/web-and-api-automation
```

### Compiling and running the project
#### API testing 
mvn clean install -Denv=prod -Dcucumber.options="--tags '@HappyHolidayMaker'"
#### Web automation 
```markdown
mvn clean install -Denv=prod -Dbrowser=chrome-Dcucumber.options="--tags '@websmoke'"
```
### System parameters
 * `env` - can be specified in the maven commandline as -Denv=prod
 * `browser` - supports chrome and firefox browsers aand can be specified with maven command line as __-Dbrowser=chrome__ or __-Dbrowser=firefox__
 
##Note
The http://openweathermap.org/forecast16 required 30$ subscription but i found the forcast api 
for 5 days is free which has almost similar responses. So forcast5 has been used for doing api testin
