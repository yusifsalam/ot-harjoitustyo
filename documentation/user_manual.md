# User manual
Download the release jar file and run it with `java -jar release_name.jar`. Note that the app was written with Java 11 in mind, so it may not work with earlier versions! 

After you run the jar file, CLI is displayed, type "1" and press enter (this will open the GUI version of the game). 

!NB this manual is inteded for the GUI version of the game.

# Using the app
The starting screen should look something like this

![main_menu](https://raw.githubusercontent.com/yusifsalam/ot-harjoitustyo/master/documentation/images/main_menu.png)

## Starting a new game
From the main screen, click the *START GAME* button. You can now see a 4x4 board. Your goal is to merge the cells until you get to 2048


## Changing username / logging in
By default, when you start the application, your username is set to "Unknown". This screen allows you to change your username. If the username is in the database, this acts as a login, otherwise the "Unknown" username is changed to user input. 

From the main screen, click the *SET USERNAME* button. Enter your new username in the text field and press *SUBMIT*. If everything goes smoothly, you should know see a message that says _Success_. 

## Viewing highscores
From the main screen, click the *HIGHSCORES* button. You can now see the best 5 highscores. Note that the list contains each user's best scores, not all best scores.
![highscore](https://raw.githubusercontent.com/yusifsalam/ot-harjoitustyo/master/documentation/images/highscores.png)

## Viewing stats
From the main screen, click the *MY STATS* button. You can now see your game statistics. 
![my_stats](https://raw.githubusercontent.com/yusifsalam/ot-harjoitustyo/master/documentation/images/my_stats.png)

## Controls and user interface
Game controls are very simple - WASD scheme is used. 
- `W` to move cells up
- `S` to move cells down
- `A` to move cells left
- `D` to move cells right
- `R` to reset game
- `ESC` to go back to main menu

![game_board](https://raw.githubusercontent.com/yusifsalam/ot-harjoitustyo/master/documentation/images/game_board.png)

At the top of the window you will see your username on the left side and your score on the right side.

## Starting over
After your game has come to a close, you'll be shown a similar screen to this
![game_over](https://raw.githubusercontent.com/yusifsalam/ot-harjoitustyo/master/documentation/images/game_over.png)

You can now start over from a fresh board or get back to the main menu (for example to check your stats). 

It is also possible to restart mid-game. To do this, press the `R` key. Note that there is no confirmation after pressing the key and your progress will be lost irretrievably. 
