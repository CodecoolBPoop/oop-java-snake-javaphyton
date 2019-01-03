package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import java.util.List;

import com.codecool.snake.entities.UI_elements.HeartIcon;
import com.codecool.snake.entities.UI_elements.LifeBar;
import javafx.scene.layout.Pane;


public class Display {
    private Pane displayPane;
    private DelayedModificationList<GameEntity> gameObjects = new DelayedModificationList<>();

    public Display(Pane pane) {
        displayPane = pane;
    }

    public void add(GameEntity entity) {
        displayPane.getChildren().add(entity);
        gameObjects.add(entity);
    }

    public void remove(GameEntity entity) {
        displayPane.getChildren().remove(entity);
        gameObjects.remove(entity);
    }

    public List<GameEntity> getObjectList() {
        return gameObjects.getList();
    }

    public void frameFinished() {
        gameObjects.doPendingModifications();
    }

    public void updateSnakeHeadDrawPosition(GameEntity snakeHead) {
        displayPane.getChildren().remove(snakeHead);
        displayPane.getChildren().add(snakeHead);
    }

    public void updateLifeBar(LifeBar lifeBar) {
        for (HeartIcon lifeIcon : lifeBar.icons) {
            displayPane.getChildren().remove(lifeIcon);
        }
        for (int i = 0; i < lifeBar.numberOfIcons; i++) {
            lifeBar.icons[i] = new HeartIcon(i);
        }

    }

    public void clear() {
        displayPane.getChildren().clear();
        gameObjects.clear();
    }
}
