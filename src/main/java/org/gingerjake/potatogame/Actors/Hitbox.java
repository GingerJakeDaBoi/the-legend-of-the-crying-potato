package org.gingerjake.potatogame.Actors;

import org.gingerjake.potatogame.Actors.Player.PlayerController;

public class Hitbox {
    final int x;
    final int y;
    final int width;
    final int height;
    final String direction;

    public Hitbox(int x, int y, int width, int height, String pushDirection) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.direction = pushDirection;
    }

    public void tick() {
        if (PlayerController.x + PlayerController.width > this.x && PlayerController.x < this.x + this.width && PlayerController.y + PlayerController.height > this.y && PlayerController.y < this.y + this.height) {
            switch (this.direction) {
                case "left" -> PlayerController.x = this.x - PlayerController.width;
                case "right" -> PlayerController.x = this.x + this.width;
                case "up" -> PlayerController.y = this.y - PlayerController.height;
                case "down" -> PlayerController.y = this.y + this.height;
            }
        }
    }
}
