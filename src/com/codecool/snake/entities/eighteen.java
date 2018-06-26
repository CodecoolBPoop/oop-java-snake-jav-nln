package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class eighteen extends GameEntity {

    public eighteen(Pane pane) {
        super(pane);
        setImage(Globals.eighteen);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(ThreadLocalRandom.current().nextLong(25, (long) Globals.WINDOW_WIDTH - 25));
        setY(ThreadLocalRandom.current().nextLong(25, (long) Globals.WINDOW_HEIGHT - 25));
    }

}
