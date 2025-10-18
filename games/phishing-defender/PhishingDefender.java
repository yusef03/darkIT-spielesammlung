package games.phishingdefender;
import javax.swing.*;
import java.awt.*;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Phishing Defender - Email Class
 * @author yusef03
 * @version 1.0
 */

// Hauptklasse für Phishing Defender
public class PhishingDefender extends JFrame {

    private JPanel mainPanel;
    private int hoechstesFreigeschaltetes; // Welcher level freigeschaltet?
    private String spielerName;

    public PhishingDefender() {
        hoechstesFreigeschaltetes = 1;
        this.spielerName = "";

// Fenster-Einstellungen
        setTitle("Phishing Defender 03yusef DEV");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

// Mindestgröße (kann nicht kleiner gemacht werden!)
        setMinimumSize(new Dimension(900, 650));

// Start-Größe (größer als vorher!)
        setSize(1200, 900);
        setLocationRelativeTo(null);

        // Haupt-Panel mit Custom Paint für Gradient-Hintergrund
        mainPanel = new AnimatedBackgroundPanel();
        mainPanel.setLayout(new BorderLayout());

        // === LOGO ===
        JLabel logoLabel = null;
        try {
            ImageIcon originalIcon = new ImageIcon("games/phishing-defender/assets/images/logo.png");
            Image scaledImage = originalIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            logoLabel = new JLabel(scaledIcon, JLabel.CENTER);
        } catch (Exception e) {
            logoLabel = new JLabel("🛡️", JLabel.CENTER);
            logoLabel.setFont(new Font("Arial", Font.PLAIN, 100));
        }
        logoLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));

        // === TITEL mit Gradient-Effekt ===
        JLabel welcomeLabel = new JLabel("PHISHING DEFENDER", JLabel.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

                // Gradient Orange → Rot
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

// EMAIL HEADER (wie echte Mail!)
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
                "<html><div style='line-height: 1.4;'>" +
                        "<span style='color: #AAAAAA; font-size: 13px;'>" +
                        "Hacker verschicken gefährliche Phishing-E-Mails in unserer Stadt!<br><br>" +
                        "<span style='color: #FF6B35;'>▸</span> Analysiere verdächtige E-Mails<br>" +
                        "<span style='color: #FF6B35;'>▸</span> Erkenne Fälschungen<br>" +
                        "<span style='color: #FF6B35;'>▸</span> Schütze die Bürger!<br><br>" +
                        "<span style='color: #64B5FF; font-weight: bold;'>Werde zum Cyber-Detektiv! 🕵️</span>" +
                        "</span>" +
                        "</div></html>"
        );
        bodyLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));

        emailBody.add(bodyLabel, BorderLayout.CENTER);

// Zusammenbauen
        storyBox.add(emailHeader, BorderLayout.NORTH);
        storyBox.add(emailBody, BorderLayout.CENTER);

        // === CONTENT PANEL ===
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        storyBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        storyBox.setMaximumSize(new Dimension(600, 220));

        contentPanel.add(logoLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        contentPanel.add(welcomeLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        contentPanel.add(storyBox);

        // === BUTTONS ===
        RoundedButton startButton = new RoundedButton("▶ START GAME");
        startButton.setFont(new Font("Arial", Font.BOLD, 22));
        startButton.setBackground(new Color(255, 107, 53));
        startButton.setColors(new Color(255, 107, 53), new Color(255, 140, 80));
        startButton.setForeground(Color.WHITE);
        startButton.setPreferredSize(new Dimension(280, 55));
        startButton.setMaximumSize(new Dimension(280, 55));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(e -> {
            if (spielerName.isEmpty()) {
                zeigeNameInput();
            } else {
                zeigeLevelAuswahl();
            }
        });

        RoundedButton highscoresButton = new RoundedButton("🏆 HIGHSCORES");
        highscoresButton.setFont(new Font("Arial", Font.BOLD, 18));
        highscoresButton.setBackground(new Color(80, 85, 110));
        highscoresButton.setColors(new Color(80, 85, 110), new Color(100, 105, 130));
        highscoresButton.setForeground(Color.WHITE);
        highscoresButton.setPreferredSize(new Dimension(280, 50));
        highscoresButton.setMaximumSize(new Dimension(280, 50));
        highscoresButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        highscoresButton.addActionListener(e -> zeigeHighscores());

        // Button-Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 40, 0));
        buttonPanel.add(startButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(highscoresButton);


// Settings Button unten rechts
        SettingsButton settingsButton = new SettingsButton();
        settingsButton.addActionListener(e -> {
            SettingsDialog dialog = new SettingsDialog(this);
            dialog.setVisible(true);
        });

        JPanel settingsWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        settingsWrapper.setOpaque(false);
        settingsWrapper.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 30));
        settingsWrapper.add(settingsButton);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);
        bottomPanel.add(settingsWrapper, BorderLayout.EAST);

        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    // Zeigt den Welcome Screen
    public void zeigeWelcomeScreen() {
        getContentPane().removeAll();
        getContentPane().add(mainPanel);
        revalidate();
        repaint();

        // Music neu starten wenn nicht läuft
        if (!MusicManager.isPlaying()) {
            MusicManager.startMenuMusic();
        }
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

    // Zeigt Result Screen
    public void zeigeResultScreen(int level, int score, int leben, int maxLeben, int gesamtEmails, boolean erfolg) {
        getContentPane().removeAll();
        getContentPane().add(new ResultScreen(this, level, score, leben, maxLeben, gesamtEmails, erfolg));
        revalidate();
        repaint();
    }

    public void levelGeschafft(int level) {
        if (level >= hoechstesFreigeschaltetes && level < 3) {
            hoechstesFreigeschaltetes = level + 1;
        }
        zeigeLevelAuswahl();
    }

    public int getHoechstesFreigeschaltetes() {
        return hoechstesFreigeschaltetes;
    }

    // Setzt den Spieler-Namen
    public void setSpielerName(String name) {
        this.spielerName = name;
    }

    // Gibt den Spieler-Namen zurück
    public String getSpielerName() {
        return spielerName;
    }

    // Zeigt Name-Input Screen
    public void zeigeNameInput() {
        getContentPane().removeAll();
        getContentPane().add(new NameInputScreen(this));
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        // Splash Screen zuerst zeigen
        SplashScreen splash = new SplashScreen();
        splash.setVisible(true);

        // Im Hintergrund laden
        new Thread(() -> {
            try {
                // Music vorladen
                MusicManager.startMenuMusic();

                // Etwas warten (damit man Splash sieht + Music lädt)
                Thread.sleep(1500);

                // Hauptfenster starten
                SwingUtilities.invokeLater(() -> {
                    splash.setVisible(false);
                    splash.dispose();

                    PhishingDefender game = new PhishingDefender();
                    game.setVisible(true);
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}