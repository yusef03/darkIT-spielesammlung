package games.phishingdefender;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

/**
 * Verwaltet die Sterne für jedes Level - PRO SPIELER!
 */
public class StarsManager {

    private String spielerName;
    private Map<Integer, Integer> levelStars;

    private static final String USER_HOME = System.getProperty("user.home");
    private static final String SAVE_DIR_PATH = USER_HOME + File.separator + ".phishingDefenderData";

    public StarsManager(String spielerName) {
        this.spielerName = spielerName;
        this.levelStars = new HashMap<>();

        // Initialisiere mit 0 Sternen
        levelStars.put(1, 0);
        levelStars.put(2, 0);
        levelStars.put(3, 0);

        loadStars();
    }

    /**
     * Gibt Dateinamen für diesen Spieler zurück
     */
    private String getStarsFile() {
        String cleanName = spielerName.toLowerCase().replaceAll("[^a-z0-9]", "");

        // Erstelle den NEUEN Ordner (genau wie im HighscoreManager)
        File dataDir = new File(SAVE_DIR_PATH);
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        // Gib den NEUEN Pfad zurück
        return SAVE_DIR_PATH + File.separator + "stars_" + cleanName + ".txt";
    }

    /**
     * Lädt Sterne aus Datei
     */
    private void loadStars() {
        File file = new File(getStarsFile());

        if (!file.exists()) {
            // Datei existiert noch nicht - Standard-Werte bleiben (0,0,0)
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    int level = Integer.parseInt(parts[0].replace("level", ""));
                    int stars = Integer.parseInt(parts[1]);
                    levelStars.put(level, stars);
                }
            }
        } catch (Exception e) {
            System.out.println("Fehler beim Laden der Sterne für " + spielerName + ": " + e.getMessage());
        }
    }

    /**
     * Speichert Sterne in Datei
     */
    private void saveStars() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getStarsFile()))) {
            for (int level = 1; level <= 3; level++) {
                int stars = levelStars.getOrDefault(level, 0);
                writer.write("level" + level + "=" + stars);
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println("Fehler beim Speichern der Sterne für " + spielerName + ": " + e.getMessage());
        }
    }

    /**
     * Gibt Sterne für ein Level zurück
     */
    public int getStarsForLevel(int level) {
        return levelStars.getOrDefault(level, 0);
    }

    /**
     * Aktualisiert Sterne (nur wenn besser!)
     */
    public void updateStars(int level, int newStars) {
        int currentStars = levelStars.getOrDefault(level, 0);

        // Nur updaten wenn neue Sterne-Zahl BESSER ist!
        if (newStars > currentStars) {
            levelStars.put(level, newStars);
            saveStars();
        }
    }

    /**
     * Berechnet Sterne basierend auf Performance
     */
    public static int berechneSterne(int richtigeAntworten, int gesamtEmails, int leben, int maxLeben) {
        // Genauigkeit berechnen
        double genauigkeit = (double) richtigeAntworten / gesamtEmails * 100;

        // 3 Sterne: ≥90% Genauigkeit UND mindestens 2 Leben übrig
        if (genauigkeit >= 90 && leben >= Math.max(2, maxLeben - 1)) {
            return 3;
        }

        // 2 Sterne: ≥70% Genauigkeit UND mindestens 1 Leben übrig
        if (genauigkeit >= 70 && leben >= 1) {
            return 2;
        }

        // 1 Stern: Level geschafft (egal wie)
        if (leben > 0) {
            return 1;
        }

        // 0 Sterne: Verloren
        return 0;
    }

    /**
     * Gibt Gesamt-Sterne zurück (für Statistik)
     */
    public int getGesamtSterne() {
        int total = 0;
        for (int stars : levelStars.values()) {
            total += stars;
        }
        return total;
    }
}