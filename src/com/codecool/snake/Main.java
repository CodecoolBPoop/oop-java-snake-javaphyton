package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import jdk.nashorn.internal.objects.Global;

import java.io.IOException;

public class Main extends Application {

    Stage primaryStage;
    Scene mainScene;
    Game game;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        game = new Game();
        mainScene = new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);
        createButtons();
        gameStart();
    }

    public void restart(Stage stage) {
        Globals.getInstance().game.cleanUp();
        Globals.getInstance().game.setSnake();
    }

    public void gameStart() {
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(mainScene);
        primaryStage.show();
        game.start();
    }


    @Override
    public void stop() throws Exception {
        System.out.println("Exiting..");
    }

    public void createButtons() {
        Button restartBtn = new Button("Restart");
        restartBtn.defaultButtonProperty();
        restartBtn.setLayoutX(0);
        restartBtn.setLayoutY(675);
        restartBtn.setOnMouseClicked((mouseEvent) -> {
            System.err.println("RESTART");
            restart(primaryStage);
        });
        Globals.getInstance().display.addButton(restartBtn);
    }


}
