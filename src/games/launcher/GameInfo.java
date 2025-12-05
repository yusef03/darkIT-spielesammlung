package games.launcher;

/**
 * Datenklasse für ein Spiel in der darkIT Spielesammlung.
 * Speichert Name, Beschreibung, JAR-Datei und Icon.
 *
 * @author yusef03
 * @version 1.0
 */
public class GameInfo {

    private final String name;
    private final String description;
    private final String jarFileName;
    private final String icon;
    private final String genre;
    private final String developer;

    /**
     * Erstellt ein neues GameInfo-Objekt.
     *
     * @param name Name des Spiels (z.B. "Phishing Defender")
     * @param description Kurzbeschreibung (z.B. "Erkenne Phishing-Mails")
     * @param jarFileName Name der JAR-Datei (z.B. "phishing-defender.jar")
     * @param icon Icon als Emoji (z.B. "🛡️")
     * @param genre Genre/Typ (z.B. "Quiz & Lernen")
     * @param developer Entwickler-Name
     */
    public GameInfo(String name, String description, String jarFileName,
                    String icon, String genre, String developer) {
        this.name = name;
        this.description = description;
        this.jarFileName = jarFileName;
        this.icon = icon;
        this.genre = genre;
        this.developer = developer;
    }

    // === GETTER ===

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getJarFileName() {
        return jarFileName;
    }

    public String getIcon() {
        return icon;
    }

    public String getGenre() {
        return genre;
    }

    public String getDeveloper() {
        return developer;
    }

    @Override
    public String toString() {
        return name + " (" + genre + ")";
    }
}