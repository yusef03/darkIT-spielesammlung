package games.phishingdefender;

import javax.swing.*;
import java.awt.*;

/**
 * Result Screen - MEGA VERSION!
 */
public class ResultScreen extends JPanel {

    private PhishingDefender hauptFenster;
    private int level;
    private int score;
    private int leben;
    private int maxLeben;
    private int gesamtEmails;
    private boolean gewonnen;
    private int erreichteSterne;
    private StarsManager starsManager;

    public ResultScreen(PhishingDefender hauptFenster, int level, int score,
                        int leben, int maxLeben, int gesamtEmails, boolean gewonnen) {
        this.hauptFenster = hauptFenster;
        this.level = level;
        this.score = score;
        this.leben = leben;
        this.maxLeben = maxLeben;
        this.gesamtEmails = gesamtEmails;
        this.gewonnen = gewonnen;

// Sterne berechnen (mit Spieler-Name!)
        starsManager = new StarsManager(hauptFenster.getSpielerName());
        int richtigeAntworten = score / 10; // 10 Punkte pro richtige Antwort
        this.erreichteSterne = gewonnen ? StarsManager.berechneSterne(richtigeAntworten, gesamtEmails, leben, maxLeben) : 0;

        // Sterne speichern (nur wenn gewonnen!)
// Sterne speichern (nur wenn gewonnen!)
        if (gewonnen) {
            starsManager.updateStars(level, erreichteSterne);

            // Highscore hinzufügen!
            HighscoreManager highscoreManager = new HighscoreManager();
            int genauigkeit = Math.min(100, (richtigeAntworten * 100) / gesamtEmails);
            highscoreManager.hinzufuegen(hauptFenster.getSpielerName(), score, genauigkeit, level);
        }

        setLayout(new BorderLayout());
        setupUI();
    }

    private void setupUI() {
        // Animierter Hintergrund
        AnimatedBackgroundPanel backgroundPanel = new AnimatedBackgroundPanel();
        backgroundPanel.setLayout(new BorderLayout());

        // === TOP: Großer Titel ===
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 30, 50));

        String titelText = gewonnen ? "🎉 LEVEL GESCHAFFT! 🎉" : "💔 LEVEL VERLOREN 💔";
        Color titelFarbe = gewonnen ? new Color(80, 200, 120) : new Color(255, 100, 100);

        JLabel titelLabel = new JLabel(titelText, JLabel.CENTER);
        titelLabel.setFont(new Font("SansSerif", Font.BOLD, 48));
        titelLabel.setForeground(titelFarbe);
        titelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel levelLabel = new JLabel("Mission " + level, JLabel.CENTER);
        levelLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        levelLabel.setForeground(new Color(180, 180, 180));
        levelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        topPanel.add(titelLabel);
        topPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        topPanel.add(levelLabel);

