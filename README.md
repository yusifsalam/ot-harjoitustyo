# Ohjelmistotekniikka, project work

[![CircleCI](https://circleci.com/gh/yusifsalam/ot-harjoitustyo.svg?style=svg)](https://circleci.com/gh/yusifsalam/ot-harjoitustyo)
[![codecov](https://codecov.io/gh/yusifsalam/ot-harjoitustyo/branch/master/graph/badge.svg)](https://codecov.io/gh/yusifsalam/ot-harjoitustyo)
[![Maintainability](https://api.codeclimate.com/v1/badges/49eb6c2c861d9a00edb8/maintainability)](https://codeclimate.com/github/yusifsalam/ot-harjoitustyo/maintainability)



The goal of the project is to code, document and test an application. The application is an implementation of the game of 2048.

![game_screenshot](https://raw.githubusercontent.com/yusifsalam/ot-harjoitustyo/master/documentation/images/screenshot.png)

### Technology
The application is a maven project written in Java. JavaFX is used for the user interface, Sprint Boot with H2 for data persistence and JUnit test suite for the testing. 
### Documentation
[User manual](https://github.com/yusifsalam/ot-harjoitustyo/blob/master/documentation/user_manual.md)

[Specification](https://github.com/yusifsalam/ot-harjoitustyo/blob/master/documentation/specification.md)

[Architecture description](https://github.com/yusifsalam/ot-harjoitustyo/blob/master/documentation/architecture.md)

[Testing documentation](https://github.com/yusifsalam/ot-harjoitustyo/blob/master/documentation/testing.md)

[Time book](https://github.com/yusifsalam/ot-harjoitustyo/blob/master/documentation/work_hours.md)

### Releases
[Week 5](https://github.com/yusifsalam/ot-harjoitustyo/releases/tag/1.0)

[Week 6](https://github.com/yusifsalam/ot-harjoitustyo/releases/tag/1.1)

[Final](https://github.com/yusifsalam/ot-harjoitustyo/releases/tag/final)

## Available commands
After you have cloned the repository, change your working directory to the game sub-directory `cd GameOf2048/`, then you can run the following commands. 
### Starting the application
You can either run the packaged jar file, create your own packaged jar file and run it or just open the project in your favorite IDE (IntelliJ recommended) and press click build/run. 
### Testing
Tests can be run with the command
```
mvn test
```
Test coverage can be generated with the following command
```
mvn jacoco:report
```
Jacoco report (html page) can be found from **target/site/jacoco/index.html.** 

Jacoco needs test results to generate the report, so it is best to chain two commands to generate the jacoco coverage report
```
mvn test jacoco:report
``` 
### Generating a jar file
You can generate a jar file with the command 
```
mvn package
```
The command will generate GameOf2048-1.0-SNAPSHOT.jar file inside target folder. You can run the jar file with 
```
java -jar target/GameOf2048-1.0-SNAPSHOT.jar
```
### JavaDoc
You can generate a custom JavaDoc using
```
mvn javadoc:javadoc
```
The generated HTML file is located at **target/site/apidocs/index.html**
### Checkstyle
File checkstyle.xml defines the rules for checkstyle.
```
mvn checkstyle:checkstyle
``` 
Checkstyle report can be found from **target/site/checkstyle.html**.

By default the checkstyle does not have any styling. To get the styling for the checkstyle report, run 
```
mvn site
```
