package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.ammo.SimpleShot;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Point2D;

import java.util.Random;


public class HomingEnemy extends Enemy implements Animatable, Interactable {

    private Point2D heading;
    private static Random rnd = new Random();
    private double xPos;
    private double yPos;
    private int xBound;
    private int yBound;

    public HomingEnemy(Vec2d snakePos) {
        super(10);

        setImage(Globals.getInstance().getImage("SimpleEnemy"));

//        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
//        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        boolean coordinatesX = rnd.nextBoolean();
        boolean coordinatesY = rnd.nextBoolean();

        if (coordinatesX) {
//            if we spawn to the right from the snake
            this.xBound = (int) Math.round(Globals.WINDOW_WIDTH) - (int) Math.round(snakePos.x);
        } else {
//            if we spawn to the left from the snake
            this.xBound = (int) Math.round(snakePos.x);
        }
//        we set the X Position of the enemy
        this.xPos = (double) rnd.nextInt(xBound) + snakePos.x;

        if (coordinatesY) {
//            if we spawn below the snake
            this.yBound = (int) Math.round(Globals.WINDOW_HEIGHT) - (int) Math.round(snakePos.y);
        } else {
//            if we spawn above the snake
            this.yBound = (int) Math.round(snakePos.y);
        }
//        we set the Y Position of the enemy
        this.yPos = snakePos.y + (double) rnd.nextInt(yBound);


        setX(this.xPos);
        setY(this.yPos);
        double direction = rnd.nextDouble() * 360;
        setRotate(direction);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        Vec2d snakePos = Globals.getInstance().game.snake.head.getSnakeHeadPosition();
        double spdX;
        if (Math.abs(snakePos.x - getX()) < 100) {
            spdX = 50;
        } else {
            spdX = 80;
        }
        double spdY;
        if (Math.abs(snakePos.y - getY()) < 100) {
            spdY = 50;
        } else {
            spdY = 80;
        }
        double deltaX = (snakePos.x - getX())/spdX;
        double deltaY = (snakePos.y - getY())/spdY;
        setX(getX() + deltaX);
        setY(getY() + deltaY);
    }

    @Override
    public void apply(GameEntity entity) {
        if (entity instanceof SnakeHead) {
            System.out.println(getMessage());
            destroy();
        }
        if(entity instanceof SimpleShot) {
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return (getDamage() + " damage");
    }
}