// === CENTER: Stats Cards (kleiner & eleganter) ===
        JPanel statsWrapper = new JPanel(new GridBagLayout());
        statsWrapper.setOpaque(false);

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(2, 2, 20, 20));
        statsPanel.setOpaque(false);
        statsPanel.setPreferredSize(new Dimension(700, 300));

        // Genauigkeit berechnen
        int genauigkeit = gesamtEmails > 0 ? (int)((double)(score / 10) / gesamtEmails * 100) : 0;

        // Rang berechnen
        int rang = getRangPlatzierung(score);

        // Stats Cards erstellen
        statsPanel.add(createStatCard("⭐", "PUNKTE", String.valueOf(score), new Color(255, 200, 80)));
        statsPanel.add(createStatCard("❤️", "LEBEN", leben + "/" + maxLeben, new Color(255, 100, 100)));
        statsPanel.add(createStatCard("🎯", "GENAUIGKEIT", genauigkeit + "%", new Color(100, 180, 255)));
        statsPanel.add(createStatCard("🏆", "RANG", "#" + rang + " von 26", new Color(255, 150, 50)));

        // === STERNE PANEL (nur wenn gewonnen!) ===
        JPanel sterneWrapper = new JPanel();
        sterneWrapper.setOpaque(false);
        sterneWrapper.setLayout(new BoxLayout(sterneWrapper, BoxLayout.Y_AXIS));
        sterneWrapper.setBorder(BorderFactory.createEmptyBorder(15, 50, 15, 50));

        if (gewonnen) {
            // Titel
            JLabel sterneTitel = new JLabel("ERREICHTE STERNE", JLabel.CENTER);
            sterneTitel.setFont(new Font("SansSerif", Font.BOLD, 18));
            sterneTitel.setForeground(new Color(180, 180, 180));
            sterneTitel.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Sterne Container
            JPanel sternePanel = new JPanel();
            sternePanel.setOpaque(false);
            sternePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));

            // 3 Sterne erstellen
            for (int i = 1; i <= 3; i++) {
                boolean erreicht = (i <= erreichteSterne);
                JLabel stern = createSternLabel(erreicht, i);
                sternePanel.add(stern);
            }

            // Text unter Sternen
            String sterneText = getSterneText(erreichteSterne);
            JLabel sterneTextLabel = new JLabel(sterneText, JLabel.CENTER);
            sterneTextLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
            sterneTextLabel.setForeground(new Color(255, 215, 0));
            sterneTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            sterneWrapper.add(sterneTitel);
            sterneWrapper.add(Box.createRigidArea(new Dimension(0, 10)));
            sterneWrapper.add(sternePanel);
            sterneWrapper.add(Box.createRigidArea(new Dimension(0, 10)));
            sterneWrapper.add(sterneTextLabel);
        }


        // === BOTTOM: Buttons ===
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 30));
        buttonPanel.setOpaque(false);

        if (gewonnen && level < 3) {
            // GEWONNEN + nicht letztes Level → Nächstes Level + Level Auswahl
            JButton naechstesButton = Theme.createStyledButton(
                    "▶ NÄCHSTES LEVEL",
                    Theme.FONT_BUTTON_LARGE,
                    Theme.COLOR_BUTTON_GREEN,
                    Theme.COLOR_BUTTON_GREEN_HOVER,
                    Theme.PADDING_BUTTON_LARGE
            );
            naechstesButton.setPreferredSize(new Dimension(280, 65));
            naechstesButton.addActionListener(e -> {
                hauptFenster.levelGeschafft(level);
                hauptFenster.starteLevel(level + 1);
            });

            JButton auswahlButton = Theme.createStyledButton(
                    "🎮 LEVEL AUSWAHL",
                    Theme.FONT_BUTTON_MEDIUM, // 18px (war 20)
                    Theme.COLOR_BUTTON_GREY,
                    Theme.COLOR_BUTTON_GREY_HOVER,
                    Theme.PADDING_BUTTON_LARGE // Großes Padding
            );
            auswahlButton.setPreferredSize(new Dimension(280, 65));
            auswahlButton.addActionListener(e -> {
                hauptFenster.levelGeschafft(level);
                hauptFenster.zeigeLevelAuswahl();
            });

            buttonPanel.add(naechstesButton);
            buttonPanel.add(auswahlButton);

        } else if (gewonnen && level >= 3) {
            // GEWONNEN + letztes Level → NUR Level Auswahl (+ Gratulation!)
            JLabel gratulationLabel = new JLabel("🎊 ALLE LEVEL GESCHAFFT! DU BIST EIN MEISTER! 🎊", JLabel.CENTER);
            gratulationLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
            gratulationLabel.setForeground(new Color(255, 215, 0));
            gratulationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel gratPanel = new JPanel();
            gratPanel.setOpaque(false);
            gratPanel.add(gratulationLabel);

            // HIER IST DIE ÄNDERUNG: JButton statt RoundedButton
            JButton auswahlButton = Theme.createStyledButton(
                    "🏆 ZURÜCK ZUR LEVEL AUSWAHL",
                    Theme.FONT_BUTTON_LARGE, // 22px
                    Theme.COLOR_BUTTON_GREEN,
                    Theme.COLOR_BUTTON_GREEN_HOVER,
                    Theme.PADDING_BUTTON_LARGE
            );
            auswahlButton.setPreferredSize(new Dimension(400, 65));
            auswahlButton.addActionListener(e -> {
                hauptFenster.levelGeschafft(level);
                hauptFenster.zeigeLevelAuswahl();
            });

            // DIESER TEIL HAT GEFEHLT:
            JPanel finalPanel = new JPanel();
            finalPanel.setLayout(new BoxLayout(finalPanel, BoxLayout.Y_AXIS));
            finalPanel.setOpaque(false);
            gratulationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            auswahlButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            finalPanel.add(gratPanel);
            finalPanel.add(Box.createRigidArea(new Dimension(0, 20)));
            finalPanel.add(auswahlButton);

            buttonPanel.add(finalPanel); // Jetzt existiert finalPanel

        } else {
            // VERLOREN → Wiederholen + Level Auswahl
            JButton wiederholenButton = Theme.createStyledButton(
                    "🔄 WIEDERHOLEN",
                    Theme.FONT_BUTTON_LARGE, // 22px
                    Theme.COLOR_ACCENT_ORANGE,
                    Theme.COLOR_ACCENT_ORANGE_HOVER,
                    Theme.PADDING_BUTTON_LARGE
            );
            wiederholenButton.setPreferredSize(new Dimension(280, 65));
            wiederholenButton.addActionListener(e -> hauptFenster.starteLevel(level));

            JButton auswahlButton = Theme.createStyledButton(
                    "🎮 LEVEL AUSWAHL",
                    Theme.FONT_BUTTON_MEDIUM, // 18px (war 20)
                    Theme.COLOR_BUTTON_GREY,
                    Theme.COLOR_BUTTON_GREY_HOVER,
                    Theme.PADDING_BUTTON_LARGE // Großes Padding
            );
            auswahlButton.setPreferredSize(new Dimension(280, 65));
            auswahlButton.addActionListener(e -> hauptFenster.zeigeLevelAuswahl());

            buttonPanel.add(wiederholenButton);
            buttonPanel.add(auswahlButton);
        }

        statsWrapper.add(statsPanel);

