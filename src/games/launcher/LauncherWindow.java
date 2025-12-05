package games.launcher;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.*;

/**
 * Hauptfenster des darkIT Launchers.
 * Zeigt alle 6 Spiele in einem 2x3 Grid.
 *
 * @author yusef03
 * @version 1.0
 */
public class LauncherWindow extends JFrame {

    private int lastVolumeBeforeMute = 30; // Merkt sich die Lautstärke
    private JSlider volumeSlider;          // Machen wir zur Member-Variable
    private JLabel volumeIcon;             // Machen wir auch zur Member-Variable
    private int volumeEventCounter = 0;  // Zählt Slider-Events

    public LauncherWindow() {
        setTitle("Launcher - 03yusefDEV testing#####");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setupUI();

        setMinimumSize(new Dimension(1100, 750));

        pack();
        setLocationRelativeTo(null);
        MusicManager.playMusic("/resources/sounds/launcher_music.wav");
    }

    private void setupUI() {
        // Hauptpanel mit dunklem Hintergrund
        JPanel mainPanel = new JPanel(new BorderLayout(0, 20));
        mainPanel.setBackground(Theme.BACKGROUND_DARK);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Header (oben)
        mainPanel.add(createHeader(), BorderLayout.NORTH);

        // Game Grid (Mitte)
        mainPanel.add(createGameGrid(), BorderLayout.CENTER);

        // Footer (unten)
        mainPanel.add(createFooter(), BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    /**
     * Erstellt den Header mit Logo und Titel.
     */
    private JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setOpaque(false);

        // Titel
        JLabel titleLabel = new JLabel("darkIT SPIELESAMMLUNG");
        titleLabel.setFont(Theme.FONT_TITLE_LARGE);
        titleLabel.setForeground(Theme.ACCENT_GREEN);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Untertitel
        JLabel subtitleLabel = new JLabel("Wähle ein Spiel und lerne IT-Sicherheit!");
        subtitleLabel.setFont(Theme.FONT_SUBTITLE);
        subtitleLabel.setForeground(Theme.TEXT_SECONDARY);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(titleLabel);
        header.add(Box.createRigidArea(new Dimension(0, 10)));
        header.add(subtitleLabel);

        return header;
    }

    /**
     * Erstellt das Grid mit allen Spielkarten (2 Reihen x 3 Spalten).
     */
    private JPanel createGameGrid() {
        JPanel gridPanel = new JPanel(new GridLayout(2, 3, 30, 30));
        gridPanel.setOpaque(false);

        List<GameInfo> games = GameRegistry.getAllGames();

        for (GameInfo game : games) {
            GameCard card = new GameCard(game, () -> startGame(game));
            gridPanel.add(card);
        }

        return gridPanel;
    }

    /**
     * Erstellt den Footer (NEUES LAYOUT).
     * Oben: Credits (Zentriert)
     * Unten: Beenden-Button (Zentriert) & Lautstärke-Regler (Rechts)
     */
    private JPanel createFooter() {
        // 1. DAS HAUPT-PANEL (stapelt alles von oben nach unten)
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.Y_AXIS));
        footerPanel.setOpaque(false);
        // (Etwas Abstand zum Grid oben und zum Fenster-Rand unten)
        footerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));


        // 2. OBERE REIHE (Nur die Credits)
        JPanel creditsPanel = new JPanel();
        creditsPanel.setLayout(new BoxLayout(creditsPanel, BoxLayout.Y_AXIS));
        creditsPanel.setOpaque(false);
        // Zentriert das Credits-Panel selbst in der Mitte (links/rechts)
        creditsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel creditLabel = new JLabel("Gruppe 03 | Hochschule Hannover | Semester 3 BIN | 2025");
        creditLabel.setFont(Theme.FONT_SMALL);
        creditLabel.setForeground(Theme.TEXT_SECONDARY.darker());
        creditLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Zentriert den Text

        JLabel betreuerLabel = new JLabel("Betreuer: Andreas Holitschke");
        betreuerLabel.setFont(new Font("SansSerif", Font.ITALIC, 11));
        betreuerLabel.setForeground(new Color(80, 80, 100));
        betreuerLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Zentriert den Text

        creditsPanel.add(creditLabel);
        creditsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        creditsPanel.add(betreuerLabel);


