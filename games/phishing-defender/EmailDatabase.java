package games.phishingdefender;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Verwaltet alle E-Mails und wählt zufällig für jedes Level aus
public class EmailDatabase {

    // Level-Konfiguration - hier kannst du Anzahl der E-Mails pro Level einstellen
    private static final int LEVEL1_ECHTE = 6;
    private static final int LEVEL1_PHISHING = 4;

    private static final int LEVEL2_ECHTE = 7;
    private static final int LEVEL2_PHISHING = 8;

    private static final int LEVEL3_ECHTE = 10;
    private static final int LEVEL3_PHISHING = 10;

    // E-Mail Pools für jedes Level
    private List<Email> level1PoolEcht;
    private List<Email> level1PoolPhishing;

    private List<Email> level2PoolEcht;
    private List<Email> level2PoolPhishing;

    private List<Email> level3PoolEcht;
    private List<Email> level3PoolPhishing;

    public EmailDatabase() {
        level1PoolEcht = new ArrayList<>();
        level1PoolPhishing = new ArrayList<>();

        level2PoolEcht = new ArrayList<>();
        level2PoolPhishing = new ArrayList<>();

        level3PoolEcht = new ArrayList<>();
        level3PoolPhishing = new ArrayList<>();

        emailsErstellen();
    }

    // Erstellt alle E-Mail-Pools
    private void emailsErstellen() {
        erstelleLevel1Pool();
        erstelleLevel2Pool();
        erstelleLevel3Pool();
    }



    /**
     * ***********************************************************************************************************************************
     * ECHTE E-Mails Level 1 20 E-Mails: 10 echt, 10 phishing)
     * ***********************************************************************************************************************************
     */


