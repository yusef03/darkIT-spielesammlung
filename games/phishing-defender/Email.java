package games.phishingdefender;

/**
 * Phishing Defender - Email Class
 * @author yusef03
 * @version 1.0
 */

// Klasse für eine einzelne E-Mail im Spiel
public class Email {

    private String absender;
    private String betreff;
    private String nachricht;
    private boolean istPhishing;

    // Konstruktor - erstellt eine neue E-Mail
    public Email(String absender, String betreff, String nachricht, boolean istPhishing) {
        this.absender = absender;
        this.betreff = betreff;
        this.nachricht = nachricht;
        this.istPhishing = istPhishing;
    }

    // Gibt den Absender zurück
    public String getAbsender() {
        return absender;
    }

    // Gibt den Betreff zurück
    public String getBetreff() {
        return betreff;
    }

    // Gibt die Nachricht zurück
    public String getNachricht() {
        return nachricht;
    }

    // Prüft ob es Phishing ist
    public boolean istPhishing() {
        return istPhishing;
    }

    // Für Debugging - zeigt E-Mail als Text
    public String toString() {
        return "Von: " + absender + "\nBetreff: " + betreff;
    }
}