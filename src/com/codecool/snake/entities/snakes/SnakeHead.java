package com.codecool.snake.entities.snakes;

import com.codecool.snake.Game;
import com.codecool.snake.Main;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Optional;

public class SnakeHead extends GameEntity implements Animatable {

    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int health;
    double dir;
    static int instances = 0;
    public String name = "Snake ";

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

    public void step() {
        dir = getRotate();
        float turnRate = 2;

        boolean boner = Globals.getBoner();
        if (boner) {
            setImage(Globals.snakeHead_v);

            this.setCache(true);
            this.setEffect(new GaussianBlur());

        } else {
            this.setCache(false);
            this.setEffect(null);
            setImage(Globals.snakeHead);
        }
        Game.healthDisplay.setText("HEALTH: " + getHealth());
        dir = getRotate();
        if (Globals.leftKeyDown) {
            dir = dir - turnRate;
        }
        if (Globals.rightKeyDown) {
            dir = dir + turnRate;}
        if (this.name.equals("Snake 1")) {
            Game.healthDisplay.setText(name + " HEALTH: " + getHealth());
        }
        if (this.name.equals("Snake 2")) {
            Game.healthDisplay2.setText(name + " HEALTH: " + getHealth());
        }


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

                final Image titleScreen = new Image("button_snake.png"); //title screen image
                final Image playButton = new Image("button_new-game.png"); //the play button image
                final Image scoreButton = new Image("button_exit.png"); //the score button image

                final ImageView flashScreen_node = new ImageView();
                flashScreen_node.setImage(titleScreen); //set the image of the title screen

                final Button play_button  = new Button();
                final ImageView play_button_node = new ImageView();

                final Button score_button = new Button();
                final ImageView score_button_node = new ImageView();

                play_button_node.setImage(playButton); //set the image of the play button
                score_button_node.setImage(scoreButton); //set the image of the score button

                play_button.setGraphic(play_button_node);
                play_button.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY))); //this is to make the button background transparent

                score_button.setGraphic(score_button_node);
                score_button.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

                /*
                 * create the container of those buttons
                 */

                Stage theStage = new Stage();
                final VBox buttonContainer = new VBox(20);
                buttonContainer.setAlignment(Pos.TOP_CENTER);
                Insets buttonContainerPadding = new Insets(400, 1, 100, 1);
                buttonContainer.setPadding(buttonContainerPadding);
                buttonContainer.getChildren().addAll(play_button, score_button);

                theStage.setTitle( "The Mighty Rock!" );
                theStage.getIcons().add(titleScreen); //stage icon

                StackPane root = new StackPane();
                Text text = new Text();
                text.setX(100);
                text.setY(100);
                text.setText("YOUR SCORE: ");
                root.getChildren().addAll(flashScreen_node, buttonContainer);
                root.getChildren().add(new Text(100,50,"HIGH SCORE"));//add the title screen and button container to the stackpane
                Scene theScene = new Scene(root, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);
                theStage.setScene( theScene );
                theStage.show();

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
