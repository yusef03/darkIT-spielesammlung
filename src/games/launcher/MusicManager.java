package games.launcher;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Kümmert sich um die Wiedergabe der Hintergrundmusik.
 *
 * @author yusef03
 * @version 1.0
 */
public class MusicManager {

    private static Clip musicClip;
    private static FloatControl gainControl;
    private static boolean isInitialized = false;

    // NEU: Cache für min/max Werte (werden nur 1x geholt)
    private static float cachedMin;
    private static float cachedMax;

    // NEU: Lookup-Table für vorberechnete dB-Werte (0-100)
    private static float[] dbLookupTable = new float[101];


    /**
     * Startet die Hintergrundmusik in einer Endlosschleife.
     * @param relativePath Pfad zur Musikdatei (z.B. "/resources/sounds/music.wav")
     */
    public static void playMusic(String relativePath) {
        if (isInitialized) {
            return; // Verhindert doppeltes Abspielen
        }

        try {
            java.net.URL musicURL = MusicManager.class.getResource(relativePath);
            if (musicURL == null) {
                throw new IOException("Musik-Ressource nicht gefunden: " + relativePath);
            }

            try (AudioInputStream ais = AudioSystem.getAudioInputStream(musicURL)) {

                musicClip = AudioSystem.getClip();
                musicClip.open(ais); // Clip lädt den kompletten Stream
                musicClip.loop(Clip.LOOP_CONTINUOUSLY); // Endlosschleife

                gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);

                // NEU: Min/Max EINMAL holen und cachen
                cachedMin = gainControl.getMinimum();
                cachedMax = gainControl.getMaximum();

                // NEU: Lookup-Table vorberechnen
                precomputeDbValues();

                isInitialized = true;

                // Setze auf 50% Start-Lautstärke
                setVolume(50);
            }

        } catch (Exception e) {
            System.err.println("Hintergrundmusik konnte nicht geladen werden: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * NEU: Berechnet alle dB-Werte vorher (0-100) in eine Lookup-Table.
     * Das macht die setVolume() Methode VIEL schneller!
     */
    private static void precomputeDbValues() {
        dbLookupTable[0] = cachedMin; // Stumm

        for (int i = 1; i <= 100; i++) {
            float amplitude = i / 100.0f;
            float dB = (float) (20.0 * Math.log10(amplitude));

            // Clamping
            if (dB < cachedMin) dB = cachedMin;
            if (dB > cachedMax) dB = cachedMax;

            dbLookupTable[i] = dB;
        }
    }

    /**
     * Setzt die Lautstärke SOFORT mit vorberechneten Werten.
     * Spezialfall: Bei Stumm (0) wird Clip gestoppt für SOFORTIGE Reaktion!
     */
    public static void setVolume(int volumePercentage) {
        if (!isInitialized || gainControl == null) {
            return;
        }

        // Wertebereich sicherstellen
        if (volumePercentage < 0) volumePercentage = 0;
        if (volumePercentage > 100) volumePercentage = 100;

        // SPEZIALFALL: Stumm = Clip stoppen (INSTANT!)
        if (volumePercentage == 0) {
            if (musicClip.isRunning()) {
                musicClip.stop();
            }
            return;
        }

        // Wenn Clip gestoppt war, starte ihn wieder
        if (!musicClip.isRunning()) {
            musicClip.start();
        }

        // Hole vorberechneten dB-Wert
        float targetDb = dbLookupTable[volumePercentage];

        // SETZE DIREKT (synchron, KEIN Thread!)
        gainControl.setValue(targetDb);
    }

    /**
     * Stoppt die Musik (Pausiert den Clip an aktueller Position).
     */
    public static void stopMusic() {
        if (isInitialized && musicClip != null && musicClip.isRunning()) {
            musicClip.stop();
        }
    }

    /**
     * Setzt die Musik fort (startet von pausierter Position weiter).
     */
    public static void resumeMusic() {
        if (isInitialized && musicClip != null && !musicClip.isRunning()) {
            musicClip.start();
        }
    }
}