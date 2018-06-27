package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.Main;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

public class ViagraPowerup extends GameEntity implements Interactable {

    public ViagraPowerup(Pane pane) {
        super(pane);
        setImage(Globals.viagra);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.changeHealth(100);
        snakeHead.addPart(10);
        Globals.setBoner(true);
        Globals.setBonerStartTime();
        Globals.addGameObject(new Eighteen(Main.game));
        destroy();
    }

    @Override
    public String getMessage() {
        return "Got SUPER power-up :)";
    }
}
