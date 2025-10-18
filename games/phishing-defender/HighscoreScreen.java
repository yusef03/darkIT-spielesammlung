package games.phishingdefender;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Phishing Defender - HighscoreScreen Class
 * Zeigt die Top 10 Highscores
 */
public class HighscoreScreen extends JPanel {

    private PhishingDefender hauptFenster;
    private HighscoreManager highscoreManager;

    public HighscoreScreen(PhishingDefender hauptFenster) {
        this.hauptFenster = hauptFenster;
        this.highscoreManager = new HighscoreManager();

        setLayout(new BorderLayout());
        setupUI();
    }

    private void setupUI() {
        // Animierter Hintergrund (wie Welcome Screen!)
        AnimatedBackgroundPanel backgroundPanel = new AnimatedBackgroundPanel();
        backgroundPanel.setLayout(new BorderLayout());

        // === TITEL ===
        JLabel titel = new JLabel("🏆 HIGHSCORES", JLabel.CENTER);
        titel.setFont(new Font("SansSerif", Font.BOLD, 48));
        titel.setForeground(new Color(255, 140, 80));
        titel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));

        // === HIGHSCORE TABELLE ===
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBackground(new Color(25, 30, 50, 230));
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 180, 255, 150), 3),
                BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));
        tablePanel.setMaximumSize(new Dimension(900, 550));

        // Top 10 laden
        List<HighscoreEntry> top10 = highscoreManager.getTop10();

        if (top10.isEmpty()) {
            // Keine Highscores vorhanden
            JLabel keineScores = new JLabel(
                    "<html><center>" +
                            "<span style='font-size: 18px; color: #AAAAAA;'>" +
                            "Noch keine Highscores vorhanden!<br><br>" +
                            "Spiele dein erstes Level um in die Rangliste zu kommen! 🎮" +
                            "</span></center></html>",
                    JLabel.CENTER
            );
            tablePanel.add(keineScores, BorderLayout.CENTER);

        } else {
            // Highscore-Einträge anzeigen
            JPanel entriesPanel = new JPanel();
            entriesPanel.setLayout(new BoxLayout(entriesPanel, BoxLayout.Y_AXIS));
            entriesPanel.setOpaque(false);

            for (int i = 0; i < top10.size(); i++) {
                HighscoreEntry entry = top10.get(i);
                JPanel entryPanel = createEntryPanel(i + 1, entry);
                entriesPanel.add(entryPanel);

                if (i < top10.size() - 1) {
                    entriesPanel.add(Box.createRigidArea(new Dimension(0, 8)));
                }
            }

            JScrollPane scrollPane = new JScrollPane(entriesPanel);
            scrollPane.setOpaque(false);
            scrollPane.getViewport().setOpaque(false);
            scrollPane.setBorder(null);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

// Scroll-Balken UNSICHTBAR machen!
            scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
            scrollPane.getVerticalScrollBar().setOpaque(false);

            tablePanel.add(scrollPane, BorderLayout.CENTER);
        }

        // === ZURÜCK BUTTON ===
        RoundedButton zurueckButton = new RoundedButton("← ZURÜCK");
        zurueckButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        zurueckButton.setBackground(new Color(80, 85, 110));
        zurueckButton.setColors(new Color(80, 85, 110), new Color(100, 105, 130));
        zurueckButton.setForeground(Color.WHITE);
        zurueckButton.setPreferredSize(new Dimension(200, 50));
        zurueckButton.setMaximumSize(new Dimension(200, 50));
        zurueckButton.addActionListener(e -> hauptFenster.zeigeWelcomeScreen());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 40, 0));
        buttonPanel.add(zurueckButton);

        // === CONTENT PANEL ===
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        titel.setAlignmentX(Component.CENTER_ALIGNMENT);
        tablePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPanel.add(titel);
        contentPanel.add(tablePanel);

        backgroundPanel.add(contentPanel, BorderLayout.CENTER);
        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(backgroundPanel, BorderLayout.CENTER);
    }

    // Erstellt ein einzelnes Highscore-Entry Panel
    private JPanel createEntryPanel(int platz, HighscoreEntry entry) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(15, 0));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        // Farben für Top 3
        Color textColor = Color.WHITE;
        String medal = "";

        if (platz == 1) {
            textColor = new Color(255, 215, 0);  // Gold
            medal = "🥇 ";
            panel.setBackground(new Color(255, 215, 0, 30));
            panel.setOpaque(true);
        } else if (platz == 2) {
            textColor = new Color(192, 192, 192);  // Silber
            medal = "🥈 ";
            panel.setBackground(new Color(192, 192, 192, 30));
            panel.setOpaque(true);
        } else if (platz == 3) {
            textColor = new Color(205, 127, 50);  // Bronze
            medal = "🥉 ";
            panel.setBackground(new Color(205, 127, 50, 30));
            panel.setOpaque(true);
        }

        // Platz
        JLabel platzLabel = new JLabel(medal + "#" + platz);
        platzLabel.setFont(new Font("Monospace", Font.BOLD, 16));
        platzLabel.setForeground(textColor);
        platzLabel.setPreferredSize(new Dimension(80, 30));

        // Name + Info
        String info = String.format(
                "<html><b style='font-size: 15px;'>%s</b>" +
                        "<span style='font-size: 12px; color: #888888;'> | " +
                        "⭐ %d Punkte | Level %d | %d%%</span></html>",
                entry.getName(),
                entry.getPunkte(),
                entry.getLevel(),
                entry.getGenauigkeit()
        );

        JLabel infoLabel = new JLabel(info);
        infoLabel.setForeground(textColor);

        // Datum rechts
        JLabel datumLabel = new JLabel(entry.getDatum());
        datumLabel.setFont(new Font("Monospace", Font.PLAIN, 14));
        datumLabel.setForeground(new Color(150, 150, 150));

        panel.add(platzLabel, BorderLayout.WEST);
        panel.add(infoLabel, BorderLayout.CENTER);
        panel.add(datumLabel, BorderLayout.EAST);

        return panel;
    }
}