    private void erstelleLevel1Pool() {

        level1PoolEcht.add(new Email(
                "info@youtube.com",
                "Dein Video wurde hochgeladen ✅",
                "Hey!\n\nDein Video 'Mein erstes Minecraft-Video' wurde erfolgreich hochgeladen und ist jetzt öffentlich!\n\nViel Erfolg mit deinem Kanal! 🎥\n\nDein YouTube Team",
                false,
                "💡 TIPP: Schau dir die E-Mail-Adresse an. Endet sie wirklich mit youtube.com? Gibt es Rechtschreibfehler?"
        ));

        level1PoolEcht.add(new Email(
                "noreply@minecraft.net",
                "Minecraft Update 1.20 ist da! 🎮",
                "Hallo Spieler!\n\nDas neue Minecraft Update ist verfügbar. Du kannst jetzt neue Blöcke und Kreaturen entdecken!\n\nStarte dein Spiel und lade das Update herunter.\n\nViel Spaß!\nDein Minecraft Team",
                false,
                "💡 TIPP: Prüfe die Domain am Ende der E-Mail-Adresse. Heißt Minecraft wirklich so?"

        ));

        level1PoolEcht.add(new Email(
                "support@netflix.com",
                "Neue Serien für dich 📺",
                "Hi!\n\nEs gibt neue Serien und Filme, die dir gefallen könnten.\n\nSchau dir unsere Empfehlungen in der App an!\n\nViel Spaß beim Schauen 😊\n\nDein Netflix Team",
                false,
                "💡 TIPP: Wird hier nach deinem Passwort gefragt? Gibt es Drohungen oder extremen Zeitdruck?"
        ));

        level1PoolEcht.add(new Email(
                "service@amazon.de",
                "Deine Bestellung wurde versendet 📦",
                "Hallo!\n\nDeine Bestellung Nr. 12345 wurde heute versendet.\n\nDu kannst dein Paket in 2-3 Tagen abholen.\n\nVielen Dank für deine Bestellung!\n\nDein Amazon Team",
                false,
                "💡 TIPP: Achte auf die Domain. Ist das wirklich Amazon? Wirkt die Nachricht professionell?"
        ));

        level1PoolEcht.add(new Email(
                "team@spotify.com",
                "Deine Lieblingssongs diese Woche 🎵",
                "Hey Musikfan!\n\nWir haben eine neue Playlist für dich erstellt mit deinen meistgehörten Songs.\n\nHör sie dir jetzt in der App an!\n\nDein Spotify Team",
                false,
                "💡 TIPP: Fragt die E-Mail nach persönlichen Daten wie Passwörtern? Wie viele Ausrufezeichen siehst du?"
        ));

        level1PoolEcht.add(new Email(
                "info@roblox.com",
                "Dein Avatar wurde aktualisiert ✨",
                "Hallo!\n\nDu hast deinen Avatar erfolgreich geändert.\n\nDeine neuen Items sind jetzt sichtbar.\n\nViel Spaß beim Spielen!\n\nDein Roblox Team",
                false,
                "💡 TIPP: Ist die E-Mail-Adresse korrekt geschrieben? Klingt die Nachricht freundlich oder panisch?"
        ));

        level1PoolEcht.add(new Email(
                "noreply@discord.com",
                "Willkommen bei Discord! 💬",
                "Hey!\n\nSchön, dass du da bist. Discord hilft dir mit Freunden zu chatten und zu spielen.\n\nSchau dir unsere Tipps für neue Nutzer an.\n\nViel Spaß!\nDein Discord Team",
                false,
                "💡 TIPP: Prüfe die Domain nach dem @-Zeichen. Wird sofortiges Handeln verlangt?"
        ));

        level1PoolEcht.add(new Email(
                "support@disneyplus.com",
                "Neue Filme sind online 🎬",
                "Hallo!\n\nEs gibt neue Disney-Filme und Serien für dich.\n\nLogge dich ein und entdecke alle Neuheiten!\n\nDein Disney+ Team",
                false,
                "💡 TIPP: Schau dir die E-Mail-Adresse genau an. Sind alle Wörter richtig geschrieben?"
        ));

        level1PoolEcht.add(new Email(
                "info@twitch.tv",
                "Dein Lieblingsstreamer ist live 🔴",
                "Hey!\n\nDer Streamer dem du folgst ist jetzt live und spielt Fortnite.\n\nSchau vorbei und chatte mit!\n\nDein Twitch Team",
                false,
                "💡 TIPP: Ist die Domain am Ende echt? Fragt die Nachricht nach Login-Daten?"
        ));

        level1PoolEcht.add(new Email(
                "noreply@epicgames.com",
                "Fortnite: Neue Season startet bald 🎮",
                "Hallo Spieler!\n\nDie neue Fortnite Season beginnt nächste Woche.\n\nFreue dich auf neue Skins und Herausforderungen!\n\nDein Epic Games Team",
                false,
                "💡 TIPP: Achte auf die E-Mail-Adresse. Passt 'epicgames.com' zu Epic Games?"
        ));




        /**
         * ***********************************************************************************************************************************
         * Phishing E-Mails Level 1
         * ***********************************************************************************************************************************
         */




        level1PoolPhishing.add(new Email(
                "youutube-gewinn@fake.com",
                "DU HAST GEWONNEN!!! 💰💰💰",
                "GLÜCKWUNSCH!!!\n\nDu hast 1 MILLION EURO gewonnen bei YouTube Gewinnspiel!!! 💰💰💰\n\nKlicke SOFORT auf Link und gib dein Passwort ein!!!\n\nNUR HEUTE GÜLTIG!!! BEEIL DICH!!!",
                true,
                "💡 TIPP: Zähle die Buchstaben 'u' in 'youutube'. Wie viele sollten es sein? Verschenkt YouTube wirklich Millionen?"
        ));

        level1PoolPhishing.add(new Email(
                "hacker999@evil.com",
                "DEIN KONTO WIRD GELÖSCHT!!!",
                "WARNUNG!!! ⚠️⚠️⚠️\n\nDein Mincraft Konto wird in 1 STUNDE GELÖSCHT!!!\n\nGib uns SOFORT dein Passwort sonst ist alles weg!!!\n\nSCHNELL SCHNELL SCHNELL!!!",
                true,
                "💡 TIPP: Würde eine echte Firma eine Domain mit 'evil' oder 'hacker' im Namen nutzen? Ist 'Mincraft' richtig geschrieben?"
        ));

        level1PoolPhishing.add(new Email(
                "netflixx-support@fake.ru",
                "GRATIS NETFLIX FÜR IMMER!!!",
                "Hey du!!!\n\nWir schenken dir NETFLIX KOMPLETT KOSTENLOS für dein ganzes Leben!!! 📺📺📺\n\nAber du must JETZT deine Kreditkarte eingeben!!!\n\nNur noch 5 Minuten Zeit!!!",
                true,
                "💡 TIPP: Wie viele 'x' hat Netflix normalerweise? Schau dir auch die Domain-Endung (.ru) an. Ist Netflix wirklich kostenlos?"
        ));

        level1PoolPhishing.add(new Email(
                "roooblox-free@scam.com",
                "10000 ROBUX GESCHENKT!!! 💎💎💎",
                "KRASS!!!\n\nWir geben dir 10000 ROBUX KOMPLETT GRATIS!!! 💎💎💎\n\nKlicke auf Link und gib Benutzername und Passwort ein!!!\n\nSCHNELL befor es zu spät ist!!!",
                true,
                "💡 TIPP: Zähle die 'o' in 'roooblox'. Ist das richtig? Würde Roblox eine Domain mit 'scam' nutzen?"
        ));

        level1PoolPhishing.add(new Email(
                "amazon-gewinner@fake.de",
                "DEIN iPHONE WARTET AUF DICH!!!",
                "GLÜKWUNSCH!!! 📱📱📱\n\nDu hast ein IPHONE 15 PRO MAX GEWONNEN bei Amazon Verlosung!!!\n\nAber du must HEUTE NOCH deine Adresse und Passwort schiken!!!\n\nSONST VERFÄLT DER GEWINN!!!",
                true,
                "💡 TIPP: Ist 'Glükwunsch' richtig geschrieben? Verschenkt Amazon einfach so teure Handys per E-Mail?"
        ));

        level1PoolPhishing.add(new Email(
                "spotfy-prämium@fake.com",
                "KOSTENLOS PREMIUM!!! 🎵🎵🎵",
                "HEY DU!!!\n\nWir geben dir Spotfy Premium GRATIS für 100 jahre!!! 🎵\n\nAber nur wen du SOFORT auf Link klickst und Passwort eingibst!!!\n\nNUR NOCH 10 MINUTEN!!!",
                true,
                "💡 TIPP: Fehlt bei 'Spotfy' nicht ein Buchstabe? Gibt es wirklich Premium für 100 Jahre gratis?"
        ));

        level1PoolPhishing.add(new Email(
                "discord-admin@scammer.ru",
                "DEIN ACCOUNT WURDE GEHACKT!!!",
                "ACHTUNG!!! ⚠️⚠️⚠️\n\nEin Hacker hat dein Discord Account!!! 😱😱😱\n\nGib uns SOFORT dein Passwort damit wir dir helfen können!!!\n\nWENN NICHT DANN IST ALLES WEG!!!",
                true,
                "💡 TIPP: Schau dir das Wort 'scammer' in der E-Mail-Adresse an. Was bedeutet das? Fragt Discord nach Passwörtern?"
        ));

        level1PoolPhishing.add(new Email(
                "fortnite-kostenlos@fake.com",
                "ALLE SKINS GRATIS!!! 🎮🎮🎮",
                "KRAAASS!!!\n\nWir schenken dir ALLE FORTNITE SKINS die es gibt KOSTENLOS!!! 🎮\n\nAber du must in 5 MINUTEN dein Passwort eingeben!!!\n\nSCHNELL SCHNELL BEVOR ES ZU SPÄT IST!!!",
                true,
                "💡  TIPP:Wie viel Zeit bekommst du zum Reagieren? Ist das realistisch? Verschenkt Fortnite alle Skins gratis?"
        ));

        level1PoolPhishing.add(new Email(
                "tiktok-verifizierung@fake.net",
                "1 MILLION FOLLOWER GESCHENKT!!!",
                "HEY!!! 🎉🎉🎉\n\nWir machen dich BERÜHMT auf TikTok!!! Du bekomst 1 MILLION FOLLOWER GRATIS!!!\n\nAber nur wen du JETZT SOFORT dein Passwort schikst!!!\n\nNUR FÜR DICH!!!",
                true,
                "💡 TIPP: Kann man wirklich 1 Million Follower geschenkt bekommen? Ist 'schikst' richtig geschrieben?"
        ));

        level1PoolPhishing.add(new Email(
                "whatsapp-sicherheit@scam.com",
                "DEIN HANDY WURDE GEHACKED!!!",
                "HILFE!!! 😱😱😱\n\nDein WhatsApp wurde von Kriminelen gehackt!!! Alle deine Nachrichten sind in Gefahr!!!\n\nSchike uns SOFORT dein Passwort damit wir dich retten können!!!\n\nNUR NOCH 2 MINUTEN ZEIT!!!",
                true,
                "💡 TIPP: Sind 'gehacked' und 'Kriminelen' richtig geschrieben? Nur 2 Minuten Zeit - ist das realistisch?"
        ));

    }




    /**
     * ***********************************************************************************************************************************
     * ECHTE E-Mails Level 2        (30 E-Mails: 15 echt, 15 phishing)
     * ***********************************************************************************************************************************
     */




