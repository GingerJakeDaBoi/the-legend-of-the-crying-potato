package org.gingerjake.potatogame;

import org.gingerjake.potatogame.Actors.Player.Player;
import org.gingerjake.potatogame.Levels.PotatoFarmChase;

import java.io.*;
import java.util.Scanner;

public class SaveSystem {
    static File saveData = new File("save.txt");

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void saveGame() throws IOException {
        if (!saveData.exists()) {
            saveData.createNewFile();
        }

        try {
            FileWriter saveWriter = new FileWriter(saveData);
            saveWriter.write("PotatoFarmCompletion: " + PotatoFarmChase.completed);
            saveWriter.write("\nPotatoHealth: " + Player.health);
            saveWriter.write("\nPoints: " + Player.pointsEarned);
            saveWriter.close();
            System.out.println("Game saved");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void loadGame() throws FileNotFoundException {
        if (saveData.exists()) {
            Scanner saveReader = new Scanner(saveData);
            while (saveReader.hasNextLine()) {
                String line = saveReader.nextLine();
                if (line.contains("PotatoFarmCompletion")) {
                    String[] splitLine = line.split(" ");
                    String value = splitLine[1];
                    PotatoFarmChase.completed = value.equals("true");
                    System.out.println("Loaded PotatoFarmCompletion: " + PotatoFarmChase.completed);
                }
                if(line.contains("PotatoHealth")) {
                    String[] splitLine = line.split(" ");
                    String value = splitLine[1];
                    Player.health = Integer.parseInt(value);
                    System.out.println("Loaded PotatoHealth: " + Player.health);
                }
                if(line.contains("Points")) {
                    String[] splitLine = line.split(" ");
                    String value = splitLine[1];
                    Player.pointsEarned = Integer.parseInt(value);
                    System.out.println("Loaded Points: " + Player.pointsEarned);
                }
            }


        }

    }
}
