package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Snake;
import javafx.scene.image.Image;

import java.util.List;
import java.util.Random;

public class GameLoop {
    private Snake snake;
    private boolean running = false;
    private int clock = 0;
    private boolean spawned = true;
    int spawnInterval;

    public GameLoop(Snake snake) {
        this.snake = snake;
    }

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }

    public void step() {
        if (running) {
            snake.step();
            {
                clock++;
                if (clock % 100 == 0) {
                    snake.score++;
                }
                Random r = new Random();
                if (spawned) {
                    spawnInterval = r.nextInt(500) + 500;
                    System.err.println(spawnInterval);
                    spawned = false;
                }
                if (clock % spawnInterval == 0) {
                    Globals.getInstance().game.spawnEnemies(4);
                    Globals.getInstance().game.spawnPowerUps(4);
                    spawned = true;
                }
            }
            for (GameEntity gameObject : Globals.getInstance().display.getObjectList()) {
                if (gameObject instanceof Animatable) {
                    ((Animatable) gameObject).step();
                }
            }
            checkCollisions();
        }

        Globals.getInstance().display.frameFinished();
    }

    private void checkCollisions() {
        List<GameEntity> gameObjs = Globals.getInstance().display.getObjectList();
        for (int idxToCheck = 0; idxToCheck < gameObjs.size(); ++idxToCheck) {
            GameEntity objToCheck = gameObjs.get(idxToCheck);
            if (objToCheck instanceof Interactable) {
                for (int otherObjIdx = idxToCheck + 1; otherObjIdx < gameObjs.size(); ++otherObjIdx) {
                    GameEntity otherObj = gameObjs.get(otherObjIdx);
                    if (otherObj instanceof Interactable) {
                        if (objToCheck.getBoundsInParent().intersects(otherObj.getBoundsInParent())) {
                            ((Interactable) objToCheck).apply(otherObj);
                            ((Interactable) otherObj).apply(objToCheck);
                        }
                    }
                }
            }
        }
    }
}
