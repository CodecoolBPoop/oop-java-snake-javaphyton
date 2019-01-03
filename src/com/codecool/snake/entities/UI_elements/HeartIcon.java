package com.codecool.snake.entities.UI_elements;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;

import java.util.Random;


public class HeartIcon extends GameEntity {

    public HeartIcon(int serial) {
        setImage(Globals.getInstance().getImage("PowerUpHeart"));

        setFitHeight(20);
        setFitWidth(20);
        setX(10 + (serial * 25));
        setY(10);
    }
}
