package games.phishingdefender;
import javax.swing.*;
import java.awt.*;
import java.awt.Image;
import javax.swing.ImageIcon;
import games.phishingdefender.HighscoreScreen;

/**
 * Phishing Defender - Email Class
 * @author yusef03
 * @version 0.8.0
 */


public class PhishingDefender extends JFrame {

    private JPanel mainPanel;
    private int hoechstesFreigeschaltetes; // Welcher level freigeschaltet?
    private String spielerName;
    private JLabel spielerAnzeigeLabel;
    private JButton wechselnButton;

    public PhishingDefender() {
        hoechstesFreigeschaltetes = 1;
        this.spielerName = "";

        // Window-Setting
        setTitle("Phishing Defender by 03yusef v.0.8");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //MinimumSize
        setMinimumSize(new Dimension(1000, 700));

        setPreferredSize(new Dimension(1300, 950));

        setLocationRelativeTo(null);

        // Haupt-Panel mit Custom Paint für Gradient-Hintergrund
        mainPanel = new AnimatedBackgroundPanel();
        mainPanel.setLayout(new BorderLayout());

                                            // === LOGO ===
        JLabel logoLabel = null;
        try {
            java.net.URL logoURL = getClass().getResource("/games/phishingdefender/assets/images/logo.png");

            if (logoURL == null) {
                throw new Exception("Logo nicht gefunden!");
            }

            ImageIcon originalIcon = new ImageIcon(logoURL);
            Image scaledImage = originalIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            logoLabel = new JLabel(scaledIcon, JLabel.CENTER);
        } catch (Exception e) {
            logoLabel = new JLabel("🛡️", JLabel.CENTER);
            logoLabel.setFont(new Font("Arial", Font.PLAIN, 100));
        }
        logoLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));

        // === TITEL ===
        JLabel welcomeLabel = new JLabel("PHISHING DEFENDER", JLabel.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);


                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(255, 150, 80),
                        getWidth(), 0, new Color(255, 70, 50)
                );
                g2d.setPaint(gradient);
                g2d.setFont(getFont());

                // Text zentriert
                FontMetrics fm = g2d.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
                g2d.drawString(getText(), x, y);
            }
        };
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 52));
        welcomeLabel.setOpaque(false);

        // === EMAIL-STYLE STORY BOX ===
        JPanel storyBox = new JPanel();
        storyBox.setLayout(new BorderLayout());
        storyBox.setBackground(new Color(25, 30, 50, 220));
        storyBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 107, 53, 180), 3),
                BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));

        // EMAIL HEADER
        JPanel emailHeader = new JPanel();
        emailHeader.setLayout(new BorderLayout());
        emailHeader.setBackground(new Color(40, 45, 70, 255));
        emailHeader.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));

        JLabel fromLabel = new JLabel("📧 Von: Cyber Security HQ");
        fromLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        fromLabel.setForeground(new Color(100, 200, 255));

        JLabel subjectLabel = new JLabel("Betreff: ⚠️ DRINGEND - Phishing-Angriff erkannt!");
        subjectLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        subjectLabel.setForeground(new Color(255, 107, 53));

        JPanel headerContent = new JPanel();
        headerContent.setLayout(new BoxLayout(headerContent, BoxLayout.Y_AXIS));
        headerContent.setOpaque(false);
        headerContent.add(fromLabel);
        headerContent.add(Box.createRigidArea(new Dimension(0, 5)));
        headerContent.add(subjectLabel);

        emailHeader.add(headerContent, BorderLayout.WEST);

        // EMAIL BODY (die Story!)
        JPanel emailBody = new JPanel();
        emailBody.setLayout(new BorderLayout());
        emailBody.setBackground(new Color(20, 25, 45, 230));
        emailBody.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel bodyLabel = new JLabel(
                "<html><div style='line-height: 1.5;'>" +
                        "<span style='color: #CCCCCC; font-size: 14px;'>" +
                        "Hacker haben gefährliche Phishing-E-Mails in unserer Stadt verschickt!<br>" +
                        "Nur du kannst sie aufhalten!<br><br>" +
                        "</span>" +
                        "<span style='color: #AAAAAA; font-size: 13px;'>" +
                        "<span style='color: #FF6B35;'>▸</span> <b>Analysiere</b> verdächtige E-Mails<br>" +
                        "<span style='color: #FF6B35;'>▸</span> <b>Erkenne</b> Fälschungen und Betrugsversuche<br>" +
                        "<span style='color: #FF6B35;'>▸</span> <b>Schütze</b> die Bürger vor Cyber-Kriminellen<br><br>" +
                        "</span>" +
                        "<span style='color: #64B5FF; font-size: 15px; font-weight: bold;'>🕵️ Werde zum Cyber-Detektiv! 💻</span>" +
                        "</div></html>"
        );
        bodyLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));

        emailBody.add(bodyLabel, BorderLayout.CENTER);

        storyBox.add(emailHeader, BorderLayout.NORTH);
        storyBox.add(emailBody, BorderLayout.CENTER);

            // === CONTENT PANEL  ===
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        storyBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPanel.add(logoLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        contentPanel.add(welcomeLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        contentPanel.add(storyBox);
        contentPanel.add(Box.createVerticalGlue()); // Füllt Rest aus!

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));



