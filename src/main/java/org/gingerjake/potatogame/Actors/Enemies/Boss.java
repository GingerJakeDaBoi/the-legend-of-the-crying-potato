package org.gingerjake.potatogame.Actors.Enemies;

import org.gingerjake.potatogame.Actors.Player.Attacks.Fist;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;

import java.awt.*;

public class Boss {
    private final int width;
    private final int height;
    private int speed;
    private int ammoSpeed;
    private final Image asset;
    private final Image ammoAsset;
    private final double threshold1;
    private final double threshold2;
    private int x;
    private int y;
    private int randomX;
    private int randomY;
    private int ammoX;
    private int ammoY;
    private int health;
    private boolean direction;
    private boolean enabled;
    private boolean shooting;
    private int phase;
    private String ammoDir;

    public Boss(int x, int y, int width, int height, int health, int speed, int isEnabled, Image enemyAsset, Image ammoAsset) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.health = health;
        this.speed = speed;
        this.enabled = isEnabled == 1;
        this.asset = enemyAsset;
        this.ammoAsset = ammoAsset;

        threshold1 = health * ((double) 2 / 3);
        threshold2 = health * ((double) 1 / 3);
    }

    public boolean isDead() {
        return health <= 0;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isShooting() {
        return shooting;
    }

    public void takeDamage(int damage) {
        int currentHealth = this.getHealth();
        currentHealth -= damage;
        this.setHealth(currentHealth);
    }

    public Image getAsset() {
        return asset;
    }

    public Image getAmmoAsset() {
        return ammoAsset;
    }

    public String getAmmoDir() {
        return ammoDir;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getAmmoX() {
        return ammoX;
    }

    public int getAmmoY() {
        return ammoY;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public String getThresholds() {
        return "Threshold 1: " + threshold1 + " Threshold 2: " + threshold2;
    }

    public void tick() {
        if(this.enabled) {
            switch (phase) {
                case 0 -> {
                    if (this.y > GamePanel.height - this.width) {
                        this.direction = false;
                    }
                    if (this.y < 0) {
                        this.direction = true;
                    }
                    if (this.direction) {
                        this.y += this.speed;
                    } else {
                        this.y -= this.speed;
                    }

                    if (health <= threshold1) {
                        phase = 1;
                        this.shooting = false;
                        speed += 1;
                        ammoSpeed = 3;
                    }
                    if (!this.shooting) {
                        this.shooting = true;
                        this.ammoX = this.x;
                        this.ammoY = this.y;
                    } else {
                        this.ammoX -= 2;
                        checkAmmo();
                    }

                }
                case 1 -> {
                    if (health <= threshold2) {
                        phase = 2;
                        this.shooting = false;
                        speed += 1;
                        ammoSpeed = 7;
                    }
                    if (this.x < this.randomX) {
                        this.x += 1;
                    } else if (this.x > this.randomX) {
                        this.x -= 1;
                    }
                    if (this.y < this.randomY) {
                        this.y += 1;
                    } else if (this.y > this.randomY) {
                        this.y -= 1;
                    }

                    randomPosition();
                    ammoTick();
                }
                case 2 -> {
                    if (health <= 0) {
                        phase = 3;
                        this.shooting = false;
                        speed += 1;
                        ammoSpeed = 0;
                    }
                    if (this.x < this.randomX) {
                        this.x += 1;
                    } else if (this.x > this.randomX) {
                        this.x -= 1;
                    }
                    if (this.y < this.randomY) {
                        this.y += 1;
                    } else if (this.y > this.randomY) {
                        this.y -= 1;
                    }

                    randomPosition();
                    ammoTick();
                }
                case 3 -> this.enabled = false;

            }
        }

        checkForFist();
        checkForPlayer();
        checkForDeath();
    }

    private void randomPosition() {
        if (this.x >= GamePanel.width - this.width) {
            this.randomX = (int) (Math.random() * 1600);
        }
        if (this.x <= 0) {
            this.randomX = (int) (Math.random() * 1600);
        }
        if (this.y >= GamePanel.height - this.height) {
            this.randomY = (int) (Math.random() * 900);
        }
        if (this.y <= 0) {
            this.randomY = (int) (Math.random() * 900);
        }
        if (this.randomX == this.x || this.randomY == this.y) {
            this.randomX = (int) (Math.random() * 1600);
            this.randomY = (int) (Math.random() * 900);
        }
    }

    private void ammoTick() {
        if (!this.shooting) {
            this.shooting = true;
            this.ammoX = this.x;
            this.ammoY = this.y;

            int random = (int) (Math.random() * 8);
            if (random == 0) {
                this.ammoDir = "up";
            } else if (random == 1) {
                this.ammoDir = "down";
            } else if (random == 2) {
                this.ammoDir = "left";
            } else if (random == 3) {
                this.ammoDir = "right";
            } else if (random == 4) {
                this.ammoDir = "upleft";
            } else if (random == 5) {
                this.ammoDir = "upright";
            } else if (random == 6) {
                this.ammoDir = "downleft";
            } else if (random == 7) {
                this.ammoDir = "downright";
            } else {
                this.ammoDir = "up";
                this.shooting = false;
                System.out.println("Error: Invalid random number for boss ammo direction");
            }
        } else {
            if (!(this.ammoDir == null)) {
                switch (this.ammoDir) {
                    case "up" -> this.ammoY -= ammoSpeed;
                    case "down" -> this.ammoY += ammoSpeed;
                    case "left" -> this.ammoX -= ammoSpeed;
                    case "right" -> this.ammoX += ammoSpeed;
                    case "upleft" -> {
                        this.ammoY -= ammoSpeed;
                        this.ammoX -= ammoSpeed;
                    }
                    case "upright" -> {
                        this.ammoY -= ammoSpeed;
                        this.ammoX += ammoSpeed;
                    }
                    case "downleft" -> {
                        this.ammoY += ammoSpeed;
                        this.ammoX -= ammoSpeed;
                    }
                    case "downright" -> {
                        this.ammoY += ammoSpeed;
                        this.ammoX += ammoSpeed;
                    }
                }
                checkAmmo();
            } else {
                this.shooting = false;
            }
        }
    }

    private void checkAmmo() {
        if (this.ammoX > GamePanel.width || this.ammoY > GamePanel.height || this.ammoX < 0 || this.ammoY < 0) {
            this.shooting = false;
        }
        if (PlayerController.x + PlayerController.width > this.ammoX && PlayerController.x < this.ammoX + 32 && PlayerController.y + PlayerController.height > this.ammoY && PlayerController.y < this.ammoY + 32) {
            PlayerController.hurt();
            this.shooting = false;
        }
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
        if (this.enabled) {
            if (PlayerController.enabled) {
                if (PlayerController.x + PlayerController.width > this.x && PlayerController.x < this.x + this.width && PlayerController.y + PlayerController.height > this.y && PlayerController.y < this.y + this.height) {
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
                this.takeDamage(Fist.power);
                System.out.println("Hit enemy!");
                if (Fist.visible) {
                    Fist.visible = false;
                }
            }
        }
    }


}
