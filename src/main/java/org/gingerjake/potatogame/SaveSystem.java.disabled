package org.gingerjake.potatogame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
            // saveWriter.write("\nPotatoHealth: " + PlayerController.health); I don't want to save this, just using it as a reference
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
//again, just a reference will remove later
//                if(line.contains("PotatoHealth")) {
//                    String[] splitLine = line.split(" ");
//                    String value = splitLine[1];
//                    PlayerController.health = Integer.parseInt(value);
//                    System.out.println("Loaded PotatoHealth: " + PlayerController.health);
//                }
            }
        }
    }
}