// === ANGEMELDETER SPIELER ANZEIGE ===
        JPanel spielerInfoPanel = new JPanel();
        spielerInfoPanel.setLayout(new BoxLayout(spielerInfoPanel, BoxLayout.Y_AXIS));
        spielerInfoPanel.setOpaque(false);
        spielerInfoPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 20, 0));

// Label als Instanz-Variable speichern!
        spielerAnzeigeLabel = new JLabel("", JLabel.CENTER);
        spielerAnzeigeLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        spielerAnzeigeLabel.setForeground(new Color(100, 200, 255));
        spielerAnzeigeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        aktualisiereSpielerAnzeige();  // Initial setzen

        spielerInfoPanel.add(spielerAnzeigeLabel);



// === BUTTONS ===
        JButton startButton = Theme.createStyledButton(
                "▶ START GAME",
                Theme.FONT_BUTTON_LARGE,
                Theme.COLOR_ACCENT_ORANGE,
                Theme.COLOR_ACCENT_ORANGE_HOVER,
                Theme.PADDING_BUTTON_LARGE
        );
        startButton.setMaximumSize(new Dimension(300, 65)); // Feste Größe
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(e -> {
            if (spielerName.isEmpty()) {
                zeigeNameInput();
            } else {
                zeigeLevelAuswahl();
            }
        });

        JButton highscoresButton = Theme.createStyledButton(
                "🏆 HIGHSCORES",
                Theme.FONT_BUTTON_MEDIUM,
                Theme.COLOR_BUTTON_GREY,
                Theme.COLOR_BUTTON_GREY_HOVER,
                Theme.PADDING_BUTTON_MEDIUM
        );
        highscoresButton.setMaximumSize(new Dimension(300, 55)); // Feste Größe
        highscoresButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        highscoresButton.addActionListener(e -> zeigeHighscores());


        // SPIELER WECHSELN Button (Instanz-Variable!)
        wechselnButton = Theme.createStyledButton(
                "🚪 SPIELER WECHSELN",
                Theme.FONT_BUTTON_MEDIUM,
                Theme.COLOR_BUTTON_PURPLE,
                Theme.COLOR_BUTTON_PURPLE_HOVER,
                Theme.PADDING_BUTTON_MEDIUM
        );
        wechselnButton.setMaximumSize(new Dimension(300, 55)); // Feste Größe
        wechselnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        wechselnButton.addActionListener(e -> spielerAbmelden());
        wechselnButton.setVisible(false);  // Initial versteckt!
// Button-Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 40, 0));
        buttonPanel.add(spielerInfoPanel);  // ← NEU!
        buttonPanel.add(startButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(highscoresButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));  // ← NEU!
        buttonPanel.add(wechselnButton);  // ← NEU!


// Settings Button unten rechts
        SettingsButton settingsButton = new SettingsButton();
        settingsButton.addActionListener(e -> {
            SettingsDialog dialog = new SettingsDialog(this);
            dialog.setVisible(true);
        });

// Button Container mit VIEL Platz für Glow/Schatten
        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new BorderLayout());
        buttonContainer.setOpaque(false);
// 15px Padding! (Glow geht 4px + Schatten 3px = mindestens 7px, also 15 ist sicher!)
        buttonContainer.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        buttonContainer.add(settingsButton, BorderLayout.CENTER);

// Wrapper positioniert Button unten rechts mit viel Abstand nach unten!
        JPanel settingsWrapper = new JPanel(new BorderLayout());
        settingsWrapper.setOpaque(false);
// 5px vom Rand (weil buttonContainer schon 15px hat!)
// 25px unten für mehr Abstand nach unten!
        settingsWrapper.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 5));
        settingsWrapper.add(buttonContainer, BorderLayout.EAST);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);
        bottomPanel.add(settingsWrapper, BorderLayout.EAST);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);

