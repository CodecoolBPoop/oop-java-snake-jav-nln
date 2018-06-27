package com.codecool.snake;

import javafx.fxml.FXML;

import static com.codecool.snake.Main.game;

public class Controller {


    @FXML
    void newGame() {
        game.primaryStage.close();
        Main.initStage(game.primaryStage);
    }

    @FXML
    void exit() {
        System.exit(0);
    }
}
