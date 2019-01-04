package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;

import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Point2D;



public class CirclingEnemy extends Enemy implements Animatable, Interactable {

    private Point2D heading;
    private static Random rnd = new Random();
    private double xPos;
    private double yPos;
    private int xBound;
    private int yBound;
    private double direction;
    private double deltaDegree;
    int speed;


    public CirclingEnemy(Vec2d snakePos) {
        super(10);

        setImage(Globals.getInstance().getImage("SimpleEnemy"));
        boolean coordinatesX = rnd.nextBoolean();
        boolean coordinatesY = rnd.nextBoolean();

        if (coordinatesX) {
            this.xBound = (int)Math.round(Globals.WINDOW_WIDTH) - (int)Math.round(snakePos.x);
        } else {
            this.xBound = (int)Math.round(snakePos.x);
        }
        if (this.xBound < 0 || this.xBound> (int)Math.round(Globals.WINDOW_WIDTH)) {
            this.xPos = (double)rnd.nextInt(500) + snakePos.x;
            this.xBound = 500;
        } else {
            this.xPos = (double) rnd.nextInt(xBound) + snakePos.x;
        }

        if (coordinatesY) {
            this.yBound = (int)Math.round(Globals.WINDOW_HEIGHT) - (int)Math.round(snakePos.y);
        } else {
            this.yBound = (int)Math.round(snakePos.y);
        }
        if (this.yBound < 0 || this.yBound > (int)Math.round(Globals.WINDOW_HEIGHT)) {
            this.yPos = snakePos.y + (double)rnd.nextInt(500);
            this.yBound = 500;
        } else {
            this.yPos = snakePos.y + (double)rnd.nextInt(yBound);
        }

        setX(this.xPos);
        setY(this.yPos);
        this.direction = rnd.nextDouble() * 360;
        setRotate(direction);

        this.speed = rnd.nextInt(5)+1;
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        double degree = (this.direction/360);
        this.deltaDegree = ((double)rnd.nextInt(50)+1)/1000;
        if (degree + this.deltaDegree > 1) {
            degree = -1;
        } else {
            degree += this.deltaDegree;
        }
        this.direction = degree* 360;
        this.heading = Utils.directionToVector(this.direction, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
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
        return (getDamage() + " damage");
    }
}