// Fenster optimal anpassen
        pack();

// Bevorzugte Größe setzen (falls pack zu klein)
        Dimension currentSize = getSize();
        Dimension preferredSize = getPreferredSize();
        setSize(Math.max(currentSize.width, preferredSize.width),
                Math.max(currentSize.height, preferredSize.height));

// Maximal 95% der Bildschirmgröße
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int maxWidth = (int) (screenSize.width * 0.95);
        int maxHeight = (int) (screenSize.height * 0.95);

        if (getWidth() > maxWidth || getHeight() > maxHeight) {
            setSize(Math.min(getWidth(), maxWidth), Math.min(getHeight(), maxHeight));
        }

// Fenster zentrieren
        setLocationRelativeTo(null);

// Layout neu berechnen
        SwingUtilities.invokeLater(() -> {
            revalidate();
            repaint();
        });
    }

    public void zeigeWelcomeScreen() {
        getContentPane().removeAll();
        getContentPane().add(mainPanel);

        // HIER MUSS DER EINE AUFRUF STEHEN:
        packAndCenterWindow();

        revalidate();
        repaint();

        // Music neu starten wenn nicht läuft
        if (!MusicManager.isPlaying()) {
            MusicManager.startMenuMusic();
        }

        // Level-Freischaltung aktualisieren falls Name schon gesetzt
        if (spielerName != null && !spielerName.isEmpty()) {
            aktualisiereLevelFreischaltung();
        }

        // Spieler-Anzeige aktualisieren
        aktualisiereSpielerAnzeige();
    }
    // Zeigt den Highscore Screen
    public void zeigeHighscores() {
        getContentPane().removeAll();
        getContentPane().add(new HighscoreScreen(this));
        revalidate();
        repaint();
    }

    // Zeigt die Level-Auswahl
    public void zeigeLevelAuswahl() {
        getContentPane().removeAll();
        getContentPane().add(new LevelSelectionScreen(this));
        revalidate();
        repaint();

        // Musik starten wenn nicht läuft!
        if (!MusicManager.isPlaying()) {
            MusicManager.startMenuMusic();
        }
    }

    // Startet ein Level
    public void starteLevel(int level) {
        MusicManager.stopMenuMusic();


        // Lade-Screen anzeigen
        getContentPane().removeAll();

        JPanel ladePanel = new JPanel();
        ladePanel.setLayout(new BorderLayout());
        ladePanel.setBackground(new Color(26, 26, 46));

        JLabel ladeLabel = new JLabel("LADEN... 🎵", JLabel.CENTER);
        ladeLabel.setFont(new Font("Arial", Font.BOLD, 48));
        ladeLabel.setForeground(new Color(255, 107, 53));

        ladePanel.add(ladeLabel, BorderLayout.CENTER);
        getContentPane().add(ladePanel);
        revalidate();
        repaint();

        // GameScreen im Hintergrund laden
        SwingUtilities.invokeLater(() -> {
            getContentPane().removeAll();
            getContentPane().add(new GameScreen(this, level));
            revalidate();
            repaint();
        });
    }

    public void zeigeResultScreen(int level, int score, int leben, int maxLeben, int gesamtEmails, boolean erfolg) {
        // Musik stoppen! (Result Screen soll KEINE Musik haben!)
        MusicManager.stopMenuMusic();

        getContentPane().removeAll();
        getContentPane().add(new ResultScreen(this, level, score, leben, maxLeben, gesamtEmails, erfolg));
        revalidate();
        repaint();
    }

    public void levelGeschafft(int level) {
        // Freischaltung wird jetzt über Sterne berechnet!
        aktualisiereLevelFreischaltung();
        zeigeLevelAuswahl();
    }

    public int getHoechstesFreigeschaltetes() {
        return hoechstesFreigeschaltetes;
    }

    // Setzt den Spieler-Namen
    public void setSpielerName(String name) {
        this.spielerName = name;
        aktualisiereLevelFreischaltung();
        aktualisiereSpielerAnzeige();  // NEU! Label aktualisieren!
    }

    // Gibt den Spieler-Namen zurück
    public String getSpielerName() {
        return spielerName;
    }

    // Berechnet höchstes freigeschaltetes Level basierend auf Sternen
    private void aktualisiereLevelFreischaltung() {
        if (spielerName == null || spielerName.isEmpty()) {
            hoechstesFreigeschaltetes = 1;
            return;
        }

        // StarsManager laden
        StarsManager starsManager = new StarsManager(spielerName);  // ✅ MIT Parameter!

        // Level 1 ist immer freigeschaltet
        hoechstesFreigeschaltetes = 1;

        // Level 2 freischalten wenn Level 1 mindestens 1 Stern hat
        if (starsManager.getStarsForLevel(1) > 0) {
            hoechstesFreigeschaltetes = 2;
        }

        // Level 3 freischalten wenn Level 2 mindestens 1 Stern hat
        if (starsManager.getStarsForLevel(2) > 0) {
            hoechstesFreigeschaltetes = 3;
        }
    }

    // Zeigt Name-Input Screen
    public void zeigeNameInput() {
        getContentPane().removeAll();
        getContentPane().add(new NameInputScreen(this));
        revalidate();
        repaint();
    }


    // Meldet aktuellen Spieler ab
    private void spielerAbmelden() {
        // Bestätigungs-Dialog
        int antwort = JOptionPane.showConfirmDialog(
                this,
                "Möchtest du den Spieler wechseln?\n\nDein Fortschritt ist gespeichert!",
                "Spieler wechseln",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (antwort == JOptionPane.YES_OPTION) {
            // Spielername zurücksetzen
            spielerName = "";

            // Level-Freischaltung zurücksetzen
            hoechstesFreigeschaltetes = 1;

            // Zum Name-Input Screen
            zeigeNameInput();
        }
    }


    // Aktualisiert die Spieler-Anzeige
    private void aktualisiereSpielerAnzeige() {
        if (spielerAnzeigeLabel != null) {
            if (spielerName == null || spielerName.isEmpty()) {
                spielerAnzeigeLabel.setText("👤 Angemeldet als: Gast");
            } else {
                spielerAnzeigeLabel.setText("👤 Angemeldet als: " + spielerName);
            }
        }

        // Button nur zeigen wenn angemeldet
        if (wechselnButton != null) {
            wechselnButton.setVisible(spielerName != null && !spielerName.isEmpty());
        }
    }

    /**
     * Helfer-Methode: Passt die Fenstergröße optimal an und zentriert das Fenster.
     * Verhindert Code-Duplikate im Konstruktor und in zeigeWelcomeScreen().
     */
    private void packAndCenterWindow() {
        // Fenster optimal anpassen
        pack();

        // Bevorzugte Größe setzen (falls pack zu klein)
        Dimension currentSize = getSize();
        Dimension preferredSize = getPreferredSize();
        setSize(Math.max(currentSize.width, preferredSize.width),
                Math.max(currentSize.height, preferredSize.height));

        // Maximal 95% der Bildschirmgröße
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int maxWidth = (int) (screenSize.width * 0.95);
        int maxHeight = (int) (screenSize.height * 0.95);

        if (getWidth() > maxWidth || getHeight() > maxHeight) {
            setSize(Math.min(getWidth(), maxWidth), Math.min(getHeight(), maxHeight));
        }

        // Fenster zentrieren
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        // 1. Zeige SOFORT den Splash Screen (auf dem Haupt-Thread)
        SplashScreen splash = new SplashScreen();
        splash.setVisible(true);

        // 2. Erstelle einen SwingWorker für die Lade-Aufgaben
        SwingWorker<PhishingDefender, Void> worker = new SwingWorker<>() {

            @Override
            protected PhishingDefender doInBackground() throws Exception {
                // DAS HIER LÄUFT IM HINTERGRUND (NICHT auf dem UI-Thread)

                // 1. Lade die Musik (das kann dauern)
                MusicManager.startMenuMusic();

                // 2. Erstelle das Hauptfenster (dauert auch kurz)
                PhishingDefender game = new PhishingDefender();

                // 3. Simuliere Ladezeit (damit man den Splash sieht)
                Thread.sleep(1500);

                // 4. Gib das fertige Spiel zurück
                return game;
            }

            @Override
            protected void done() {
                // DAS HIER LÄUFT ZURÜCK AUF DEM UI-THREAD (wenn doInBackground fertig ist)
                try {
                    // 1. Hole das geladene Spiel
                    PhishingDefender game = get();

                    // 2. Zeige das Spiel an
                    game.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                    // Zeige einen Fehler, falls das Laden fehlschlägt
                    JOptionPane.showMessageDialog(null, "Spiel konnte nicht geladen werden!", "Fehler", JOptionPane.ERROR_MESSAGE);
                } finally {
                    // 3. Schließe den Splash Screen EGAL WAS PASSIERT
                    splash.setVisible(false);
                    splash.dispose();
                }
            }
        };

        // 3. Starte den Worker
        worker.execute();
    }
}