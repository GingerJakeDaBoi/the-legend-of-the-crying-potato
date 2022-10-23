package org.gingerjake.potatogame;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class AudioEngine {
    File audioFile;

    public AudioEngine(String sound){
        audioFile = new File(sound);

        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(audioFile));
            clip.start();
            System.out.println("Playing " + sound);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
