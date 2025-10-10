package games.phishingdefender;
import java.util.ArrayList;
import java.util.List;

/**
 * Phishing Defender - Email Database Class
 * @author yusef03
 * @version 1.0
 */

// Speichert alle E-Mails für das Spiel
public class EmailDatabase {

    private List<Email> alleEmails;

    public EmailDatabase() {
        alleEmails = new ArrayList<>();
        emailsErstellen();
    }

    // Erstellt alle 20 E-Mails
    private void emailsErstellen() {

        // ECHTE E-MAILS (10 Stück)

        alleEmails.add(new Email(
                "newsletter@techmagazin.de",
                "Newsletter erfolgreich abonniert",
                "Hallo!\n\nVielen Dank für dein Interesse an unserem Newsletter.\n" +
                        "Du erhältst ab jetzt wöchentliche Tech-News.\n\n" +
                        "Abmelden: https://techmagazin.de/abmelden\n\n" +
                        "Viele Grüße,\nDas TechMagazin Team",
                false
        ));

        alleEmails.add(new Email(
                "sekretariat@realschule-muster.de",
                "Erinnerung: Elternabend am 15. Oktober",
                "Liebe Eltern,\n\nwir möchten Sie an den Elternabend am 15.10.\n" +
                        "um 18:00 Uhr in der Aula erinnern.\n\n" +
                        "Bei Fragen: Telefon 0123-456789\n\n" +
                        "Mit freundlichen Grüßen\nSchulleitung",
                false
        ));

        alleEmails.add(new Email(
                "info@dhl.de",
                "Ihr Paket wird heute zugestellt",
                "Guten Tag,\n\nIhre Sendung 12345678 wird heute zwischen\n" +
                        "14-18 Uhr zugestellt.\n\n" +
                        "Sendungsverfolgung: dhl.de\n\nDHL Kundenservice",
                false
        ));

        alleEmails.add(new Email(
                "stadtbibliothek@stadt-muster.de",
                "Erinnerung: Buch-Rückgabe",
                "Hallo,\n\ndas Buch \"Java lernen\" ist in 3 Tagen fällig.\n" +
                        "Verlängerung möglich unter: bibliothek.stadt-muster.de\n\n" +
                        "Öffnungszeiten: Mo-Fr 10-18 Uhr\n\nStadtbibliothek Muster",
                false
        ));

        alleEmails.add(new Email(
                "vorstand@fussball-sv-muster.de",
                "Training fällt aus",
                "Liebe Spieler,\n\ndas Training am Mittwoch fällt wegen\n" +
                        "Hallensanierung aus.\n\nNächster Termin: Samstag 10 Uhr\n\n" +
                        "Sportliche Grüße\nTrainer Schmidt",
                false
        ));

        alleEmails.add(new Email(
                "bestellung@buchladen-online.de",
                "Bestellbestätigung #789456",
                "Vielen Dank für Ihre Bestellung!\n\n" +
                        "Artikel: \"Python Crashkurs\"\nBetrag: 29,99 €\n" +
                        "Versand: 2-3 Werktage\n\nRechnung im Anhang.\n\nBuchladen Online",
                false
        ));

        alleEmails.add(new Email(
                "praxis@zahnarzt-mueller.de",
                "Termin-Erinnerung",
                "Guten Tag Herr Mustermann,\n\nwir erinnern Sie an Ihren Termin:\n" +
                        "Datum: 05.11.2024, 15:00 Uhr\n\n" +
                        "Bei Verhinderung bitte 24h vorher absagen.\nTel: 0234-567890\n\n" +
                        "Praxis Dr. Müller",
                false
        ));

        alleEmails.add(new Email(
                "lisa.schmidt@gmail.com",
                "Geburtstag nächste Woche!",
                "Hey!\n\nNächsten Samstag ist mein Geburtstag.\n" +
                        "Party bei mir ab 18 Uhr\n\nBringst du Kuchen mit?\n\nLG Lisa",
                false
        ));

        alleEmails.add(new Email(
                "wetter@wetterservice.de",
                "Wettervorhersage für Ihre Region",
                "Guten Morgen!\n\nHeute: Sonnig, 22°C\nMorgen: Bewölkt, Regen möglich\n\n" +
                        "Detailprognose: wetterservice.de\n\nIhr Wetter-Team",
                false
        ));

        alleEmails.add(new Email(
                "dekanat@uni-muster.de",
                "Vorlesungsausfall Prof. Weber",
                "Liebe Studierende,\n\ndie Vorlesung \"Algorithmen\" am Donnerstag\n" +
                        "entfällt krankheitsbedingt.\n\n" +
                        "Nachholtermin wird noch bekannt gegeben.\n\nDekanat Informatik",
                false
        ));

        // PHISHING E-MAILS (10 Stück)

        alleEmails.add(new Email(
                "service@sparkasse-sicherheit.com",
                "DRINGEND: Konto gesperrt!",
                "Sehr geehrter Kunde,\n\nIhr Konto wurde aus Sicherheitsgründen gesperrt.\n\n" +
                        "► JETZT ENTSPERREN: bit.ly/entsperr123\n\n" +
                        "Ohne Bestätigung wird Ihr Konto in 24h gelöscht!\n\nSparkasse Sicherheitsteam",
                true
        ));

        alleEmails.add(new Email(
                "noreply@paypal-verify.net",
                "Ungewöhnliche Aktivität erkannt",
                "Hallo,\n\nwir haben verdächtige Aktivitäten in Ihrem Konto festgestellt.\n\n" +
                        "Bitte bestätigen Sie Ihre Identität hier:\n" +
                        "www.paypal-sicherheit.com/verify\n\nPayPal Sicherheit",
                true
        ));

        alleEmails.add(new Email(
                "dpd-service@mail.ru",
                "Ihr Paket konnte nicht zugestellt werden",
                "Ihr Paket wartet auf Abholung!\n\nZahlen Sie 2€ Gebühr:\n" +
                        "http://dpd-retoure.tk/pay\n\nTracking: 99999999\n\nDPD Express",
                true
        ));

        alleEmails.add(new Email(
                "netflix@account-update.org",
                "Zahlungsmethode aktualisieren",
                "Hallo,\n\nIhre Zahlung ist fehlgeschlagen.\n" +
                        "Aktualisieren Sie Ihre Kreditkarte sofort:\n\n" +
                        "► UPDATE: netflix-payment.com/update\n\nIhr Netflix Team",
                true
        ));

        alleEmails.add(new Email(
                "amazon-service@gmailcom",
                "Gewinnspiel: Sie haben gewonnen!",
                "Herzlichen Glückwunsch!\n\nSie wurden als Gewinner ausgewählt:\n" +
                        "iPhone 15 Pro GRATIS!\n\n" +
                        "Jetzt anfordern: amzn-gift.tk/claim?user=12345\n\nAmazon Prime",
                true
        ));

        alleEmails.add(new Email(
                "security@microsoft-alert.com",
                "Ihr PC ist infiziert!",
                "WARNUNG!\n\nIhr Windows wurde mit 5 Viren infiziert.\n\n" +
                        "Scannen Sie JETZT:\nwindows-fix.download/scan\n\nMicrosoft Support Team",
                true
        ));

        alleEmails.add(new Email(
                "finanzamt@steuer-rueckerstattung.info",
                "Steuerrückerstattung: 850€",
                "Guten Tag,\n\nSie haben Anspruch auf 850€ Steuerrückerstattung!\n\n" +
                        "Formular ausfüllen:\nwww.finanzamt-formular.com/claim\n\nFinanzamt Deutschland",
                true
        ));

        alleEmails.add(new Email(
                "support@facebook-security.net",
                "Freundschaftsanfrage von Lisa Müller",
                "Hallo,\n\nLisa Müller möchte mit dir befreundet sein.\n\n" +
                        "Profil ansehen: fb-login.com/profile\n\nJetzt bestätigen!\n\nFacebook Team",
                true
        ));

        alleEmails.add(new Email(
                "gewinnspiel@gmx.com",
                "GLÜCKWUNSCH! 5000€ gewonnen!",
                "Sie sind der 1.000.000 Besucher!!!\n\nFordern Sie jetzt 5000€ an:\n" +
                        "www.gewinn-sofort.tk/claim\n\nNur heute gültig!\n\nGewinnspiel-Zentrale",
                true
        ));

        alleEmails.add(new Email(
                "polizei@warnung-deutschland.com",
                "WICHTIG: Vorladung",
                "Sehr geehrte/r Bürger/in,\n\ngegen Sie liegt eine Anzeige vor.\n\n" +
                        "Details einsehen: polizei-vorladung.net/id=9876\n\n" +
                        "Bei Ignorieren: Haftbefehl!\n\nPolizeidirektion",
                true
        ));
    }

    // Gibt alle E-Mails zurück
    public List<Email> getAlleEmails() {
        return alleEmails;
    }

    // Gibt E-Mails für ein bestimmtes Level zurück
    public List<Email> getEmailsFuerLevel(int level) {
        List<Email> levelEmails = new ArrayList<>();

        if (level == 1) {
            // Level 1: Erste 5 echte + erste 5 phishing (10 E-Mails)
            for (int i = 0; i < 10; i++) {
                levelEmails.add(alleEmails.get(i));
            }
        } else if (level == 2) {
            // Level 2: 5 echte + 10 phishing (15 E-Mails)
            for (int i = 5; i < 20; i++) {
                levelEmails.add(alleEmails.get(i));
            }
        } else if (level == 3) {
            // Level 3: Alle 20 E-Mails
            levelEmails.addAll(alleEmails);
        }

        return levelEmails;
    }
}