package com.codecool.snake.entities.snakes;

import com.codecool.snake.Game;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeHead extends GameEntity implements Animatable {

    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int health;
    static int instances = 0;
    public String name = "Snake ";
    static int score;

    public SnakeHead(Pane pane, int xc, int yc) {
        super(pane);
        setX(xc);
        setY(yc);
        health = 100;
        tail = this;
        setImage(Globals.snakeHead);
        pane.getChildren().add(this);
        addPart(4);
        instances++;
        name = name + String.valueOf(instances);
    }

    public void step() throws IOException {
        if (this.name.equals("Snake 1")) {
            Game.healthDisplay.setText(name + " HEALTH: " + getHealth());
        }
        if (this.name.equals("Snake 2")) {
            Game.healthDisplay2.setText(name + " HEALTH: " + getHealth());
        }

        double dir = getRotate();
        float turnRate = 2;

        if (name.equals("Snake 1")) {
            if (Globals.leftKeyDown) {
                dir = dir - turnRate;
            }
            if (Globals.rightKeyDown) {
                dir = dir + turnRate;
            }
        }

        if (name.equals("Snake 2")) {
            if (Globals.aKeyDown) {
                dir = dir - turnRate;
            }
            if (Globals.dKeyDown) {
                dir = dir + turnRate;
            }
        }

        // set rotation and position
        setRotate(dir);
        float speed = 2;
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                    score+=10;
                }
            }
        }

        // check for game over condition
        if (isOutOfBounds() || health <= 0) {
            System.out.println(name + " DIED!!!");
            if (this.name.equals("Snake 1")) {
                Game.healthDisplay.setText(name + " DIED!!!");
            }
            if (this.name.equals("Snake 2")) {
                Game.healthDisplay2.setText(name + " DIED!!!");
            }
            destroy();
            instances--;
            if (instances==0) {
                Stage stage = new Stage();
                stage.setTitle("All the snakes has been DIED");

                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("anyad.fxml"));

                Text text = new Text();
                text.setY(100);
                text.setX(250);
                text.setText("YOUR SCORE: " + score);

                Group group = new Group();
                group.getChildren().addAll(root, text);

                Scene scene = new Scene(group);

                stage.setScene(scene);
                stage.show();

                Globals.gameLoop.stop();
            }
        }
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail);
            tail = newPart;
        }
    }

    public void changeHealth(int diff) {
        health += diff;
    }

    public int getHealth() {
        return health;
    }

}
