package org.gingerjake.potatogame;

public class Settings {
    public static String activeSetting;
    public static String[] settings = {"Controls", "Audio", "Keybindings", "Save", };
    public static String[] controlModes = {"Keyboard", "Controller"};
    public static String[] audioModes = {"musicVolume", "soundVolume"};
    public static String[] keybinds = {"moveUp", "moveDown", "moveLeft", "moveRight", "attackUp", "attackDown",
            "attackLeft","attackRight", "pause",};

    public static int musicVolume,soundVolume;
    public static boolean isKeyboardControls,isControllerControls;

    //Keybinds
    public static String moveUp, moveDown, moveLeft, moveRight, attackUp, attackDown, attackLeft, attackRight, pause;



}
