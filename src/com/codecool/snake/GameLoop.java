package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.enemies.RandomPathEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerup;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javafx.animation.AnimationTimer;


public class GameLoop extends AnimationTimer {

    public static final int MIN_ENEMY_DELAY = 1000;
    private long spawnTimer;
    private Random random = new Random();

    // This gets called every 1/60 seconds
    @Override
    public void handle(long now) {

        respawnEntities();

        for (GameEntity gameObject : Globals.gameObjects) {
            if (gameObject instanceof Animatable) {
                Animatable animObject = (Animatable) gameObject;
                try {
                    animObject.step();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Globals.gameObjects.addAll(Globals.newGameObjects);
        Globals.newGameObjects.clear();

        Globals.gameObjects.removeAll(Globals.oldGameObjects);
        Globals.oldGameObjects.clear();
    }

    private void respawnEntities() {
        long unixTime = System.currentTimeMillis();
        final int SPAWN_DELAY_MAY = 3000;
        final int SPAWN_DELAY_MIN = 200;
        final int MAX_POWERUPS = 5;
        final int MAX_SIMPLEENEMIES = 5;
        final int MAX_RANDOMPATHENEMIES = 5;

        if (unixTime > spawnTimer) {
            long spawnDelay = ThreadLocalRandom.current()
                .nextLong(SPAWN_DELAY_MIN, SPAWN_DELAY_MAY);
            spawnTimer = unixTime + spawnDelay;


            int powerUpCounter = 0;
            int simpleEnemyCounter = 0;
            int randomPathEnemyCounter = 0;
            for (GameEntity o : Globals.gameObjects) {
                if (o instanceof SimplePowerup) {
                    System.out.println(o + " " + o.getX() + " " + o.getY());
                    powerUpCounter++;
                }
                if (o instanceof SimpleEnemy) {
                    System.out.println(o + " " + o.getX() + " " + o.getY());
                    simpleEnemyCounter++;
                }
                if (o instanceof RandomPathEnemy) {
                    System.out.println(o + " " + o.getX() + " " + o.getY());
                    randomPathEnemyCounter++;
                }
            }
            System.out.println("SpawnDelay, pUps: " + spawnDelay + " " + powerUpCounter);
            if (powerUpCounter < MAX_POWERUPS) {
                Globals.addGameObject(new SimplePowerup(Main.game));
            }

            System.out.println("SpawnDelay, pUps: " + spawnDelay + " " + powerUpCounter);
            if (simpleEnemyCounter < MAX_SIMPLEENEMIES) {
                Globals.addGameObject(new SimpleEnemy(Main.game));
            }

            System.out.println("SpawnDelay, pUps: " + spawnDelay + " " + powerUpCounter);
            if (randomPathEnemyCounter < MAX_RANDOMPATHENEMIES) {
                Globals.addGameObject(new RandomPathEnemy(Main.game));
            }
        }
    }
}
