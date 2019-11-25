package game;

import game.domain.Board;
import game.domain.Game;
import game.domain.User;
import game.ui.GameUI;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        User user = new User("Unknown");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Let the game begin");

        int userChoice = 0;
        while (userChoice != 4) {
            printActions();
            userChoice = scanner.nextInt();
            switch (userChoice) {
                case 1:
                    GameUI.main(args);
                    break;
                case 2:
                    System.out.println("What is your name?");
                    String name = scanner.next();
                    user.setUsername(name);
                    break;
                case 3:
                    System.out.println("Please pick a random seed");
                    int seed = scanner.nextInt();
                    Random r = new Random(seed);
                    Board board = new Board(r);
                    Game game = new Game(board, user);
                    System.out.println(game);
                    break;
                case 4:
                    break;
                default:
                    printActions();
            }
        }
    }

    public static void printActions() {
        System.out.println("Available actions:");
        System.out.println("\t1. Open GUI");
        System.out.println("\t2. Set your name");
        System.out.println("\t3. Start game in command line mode");
        System.out.println("\t4. Quit");
    }
}