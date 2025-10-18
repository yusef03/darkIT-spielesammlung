package games.phishingdefender;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Phishing Defender - HighscoreEntry Class
 * Speichert einen einzelnen Highscore-Eintrag
 * @author yusef03
 * @version 1.0
 */
public class HighscoreEntry {

    private String name;
    private int punkte;
    private int genauigkeit;
    private int level;
    private String datum;

    // Konstruktor
    public HighscoreEntry(String name, int punkte, int genauigkeit, int level, String datum) {
        this.name = name;
        this.punkte = punkte;
        this.genauigkeit = genauigkeit;
        this.level = level;
        this.datum = datum;
    }

    // Getter-Methoden
    public String getName() {
        return name;
    }

    public int getPunkte() {
        return punkte;
    }

    public int getGenauigkeit() {
        return genauigkeit;
    }

    public int getLevel() {
        return level;
    }

    public String getDatum() {
        return datum;
    }

    // Für Datei: Name,Punkte,Genauigkeit,Level,Datum
    public String toFileString() {
        return name + "," + punkte + "," + genauigkeit + "," + level + "," + datum;
    }

    // Für Debugging
    public String toString() {
        return name + ": " + punkte + " Punkte (Level " + level + ")";
    }
}