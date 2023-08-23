package Music;
import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Music extends Thread{
    private static Clip clip;
    private static AudioInputStream str;
    private static FloatControl control;
    private static float  volume = -23;


    public static float getVolume() {
        return volume;
    }

    public Music(){
        try {
            File mus1 = new File("Music/The Four Horsemen.wav");
            str = AudioSystem.getAudioInputStream(mus1);
            clip = AudioSystem.getClip();
            clip.open(str);
            control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(volume);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void run(){
            clip.start();
    }

    public static void MusicStart(){
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public static void MusicStop(){
        clip.stop();

    }
    public static void changeSong(String songPath){
        clip.close();
       try {
            str = AudioSystem.getAudioInputStream(new File(songPath));
            clip = AudioSystem.getClip();
            clip.open(str);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            control.setValue(volume);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }


    }


    public static void VolumeChange(JSlider slider){
        volume = slider.getValue();

        control.setValue(volume);
    }
}

