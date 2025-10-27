package games.phishingdefender;

import javax.sound.sampled.*;
import java.io.File;

/**
 * Verwaltet Background Music (Loop)
 */
public class MusicManager {

    private static Clip musicClip;
    private static boolean isPlaying = false;

    // Startet Background Music (Loop)
    public static void startMenuMusic() {
        if (isPlaying) return;

        try {
            java.net.URL musicURL = MusicManager.class.getResource("/games/phishingdefender/assets/sounds/menu_music.wav");

            if (musicURL == null) {
                System.out.println("Menu Music nicht gefunden!");
                return;
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(musicURL);
            musicClip = AudioSystem.getClip();
            musicClip.open(audioIn);

            // LOOP endlos
            musicClip.loop(Clip.LOOP_CONTINUOUSLY);

            // Lautstärke setzen
            updateVolume();

            musicClip.start();
            isPlaying = true;

            System.out.println("Menu Music gestartet!");

        } catch (Exception e) {
            System.out.println("Fehler beim Abspielen der Menu Music: " + e.getMessage());
        }
    }

    // Stoppt Background Music
    public static void stopMenuMusic() {
        if (musicClip != null && isPlaying) {
            musicClip.stop();
            musicClip.close();
            isPlaying = false;
            System.out.println("Menu Music gestoppt!");
        }
    }

    // Checkt ob Music läuft
    public static boolean isPlaying() {
        return isPlaying;
    }

    // Konvertiert Prozent (0-100) zu Dezibel
    public static float percentToDecibel(int percent) {
        if (percent <= 0) {
            return -80.0f;  // Fast stumm
        }
        if (percent >= 100) {
            return 0.0f;  // Maximum
        }
        // Logarithmische Skalierung
        return (float) (20.0 * Math.log10(percent / 100.0));
    }

    // Updated die Lautstärke
    public static void updateVolume() {
        if (musicClip != null) {
            try {
                FloatControl volume = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
                int currentVolume = SettingsDialog.getMusicVolume();
                float db = percentToDecibel(currentVolume);
                volume.setValue(db);
                System.out.println("Lautstärke gesetzt: " + currentVolume + "% (" + db + " dB)");
            } catch (Exception e) {
                System.out.println("Fehler beim Setzen der Lautstärke: " + e.getMessage());
            }
        }
    }
}