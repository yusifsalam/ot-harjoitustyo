# Architecture description

## Structure
![structure](https://raw.githubusercontent.com/yusifsalam/ot-harjoitustyo/master/documentation/images/package.png)

The application is split to three packages - ui, domain, and dao. 

DAO is responsibly for persistence and communication with the database, domain contains the entities and application logic, while ui contains the JavaFX application that serves the graphical user interface.

## User interface
There is a total of 6 different views, and they are: 
- main application menu
- main game view
- game over view
- highscores view
- my stats view
- change username/login view

Each view is implemented as a JavaFX Scene. Switching between the views simply redraws the corresponding scene. 

Game scene is set up to listen to keyboard events. After each keyboard press, the GUI sends a request to the game logic object, which then returns if the move is possible and whether there are any possible moves left. If the game is possible to continue, the board is first updated on the logic level, and then the game scene is redrawn to represent the newly updated board. 

## Logic
The top level object is the User. Each time the application is started, a new User object is created. If the user logs in to his previous username (via changing the username to an existing one), the user object is loaded from the database, while the previous user object is eventually garbage collected. 

Game object is a wrapper for the logic for a single game and contains a Board object. Game object has methods for checking if the game has ended, whether the game has been won and method for interfacing with the board object.

Board object is the logic for a single game board and contains a 4x4 grid of cells. This object has methods for initializing the starting board, checking if the board can be moved in a specific direction, as well as logic for randomly spawning a new cell between each move.

Cell object is an individual cell inside the board. Cell only contains its value, which is an integer that is 0 or a power of 2. 


## Database
!NB actual implementation is different from the diagram below.
![db](https://raw.githubusercontent.com/yusifsalam/ot-harjoitustyo/master/documentation/images/db.png)

Spring Boot was used for persistence. User class is the only entity used for persistence. Each user entry contains the following:
- Id: Long
- username: String
- highscore: Integer
- gamesWon: Integer
- history: String

History is written in the format "[result][score]", where [result] is either "W" for win or "L" for loss and [score] is the game score integer. The entries as separated with semicolons. For example history could look like this: *L160;W10540;L372*.

Ideally, history would be stored as a separate Game table in the database. However, due to technical issues with H2 and Hibernate, this was not implemented. While current solution is working and the maximum length of a String (2<sup>31</sup>-1) in Java is unlikely to be reached, each new entry makes the statistics calculation more resource intensive. 