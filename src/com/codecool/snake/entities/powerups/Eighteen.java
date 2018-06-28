package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import javafx.scene.layout.Pane;

import java.util.Random;

public class Eighteen extends GameEntity {

    public Eighteen(Pane pane) {
        super(pane);
        setImage(Globals.eighteen);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(650);
        setY(350);
    }
}
