package Music;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music extends Thread {
    private static Music instance;
    private Clip clip;
    private AudioInputStream str;
    private FloatControl control;
    private float volume = -23;

    private Music() {
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

    public static Music getInstance() {
        if (instance == null) {
            instance = new Music();
        }
        return instance;
    }

    @Override
    public void run() {
        clip.start();
    }

    public void musicStart() {
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void musicStop() {
        clip.stop();
    }

    public void changeSong(String songPath) {
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

    public void volumeChange(float newVolume) {
        volume = newVolume;
        control.setValue(volume);
    }

    public float getVolume() {
        return volume;
    }
}
