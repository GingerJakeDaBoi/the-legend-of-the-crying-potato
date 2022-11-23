package org.gingerjake.potatogame.Actors.Enemies;

import org.gingerjake.potatogame.Actors.Player.Fist;
import org.gingerjake.potatogame.Actors.Player.PlayerController;

import java.awt.*;

public class BadPotato {
    private int x;
    private int y;
    private final int width;
    private final int height;
    private int health;
    private final int speed;
    private boolean enabled;
    private final Image asset;

    public BadPotato(int x, int y, int width, int height, int health, int speed, int isEnabled, Image enemyAsset) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.health = health;
        this.speed = speed;
        this.enabled = isEnabled == 1;
        this.asset = enemyAsset;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void takeDamage(int damage) {
        int currentHealth = this.getHealth();
        currentHealth -= damage;
        this.setHealth(currentHealth);
    }

    public Image getAsset() {
        return asset;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getHealth() {
        return health;
    }

    public void tick() {
        if (PlayerController.x > this.x) {
            this.x += this.speed;
        } else if (PlayerController.x < this.x) {
            this.x -= this.speed;
        }
        if (PlayerController.y > this.y) {
            this.y += this.speed;
        } else if (PlayerController.y < this.y) {
            this.y -= this.speed;
        }

        checkForFist();
        checkForPlayer();
        checkForDeath();
    }

    private void checkForDeath() {
        if (this.isDead()) {
            this.setEnabled(false);
        }
        if (this.getHealth() <= 0) {
            this.setEnabled(false);
        }
    }

    private void checkForPlayer() {
        if (!this.enabled) {
            return;
        }
        if (!PlayerController.enabled) {
            this.setEnabled(false);
        } else {
            if(PlayerController.x + PlayerController.width > this.x && PlayerController.x < this.x + this.width && PlayerController.y + PlayerController.height > this.y && PlayerController.y < this.y + this.height) {
                PlayerController.hurt();
            }
        }
    }

    private void checkForFist() {
        if (!Fist.visible) {
            return;
        }
        if (Fist.x + Fist.width <= this.x || Fist.x >= this.x + this.width || Fist.y + Fist.height <= this.y || Fist.y >= this.y + this.height) {
            return;
        }
        this.takeDamage(Fist.power);
        System.out.println("Hit enemy!");
        if (Fist.visible) {
            Fist.visible = false;
        }
    }
}