    private void erstelleLevel2Pool() {
        level2PoolEcht.add(new Email(
                "security@youtube.com",
                "Neue Anmeldung auf deinem Konto",
                "Hallo,\n\nwir haben eine neue Anmeldung auf deinem YouTube-Konto festgestellt.\n\nGerät: iPhone 12\nOrt: Berlin, Deutschland\nZeit: Heute, 14:30 Uhr\n\nWenn du das warst, brauchst du nichts zu tun. Falls nicht, ändere bitte dein Passwort in den Kontoeinstellungen.\n\nDein YouTube Sicherheitsteam",
                false,
                "💡 TIPP: Ist die Domain korrekt? Wird hier etwas von dir verlangt oder nur informiert?"
        ));

        level2PoolEcht.add(new Email(
                "billing@spotify.com",
                "Deine Rechnung für Premium 💳",
                "Hallo,\n\nvielen Dank für dein Spotify Premium Abo!\n\nWir haben heute 4,99€ von deinem Konto abgebucht.\n\nDeine Rechnung findest du im Anhang oder in deinen Kontoeinstellungen.\n\nBei Fragen sind wir für dich da.\n\nDein Spotify Team",
                false,
                "💡 TIPP: Prüfe die E-Mail-Adresse genau. Fragt die E-Mail nach sensiblen Daten?"
        ));

        level2PoolEcht.add(new Email(
                "noreply@nintendo.com",
                "Dein Nintendo Switch Online läuft bald ab ⏰",
                "Hallo!\n\nDein Nintendo Switch Online Abo endet in 7 Tagen.\n\nWenn du weiterspielen möchtest, verlängere dein Abo in den Einstellungen.\n\nDu kannst das jederzeit in deinem Nintendo Account machen.\n\nDein Nintendo Team",
                false,
                "💡 TIPP: Ist die Domain echt? Wie viel Zeit bekommst du (7 Tage oder nur Stunden)?"
        ));

        level2PoolEcht.add(new Email(
                "community@roblox.com",
                "Neue Sicherheitseinstellungen verfügbar 🔒",
                "Hallo!\n\nWir haben neue Sicherheitsoptionen für dein Konto hinzugefügt.\n\nDu kannst jetzt die Zwei-Faktor-Authentifizierung aktivieren, um dein Konto besser zu schützen.\n\nSchau in deine Kontoeinstellungen für mehr Infos.\n\nDein Roblox Team",
                false,
                "💡 TIPP: Schau auf die E-Mail-Adresse. Wird dir etwas angeboten oder wird etwas gefordert?"
        ));

        level2PoolEcht.add(new Email(
                "support@epicgames.com",
                "Deine Anfrage wurde bearbeitet ✅",
                "Hallo,\n\nwir haben deine Support-Anfrage erhalten und bearbeitet.\n\nTicket-Nummer: #45678\n\nDein Problem sollte jetzt behoben sein. Falls du noch Fragen hast, antworte einfach auf diese E-Mail.\n\nDein Epic Games Support Team",
                false,
                "💡 TIPP: Ist eine Ticket-Nummer angegeben? Wirkt die E-Mail professionell?"
        ));

        level2PoolEcht.add(new Email(
                "orders@amazon.de",
                "Lieferung verzögert sich leider 📦",
                "Hallo,\n\nleider verzögert sich die Lieferung deiner Bestellung Nr. 78945 um 2 Tage.\n\nNeues Lieferdatum: 18. Oktober 2025\n\nWir entschuldigen uns für die Unannehmlichkeiten.\n\nDein Amazon Team",
                false,
                "💡 TIPP: Gibt es eine echte Bestellnummer? Wird gedroht oder entschuldigt sich die E-Mail?"
        ));

        level2PoolEcht.add(new Email(
                "info@minecraft.net",
                "Minecraft Realms: Zahlung fehlgeschlagen",
                "Hallo,\n\ndie Zahlung für deinen Realms-Server konnte nicht durchgeführt werden.\n\nBitte überprüfe deine Zahlungsmethode in den Kontoeinstellungen.\n\nDein Server bleibt noch 3 Tage aktiv, damit du Zeit hast, das Problem zu lösen.\n\nDein Minecraft Team",
                false,
                "💡 TIPP: Wie viel Zeit gibt dir die E-Mail zum Reagieren? Ist das realistisch?"
        ));

        level2PoolEcht.add(new Email(
                "noreply@netflix.com",
                "Wir vermissen dich! Komm zurück 💙",
                "Hey,\n\ndu hast Netflix schon eine Weile nicht mehr genutzt.\n\nWir haben viele neue Serien und Filme, die dir gefallen könnten!\n\nSchau doch mal wieder vorbei.\n\nDein Netflix Team",
                false,
                "💡 TIPP: Prüfe die Domain. Wird hier Druck gemacht oder nur freundlich eingeladen?"
        ));

        level2PoolEcht.add(new Email(
                "team@discord.com",
                "Dein Server wurde gemeldet",
                "Hallo,\n\nein Nutzer hat deinen Discord-Server gemeldet.\n\nWir prüfen den Fall gerade. Falls wir gegen unsere Regeln verstoßen wurde, melden wir uns bei dir.\n\nMehr Infos zu unseren Community-Richtlinien findest du auf unserer Webseite.\n\nDein Discord Trust & Safety Team",
                false,
                "💡 TIPP: Ist die E-Mail-Adresse korrekt? Wird sofort gesperrt oder nur informiert?"
        ));

        level2PoolEcht.add(new Email(
                "updates@twitch.tv",
                "Neue Funktion: Clips speichern 🎬",
                "Hey Streamer!\n\nDu kannst jetzt deine besten Clips automatisch speichern lassen.\n\nAktiviere die Funktion in deinen Creator-Einstellungen.\n\nViel Erfolg mit deinem Kanal!\n\nDein Twitch Team",
                false,
                "💡 TIPP: Achte auf die Domain. Werden neue Features erklärt oder wird etwas verlangt?"
        ));

        level2PoolEcht.add(new Email(
                "service@playstation.com",
                "PlayStation Plus: Neue Gratisspiele 🎮",
                "Hallo,\n\ndiesen Monat gibt es neue Gratisspiele für PlayStation Plus Mitglieder.\n\nLade sie bis Ende des Monats herunter, dann gehören sie dir!\n\nViel Spaß beim Zocken!\n\nDein PlayStation Team",
                false,
                "💡 TIPP: Ist die Domain echt? Sind die Gratisspiele für bestehende Mitglieder oder 'für alle'?"
        ));

        level2PoolEcht.add(new Email(
                "help@tiktok.com",
                "Dein Video wurde überprüft",
                "Hi!\n\nDein Video wurde von unserem Team überprüft und entspricht unseren Community-Richtlinien.\n\nEs bleibt online und ist für alle sichtbar.\n\nWeiter so!\n\nDein TikTok Team",
                false,
                "💡 TIPP: Schau dir die E-Mail-Adresse an. Ist das eine Bestätigung oder eine Forderung?"
        ));

        level2PoolEcht.add(new Email(
                "no-reply@apple.com",
                "Deine App wurde aktualisiert 📱",
                "Hallo,\n\ndie App 'Minecraft' wurde auf deinem iPhone aktualisiert.\n\nVersion 1.20.5 ist jetzt installiert.\n\nDu findest die Neuerungen in den App-Infos.\n\nDein Apple Team",
                false,
                "💡 TIPP: Prüfe die Domain. Wird hier nur informiert oder musst du etwas tun?"
        ));

        level2PoolEcht.add(new Email(
                "notifications@instagram.com",
                "Jemand hat dein Foto geliked ❤️",
                "Hey!\n\nDein Foto hat 50 neue Likes bekommen!\n\nSchau dir an, wer dein Foto geliked und kommentiert hat.\n\nÖffne die Instagram App, um mehr zu sehen.\n\nDein Instagram Team",
                false,
                "💡 TIPP: Ist die E-Mail-Adresse korrekt geschrieben? Fragt sie nach Passwörtern?"
        ));

        level2PoolEcht.add(new Email(
                "support@zoom.us",
                "Dein Meeting startet in 15 Minuten ⏰",
                "Hallo,\n\ndein Zoom-Meeting 'Online-Unterricht Mathe' beginnt um 15:00 Uhr.\n\nMeeting-ID: 123-456-789\n\nKlicke auf den Link in deinem Kalender, um beizutreten.\n\nDein Zoom Team",
                false,
                "💡 TIPP: Gibt es konkrete Meeting-Details? Wirkt die E-Mail wie eine normale Erinnerung?"
        ));




        /**
         * ***********************************************************************************************************************************
         * Phishing E-Mails Level 2
         * ***********************************************************************************************************************************
         */


        level2PoolPhishing.add(new Email(
                "security-alert@youtube-verify.com",
                "Verdächtige Aktivität auf deinem Konto! ⚠️",
                "Hallo,\n\nwir haben ungewöhnliche Aktivitäten auf deinem YouTube-Konto entdeckt.\n\nJemand versucht sich von einem fremden Gerät anzumelden!\n\nBitte bestätige deine Identität innerhalb von 24 Stunden, sonst müssen wir dein Konto vorübergehend sperren.\n\nKlicke hier zur Verifizierung.\n\nYouTube Sicherheitsteam",
                true,
                "💡 TIPP: Schau genau auf die Domain nach dem @. Steht da 'youtube.com' oder etwas mit Bindestrich? Wie viele Stunden bleiben dir?"
        ));

        level2PoolPhishing.add(new Email(
                "prizes@roblox-event.com",
                "Du wurdest ausgewählt! 🎉",
                "Glückwunsch!\n\nDu wurdest zufällig ausgewählt, um 2500 Robux zu gewinnen.\n\nUm deinen Preis zu erhalten, musst du nur deine Roblox-Benutzerdaten bestätigen.\n\nDieses Angebot ist nur 48 Stunden gültig.\n\nRoblox Gewinnspiel-Team",
                true,
                "💡 TIPP: Ist 'roblox-event.com' die echte Roblox-Domain? Verschenkt Roblox einfach so Robux?"
        ));

        level2PoolPhishing.add(new Email(
                "billing-support@spotify-help.net",
                "Problem mit deiner Zahlung 💳",
                "Hallo,\n\ndeine letzte Zahlung für Spotify Premium konnte nicht durchgeführt werden.\n\nBitte aktualisiere deine Zahlungsinformationen in den nächsten 3 Tagen, sonst wird dein Account deaktiviert.\n\nZur Aktualisierung hier klicken.\n\nSpotify Abrechnungsteam",
                true,
                "💡 TIPP: Achte auf Bindestriche in der Domain. Endet die echte Spotify-Domain mit '.net'? Wird mit Deaktivierung gedroht?"
        ));

        level2PoolPhishing.add(new Email(
                "noreply@minecraft-update.com",
                "Wichtiges Sicherheits-Update verfügbar 🔒",
                "Hallo Spieler,\n\nein kritisches Sicherheits-Update für Minecraft ist verfügbar.\n\nOhne dieses Update könntest du Probleme beim Spielen haben.\n\nLade das Update jetzt herunter und gib deine Login-Daten ein, um fortzufahren.\n\nMinecraft Update-Team",
                true,
                "💡 TIPP: Hat die echte Minecraft-Domain einen Bindestrich? Fragt Minecraft per E-Mail nach Login-Daten?"
        ));

        level2PoolPhishing.add(new Email(
                "account-review@netflix-service.com",
                "Dein Konto wird überprüft",
                "Hallo,\n\nwir überprüfen gerade alle Netflix-Konten auf verdächtige Aktivitäten.\n\nBitte bestätige deine Kontodaten innerhalb von 72 Stunden.\n\nFalls du nicht reagierst, müssen wir dein Konto leider sperren.\n\nNetflix Sicherheitsteam",
                true,
                "💡 TIPP: Vergleiche 'netflix-service.com' mit der echten Netflix-Domain. Wie viele Stunden Frist - ist das realistisch?"
        ));

        level2PoolPhishing.add(new Email(
                "support@discord-nitro.net",
                "Kostenloses Discord Nitro für dich! 🎁",
                "Hey!\n\nWir verschenken Discord Nitro an zufällig ausgewählte Nutzer.\n\nDu bist einer der Glücklichen!\n\nUm dein 3-Monate Nitro-Abo zu aktivieren, musst du nur deine Konto-Informationen bestätigen.\n\nDiscord Promo-Team",
                true,
                "💡 TIPP: Schau auf die Domain. Gehört 'discord-nitro.net' wirklich zu Discord? Verschenkt Discord Nitro zufällig?"
        ));

        level2PoolPhishing.add(new Email(
                "orders-update@amazon-delivery.de",
                "Deine Paketzustellung braucht Bestätigung 📦",
                "Hallo,\n\ndein Paket kann nicht zugestellt werden, weil wir deine Adresse nicht verifizieren konnten.\n\nBitte bestätige deine Lieferadresse in den nächsten 24 Stunden.\n\nSonst wird das Paket zurückgeschickt.\n\nAmazon Logistik",
                true,
                "💡 TIPP: Ist 'amazon-delivery.de' die echte Amazon-Domain? Musst du bei echten Paketen deine Adresse per E-Mail bestätigen?"
        ));

        level2PoolPhishing.add(new Email(
                "team@fortnite-rewards.com",
                "Exklusive Season-Belohnung wartet! 🎮",
                "Hey Spieler!\n\nDu hast genug Punkte für eine exklusive Season-Belohnung gesammelt.\n\nHole dir jetzt deinen kostenlosen Battle Pass!\n\nGib einfach deine Epic Games Daten ein, um die Belohnung zu erhalten.\n\nFortnite Belohnungs-Team",
                true,
                "💡 TIPP: Gehört 'fortnite-rewards.com' zu Epic Games? Gibt es kostenlose Battle Passes per E-Mail?"
        ));

        level2PoolPhishing.add(new Email(
                "verification@tiktok-official.com",
                "Verifiziere dein Konto für mehr Reichweite 📈",
                "Hi Creator!\n\nVerifizierte Konten bekommen mehr Views und Follower.\n\nWir können dein Konto kostenlos verifizieren!\n\nDu musst nur deine Login-Daten eingeben, um den Prozess zu starten.\n\nTikTok Verifizierungs-Team",
                true,
                "💡 TIPP: Auch wenn 'official' draufsteht - ist 'tiktok-official.com' die echte Domain? Kostet Verifizierung normalerweise Geld?"
        ));

        level2PoolPhishing.add(new Email(
                "info@playstation-giveaway.com",
                "PlayStation 5 Gewinnspiel 🎮",
                "Glückwunsch!\n\nDu nimmst jetzt am PS5-Gewinnspiel teil.\n\nUm deine Gewinnchancen zu erhöhen, teile bitte deine PlayStation-Kontodaten.\n\nGewinner werden in 2 Wochen bekannt gegeben.\n\nPlayStation Gewinnspiel-Team",
                true,
                "💡 TIPP: Ist das die echte PlayStation-Domain? Warum würden sie nach Kontodaten fragen für ein Gewinnspiel?"
        ));

        level2PoolPhishing.add(new Email(
                "alert@instagram-security.com",
                "Jemand hat versucht, dein Passwort zu ändern! 🔐",
                "Achtung!\n\nEs gab einen Versuch, dein Instagram-Passwort zu ändern.\n\nWenn du das nicht warst, solltest du sofort handeln!\n\nVerifiziere dein Konto jetzt, um es zu schützen.\n\nInstagram Sicherheits-Team",
                true,
                "💡 TIPP: Vergleiche mit der echten Instagram-Domain. Wie dringend klingt die Nachricht - ist das normal?"
        ));

        level2PoolPhishing.add(new Email(
                "customer-service@apple-support.net",
                "Dein iCloud-Speicher ist voll ☁️",
                "Hallo,\n\ndein iCloud-Speicher ist zu 98% voll.\n\nWir können dir helfen, mehr Speicher freizugeben.\n\nGib einfach deine Apple-ID und Passwort ein, um fortzufahren.\n\nApple Support-Team",
                true,
                "💡 TIPP: Endet die echte Apple-Domain mit '.net'? Fragt Apple per E-Mail nach deiner Apple-ID und Passwort?"
        ));

        level2PoolPhishing.add(new Email(
                "rewards@twitch-partner.com",
                "Werde Twitch-Partner! 🌟",
                "Hey Streamer!\n\nDu erfüllst die Voraussetzungen für das Twitch-Partner-Programm.\n\nPartner verdienen Geld mit ihren Streams!\n\nBestätige deine Kontodaten, um den Antrag zu starten.\n\nTwitch Partner-Team",
                true,
                "💡 TIPP: Ist 'twitch-partner.com' die echte Twitch-Domain? Braucht eine Partnerschaft wirklich Kontodaten per E-Mail?"
        ));

        level2PoolPhishing.add(new Email(
                "service@whatsapp-verify.com",
                "Bestätige deine Telefonnummer 📱",
                "Hallo,\n\nwir müssen deine Telefonnummer für WhatsApp überprüfen.\n\nOhne Bestätigung könntest du keine Nachrichten mehr senden.\n\nBitte gib deinen Verifizierungscode innerhalb von 24 Stunden ein.\n\nWhatsApp Verifizierungs-Team",
                true,
                "💡 TIPP: Schau auf die Domain. Verifiziert WhatsApp per E-Mail oder in der App? Wie viele Stunden Zeit bekommst du?"
        ));

        level2PoolPhishing.add(new Email(
                "notifications@steam-deals.com",
                "Deine Wunschlisten-Spiele sind im Angebot! 💰",
                "Hey Gamer!\n\nViele Spiele von deiner Steam-Wunschliste sind jetzt 90% reduziert.\n\nDieses Angebot gilt nur für die nächsten 12 Stunden!\n\nLogge dich ein, um die Deals nicht zu verpassen.\n\nSteam Angebots-Team",
                true,
                "💡 TIPP: Ist das die echte Steam-Domain? Sind 90% Rabatt nur für 12 Stunden realistisch?"
        ));


    }






