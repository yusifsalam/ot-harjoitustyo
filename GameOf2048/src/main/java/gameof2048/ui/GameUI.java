package gameof2048.ui;

import javafx.application.Application;
import javafx.stage.Stage;

public class GameUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("2048");
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
