package org.gingerjake.potatogame.Actors.Enemies.Final;

import org.gingerjake.potatogame.Actors.Player.Attacks.Fist;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;

public class FinalBoss {
    public static int x;
    public static int y;
    public static int width;
    public static int height;
    public static int speed = 3;
    public static int health = 8;
    private static int randomX = (int) (Math.random() * GamePanel.width);
    private static int randomY = (int) (Math.random() * GamePanel.height);
    public static boolean enabled;
    public static int phase;

    public static void spawn(int x, int y, int width, int height) {
        FinalBoss.x = x;
        FinalBoss.y = y;
        FinalBoss.width = width;
        FinalBoss.height = height;
    }

    public static void enable() {
        enabled = true;
    }

    public static void disable() {
        enabled = false;
    }

    @SuppressWarnings("BusyWait")
    public static void phase1() {
        phase = 0;
        boolean direction = true;
        while (enabled) {
            if (phase == 0) {
                if (!direction) {
                    y += speed;
                } else {
                    y -= speed;
                }

                if (y <= 0) {
                    direction = false;
                } else if (y >= GamePanel.height - height) {
                    direction = true;
                }

                if (Fist.x + Fist.width > x && Fist.x < x + width && Fist.y + Fist.height > y && Fist.y < y + height) {
                    if (Fist.visible) {
                        health -= Fist.power;
                        Fist.visible = false;
                    }
                }

                if (!FinalAmmo.enabled) {
                    FinalAmmo.spawn(x + width / 3, y + height / 3, width / 2, height / 2);
                    FinalAmmo.enable();
                    new Thread(FinalAmmo::chase).start();
                }

                try {
                    Thread.sleep(GamePanel.TARGET_TIME / speed);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if (health <= 0) {
                    phase = 1;
                    new Thread(FinalBoss::phase2).start();
                    health = 8;
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    @SuppressWarnings("BusyWait")
    public static void phase2() {
        while (enabled) {

            if (x == randomX || x == 0 || x == GamePanel.width - width) {
                randomX = (int) (Math.random() * GamePanel.width);
            }

            if (y == randomY || y == 0 || y == GamePanel.height - height) {
                randomY = (int) (Math.random() * GamePanel.height);
            }


            if (x < randomX) {
                x += 1;
            } else if (x > randomX) {
                x -= 1;
            }
            if (y < randomY) {
                y += 1;
            } else if (y > randomY) {
                y -= 1;
            }

            if (!FinalAmmo.enabled) {
                FinalAmmo.spawn(x + width / 4, y + height / 4, width / 4, height / 4);
                FinalAmmo.enable();
                new Thread(FinalAmmo::chase).start();
            }

            //if either Fist or FinalBoss overlap, damage the chaser.
            if (Fist.x + Fist.width > x && Fist.x < x + width && Fist.y + Fist.height > y && Fist.y < y + height) {
                if (Fist.visible) {
                    health -= Fist.power;
                    Fist.visible = false;
                }
            }

            if (PlayerController.x + PlayerController.width > x && PlayerController.x < x + width && PlayerController.y + PlayerController.height > y && PlayerController.y < y + height) {
                if (!PlayerController.hurting) {
                    PlayerController.hurting = true;
                    new Thread(PlayerController::hurt).start();
                }
            }

            try {
                Thread.sleep(GamePanel.TARGET_TIME / speed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (health <= 0) {
                if (phase == 1) {
                    phase = 2;
                    speed += 6;
                    health = 12;
                } else {
                    disable();
                    FinalAmmo.disable();
                }
            }
        }
        Thread.currentThread().interrupt();


    }

}