    /**
     * ***********************************************************************************************************************************
     * ECHTE E-Mails Level 3         (40 E-Mails: 20 echt, 20 phishing)
     * ***********************************************************************************************************************************
     */

    private void erstelleLevel3Pool() {

        level3PoolEcht.add(new Email(
                "security@accounts.google.com",
                "Sicherheitswarnung für dein Google-Konto",
                "Hallo,\n\nwir haben eine Anmeldung von einem neuen Gerät festgestellt.\n\nGerät: Samsung Galaxy\nOrt: München, Deutschland\nZeit: 14. Oktober, 16:45 Uhr\n\nWenn du das warst, kannst du diese E-Mail ignorieren. Falls nicht, sichere dein Konto sofort über myaccount.google.com\n\nGoogle Sicherheitsteam",
                false,
                "💡 TIPP: Lies die Domain ganz genau Buchstabe für Buchstabe. Ist ein 's' am Ende von 'account'? Wird etwas verlangt?"
        ));

        level3PoolEcht.add(new Email(
                "payment@paypal.com",
                "Zahlung erhalten - 15,99 EUR",
                "Hallo,\n\ndu hast eine Zahlung erhalten.\n\nVon: mama.mueller@gmail.com\nBetrag: 15,99 EUR\nBetreff: Taschengeld\n\nDas Geld ist jetzt auf deinem PayPal-Konto verfügbar.\n\nDein PayPal-Team",
                false,
                "💡 TIPP: Zähle die Buchstaben in 'paypal'. Sind alle Buchstaben vorhanden? Sind konkrete Details angegeben?"
        ));

        level3PoolEcht.add(new Email(
                "noreply@steampowered.com",
                "Steam Guard: Neues Gerät autorisiert",
                "Hallo,\n\nein neues Gerät wurde für dein Steam-Konto autorisiert.\n\nGerät: Windows PC\nStandort: Berlin\n\nFalls du das nicht warst, ändere sofort dein Passwort und widerrufe die Autorisierung in deinen Kontoeinstellungen.\n\nSteam Support",
                false,
                "💡 TIPP: Wie heißt die echte Steam-Domain - steam.com oder steampowered.com? Wird hier informiert oder gefordert?"
        ));

        level3PoolEcht.add(new Email(
                "account-update@blizzard.com",
                "Battle.net: Passwort erfolgreich geändert",
                "Hallo,\n\ndein Battle.net Passwort wurde soeben geändert.\n\nFalls du diese Änderung nicht vorgenommen hast, kontaktiere sofort unseren Support.\n\nÄnderungsdatum: 14. Okt. 2025, 18:30 Uhr\n\nBlizzard Entertainment",
                false,
                "💡 TIPP: Schau auf die Domain nach dem @. Gibt dir die E-Mail Optionen oder setzt sie dich unter Druck?"
        ));

        level3PoolEcht.add(new Email(
                "do-not-reply@microsoft.com",
                "Ungewöhnliche Anmeldeaktivität",
                "Hallo,\n\nwir haben eine Anmeldung bei deinem Microsoft-Konto von einem unbekannten Standort erkannt.\n\nWenn du das warst, ist alles in Ordnung. Falls nicht, überprüfe deine Kontosicherheit unter account.microsoft.com\n\nMicrosoft-Kontoteam",
                false,
                "💡 TIPP: Ist die Domain korrekt? Wie wird die Nachricht formuliert - panisch oder informativ?"
        ));

        level3PoolEcht.add(new Email(
                "verification@twitter.com",
                "Bestätige deine E-Mail-Adresse",
                "Hey,\n\ndu hast deine E-Mail-Adresse bei Twitter geändert.\n\nBitte bestätige die neue Adresse, indem du auf den Link in dieser E-Mail klickst.\n\nDer Link ist 24 Stunden gültig.\n\nTwitter-Team",
                false,
                "💡 TIPP: Prüfe die E-Mail-Adresse. Hast du selbst eine Änderung vorgenommen? Wie viel Zeit gibt dir die E-Mail?"
        ));

        level3PoolEcht.add(new Email(
                "receipts@humblebundle.com",
                "Danke für deinen Einkauf! 🎮",
                "Hallo,\n\nvielen Dank für deinen Kauf bei Humble Bundle.\n\nBestellnummer: HB-78945-2025\nBetrag: 12,00 EUR\nProdukt: Indie Game Bundle\n\nDeine Download-Links findest du in deiner Bibliothek.\n\nHumble Bundle",
                false,
                "💡 TIPP: Schau auf die Domain. Sind konkrete Bestelldetails angegeben wie Nummer und Betrag?"
        ));

        level3PoolEcht.add(new Email(
                "no-reply@ea.com",
                "EA Account: Login von neuem Standort",
                "Hallo,\n\nes gab eine Anmeldung bei deinem EA-Konto von einem neuen Standort.\n\nStandort: Frankfurt, Deutschland\nZeit: Heute, 12:15 Uhr\n\nWenn du das nicht warst, sichere dein Konto unter help.ea.com\n\nElectronic Arts",
                false,
                "💡 TIPP: Ist die Domain korrekt? Wird hier nur gewarnt oder sofort etwas gefordert?"
        ));

        level3PoolEcht.add(new Email(
                "info@bandainamcoent.eu",
                "Deine Vorbestellung wurde bestätigt ✅",
                "Hallo,\n\ndeine Vorbestellung für 'Elden Ring DLC' wurde bestätigt.\n\nBestellnummer: BN-45612\nVoraussichtliches Release: 25. Oktober 2025\n\nWir informieren dich, sobald das Spiel verfügbar ist.\n\nBandai Namco Entertainment",
                false,
                "💡 TIPP: Auch komplizierte Domains können echt sein. Gibt es eine echte Bestellnummer? Klingt es professionell?"
        ));

        level3PoolEcht.add(new Email(
                "community@ubisoft.com",
                "Ubisoft Connect: Zwei-Faktor-Authentifizierung aktiviert",
                "Hallo,\n\ndu hast die Zwei-Faktor-Authentifizierung für dein Ubisoft-Konto aktiviert.\n\nDein Konto ist jetzt besser geschützt.\n\nDanke, dass du deine Sicherheit ernst nimmst!\n\nUbisoft Support",
                false,
                "💡 TIPP: Prüfe die Domain. Bestätigt die E-Mail etwas, das du selbst gemacht hast?"
        ));

        level3PoolEcht.add(new Email(
                "support@epicgames.com",
                "Rückerstattung genehmigt",
                "Hallo,\n\ndeine Rückerstattungsanfrage für 'Rocket League Credits' wurde genehmigt.\n\nBetrag: 9,99 EUR\nRückerstattung innerhalb von 3-5 Werktagen\n\nTicket-Nummer: #EG-78945\n\nEpic Games Kundendienst",
                false,
                "💡 TIPP: Achte auf die E-Mail-Adresse. Gibt es eine Ticket-Nummer und konkrete Details?"
        ));

        level3PoolEcht.add(new Email(
                "orders@nintendo.com",
                "Nintendo eShop: Bestellbestätigung",
                "Hallo,\n\nvielen Dank für deinen Einkauf im Nintendo eShop.\n\nProdukt: Zelda: Tears of the Kingdom\nBetrag: 59,99 EUR\nBestellnummer: NO-895647\n\nDas Spiel wird auf deine Switch heruntergeladen.\n\nNintendo",
                false,
                "💡 TIPP: Ist die Domain echt? Sind Bestellnummer und Betrag angegeben?"
        ));

        level3PoolEcht.add(new Email(
                "noreply@origin.com",
                "Origin: Dein Wunschlisten-Spiel ist reduziert",
                "Hey,\n\nein Spiel von deiner Wunschliste ist jetzt im Angebot:\n\nSims 4: Bundle - 60% Rabatt\nJetzt 19,99 EUR statt 49,99 EUR\n\nAngebot gültig bis 20. Oktober.\n\nOrigin Team",
                false,
                "💡 TIPP: Schau auf die Domain. Klingt das Angebot realistisch oder zu gut um wahr zu sein?"
        ));

        level3PoolEcht.add(new Email(
                "hello@gog.com",
                "GOG Galaxy: Update verfügbar",
                "Hallo,\n\nein Update für GOG Galaxy ist verfügbar.\n\nVersion 2.0.75\nNeue Features und Bugfixes\n\nDie App wird beim nächsten Start automatisch aktualisiert.\n\nGOG.com",
                false,
                "💡 TIPP: Ist die Domain korrekt? Wird hier nur informiert oder musst du sofort handeln?"
        ));

        level3PoolEcht.add(new Email(
                "team@crunchyroll.com",
                "Neue Anime-Folgen verfügbar! 📺",
                "Hi Anime-Fan,\n\nneve Folgen deiner Lieblings-Serien sind jetzt verfügbar:\n\n- One Piece: Folge 1089\n- My Hero Academia: Staffel 7, Folge 3\n\nSchau sie dir jetzt an!\n\nCrunchyroll",
                false,
                "💡 TIPP: Prüfe die E-Mail-Adresse. Werden konkrete Folgen-Nummern genannt?"
        ));

        level3PoolEcht.add(new Email(
                "info@hbomax.com",
                "HBO Max wird zu Max",
                "Hallo,\n\nHBO Max heißt jetzt einfach 'Max'.\n\nAlle deine Inhalte und Einstellungen bleiben gleich. Du musst nichts tun.\n\nDie App wird automatisch aktualisiert.\n\nMax Team",
                false,
                "💡 TIPP: Schau auf die Domain. Wird etwas von dir verlangt oder nur informiert?"
        ));

        level3PoolEcht.add(new Email(
                "notify@reddit.com",
                "Dein Post hat 100 Upvotes bekommen! ⬆️",
                "Hey u/deinusername,\n\ndein Post in r/gaming ist beliebt!\n\nTitel: 'Meine Minecraft-Basis nach 200 Stunden'\n100 Upvotes, 23 Kommentare\n\nSchau dir die Kommentare an!\n\nReddit",
                false,
                "💡 TIPP: Ist die Domain korrekt geschrieben? Sind konkrete Details wie Upvotes angegeben?"
        ));

        level3PoolEcht.add(new Email(
                "no-reply@battle.net",
                "Battle.net: Geschenk erhalten 🎁",
                "Hallo,\n\nein Freund hat dir ein Geschenk geschickt!\n\nGeschenk: Overwatch 2 - Legendary Skin\nVon: MaxGamer123\n\nDas Geschenk wurde deinem Konto hinzugefügt.\n\nBlizzard Entertainment",
                false,
                "💡 TIPP: Achte auf die Domain. Wird ein konkreter Absender genannt der dir etwas geschenkt hat?"
        ));

        level3PoolEcht.add(new Email(
                "updates@unity.com",
                "Unity: Dein Projekt wurde gespeichert ☁️",
                "Hallo,\n\ndein Unity-Projekt 'MeinSpiel_v2' wurde erfolgreich in die Cloud gespeichert.\n\nLetzte Änderung: 14. Okt. 2025, 17:30 Uhr\nGröße: 245 MB\n\nZugriff jederzeit über unity.com\n\nUnity Technologies",
                false,
                "💡 TIPP: Prüfe die E-Mail-Adresse. Gibt es konkrete technische Details wie Größe und Zeitpunkt?"
        ));

        level3PoolEcht.add(new Email(
                "billing@adobe.com",
                "Adobe Creative Cloud: Rechnung Oktober 2025",
                "Hallo,\n\ndeine monatliche Rechnung ist verfügbar.\n\nBetrag: 12,99 EUR\nDatum: 14. Oktober 2025\nRechnungsnummer: AD-785469\n\nDownload unter account.adobe.com\n\nAdobe Systems",
                false,
                "💡 TIPP: Ist die Domain echt? Gibt es eine konkrete Rechnungsnummer und Datum?"
        ));




        /**
         * ***********************************************************************************************************************************
         * Phishing E-Mails Level 3
         * ***********************************************************************************************************************************
         */

        level3PoolPhishing.add(new Email(
                "security@account-google.com",
                "Sicherheitswarnung für dein Google-Konto",
                "Hallo,\n\nwir haben einen verdächtigen Anmeldeversuch festgestellt.\n\nGerät: Unbekanntes Android-Gerät\nOrt: Russland\nZeit: 14. Oktober, 17:20 Uhr\n\nÜberprüfe deine Kontoaktivität sofort unter diesem Link, um dein Konto zu schützen.\n\nGoogle Sicherheitsteam",
                true,
                "💡 TIPP: GANZ GENAU hinschauen! Steht da 'account' oder 'accounts' (mit s)? Aus welchem Land kommt der Anmeldeversuch?"
        ));

        level3PoolPhishing.add(new Email(
                "support@paypa1.com",
                "PayPal: Ungewöhnliche Transaktion erkannt",
                "Hallo,\n\nwir haben eine ungewöhnliche Transaktion über 299 EUR festgestellt.\n\nEmpfänger: TechStore_Online\nBetrag: 299,00 EUR\n\nFalls du diese Zahlung nicht autorisiert hast, bestätige bitte deine Identität innerhalb von 24 Stunden.\n\nPayPal Sicherheitsteam",
                true,
                "💡 TIPP: ACHTUNG! Ist das ein kleines L oder eine EINS (1) in 'paypa1'? Klicke auf die Adresse und schau genau hin!"
        ));

        level3PoolPhishing.add(new Email(
                "noreply@steam-community.com",
                "Steam: Verdächtige Aktivität auf deinem Konto",
                "Hallo,\n\njemand hat versucht, teure Items aus deinem Inventar zu handeln.\n\nWir haben die Transaktion vorläufig blockiert.\n\nBitte verifiziere dein Konto innerhalb von 48 Stunden, um es zu entsperren.\n\nSteam Guard Team",
                true,
                "💡 TIPP: Hat die echte Steam-Domain das Wort 'community' drin? Fragt Steam per E-Mail nach Verifizierung?"
        ));

        level3PoolPhishing.add(new Email(
                "account@blizzard-entertainment.com",
                "Battle.net: Kontoüberprüfung erforderlich",
                "Hallo,\n\nwir führen routinemäßige Sicherheitsüberprüfungen durch.\n\nDein Konto wurde für eine Überprüfung ausgewählt.\n\nBitte bestätige deine Kontodaten innerhalb von 72 Stunden, um Einschränkungen zu vermeiden.\n\nBlizzard Sicherheitsteam",
                true,
                "💡 TIPP: Ist 'blizzard-entertainment.com' die echte Domain oder nur 'blizzard.com'? Wie viele Stunden Frist - ist das normal?"
        ));

        level3PoolPhishing.add(new Email(
                "verify@microsoft-account.com",
                "Microsoft: Bestätige deine Identität",
                "Hallo,\n\nzur Verbesserung der Sicherheit bitten wir dich, deine Identität zu bestätigen.\n\nDieser Prozess dauert nur 2 Minuten.\n\nOhne Bestätigung könnte dein Konto eingeschränkt werden.\n\nMicrosoft-Sicherheitsteam",
                true,
                "💡 TIPP: Schau genau: 'microsoft-account.com' oder 'microsoft.com'? Fragt Microsoft per E-Mail nach Identitätsbestätigung?"
        ));

        level3PoolPhishing.add(new Email(
                "security-team@twitter-official.com",
                "Twitter: Ungewöhnliche Login-Aktivität",
                "Hey,\n\nes gab mehrere fehlgeschlagene Login-Versuche bei deinem Twitter-Account.\n\nStandort: Ukraine\nVersuche: 7\n\nSichere dein Konto jetzt, indem du deine Login-Daten aktualisierst.\n\nTwitter Sicherheits-Team",
                true,
                "💡 TIPP: Auch mit 'official' im Namen - ist das die echte Twitter-Domain? Aus welchem Land kommen die Login-Versuche?"
        ));

        level3PoolPhishing.add(new Email(
                "help@ea-support.com",
                "EA: Dein Konto wurde temporär eingeschränkt",
                "Hallo,\n\nwir haben Aktivitäten auf deinem Konto festgestellt, die gegen unsere Nutzungsbedingungen verstoßen könnten.\n\nDein Konto ist vorläufig eingeschränkt.\n\nReiche einen Einspruch ein, um die Einschränkung aufzuheben.\n\nEA Support Team",
                true,
                "💡 TIPP: Vergleiche 'ea-support.com' mit der echten EA-Domain. Sperrt EA sofort ohne vorherige Warnung?"
        ));

        level3PoolPhishing.add(new Email(
                "notification@epicgames-store.com",
                "Epic Games: Kostenlose V-Bucks für Fortnite",
                "Hallo,\n\nals Dankeschön für deine Treue schenken wir dir 1000 V-Bucks.\n\nDiese Aktion ist zeitlich begrenzt und gilt nur für ausgewählte Spieler.\n\nLöse deinen Code innerhalb der nächsten 48 Stunden ein.\n\nEpic Games Promotions",
                true,
                "💡 TIPP: Ist 'epicgames-store.com' die echte Domain? Werden V-Bucks an 'ausgewählte Spieler' verschenkt?"
        ));

        level3PoolPhishing.add(new Email(
                "service@nintendo-eshop.com",
                "Nintendo: Zahlungsproblem bei deinem Abo",
                "Hallo,\n\ndie Zahlung für dein Nintendo Switch Online Abo konnte nicht durchgeführt werden.\n\nBitte aktualisiere deine Zahlungsmethode innerhalb von 5 Tagen.\n\nSonst wird dein Abo deaktiviert und du verlierst den Zugriff auf Online-Funktionen.\n\nNintendo Billing",
                true,
                "💡 TIPP: Hat die echte Nintendo-Domain einen Bindestrich? Wie wird gedroht - subtil oder direkt?"
        ));

        level3PoolPhishing.add(new Email(
                "updates@discord-app.com",
                "Discord: Wichtiges Sicherheitsupdate",
                "Hey,\n\nein kritisches Sicherheitsupdate ist verfügbar.\n\nOhne dieses Update könnte dein Konto gefährdet sein.\n\nInstalliere das Update jetzt, indem du deine Kontodaten bestätigst.\n\nDiscord Security Team",
                true,
                "💡 TIPP: Ist 'discord-app.com' die echte Discord-Domain? Brauchen Updates wirklich Kontodaten?"
        ));

        level3PoolPhishing.add(new Email(
                "alerts@spotify-premium.com",
                "Spotify: Dein Abo läuft ab",
                "Hallo,\n\ndein Spotify Premium Abo endet in 3 Tagen.\n\nUm Unterbrechungen zu vermeiden, bestätige bitte deine Zahlungsinformationen.\n\nAktuelle Mitglieder erhalten 2 Monate gratis bei sofortiger Verlängerung.\n\nSpotify Membership Team",
                true,
                "💡 TIPP: Schau auf die Domain nach dem @. Gibt es '2 Monate gratis' bei normaler Verlängerung?"
        ));

        level3PoolPhishing.add(new Email(
                "info@roblox-support.com",
                "Roblox: Jemand hat dein Passwort zurückgesetzt",
                "Hallo,\n\nes wurde eine Passwort-Zurücksetzung für dein Konto beantragt.\n\nWenn du das nicht warst, sichere dein Konto sofort!\n\nKlicke hier, um die Zurücksetzung zu blockieren.\n\nRoblox Account Security",
                true,
                "💡 TIPP: Vergleiche mit der echten Roblox-Domain. Wo würdest du eine Passwort-Zurücksetzung blockieren - per E-Mail-Link oder in der App?"
        ));

        level3PoolPhishing.add(new Email(
                "team@netflix-billing.com",
                "Netflix: Aktualisierung deiner Zahlungsdaten nötig",
                "Hallo,\n\ndeine Kreditkarte ist abgelaufen.\n\nBitte aktualisiere deine Zahlungsinformationen, um dein Netflix-Abo fortzusetzen.\n\nOhne Aktualisierung wird dein Konto in 7 Tagen deaktiviert.\n\nNetflix Payment Services",
                true,
                "💡 TIPP: Ist das die echte Netflix-Domain? Wie wird gedroht - wie viele Tage bleiben dir?"

        ));

        level3PoolPhishing.add(new Email(
                "verify@instagram-team.com",
                "Instagram: Verifizierungsabzeichen verfügbar ✓",
                "Hi,\n\ndein Account erfüllt die Voraussetzungen für ein Verifizierungsabzeichen.\n\nDer blaue Haken erhöht deine Reichweite und Glaubwürdigkeit.\n\nStarte den Verifizierungsprozess jetzt - nur noch 50 Plätze verfügbar!\n\nInstagram Verification Team",
                true,
                "💡 TIPP: Schau auf die Domain. Ist das Verifizierungsabzeichen kostenlos oder kostet es Geld?"
        ));

        level3PoolPhishing.add(new Email(
                "support@twitch-partners.com",
                "Twitch: Du wurdest zum Partner nominiert",
                "Hey Streamer,\n\nGlückwunsch! Du wurdest für das Twitch-Partner-Programm nominiert.\n\nPartner verdienen durchschnittlich 500 EUR pro Monat.\n\nSchließe deine Bewerbung ab, indem du deine Kontodaten verifizierst.\n\nTwitch Partnership Team",
                true,
                "💡 TIPP: Ist das die echte Twitch-Domain? Wirst du für Partnerschaften nominiert oder musst du dich bewerben?"
        ));

        level3PoolPhishing.add(new Email(
                "noreply@amazon-security.de",
                "Amazon: Verdächtige Bestellung erkannt",
                "Hallo,\n\nwir haben eine verdächtige Bestellung über 459 EUR auf deinem Konto entdeckt.\n\nProdukt: iPhone 15 Pro\nBestellnummer: DE-998847\n\nFalls du diese Bestellung nicht aufgegeben hast, storniere sie innerhalb von 12 Stunden.\n\nAmazon Fraud Detection",
                true,
                "💡 TIPP: Vergleiche mit der echten Amazon-Domain. Wie viele Stunden Zeit - ist das realistisch bei so teuren Bestellungen?"
        ));

        level3PoolPhishing.add(new Email(
                "alerts@apple-id.com",
                "Apple: Deine Apple-ID wurde auf einem neuen Gerät verwendet",
                "Hallo,\n\ndeine Apple-ID wurde auf einem neuen Gerät angemeldet.\n\nGerät: MacBook Pro\nStandort: Polen\n\nWenn du das nicht warst, sperre den Zugriff sofort über diesen Link.\n\nApple Security",
                true,
                "💡 TIPP: Ist 'apple-id.com' die echte Apple-Domain? Aus welchem Land kommt die Anmeldung - wirkt das verdächtig?"
        ));

        level3PoolPhishing.add(new Email(
                "prizes@playstation-rewards.com",
                "PlayStation: Exklusive Belohnung für treue Spieler 🎮",
                "Hallo,\n\naufgrund deiner Spielzeit hast du dich für eine exklusive Belohnung qualifiziert.\n\nBelohnung: 50 EUR PlayStation Store Guthaben\n\nLöse deinen Code innerhalb von 72 Stunden ein.\n\nPlayStation Rewards Program",
                true,
                "💡 TIPP: Schau auf die Domain nach dem @. Verschenkt PlayStation Guthaben für 'Spielzeit'?"
        ));

        level3PoolPhishing.add(new Email(
                "notifications@tiktok-creators.com",
                "TikTok: Creator Fund Zahlung ausstehend 💰",
                "Hi Creator,\n\ndeine Creator Fund Zahlung von 127 EUR steht bereit.\n\nZahlungszeitraum: September 2025\n\nVerifiziere deine Zahlungsinformationen, um die Auszahlung zu erhalten.\n\nTikTok Creator Fund Team",
                true,
                "💡 TIPP: Ist das die echte TikTok-Domain? Werden Creator Fund Zahlungen per E-Mail-Link oder über die App abgewickelt?"
        ));

        level3PoolPhishing.add(new Email(
                "service@minecraft-realms.net",
                "Minecraft: Dein Realms-Server wird gelöscht",
                "Hallo,\n\ndein Realms-Server konnte nicht verlängert werden, da die Zahlung fehlgeschlagen ist.\n\nDer Server wird in 48 Stunden permanent gelöscht.\n\nAlle deine Welten und Fortschritte gehen verloren!\n\nAktualisiere deine Zahlungsdaten sofort.\n\nMinecraft Realms Support",
                true,
                "💡 TIPP: Prüfe die Domain genau. Wie wird gedroht - 48 Stunden bis permanente Löschung? Ist das realistisch?"
        ));

    }

