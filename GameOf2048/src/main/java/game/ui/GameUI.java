package game.ui;

import game.Main;
import game.dao.UserService;
import game.domain.Board;
import game.domain.Cell;
import game.domain.Game;
import game.domain.User;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameUI extends Application {

    private Scene gameScene;
    private Scene gameOverScene;
    private Scene mainMenuScene;
    private Scene changeUsernameScene;
    private Scene myStatsScene;
    private Scene highscoresScene;
    private Game game;
    private User user;
    private VBox gamePane;
    private Stage stage;
    private ConfigurableApplicationContext applicationContext;
    private UserService userService;
    private String style = "/stylesheet.css";



    @Override
    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);

        this.applicationContext = new SpringApplicationBuilder()
                .sources(Main.class)
                .run(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        userService = applicationContext.getBean(UserService.class);
        user = userService.create("Unknown_" + userService.getAllSize());
        stage = primaryStage;

        primaryStage.setTitle("2048");
        primaryStage.setResizable(false);
        VBox startPane = new VBox(10);
        Label gameTitle = new Label("Game of 2048");
        Button newGameBtn = new Button("START GAME");

        newGameBtn.setOnAction(e->{
            primaryStage.setScene(gameScene);
        });
        Button setUsername = new Button("SET USERNAME");
        setUsername.setOnAction(e -> {
            drawChangeUsername();
            primaryStage.setScene(changeUsernameScene);
        });
        Button highscoresBtn = new Button("HIGHSCORES");
        highscoresBtn.setOnAction(e -> {
            drawHighscores();
            stage.setScene(highscoresScene);
        });
        Button myStatsBtn = new Button("MY STATS");
        myStatsBtn.setOnAction(e -> {
            drawMyStats();
            stage.setScene(myStatsScene);
        });
        startPane.getChildren().addAll(gameTitle, newGameBtn, setUsername, highscoresBtn, myStatsBtn);

        mainMenuScene = new Scene(startPane, 450, 450);
        mainMenuScene.getStylesheets().add(style);

        primaryStage.setScene(mainMenuScene);
        primaryStage.show();
        startGame();

    }

    void startGame() {
        if (gamePane != null) gamePane.getChildren().clear();
        else {
            gamePane = new VBox(10);
            gameScene = new Scene(gamePane, 440, 500);
            gameScene.getStylesheets().add(style);
            gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    switch (event.getCode()) {
                        case W:
                            game.moveBoardUp();
                            break;
                        case S:
                            game.moveBoardDown();
                            break;
                        case A:
                            game.moveBoardLeft();
                            break;
                        case D:
                            game.moveBoardRight();
                            break;
                        case R:
                            game = new Game(new Board(new Random()), user, new Scanner(System.in));
                            drawBoard();
                            break;
                        case ESCAPE:
                            stage.setScene(mainMenuScene);
                            break;
                    }
                    if (game.nextMove()) {
                        drawBoard();
                        System.out.println(game.getBoard());
                    }
                    else {
                        boolean gameWon = game.gameWon();
                        user = userService.addGame(user.getId(), game.getScore(), gameWon);
                        drawGameOver();
                        stage.setScene(gameOverScene);
                    }

                }
            });
        }
        game = new Game(new Board(new Random()), user, new Scanner(System.in));
        drawBoard();

    }

    private void drawBoard() {
        gamePane.getChildren().clear();
        BorderPane gameInfo = new BorderPane();
        Font f = new Font(15);
        Text usernameTxt = new Text();
        usernameTxt.setText(user.getUsername());
        usernameTxt.setFill(Color.WHITE);
        usernameTxt.setFont(f);

        Text gameScoreTxt = new Text();
        gameScoreTxt.setText("Score: " + game.getScore());
        gameScoreTxt.setFill(Color.WHITE);
        gameScoreTxt.setFont(f);
        gameInfo.setLeft(usernameTxt);
        gameInfo.setRight(gameScoreTxt);
        GridPane gameGrid = new GridPane();
        int index = 0;
        for (Cell cell: game.getBoard().getCells()) {
            StackPane square = new StackPane();
            Label cellValue = new Label(""+cell.getValue());
            cellValue.setFont(new Font("Roboto Mono", 20));
            Rectangle rect = new Rectangle();
            rect.setWidth(100);
            rect.setHeight(100);
            rect.setFill(getCellFillColor(cell.getValue()));
            square.getChildren().addAll(rect, cellValue);
            GridPane.setRowIndex(square, index / 4);
            GridPane.setColumnIndex(square, index % 4);
            GridPane.setMargin(square, new Insets(5,5,5,5));
            gameGrid.getChildren().add(square);
            index++;
        }
        gamePane.getChildren().addAll(gameInfo, gameGrid);
    }

    private void drawGameOver() {
        VBox vb = new VBox(20);
        gameOverScene = new Scene(vb, 440, 500);
        gameOverScene.getStylesheets().add(style);
        gamePane.getChildren().clear();
        Font f = new Font(30);
        Text t1 = new Text();
        t1.setFont(f);
        t1.setText("GAME OVER!");
        t1.setFill(Color.WHITE);
        Text t2 = new Text();
        t2.setFont(f);
        t2.setText("YOUR SCORE WAS: " + game.getScore());
        t2.setFill(Color.WHITE);

        Button startOver = new Button("Start over");
        startOver.setOnAction(click -> {
            stage.setScene(gameScene);
            startGame();
        });

        Button backToMenu = new Button("Back to main menu");
        backToMenu.setOnAction(click -> {
            stage.setScene(mainMenuScene);
            VBox roo = (VBox) mainMenuScene.getRoot();
            Button btn = (Button) roo.getChildren().get(1);
            btn.setText("BACK TO GAME");
        });
        vb.getChildren().addAll(t1, t2, startOver, backToMenu);
    }

    private void drawChangeUsername() {
        VBox vb = new VBox();
        vb.setSpacing(20);
        changeUsernameScene = new Scene(vb, 440, 500);
        changeUsernameScene.getStylesheets().addAll(style);
        Label label = new Label("Change username");
        Label statusLabel = new Label();
        TextField textField = new TextField();
        HBox hb = new HBox();
        Button submitBtn = new Button("SUBMIT");
        submitBtn.setOnAction(e -> {
            if (textField.getText() != null && !textField.getText().isEmpty()) {
                user = userService.updateName(user.getId(), textField.getText());
                statusLabel.setText("Success");
            } else {
                statusLabel.setText("Try again...");
            }
        });
        Button goBackBtn = new Button("BACK TO MAIN MENU");
        goBackBtn.setOnAction(e -> {
            stage.setScene(mainMenuScene);
        });
        hb.getChildren().addAll(submitBtn, goBackBtn);
        vb.getChildren().addAll(label, textField, statusLabel, hb);
    }

    private void drawMyStats() {
        VBox vb = new VBox();
        vb.setSpacing(20);
        myStatsScene = new Scene(vb, 440, 500);
        myStatsScene.getStylesheets().add(style);
        Label label = new Label("My stats");
        Button goBackBtn = new Button("BACK TO MAIN MENU");
        goBackBtn.setOnAction(e -> {
            stage.setScene(mainMenuScene);
        });
        int gameCount = user.getHistory().split(";").length;
        Text gamesPlayed = new Text("Games played: " + gameCount);
        Text gamesWon = new Text("Games won: " + user.getGamesWon());
        int winning = gameCount != 0 ? user.getGamesWon()/gameCount*100 : 0;
        Text winningShare = new Text("Winning share: " + winning + "%");
        Text userHighscore = new Text("Highscore: " + user.getHighscore());
        Font f = new Font(15);
        gamesPlayed.setFont(f);
        gamesWon.setFont(f);
        winningShare.setFont(f);
        userHighscore.setFont(f);
        gamesPlayed.setFill(Color.WHITE);
        gamesWon.setFill(Color.WHITE);
        winningShare.setFill(Color.WHITE);
        userHighscore.setFill(Color.WHITE);
        vb.getChildren().addAll(label, gamesPlayed, gamesWon, winningShare, userHighscore, goBackBtn);
    }

    private void drawHighscores() {
        VBox vb = new VBox();
        vb.setSpacing(20);
        highscoresScene = new Scene(vb, 440, 500);
        highscoresScene.getStylesheets().add(style);
        Label label = new Label("HIGHSCORES");
        Button goBackBtn = new Button("BACK TO MAIN MENU");
        goBackBtn.setOnAction(e -> {
            stage.setScene(mainMenuScene);
        });
        List<User> leaders = userService.getFiveBest();
        vb.getChildren().add(label);
        int index = 1;
        for (User usr: leaders) {
            if (usr.getHighscore() != 0) {
                Text userTxt = new Text(index + "." + usr.getUsername() + ": " + usr.getHighscore());
                userTxt.setFill(Color.WHITE);
                userTxt.setFont(new Font(15));
                vb.getChildren().add(userTxt);
                index++;
            }
        }
        vb.getChildren().add(goBackBtn);
    }

    private Color getCellFillColor(int value) {
        Color rectFill;
        switch (value) {
            case 2:
                rectFill = Color.web("FFFF00");
                break;
            case 4:
                rectFill = Color.web("FFDD00");
                break;
            case 8:
                rectFill = Color.web("FFBB00");
                break;
            case 16:
                rectFill = Color.web("FF9900");
                break;
            case 32:
                rectFill = Color.web("FF9900");
                break;
            case 64:
                rectFill = Color.web("FF7700");
                break;
            case 128:
                rectFill = Color.web("FF6600");
                break;
            case 256:
                rectFill = Color.web("FF5500");
                break;
            case 512:
                rectFill = Color.web("FF4400");
                break;
            case 1024:
                rectFill = Color.web("FF2200");
                break;
            case 2048:
                rectFill = Color.web("FF1100");
                break;
            default:
                rectFill = Color.BEIGE;
                break;
        }
        return rectFill;
    }

    @Override
    public void stop() {
        System.out.println("app is closing now");
        applicationContext.stop();
    }

    public static void main(String[] args) {
        launch(GameUI.class, args);
    }
}
