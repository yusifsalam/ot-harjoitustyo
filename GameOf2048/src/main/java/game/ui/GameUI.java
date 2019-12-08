package game.ui;

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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Scanner;

public class GameUI extends Application {

    private Scene gameScene;
    private Scene mainMenuScene;
    private Game game;
    private User user;
    private VBox gamePane;
    private Stage stage;


    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.setTitle("2048");
        primaryStage.setResizable(false);
        VBox startPane = new VBox(10);
        Label gameTitle = new Label("Game of 2048");
        Button newGameBtn = new Button("START GAME");

        newGameBtn.setOnAction(e->{
            primaryStage.setScene(gameScene);
        });
        Button highscoresBtn = new Button("HIGHSCORES");
        Button myStatsBtn = new Button("MY STATS");
        startPane.getChildren().addAll(gameTitle, newGameBtn, highscoresBtn, myStatsBtn);

        mainMenuScene = new Scene(startPane, 450, 450);
        user = new User("test");

        primaryStage.setScene(mainMenuScene);
        primaryStage.show();
        startGame();

    }

    void startGame() {
        if (gamePane != null) gamePane.getChildren().clear();
        else {
            gamePane = new VBox(10);
            gameScene = new Scene(gamePane, 440, 500);
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
                    }
                    if (game.nextMove()) {
                        drawBoard();
                        System.out.println(game.getBoard());
                    }
                    else {
                        drawGameOver();
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
        gameInfo.setLeft(new Label("Username: " + user.getUsername()));
        gameInfo.setRight(new Label("Score: " + game.getScore()));
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
        gamePane.getChildren().clear();
        Font f = new Font(30);
        Text t1 = new Text();
        t1.setFont(f);
        t1.setText("GAME OVER!");
        Text t2 = new Text();
        t2.setFont(f);
        t2.setText("YOUR SCORE WAS: " + game.getScore());

        Button startOver = new Button("Start over");
        startOver.setOnAction(click -> startGame());

        Button backToMenu = new Button("Back to main menu");
        backToMenu.setOnAction(click -> {
            stage.setScene(mainMenuScene);
            VBox roo = (VBox) mainMenuScene.getRoot();
            Button btn = (Button) roo.getChildren().get(1);
            btn.setText("BACK TO GAME");
        });
        gamePane.getChildren().addAll(t1, t2, startOver, backToMenu);
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
    }

    public static void main(String[] args) {
        launch(args);
    }
}
