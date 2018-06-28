package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;
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
        boner = isBoner;
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

    public static final Image eighteen = new Image("18.png");
    public static final Image viagra = new Image("viagra.png");
    public static final Image snakeHead = new Image("snake_head.png");
    public static final Image snakeBody = new Image("snake_body.png");
    public static final Image snakeHead_v = new Image("snake_head_pink.png");
    public static final Image snakeBody_v = new Image("snake_body_skin.png");
    public static final Image simpleEnemy = new Image("simple_enemy.png");
    public static final Image followingEnemy = new Image("padlizsan.png");
    public static final Image randomPathEnemy = new Image("barack.png");
    public static final Image powerupBerry = new Image("powerup_berry.png");
    public static final Image superPowerUp = new Image("powerup_pill.png");
    //.. put here the other images you want to use



    public static boolean leftKeyDown;
    public static boolean rightKeyDown;
    public static boolean aKeyDown;
    public static boolean dKeyDown;
    public static final List<GameEntity> gameObjects;
    public static final List<GameEntity> newGameObjects; // Holds game objects crated in this frame.
    public static final List<GameEntity> oldGameObjects; // Holds game objects that will be destroyed this frame.
    public static final List<SnakeHead> snakeHeads;
    public static GameLoop gameLoop;
    public static final Random rnd = new Random();

    static {
        gameObjects = new LinkedList<>();
        newGameObjects = new LinkedList<>();
        oldGameObjects = new LinkedList<>();
        snakeHeads = new LinkedList<>();
    }

    public static void addGameObject(GameEntity toAdd) {
        newGameObjects.add(toAdd);
    }

    public static void addSnakeHead(SnakeHead snakeHead) {
        snakeHeads.add(snakeHead);
    }

    public static void removeGameObject(GameEntity toRemove) {
        oldGameObjects.add(toRemove);
    }

    public static List<GameEntity> getGameObjects() {
        return Collections.unmodifiableList(gameObjects);
    }
}
