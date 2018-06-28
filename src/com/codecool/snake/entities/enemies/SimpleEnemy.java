package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

// a simple enemy TODO make better ones.
public class SimpleEnemy extends GameEntity implements Animatable, Interactable {

    Point2D heading;
    private static final int damage = 10;
    static final int SPEED = 1;
    final Random rnd = new Random();

    double x, y;

    public SimpleEnemy(Pane pane) {
        super(pane);

        setImage(Globals.simpleEnemy);
        pane.getChildren().add(this);

        int speed = 1;
        Random rnd = new Random();

        setSafeCoords(rnd);

        setX(x);
        setY(y);

        double direction = rnd.nextDouble() * 360;
        setRotate(direction);
        heading = Utils.directionToVector(direction, SPEED);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        destroy();
    }

    @Override
    public String getMessage() {
        return "10 damage";
    }

    public void setSafeCoords(Random rnd){
        boolean closeToPlayer = true;
        double randX = 0;
        double randY = 0;

        while (closeToPlayer){
            randX = rnd.nextDouble() * Globals.WINDOW_WIDTH;
            randY = rnd.nextDouble() * Globals.WINDOW_HEIGHT;

            double distance = 100.0;
            System.out.println(Globals.gameObjects.size());
            for (GameEntity e : Globals.gameObjects){
                System.out.println(e);
                if (e instanceof SnakeHead){
                    System.out.println("HAS SNAKE HEAD");
                    double newDistance = Math.hypot(randX-((SnakeHead) e).x, randY-((SnakeHead) e).y);
                    if (distance > newDistance){
                        distance = newDistance;
                    }
                    System.out.println("SZÁMÍTÁS: " + newDistance);

                } else if (e instanceof SnakeBody) {
                    System.out.println("HAS SNAKE BODY");
                    double newDistance = Math.hypot(randX-((SnakeHead) e).x, randY-((SnakeHead) e).y);
                    System.out.println("SZÁMÍTÁS: " + newDistance);
                    if (distance > newDistance){
                        distance = newDistance;
                    }
                }
            }

            if (distance > 30.0){
                System.out.println("DISTANCE  OK:" + distance + " " + randX + " " + randY);
                x = randX;
                y = randY;
                closeToPlayer = false;
            } else {
                System.out.println("DISTANCE  NOTOK:" + distance + " " + randX + " " + randY);
            }
        }
    }
}
