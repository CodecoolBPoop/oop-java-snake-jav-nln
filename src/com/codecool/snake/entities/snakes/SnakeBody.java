package com.codecool.snake.entities.snakes;

import com.codecool.snake.Utils;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SnakeBody extends GameEntity implements Animatable, Interactable {

    private final GameEntity parent;
    private final Queue<Vec2d> history = new LinkedList<>();
    private static final int historySize = 10;

    public SnakeBody(Pane pane, GameEntity parent, String name) {
        super(pane);
        this.parent = parent;
        setImage(Globals.snakeBody);
        super.name = name;

        // place it visually below the current tail
        List<Node> children = pane.getChildren();
        children.add(children.indexOf(parent), this);

        double xc = parent.getX();
        double yc = parent.getY();
        setX(xc);
        setY(yc);
        for (int i = 0; i < historySize; i++) {
            history.add(new Vec2d(xc, yc));
        }
    }

    public void step() {
        Vec2d pos = history.poll(); // remove the oldest item from the history

        if (Globals.getBoner()) {
            this.setCache(true);
            this.setEffect(new GaussianBlur());
            setImage(Globals.snakeBody_v);
            for (GameEntity e : Globals.gameObjects) {
                if (e instanceof SnakeHead) {
                    System.out.println("DIR:"+((SnakeHead) e).dir);
                    Point2D heading = Utils.directionToVector(((SnakeHead) e).dir, -20);
                    double x = parent.getX() + heading.getX();
                    double y = parent.getY() + heading.getY();
                    history.add(new Vec2d(x, y));
                    setX(x);
                    setY(y);
                    break;
                }

            }
        } else {
            this.setCache(false);
            this.setEffect(null);
            setImage(Globals.snakeBody);
            setX(pos.x);
            setY(pos.y);
            history.add(new Vec2d(parent.getX(), parent.getY())); // add the parent's current position to the beginning of the history
        }
    }


    @Override
    public void apply(SnakeHead snakeHead) {
        for (GameEntity entity : Globals.getGameObjects()) {
            if(entity.name.equals(snakeHead.name)) {
                entity.destroy();
            }
        }
    }

    @Override
    public String getMessage() {
        return "OUCH!";
    }
}
