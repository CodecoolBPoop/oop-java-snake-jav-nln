package com.codecool.snake;

import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.powerups.SuperPowerup;
import com.codecool.snake.entities.powerups.ViagraPowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Game extends Pane {

    public static Text healthDisplay = new Text(50,50,"SNAKE 1 HEALTH: ");
    public static Text healthDisplay2 = new Text(50,75,"SNAKE 2 HEALTH: ");
    public static Button restartButton = new Button("something");
    private Stage primaryStage;

    public Game(Stage primaryStage) {
        new SnakeHead(this, 500, 500);
        new SnakeHead(this, 300, 300);

        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);

        new SuperPowerup(this);
        new SuperPowerup(this);
        new ViagraPowerup(this);

        restart();


        this.primaryStage = primaryStage;
    }

    public void restart() {
        Image image = new Image("restart.png");
        ImageView imageView = new ImageView(image);
        Button restartButton = new Button("", imageView);
        restartButton.setLayoutX(Globals.WINDOW_WIDTH - 60);
        restartButton.setLayoutY(10);
        this.getChildren().add(restartButton);
        restartButton.setOnAction(ActionEvent -> {
            primaryStage.close();
            Main.initStage(primaryStage);
        });
    }

    public void start() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:
                    Globals.leftKeyDown = true;
                    break;
                case RIGHT:
                    Globals.rightKeyDown = true;
                    break;
                case A:
                    Globals.aKeyDown = true;
                    break;
                case D:
                    Globals.dKeyDown = true;
                    break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:
                    Globals.leftKeyDown = false;
                    break;
                case RIGHT:
                    Globals.rightKeyDown = false;
                    break;
                case A:
                    Globals.aKeyDown = false;
                    break;
                case D:
                    Globals.dKeyDown = false;
                    break;
            }
        });
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }
}
