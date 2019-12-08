# Specification

## Purpose
The application is a variant of the game of 2048 with the primary goal of bringing joy to the end users aka players. As such, the primary purpose for users engaging with the application is to pass time.

## Users
The only user roles planned for the application are *players*. The player can start and end a game, as well as compare his highscore to other users' highscores. 

## User interface
The first version of the application will be interfaced with through the command line. 

After the CLI is functional, a graphical user interface will be implemented. The GUI prototype is shown below
![GUI](https://raw.githubusercontent.com/yusifsalam/ot-harjoitustyo/master/documentation/images/prototype.png)

## Basic features
- start new game of 2048 [done]
- restart game [done]
- view stats
- reset stats
- compare highscores

## Future development
Possible expansion ideas include:
- the PvP mode where two or more players face off
- achievements
- time trial mode

## Implemented features as of 08.12.2019
- basic cli to start a game or change username
- game board's logic
- player can start a game from command line
- current version is slightly different from the conventional 2048 game in that the cells only move by one instead of moving as far as the board allows. 
- user can now start a GUI version of the game 
- user can restart a game from GUI