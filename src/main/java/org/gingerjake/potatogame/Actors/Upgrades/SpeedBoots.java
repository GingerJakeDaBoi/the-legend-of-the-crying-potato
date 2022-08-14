package org.gingerjake.potatogame.Actors.Upgrades;

import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;

public class SpeedBoots {
    public static int x;
    public static int y;
    public static int width;
    public static int height;
    public static boolean enabled;

    public static void spawn(int x, int y, int width, int height) {
        SpeedBoots.x = x;
        SpeedBoots.y = y;
        SpeedBoots.width = width;
        SpeedBoots.height = height;
        new Thread(SpeedBoots::enable).start();
    }

    @SuppressWarnings("BusyWait")
    public static void enable() {
        enabled = true;
        while (enabled) {

            if (PlayerController.x + PlayerController.width > x && PlayerController.x < x + width && PlayerController.y + PlayerController.height > y && PlayerController.y < y + height) {
                new Thread(PlayerController::giveSpeed).start();
                enabled = false;
            }

            try {
                Thread.sleep(GamePanel.TARGET_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Thread.currentThread().interrupt();


    }

}

