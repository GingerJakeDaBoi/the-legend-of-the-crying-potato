package org.gingerjake.potatogame.Actors.Player;

public class PlayerController {
    public static int x;
    public static int y;
    public static int health = 3;
    public static boolean enabled;
    public static boolean uping;
    public static boolean downing;
    public static boolean lefting;
    public static boolean righting;

    public static void spawn(int x, int y) {
        PlayerController.x = x;
        PlayerController.y = y;
    }

    public static void enable() {
        enabled = true;
    }

    public static void disable() {
        enabled = false;
    }

    @SuppressWarnings("BusyWait")
    public static void moveUp() {
        if (enabled) {
            while (uping) {
                y -= 1;
                System.out.println("Player up");
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @SuppressWarnings("BusyWait")
    public static void moveDown() {
        if (enabled) {
            while (downing) {
                y += 1;
                System.out.println("Player down");
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @SuppressWarnings("BusyWait")
    public static void moveLeft() {
        if (enabled) {
            while (lefting) {
                x -= 1;
                System.out.println("Player left");
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @SuppressWarnings("BusyWait")
    public static void moveRight() {
        if (enabled) {
            while (righting) {
                x += 1;
                System.out.println("Player right");
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}



