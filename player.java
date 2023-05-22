import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class MusicPlayer {
    private Clip clip;
    private boolean isPlaying;

    public void play(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioStream);
            clip.start();
            isPlaying = true;
            System.out.println("Now playing: " + audioFile.getName());
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        if (isPlaying) {
            clip.stop();
            isPlaying = false;
            System.out.println("Playback paused.");
        }
    }

    public void resume() {
        if (!isPlaying) {
            clip.start();
            isPlaying = true;
            System.out.println("Playback resumed.");
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
            clip.close();
            isPlaying = false;
            System.out.println("Playback stopped.");
        }
    }

    public static void main(String[] args) {
        MusicPlayer player = new MusicPlayer();
        String filePath = "path/to/your/music/file.mp3";
        player.play(filePath);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        player.pause();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        player.resume();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        player.stop();
    }
}
