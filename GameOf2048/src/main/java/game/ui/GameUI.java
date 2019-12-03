package game.ui;

import game.domain.Board;
import game.domain.Cell;
import game.domain.Game;
import game.domain.User;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Scanner;

public class GameUI extends Application {

    private Scene gameScene;
    private Game game;
    private User user;


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("2048");
        primaryStage.setResizable(false);
        VBox startPane = new VBox(10);
        Label gameTitle = new Label("Game of 2048");
        Button newGameBtn = new Button("NEW GAME");

        newGameBtn.setOnAction(e->{
            primaryStage.setScene(gameScene);
        });
        Button highscoresBtn = new Button("HIGHSCORES");
        Button myStatsBtn = new Button("MY STATS");
        startPane.getChildren().addAll(gameTitle, newGameBtn, highscoresBtn, myStatsBtn);

        Scene scene = new Scene(startPane, 450, 450);
        user = new User("test");
        game = new Game(new Board(new Random()), user, new Scanner(System.in));
        primaryStage.setScene(scene);
        primaryStage.show();


        VBox gamePane = new VBox(10);
        BorderPane gameInfo = new BorderPane();
        gameInfo.setLeft(new Label("Username: " + user.getUsername()));
        gameInfo.setRight(new Label("Score: "+game.getScore()));
        GridPane gameGrid = new GridPane();
        int index = 0;
        for (Cell cell: game.getBoard().getCells()) {
            StackPane square = new StackPane();
            Label cellValue = new Label(""+cell.getValue());
            Rectangle rect = new Rectangle();
            rect.setWidth(100);
            rect.setHeight(100);
            Color rectFill;
            switch (cell.getValue()) {
                case 2:
                    rectFill = Color.BLANCHEDALMOND;
                    break;
                case 4:
                    rectFill = Color.BISQUE;
                    break;
                case 8:
                    rectFill = Color.BURLYWOOD;
                    break;
                case 16:
                    rectFill = Color.DARKKHAKI;
                    break;
                default:
                    rectFill = Color.BEIGE;
                    break;
            }
            rect.setFill(rectFill);
            square.getChildren().addAll(rect, cellValue);
            GridPane.setRowIndex(square, index / 4);
            GridPane.setColumnIndex(square, index % 4);
            GridPane.setMargin(square, new Insets(5,5,5,5));
            gameGrid.getChildren().add(square);
            index++;
        }
        gamePane.getChildren().addAll(gameInfo, gameGrid);
        gameScene = new Scene(gamePane, 440, 500);

    }

    @Override
    public void stop() {
        System.out.println("app is closing now");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
