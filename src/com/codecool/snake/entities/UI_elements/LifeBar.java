package com.codecool.snake.entities.UI_elements;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;

public class LifeBar extends GameEntity {
    public int currentHealth;
    public int numberOfIcons;
    private int maxHealth = 200;
    public HeartIcon[] icons;

    public LifeBar(int startingHealth) {
        currentHealth = startingHealth;
        icons = new HeartIcon[maxHealth];
        reDrawLifeBar();
    }

    public void changeHealth(int delta) {
        currentHealth = currentHealth + delta;
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
        reDrawLifeBar();
    }

    private void reDrawLifeBar() {
        numberOfIcons = currentHealth / 10;
        Globals.getInstance().display.updateLifeBar(this);
    }

    public void resetHealth(int health){
        currentHealth = health;
        reDrawLifeBar();
    }

}
