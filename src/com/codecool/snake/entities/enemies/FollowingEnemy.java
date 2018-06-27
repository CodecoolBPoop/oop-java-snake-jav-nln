package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import javafx.scene.layout.Pane;

public class FollowingEnemy extends SimpleEnemy {

    protected static final int SPEED = 2;

    public FollowingEnemy(Pane pane) {
        super(pane);
        setImage(Globals.followingEnemy);
        double direction = getAngle(Globals.snakeHeads.get(0).getX(),
            Globals.snakeHeads.get(0).getY(), getX(), getY());
        setRotate(direction);
        heading = Utils.directionToVector(direction, SPEED);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
//            destroy();
        }
        double direction = getAngle(Globals.snakeHeads.get(0).getX(),
            Globals.snakeHeads.get(0).getY(), getX(), getY());
        setRotate(direction);
        heading = Utils.directionToVector(direction, SPEED);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    public double getAngle(double targetX, double targetY, double thisX, double thisY) {
        double angle = Math.toDegrees(Math.atan2(targetY - thisY, targetX - thisX));
        if (angle < 0) {
            angle += 360;
        }
        return angle;
    }

}
