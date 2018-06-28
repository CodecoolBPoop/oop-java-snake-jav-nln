package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import javafx.scene.layout.Pane;

public class RandomPathEnemy extends SimpleEnemy {

    private int step = 0;

    public RandomPathEnemy(Pane pane) {
        super(pane);
        setImage(Globals.randomPathEnemy);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        if (step % 80 == 0) {
            double direction = Globals.rnd.nextDouble() * 360;
            setRotate(direction);
            heading = Utils.directionToVector(direction, SPEED);
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
        step++;
    }
}