    // Gibt zufällig ausgewählte E-Mails für ein Level zurück
    public List<Email> getEmailsFuerLevel(int level) {
        List<Email> ausgewaehlteEmails = new ArrayList<>();

        if (level == 1) {
            ausgewaehlteEmails = waehleZufaelligeEmails(
                    level1PoolEcht,
                    level1PoolPhishing,
                    LEVEL1_ECHTE,
                    LEVEL1_PHISHING
            );
        } else if (level == 2) {
            ausgewaehlteEmails = waehleZufaelligeEmails(
                    level2PoolEcht,
                    level2PoolPhishing,
                    LEVEL2_ECHTE,
                    LEVEL2_PHISHING
            );
        } else if (level == 3) {
            ausgewaehlteEmails = waehleZufaelligeEmails(
                    level3PoolEcht,
                    level3PoolPhishing,
                    LEVEL3_ECHTE,
                    LEVEL3_PHISHING
            );
        }

        return ausgewaehlteEmails;
    }

    // Wählt zufällig X echte und Y phishing E-Mails aus und mischt sie
    private List<Email> waehleZufaelligeEmails(List<Email> poolEcht, List<Email> poolPhishing,
                                               int anzahlEchte, int anzahlPhishing) {
        List<Email> resultat = new ArrayList<>();

        // Kopien der Pools erstellen um Original nicht zu verändern
        List<Email> echtKopie = new ArrayList<>(poolEcht);
        List<Email> phishingKopie = new ArrayList<>(poolPhishing);

        // Pools mischen
        Collections.shuffle(echtKopie);
        Collections.shuffle(phishingKopie);

        // X echte E-Mails hinzufügen
        for (int i = 0; i < anzahlEchte && i < echtKopie.size(); i++) {
            resultat.add(echtKopie.get(i));
        }

        // Y phishing E-Mails hinzufügen
        for (int i = 0; i < anzahlPhishing && i < phishingKopie.size(); i++) {
            resultat.add(phishingKopie.get(i));
        }

        // Finale Liste mischen damit echt und phishing gemischt sind
        Collections.shuffle(resultat);

        return resultat;
    }

    // Gibt alle E-Mails zurück (für Tests oder Statistiken)
    public List<Email> getAlleEmails() {
        List<Email> alle = new ArrayList<>();
        alle.addAll(level1PoolEcht);
        alle.addAll(level1PoolPhishing);
        alle.addAll(level2PoolEcht);
        alle.addAll(level2PoolPhishing);
        alle.addAll(level3PoolEcht);
        alle.addAll(level3PoolPhishing);
        return alle;
    }
}