package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javafx.scene.image.Image;

// class for holding all static stuff
public class Globals {

    private static boolean boner = false;
    public static double bonerStartTime = 0;

    public static void setBoner(boolean isBoner) {
        if (isBoner) {
            boner = true;
        } else {
            boner = false;
        }
    }

    public static boolean getBoner(){
        return boner;
    }

    public static void setBonerStartTime(){
        bonerStartTime = System.currentTimeMillis();
    }

    public static double getBonerStartTime(){
        return bonerStartTime;
    }

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;

    public static Image eighteen = new Image("18.png");
    public static Image viagra = new Image("viagra.png");
    public static Image snakeHead = new Image("snake_head.png");
    public static Image snakeBody = new Image("snake_body.png");
    public static Image snakeHead_v = new Image("snake_head_pink.png");
    public static Image snakeBody_v = new Image("snake_body_skin.png");
    public static Image simpleEnemy = new Image("simple_enemy.png");
    public static Image randomPathEnemy = new Image("barack.png");
    public static Image powerupBerry = new Image("powerup_berry.png");
    public static Image superPowerUp = new Image("powerup_pill.png");
    //.. put here the other images you want to use



    public static boolean leftKeyDown;
    public static boolean rightKeyDown;
    public static boolean aKeyDown;
    public static boolean dKeyDown;
    public static List<GameEntity> gameObjects;
    public static List<GameEntity> newGameObjects; // Holds game objects crated in this frame.
    public static List<GameEntity> oldGameObjects; // Holds game objects that will be destroyed this frame.
    public static List<GameEntity> snakeHeads;
    public static GameLoop gameLoop;
    public static Random rnd = new Random();

    static {
        gameObjects = new LinkedList<>();
        newGameObjects = new LinkedList<>();
        oldGameObjects = new LinkedList<>();
        snakeHeads = new LinkedList<>();
    }

    public static void addGameObject(GameEntity toAdd) {
        newGameObjects.add(toAdd);
    }

    public static void addSnakeHead(GameEntity snakeHead) {
        snakeHeads.add(snakeHead);
    }

    public static void removeGameObject(GameEntity toRemove) {
        oldGameObjects.add(toRemove);
    }

    public static List<GameEntity> getGameObjects() {
        return Collections.unmodifiableList(gameObjects);
    }
}
