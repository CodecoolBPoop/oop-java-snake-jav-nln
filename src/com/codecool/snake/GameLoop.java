package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.powerups.SimplePowerup;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

import java.util.concurrent.ThreadLocalRandom;

public class GameLoop extends AnimationTimer {

    private long spawnTimer;

    // This gets called every 1/60 seconds
    @Override
    public void handle(long now) {

        respawnPowerUps();

        if (Globals.getBonerStartTime() + 5000 < System.currentTimeMillis()){
            Globals.setBoner(false);
        }

        for (GameEntity gameObject : Globals.gameObjects) {
            if (gameObject instanceof Animatable) {
                Animatable animObject = (Animatable) gameObject;
                animObject.step();
            }
        }
        Globals.gameObjects.addAll(Globals.newGameObjects);
        Globals.newGameObjects.clear();

        Globals.gameObjects.removeAll(Globals.oldGameObjects);
        Globals.oldGameObjects.clear();
    }

    private void respawnPowerUps() {
        long unixTime = System.currentTimeMillis();
        int spawnDelayMax = 7000;
        int spawnDelayMin = 1000;
        int maxPowerUps = 5;

        if (unixTime > spawnTimer) {
            //long spawnDelay = ThreadLocalRandom.current().nextLong(spawnDelayMin, spawnDelayMax);
            long spawnDelay = 1000;
            spawnTimer = unixTime + spawnDelay;


            int powerUpCounter = 0;
            for (GameEntity o : Globals.gameObjects) {
                if (o instanceof SimplePowerup) {
                    System.out.println(o + " " + o.getX() + " " + o.getY());
                    powerUpCounter++;
                }
            }
            if (powerUpCounter < maxPowerUps) {
                Globals.addGameObject(new SimplePowerup(Main.game));
            }
        }
    }
}
