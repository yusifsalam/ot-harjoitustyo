# Ohjelmistotekniikka, project work

The goal of the project is to code, document and test an application. The application is an implementation of the game of 2048.

### Technology
The application is a maven project written in Java. JavaFX is used for the user interface and JUnit test suite for the testing. 
### Documentation
[Specification](https://github.com/yusifsalam/ot-harjoitustyo/blob/master/documentation/specification.md)

[Time book](https://github.com/yusifsalam/ot-harjoitustyo/blob/master/documentation/work_hours.md)

## Available commands
### Starting the application
```
    mvn compile exec:java -Dexec.mainClass=gameof2048.Main
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
mvn jxr:jxr checkstyle:checkstyle
``` 
Checkstyle report can be found from **target/site/checkstyle.html**