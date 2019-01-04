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
    private double direction;
    private double degree;
    int speed;

    public HomingEnemy(Vec2d snakePos) {
        super(10);

        setImage(Globals.getInstance().getImage("SimpleEnemy"));

        boolean coordinatesX = rnd.nextBoolean();
        boolean coordinatesY = rnd.nextBoolean();

        if (coordinatesX) {
            this.xBound = (int) Math.round(Globals.WINDOW_WIDTH) - (int) Math.round(snakePos.x);
        } else {
            this.xBound = (int) Math.round(snakePos.x);
        }
        this.xPos = (double) rnd.nextInt(xBound) + snakePos.x;

        if (coordinatesY) {
            this.yBound = (int) Math.round(Globals.WINDOW_HEIGHT) - (int) Math.round(snakePos.y);
        } else {
            this.yBound = (int) Math.round(snakePos.y);
        }
        this.yPos = snakePos.y + (double) rnd.nextInt(yBound);


        setX(this.xPos);
        setY(this.yPos);
        this.direction = rnd.nextDouble() * 360;
        setRotate(direction);

        heading = Utils.directionToVector(direction, 1);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        Vec2d snakePos = Globals.getInstance().game.snake.head.getSnakeHeadPosition();
        this.xPos = snakePos.x - getX();
        this.yPos = snakePos.y - getY();
        this.degree = Math.toDegrees(Math.atan(yPos/xPos));
        if(xPos < 0) {
            direction = degree - 90;
        } else {
            direction = degree + 90;
        }
        setRotate(direction);
        int maxSpeed = (int)Math.round(Globals.getInstance().game.snake.speed);
        this.speed = rnd.nextInt(maxSpeed)+maxSpeed/2;

        heading = Utils.directionToVector(direction, speed);

        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

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
