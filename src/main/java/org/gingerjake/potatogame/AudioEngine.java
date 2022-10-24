package org.gingerjake.potatogame;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class AudioEngine {
    File audioFile;

    public AudioEngine(String sound){
        audioFile = new File(sound);

        try { //WARNING: You need to make the delay outside this class, because I haven't implemented it yet :P
            if(audioFile.exists()) {
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(audioFile));
                clip.start();
                System.out.println("Playing " + sound);
            } else {
                System.out.println("File not found.");
            }
        } catch (Exception e) {
            System.out.println("Error playing " + sound);
            e.printStackTrace();
        }
    }
}
