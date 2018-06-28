package com.codecool.snake;

import static com.codecool.snake.Main.game;

import javafx.fxml.FXML;

public class Controller {

    @FXML
    void newGame() {
        Main.initStage(game.primaryStage);
    }

    @FXML
    void exit() {
        System.exit(0);
    }
}
