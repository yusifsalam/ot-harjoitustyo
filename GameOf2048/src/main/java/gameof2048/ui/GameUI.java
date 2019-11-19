package gameof2048.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("2048");
        VBox startPane = new VBox(10);
        Label gameTitle = new Label("Game of 2048");
        Button newGameBtn = new Button("NEW GAME");
        Button highscoresBtn = new Button("HIGHSCORES");
        Button myStatsBtn = new Button("MY STATS");
        startPane.getChildren().addAll(gameTitle, newGameBtn, highscoresBtn, myStatsBtn);

        Scene scene = new Scene(startPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        System.out.println("app is closing now");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
