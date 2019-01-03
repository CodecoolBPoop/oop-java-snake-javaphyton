package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;


public class Game extends Pane {

    public Snake snake = null;
    private GameTimer gameTimer = new GameTimer();
    public ArrayList<GameEntity> sprites = new ArrayList<>();


    public Game() {
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();
        init();
    }

    public void init() {
        spawnSnake();
        spawnEnemies(4);
        spawnPowerUps(4);

        GameLoop gameLoop = new GameLoop(snake);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        gameTimer.play();
    }

    public void start() {
        setupInputHandling();
        Globals.getInstance().startGame();
    }

    private void spawnSnake() {
        snake = new Snake(new Vec2d(500, 500));
    }

    public void spawnEnemies(int numberOfEnemies) {
        for (int i = 0; i < numberOfEnemies; ++i){
            GameEntity temp = new SimpleEnemy();
            sprites.add(temp);
        }
    }

    public void spawnPowerUps(int numberOfPowerUps) {
        for (int i = 0; i < numberOfPowerUps; ++i) {
            GameEntity temp = new SimplePowerUp();
            sprites.add(temp);
        }
    }

    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }

    public void cleanUp(){
        Iterator snakeBody = this.snake.body.getList().iterator();
        Iterator sprite = this.sprites.iterator();

        while (snakeBody.hasNext()){
            ((GameEntity)snakeBody.next()).destroy();
        }
        this.snake.body.clear();

        while (sprite.hasNext()){
            ((GameEntity)sprite.next()).destroy();
        }

        spawnEnemies(4);
        spawnPowerUps(4);

    }

    public void setSnake(){
        this.snake.head.setX(500);
        this.snake.head.setY(500);
        snake.addPart(4);
        this.snake.health = 30;
        Globals.getInstance().startGame();
    }


}
