package org.gingerjake.potatogame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class AudioEngine {
    public static synchronized void playSound(final File soundFile) {
        if(soundFile.exists()) {
            new Thread(() -> {
                try {
                    Clip clip = AudioSystem.getClip();

                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundFile);
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        } else {
            System.out.println("Sound file not found: " + soundFile.getPath());
        }
    }
}
