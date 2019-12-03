# Ohjelmistotekniikka, project work

The goal of the project is to code, document and test an application. The application is an implementation of the game of 2048.

### Technology
The application is a maven project written in Java. JavaFX is used for the user interface and JUnit test suite for the testing. 
### Documentation
[Specification](https://github.com/yusifsalam/ot-harjoitustyo/blob/master/documentation/specification.md)

[Architecture description](https://github.com/yusifsalam/ot-harjoitustyo/blob/master/documentation/architecture.md)

[Time book](https://github.com/yusifsalam/ot-harjoitustyo/blob/master/documentation/work_hours.md)

### Releases
[Week 5](https://github.com/yusifsalam/ot-harjoitustyo/releases/tag/1.0)

## Available commands
### Starting the application
```
mvn compile exec:java -Dexec.mainClass=game.Main
```
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
The command will generate GameOf2048-1.0-SNAPSHOT.jar file inside target folder. 
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
