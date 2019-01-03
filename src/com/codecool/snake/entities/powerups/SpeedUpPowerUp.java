package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;

import java.util.Random;


public class SpeedUpPowerUp extends GameEntity implements Interactable {
    private static Random rnd = new Random();

    public SpeedUpPowerUp() {
        setImage(Globals.getInstance().getImage("PowerUpDeath"));

        setFitHeight(40);
        setFitWidth(40);
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            System.out.println(getMessage());
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return "Uh-oh! Speed up, be careful!";
    }
}
