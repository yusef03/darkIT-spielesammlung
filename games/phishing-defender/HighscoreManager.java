package games.phishingdefender;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.io.File;

/**
 * Phishing Defender - HighscoreManager Class
 * Verwaltet alle Highscores (Lesen, Schreiben, Sortieren)
 * @author yusef03
 * @version 1.0
 */
public class HighscoreManager {

    // NEU: Der korrekte, systemunabhängige Speicherpfad
    private static final String USER_HOME = System.getProperty("user.home");
    private static final String SAVE_DIR_PATH = USER_HOME + File.separator + ".phishingDefenderData";
    private static final String DATEI_NAME = SAVE_DIR_PATH + File.separator + "highscores.txt";

    private List<HighscoreEntry> highscores;
    // Konstruktor - lädt Highscores beim Start
    public HighscoreManager() {
        highscores = new ArrayList<>();
        laden();
    }

    // Lädt Highscores aus Datei
    private void laden() {
        // Erstelle den NEUEN Ordner im Home-Verzeichnis
        File dataDir = new File(SAVE_DIR_PATH); //
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        File datei = new File(DATEI_NAME);

        // Datei existiert nicht? Dann erstelle leere Liste
        if (!datei.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(datei))) {
            String zeile;
            while ((zeile = reader.readLine()) != null) {
                // Zeile aufteilen: Name,Punkte,Genauigkeit,Level,Datum
                String[] teile = zeile.split(",");

                if (teile.length == 5) {
                    String name = teile[0];
                    int punkte = Integer.parseInt(teile[1]);
                    int genauigkeit = Integer.parseInt(teile[2]);
                    int level = Integer.parseInt(teile[3]);
                    String datum = teile[4];

                    highscores.add(new HighscoreEntry(name, punkte, genauigkeit, level, datum));
                }
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Laden der Highscores: " + e.getMessage());
        }

        sortieren();
    }

    // Speichert Highscores in Datei
    private void speichern() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATEI_NAME))) {
            for (HighscoreEntry entry : highscores) {
                writer.write(entry.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Speichern der Highscores: " + e.getMessage());
        }
    }

    // Sortiert nach Punkte (höchste zuerst)
    private void sortieren() {
        Collections.sort(highscores, new Comparator<HighscoreEntry>() {
            public int compare(HighscoreEntry a, HighscoreEntry b) {
                return b.getPunkte() - a.getPunkte();  // b - a = absteigend
            }
        });
    }

    // Fügt neuen Highscore hinzu
    public void hinzufuegen(String name, int punkte, int genauigkeit, int level) {
        // Aktuelles Datum
        LocalDateTime jetzt = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String datum = jetzt.format(formatter);

        // Neuen Entry erstellen
        HighscoreEntry neuerEntry = new HighscoreEntry(name, punkte, genauigkeit, level, datum);
        highscores.add(neuerEntry);

        sortieren();
        speichern();
    }

    // Gibt Top 10 zurück
    public List<HighscoreEntry> getTop10() {
        List<HighscoreEntry> top10 = new ArrayList<>();

        for (int i = 0; i < Math.min(10, highscores.size()); i++) {
            top10.add(highscores.get(i));
        }

        return top10;
    }

    // Gibt Platzierung für einen Score zurück (1 = bester)
    public int getPlatzierung(int punkte) {
        int platzierung = 1;

        for (HighscoreEntry entry : highscores) {
            if (entry.getPunkte() > punkte) {
                platzierung++;
            }
        }

        return platzierung;
    }

    // Gibt Anzahl aller Highscores zurück
    public int getAnzahl() {
        return highscores.size();
    }
}