package com.codecool.snake;

import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static com.codecool.snake.Game.healthDisplay;

public class Main extends Application {
    public static Game game = new Game();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Snake Game");
        Group root = new Group(game, healthDisplay);
        primaryStage.setScene(new Scene(root, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT));
        primaryStage.show();
        game.start();
    }

}
