package game;

import game.dao.UserService;
import game.domain.Board;
import game.domain.Game;
import game.domain.User;
import game.dao.UserRepository;
import game.ui.GameUI;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Random;
import java.util.Scanner;


@SpringBootApplication
public class Main  {

    @Autowired
    private UserRepository ur;

    @Autowired
    private UserService us;

    private User user;

    public static void main(String[] args) {
//        SpringApplication.run(Main.class, args);
        Application.launch(GameUI.class, args);
    }

//    @Override
//    public void run(String... args) {
//        user = us.create("new");
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Let the game begin");
//
//        int userChoice = 0;
//        while (userChoice != 4) {
//            printActions();
//            userChoice = scanner.nextInt();
//            switch (userChoice) {
//                case 1:
//                    GameUI.main(args);
//                    break;
//                case 2:
//                    System.out.println("What is your name?");
//                    String name = scanner.next();
//                    user = us.updateName(user.getUsername(), name);
//                    break;
//                case 3:
//                    System.out.println("Please pick a random seed");
//                    int seed = scanner.nextInt();
//                    Random r = new Random(seed);
//                    Board board = new Board(r);
//                    Game game = new Game(board, user, scanner);
//                    System.out.println(game.playGame());
//                    break;
//                case 4:
//                    break;
//                default:
//                    printActions();
//            }
//        }
//    }

    public static void printActions() {
        System.out.println("Available actions:");
        System.out.println("\t1. Open GUI");
        System.out.println("\t2. Set your name");
        System.out.println("\t3. Start game in command line mode");
        System.out.println("\t4. Quit");
    }
}
