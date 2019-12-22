# Testing documentation
The application is a JavaFX application wrapped around Spring Boot for data persistence. As a result, testing is not as straight-forward. In this instance, the testing consists of unit and integration tests via JUnit, as well as manual system tests. 

## Unit and integration tests
### Coverage
Test coverage is checked with Jacoco. For the latest coverage report you can check the Codecov report [here](https://codecov.io/gh/yusifsalam/ot-harjoitustyo)

## Unit tests
Unit tests are split to two - testing of Game logic and testing of DAO. 
Game logic tests are designed to test that user actions generate correct responses from the application. DAO tests ensure that the database communication, reading and writing are working as intended. 

## System tests
CircleCI build is set up for the application. You can view the CircleCI statuses [here](https://circleci.com/gh/yusifsalam/ot-harjoitustyo). There are no other automatic system tests shipped with the application. 
### Installation
Application has been tested on Mac OS (10.14.6) and Linux (Archlinux and Helsinki University's Cubbli distribution). 
### Functionality
All of the basic features given in the [specification](https://github.com/yusifsalam/ot-harjoitustyo/blob/master/documentation/specification.md) pass the system tests, as they seem to be working as intended.  

# Known issues
Code Climate is set up to check for code smells and other issues. The report can be found [here](https://codeclimate.com/github/yusifsalam/ot-harjoitustyo)
- Game board logic seems to be faulty, and from time to time the games does not end, even though there are no possible moves left. When this happens, it is best to reset the game by pressing the `R` key. 
- After the game is over, if the player goes back to main menu and then back to the game, the screen is black. The game-over screen can be brought back up with the `ESC` key. 