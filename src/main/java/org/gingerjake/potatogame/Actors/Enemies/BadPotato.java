package org.gingerjake.potatogame.Actors.Enemies;

import org.gingerjake.potatogame.Actors.Player.Attacks.Fist;
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

    public void setX(int x) {
        this.x = x;
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

    public int getSpeed() {
        return speed;
    }

    public void tick() {
        if (PlayerController.x - (PlayerController.width / 2) > this.x) {
            this.x += this.speed;
        } else if (PlayerController.x + (PlayerController.width / 2) < this.x) {
            this.x -= this.speed;
        }
        if (PlayerController.y + (PlayerController.y / 2)> this.y) {
            this.y += this.speed;
        } else if (PlayerController.y + (PlayerController.height / 2) < this.y) {
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
        if(this.enabled) {
            if(PlayerController.enabled) {
                if(PlayerController.x + PlayerController.width > this.x && PlayerController.x < this.x + this.width && PlayerController.y + PlayerController.height > this.y && PlayerController.y < this.y + this.height) {
                    PlayerController.hurt();
                }
            } else {
                this.setEnabled(false);
            }
        }
    }

    private void checkForFist() {
        if (Fist.visible) {
            if (Fist.x + Fist.width > this.x && Fist.x < this.x + this.width && Fist.y + Fist.height > this.y && Fist.y < this.y + this.height) {
                this.takeDamage(1);
                System.out.println("Hit enemy!");
                if (Fist.visible) {
                    Fist.visible = false;
                }
            }
        }
    }
}
