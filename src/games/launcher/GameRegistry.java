package games.launcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Registry aller Spiele in der darkIT Spielesammlung.
 * Definiert die 6 Spiele mit allen Informationen.
 *
 * @author yusef03
 * @version 1.0
 */
public class GameRegistry {

    private static final List<GameInfo> GAMES = new ArrayList<>();

    // Statischer Block: Wird beim Laden der Klasse ausgeführt
    static {
        // Spiel 1: Phishing Defender (yusef03)
        GAMES.add(new GameInfo(
                "Phishing Defender",
                "Erkenne gefährliche Phishing-Mails und schütze dein System!",
                "phishing-defender.jar",
                "🛡️",
                "Quiz & Lernen",
                "Yusef Bach"
        ));

        // Spiel 2
        GAMES.add(new GameInfo(
                "SafeDigi Duell",
                "Beschreibung",
                "safedigi-duell.jar",
                "⚔️",
                "genre",
                "entwickler"
        ));

        // Spiel 3
        GAMES.add(new GameInfo(
                "Data Memory",
                "Beschreibung",
                "data-memory.jar",
                "🃏",
                "genre",
                "entwickler"
        ));

        // Spiel 4
        GAMES.add(new GameInfo(
                "Maze Adventure",
                "Beschreibung",
                "maze-adventure.jar",
                "🏃",
                "genre",
                "entwickler"
        ));

        // Spiel 5
        GAMES.add(new GameInfo(
                "Data Keeper",
                "Beschreibung",
                "data-keeper.jar",
                "👾",
                "genre",
                "entwickler"
        ));

        // Spiel 6
        GAMES.add(new GameInfo(
                "Stroop Sprint",
                "Beschreibung",
                "stroop-sprint.jar",
                "🌈",
                "genre",
                "entwickler"
        ));
    }

    /**
     * Gibt alle registrierten Spiele zurück.
     */
    public static List<GameInfo> getAllGames() {
        return new ArrayList<>(GAMES);
    }

    /**
     * Gibt die Anzahl der Spiele zurück.
     */
    public static int getGameCount() {
        return GAMES.size();
    }
}