// Center Panel mit Stats + Sterne
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        centerPanel.add(statsPanel);
        if (gewonnen) {
            centerPanel.add(sterneWrapper);
        }

        backgroundPanel.add(topPanel, BorderLayout.NORTH);
        backgroundPanel.add(centerPanel, BorderLayout.CENTER);
        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(backgroundPanel);
    }

    // Erstellt eine Stat Card
    private JPanel createStatCard(String icon, String label, String value, Color accentColor) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int w = getWidth();
                int h = getHeight();

                // Schatten
                g2.setColor(new Color(0, 0, 0, 80));
                g2.fillRoundRect(5, 5, w - 5, h - 5, 20, 20);

                // Haupt-Card mit Gradient
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(35, 40, 65),
                        0, h, new Color(25, 30, 50)
                );
                g2.setPaint(gradient);
                g2.fillRoundRect(0, 0, w - 5, h - 5, 20, 20);

                // Border
                g2.setColor(new Color(accentColor.getRed(), accentColor.getGreen(),
                        accentColor.getBlue(), 150));
                g2.setStroke(new BasicStroke(3));
                g2.drawRoundRect(0, 0, w - 5, h - 5, 20, 20);

                // Glanz
                GradientPaint gloss = new GradientPaint(
                        0, 0, new Color(255, 255, 255, 25),
                        0, h / 2, new Color(255, 255, 255, 0)
                );
                g2.setPaint(gloss);
                g2.fillRoundRect(0, 0, w - 5, h / 2, 20, 20);

                g2.dispose();
            }
        };

        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setOpaque(false);
        card.setBorder(BorderFactory.createEmptyBorder(18, 15, 18, 15));

        // Icon (kleiner)
        JLabel iconLabel = new JLabel(icon, JLabel.CENTER);
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

// Label (kleiner)
        JLabel textLabel = new JLabel(label, JLabel.CENTER);
        textLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        textLabel.setForeground(new Color(140, 140, 140));
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

// Value (kleiner)
        JLabel valueLabel = new JLabel(value, JLabel.CENTER);
        valueLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        valueLabel.setForeground(accentColor);
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(iconLabel);
        card.add(Box.createRigidArea(new Dimension(0, 6)));
        card.add(textLabel);
        card.add(Box.createRigidArea(new Dimension(0, 4)));
        card.add(valueLabel);

        return card;
    }

    // Berechnet Rang-Platzierung
    private int getRangPlatzierung(int score) {
        HighscoreManager manager = new HighscoreManager();
        java.util.List<HighscoreEntry> top10 = manager.getTop10();

        int rang = 1;
        for (HighscoreEntry entry : top10) {
            if (score < entry.getPunkte()) {
                rang++;
            }
        }

        return Math.min(rang, 26);
    }

    // Erstellt ein Stern-Label (erreicht oder nicht)
    private JLabel createSternLabel(boolean erreicht, int sternNummer) {
        String emoji = erreicht ? "⭐" : "☆";

        JLabel stern = new JLabel(emoji, JLabel.CENTER);
        stern.setFont(new Font("Arial", Font.PLAIN, 60));
        stern.setForeground(erreicht ? new Color(255, 215, 0) : new Color(80, 80, 80));

        // Animation für erreichte Sterne
        if (erreicht) {
            Timer animTimer = new Timer(300 * sternNummer, e -> {
                // Sound-Effekt (optional)
                // Kurze Scale-Animation könnte hier hin
                stern.setFont(new Font("Arial", Font.PLAIN, 70));
                Timer scaleBack = new Timer(150, ev -> {
                    stern.setFont(new Font("Arial", Font.PLAIN, 60));
                });
                scaleBack.setRepeats(false);
                scaleBack.start();
            });
            animTimer.setRepeats(false);
            animTimer.start();
        }

        return stern;
    }

    // Gibt passenden Text für Sterne-Anzahl
    private String getSterneText(int sterne) {
        switch (sterne) {
            case 3: return "⭐⭐⭐ PERFEKT! MEISTERHAFT! 🏆";
            case 2: return "⭐⭐ SEHR GUT! WEITER SO! 💪";
            case 1: return "⭐ GESCHAFFT! VERSUCH'S NOCHMAL FÜR MEHR! 🎯";
            default: return "";
        }
    }
}