// 3. UNTERE REIHE (Buttons & Regler)
        // Dieses Panel benutzt BorderLayout für die Symmetrie
        JPanel controlsPanel = new JPanel(new BorderLayout());
        controlsPanel.setOpaque(false);
        controlsPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));

        // 3a. "Beenden"-Button (MITTE)
        JButton exitBtn = Theme.createStyledButton(
                "✖ BEENDEN",
                Theme.BUTTON_RED,
                Theme.BUTTON_RED_HOVER
        );
        exitBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this, "Launcher wirklich beenden?", "Beenden",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE
            );
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        // Wir packen den Button in ein FlowLayout, damit er nicht gestreckt wird
        JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        exitPanel.setOpaque(false);
        exitPanel.add(exitBtn);


        // 3b. Lautstärke-Regler (RECHTS)
        JPanel volumePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        volumePanel.setOpaque(false);

        volumeIcon = new JLabel("🔊"); // Benutzt die Member-Variable
        volumeIcon.setForeground(Theme.TEXT_SECONDARY);
        volumeIcon.setFont(Theme.FONT_BUTTON);
        volumeIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));

        volumeSlider = new JSlider(0, 100, 75); // Benutzt die Member-Variable
        volumeSlider.setUI(new DarkSliderUI(volumeSlider));
        volumeSlider.setOpaque(false);
        volumeSlider.setPreferredSize(new Dimension(150, 30));

        // (Der "dumme" Klick-Listener für das Icon)
        volumeIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (volumeSlider.getValue() > 0) {
                    volumeSlider.setValue(0);
                } else {
                    volumeSlider.setValue(lastVolumeBeforeMute);
                }
            }
        });

        // ==========================================
        // GEÄNDERT: SOFORTIGE Musik-Updates (keine Verzögerung)
        // ==========================================
        volumeSlider.addChangeListener(e -> {
            int currentValue = volumeSlider.getValue();

            // Icon SOFORT aktualisieren (UI bleibt flüssig)
            if (currentValue == 0) {
                volumeIcon.setText("🔇");
            } else {
                volumeIcon.setText("🔊");
                lastVolumeBeforeMute = currentValue;
            }

            // SPEZIALFALL: Stumm (0) immer SOFORT
            if (currentValue == 0) {
                volumeEventCounter = 0;  // Counter zurücksetzen
                MusicManager.setVolume(0);
                return;
            }

            // Für andere Werte: Nur jeden 3. Event verarbeiten
            volumeEventCounter++;
            if (volumeEventCounter % 5 == 0) {
                MusicManager.setVolume(currentValue);
            }
        });


        volumePanel.add(volumeIcon);
        volumePanel.add(volumeSlider);

        // 3c. Ein LEERES Panel (LINKS)
        JPanel emptyPanel = new JPanel();
        emptyPanel.setOpaque(false);
        // WICHTIG: Gib ihm die gleiche Größe wie dem echten Regler-Panel
        emptyPanel.setPreferredSize(volumePanel.getPreferredSize());


        // 4. ALLES ZUSAMMENBAUEN

        controlsPanel.add(exitPanel, BorderLayout.CENTER);   // Button
        controlsPanel.add(volumePanel, BorderLayout.EAST);  // Regler rechts
        controlsPanel.add(emptyPanel, BorderLayout.WEST);   // Unsichtbarer Platzhalter links

        // Füge die obere und untere Reihe zum Haupt-Panel hinzu
        footerPanel.add(creditsPanel);
        footerPanel.add(Box.createRigidArea(new Dimension(0, 25))); // Abstand
        footerPanel.add(controlsPanel);

        return footerPanel;
    }

    /**
     * Startet ein Spiel (öffnet die JAR-Datei).
     * Stoppt die Launcher-Musik und startet sie wieder, wenn das Spiel geschlossen wird.
     */
    private void startGame(GameInfo game) {
        String jarPath = "lib/" + game.getJarFileName();
        File jarFile = new File(jarPath);

        // Prüfe ob JAR existiert (wie vorher)
        if (!jarFile.exists()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Spiel nicht gefunden!\n\n" +
                            "Bitte lege die Datei hier ab:\n" +
                            jarFile.getAbsolutePath(),
                    "Fehler",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // ==========================================
        // GEÄNDERT: Launcher verstecken (wie Steam/Epic)
        // ==========================================
        this.setVisible(false);

        // 1. Musik stoppen, BEVOR das Spiel startet
        MusicManager.stopMusic();

        try {
            // 2. Spiel-Prozess starten (wie vorher)
            ProcessBuilder pb = new ProcessBuilder("java", "-jar", jarPath);
            Process gameProcess = pb.start();

            // 3. Einen NEUEN Thread starten, der auf das Spiel "wartet"
            //    (Damit der Launcher NICHT einfriert!)
            new Thread(() -> {
                try {
                    // 4. Dieser Befehl blockiert den HINTERGRUND-Thread,
                    //    bis das Spiel (gameProcess) geschlossen wird.
                    gameProcess.waitFor();

                } catch (InterruptedException e) {
                    // Fehler beim Warten
                    e.printStackTrace();
                }

                // 5. Das Spiel wurde geschlossen. Wir starten die Musik wieder.
                //    WICHTIG: Wir müssen zurück zum Haupt-Thread (EDT)
                SwingUtilities.invokeLater(() -> {
                    // ==========================================
                    // GEÄNDERT: Launcher wieder zeigen + nach vorne bringen
                    // ==========================================
                    this.setVisible(true);
                    this.toFront();
                    this.requestFocus();

                    // 6. Musik nur starten, wenn der Regler nicht auf STUMM (0) steht
                    if (volumeSlider.getValue() > 0) {
                        MusicManager.resumeMusic();
                    }
                });

            }).start(); // Den neuen Hintergrund-Thread sofort starten

        } catch (IOException ex) {
            // ==========================================
            // GEÄNDERT: Bei Fehler Launcher wieder zeigen
            // ==========================================
            this.setVisible(true);

            JOptionPane.showMessageDialog(
                    this,
                    "Fehler beim Starten des Spiels:\n" + ex.getMessage(),
                    "Fehler",
                    JOptionPane.ERROR_MESSAGE
            );
            ex.printStackTrace();

            // WICHTIG: Wenn das Spiel NICHT starten konnte (Fehler),
            // Musik wieder anmachen.
            if (volumeSlider.getValue() > 0) {
                MusicManager.resumeMusic();
            }
        }
    }
}