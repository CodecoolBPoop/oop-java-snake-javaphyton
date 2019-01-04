package com.codecool.snake.entities.ammo;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.snakes.Snake;
import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Point2D;

public class SimpleShot extends GameEntity implements Interactable, Animatable {
    private Point2D heading;

    public SimpleShot() {
        setImage(Globals.getInstance().getImage("Shot"));
        setFitHeight(10);
        setFitWidth(10);
        Snake currentSnake = Globals.getInstance().game.snake;
        double rotation = currentSnake.head.getRotate();
        setRotate(currentSnake.head.getRotate() - 90);
        Vec2d startingPos = currentSnake.head.getSnakeHeadPosition();
        float speed = currentSnake.speed * 2;
        heading = Utils.directionToVector(rotation, speed);
        setX(startingPos.x);
        setY(startingPos.y);
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof Enemy){
            System.out.println(getMessage());
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }
}
