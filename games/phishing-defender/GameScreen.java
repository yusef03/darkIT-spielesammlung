package games.phishingdefender;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import javax.swing.KeyStroke;


/**
 * Phishing Defender - GameScreen Class
 * @author yusef03
 * @version 1.0
 */

// Der Haupt-Spielbildschirm
public class GameScreen extends JPanel {


    // Timer-Konfiguration (in Sekunden)
    private static final int LEVEL1_ZEIT = 15;
    private static final int LEVEL2_ZEIT = 20;
    private static final int LEVEL3_ZEIT = 20;


    // Firewall-Bonus Konfiguration
    private static final int BONUS_RICHTIGE_NOETIG = 5;     // Wie viele richtige für Bonus
    private static final int BONUS_DAUER_EMAILS = 3;        // Bonus hält für X E-Mails
    private static final double BONUS_ZEIT_MULTIPLIKATOR = 0.5;  // 0.5 = 50% mehr Zeit
    private static final int BONUS_PUNKTE = 20;             // Punkte mit Bonus
    private static final int NORMALE_PUNKTE = 10;           // Normale Punkte

    // Steuerung Config
    private static final int TASTE_SICHER = KeyEvent.VK_A; // A = Sicher
    private static final int TASTE_PHISHING = KeyEvent.VK_L; // L = Phishing
    private static final int TASTE_PAUSE = KeyEvent.VK_SPACE; // Space = Pause
    private static final int TASTE_ZURUECK = KeyEvent.VK_ESCAPE;  // ESC = Zurück


    private PhishingDefender hauptFenster;
    private int level;
    private List<Email> emails;
    private int aktuelleEmailIndex;
    private int score;
    private int leben;
    private int maxLeben;
    private int zeitProEmail;
    private int verbleibendeSekunden;
    private int richtigeInFolge;
    private boolean firewallAktiv;
    private int firewallCounter;
    private boolean isPausiert;

    // UI Komponenten
    private JPanel pauseOverlay;
    private JLabel scoreLabel;
    private JLabel lebenLabel;
    private JTextArea emailAnzeigeArea;
    private JLabel absenderLabel;
    private JLabel betreffLabel;
    private Timer timer;
    private JLabel timerLabel;
    private JLabel feedbackLabel;
    private JLabel tippLabel;
    private JPanel pauseGlassPane;

    private Clip clipRichtig;
    private Clip clipFalsch;
    private Clip clipBonus;
    private Clip clipLevelGeschafft;
    private Clip clipGameOver;
    private FeedbackCard feedbackCard;
    private TippCard tippCard;
    private PauseMenu pauseMenu;


    public GameScreen(PhishingDefender hauptFenster, int level) {
        this.hauptFenster = hauptFenster;
        this.level = level;
        this.aktuelleEmailIndex = 0;
        this.score = 0;
        this.richtigeInFolge = 0;
        this.firewallAktiv = false;
        this.firewallCounter = 0;
        this.isPausiert = false;

        // Leben basierend auf Level
        if (level == 1) {
            this.maxLeben = 3;
        } else if (level == 2) {
            this.maxLeben = 4;
        } else {
            this.maxLeben = 5;
        }
        this.leben = maxLeben;

// Zeit pro E-Mail basierend auf Level
        if (level == 1) {
            this.zeitProEmail = LEVEL1_ZEIT;
        } else if (level == 2) {
            this.zeitProEmail = LEVEL2_ZEIT;
        } else {
            this.zeitProEmail = LEVEL3_ZEIT;
        }

        // E-Mails für dieses Level laden
        EmailDatabase database = new EmailDatabase();
        this.emails = database.getEmailsFuerLevel(level);

        setupUI();
        zeigeNaechsteEmail();

// Tastatur-Input
        setFocusable(true);

// Fokus mit Verzögerung setzen (nach dem UI-Aufbau)
        SwingUtilities.invokeLater(() -> {
            requestFocusInWindow();
        });
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == TASTE_PAUSE) {
                    togglePause();
                } else if (isPausiert) {
                    return;  // Keine anderen Tasten wenn pausiert
                } else if (e.getKeyCode() == TASTE_SICHER) {
                    antwortGeben(false);
                } else if (e.getKeyCode() == TASTE_PHISHING) {
                    antwortGeben(true);
                } else if (e.getKeyCode() == TASTE_ZURUECK) {
                    if (timer != null) {
                        timer.stop();
                    }
                    hauptFenster.zeigeLevelAuswahl();
                }
            }
        });
        soundsVorladen();
    }

    // Erstellt die Benutzeroberfläche
    private void setupUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(15, 20, 35)); // DUNKLER!

        // === TOP BAR - Gaming HUD ===
        JPanel topBar = new JPanel();
        topBar.setLayout(new BorderLayout());
        topBar.setBackground(new Color(20, 25, 45));
        topBar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(100, 180, 255, 60)),
                BorderFactory.createEmptyBorder(20, 35, 20, 35)
        ));
        topBar.setPreferredSize(new Dimension(0, 95));

        // Links: Score
        scoreLabel = new JLabel("⭐ 0");
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        scoreLabel.setForeground(new Color(255, 215, 100));

        // Mitte: Timer
        timerLabel = new JLabel("⏱️ 15s");
        timerLabel.setFont(new Font("Monospace", Font.BOLD, 34));
        timerLabel.setForeground(new Color(255, 140, 80));
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Rechts: Leben
        lebenLabel = new JLabel(erstelleLebenString());
        lebenLabel.setFont(new Font("Arial", Font.PLAIN, 34));
        lebenLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        topBar.add(scoreLabel, BorderLayout.WEST);
        topBar.add(timerLabel, BorderLayout.CENTER);
        topBar.add(lebenLabel, BorderLayout.EAST);

        // === CENTER WRAPPER ===
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setBackground(new Color(15, 20, 35));
        centerWrapper.setBorder(BorderFactory.createEmptyBorder(25, 50, 25, 50));

        // === EMAIL PANEL - Dunkler Email Client ===
        JPanel emailPanel = new JPanel();
        emailPanel.setLayout(new BorderLayout(0, 0));
        emailPanel.setBackground(new Color(25, 30, 50));
        emailPanel.setPreferredSize(new Dimension(950, 520));

        // Mega Schatten + Glow
        emailPanel.setBorder(BorderFactory.createCompoundBorder(
                new javax.swing.border.AbstractBorder() {
                    @Override
                    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                        Graphics2D g2 = (Graphics2D) g.create();
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                        // Schatten
                        g2.setColor(new Color(0, 0, 0, 80));
                        g2.fillRoundRect(x + 5, y + 5, width - 5, height - 5, 15, 15);

                        // Subtiler Glow
                        g2.setColor(new Color(100, 180, 255, 20));
                        g2.fillRoundRect(x - 2, y - 2, width + 4, height + 4, 18, 18);

                        g2.dispose();
                    }
                    @Override
                    public Insets getBorderInsets(Component c) {
                        return new Insets(5, 5, 10, 10);
                    }
                },
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(60, 80, 120), 1),
                        BorderFactory.createEmptyBorder(0, 0, 0, 0)
                )
        ));

        // === EMAIL HEADER ===
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(new Color(30, 35, 60));
        headerPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(60, 80, 120)),
                BorderFactory.createEmptyBorder(22, 28, 22, 28)
        ));

        absenderLabel = new JLabel("📨 Von: ");
        absenderLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        absenderLabel.setForeground(new Color(180, 200, 230));
        absenderLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        betreffLabel = new JLabel("📌 Betreff: ");
        betreffLabel.setFont(new Font("SansSerif", Font.BOLD, 19));
        betreffLabel.setForeground(new Color(255, 255, 255));
        betreffLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel datumLabel = new JLabel("📅 18. Okt 2025, 14:30");
        datumLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        datumLabel.setForeground(new Color(140, 160, 190));
        datumLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        headerPanel.add(absenderLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 9)));
        headerPanel.add(betreffLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 9)));
        headerPanel.add(datumLabel);

        // === EMAIL BODY ===
        emailAnzeigeArea = new JTextArea();
        emailAnzeigeArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
        emailAnzeigeArea.setEditable(false);
        emailAnzeigeArea.setLineWrap(true);
        emailAnzeigeArea.setWrapStyleWord(true);
        emailAnzeigeArea.setBackground(new Color(25, 30, 50));
        emailAnzeigeArea.setForeground(new Color(220, 220, 220));
        emailAnzeigeArea.setCaretColor(new Color(255, 140, 80));
        emailAnzeigeArea.setBorder(BorderFactory.createEmptyBorder(28, 28, 28, 28));

        JScrollPane scrollPane = new JScrollPane(emailAnzeigeArea);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

// === FEEDBACK PANEL mit Custom Cards ===
        JPanel feedbackPanel = new JPanel();
        feedbackPanel.setLayout(new BoxLayout(feedbackPanel, BoxLayout.Y_AXIS));
        feedbackPanel.setBackground(new Color(25, 30, 50));
        feedbackPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        feedbackPanel.setPreferredSize(new Dimension(850, 150));

// Custom Cards
        feedbackCard = new FeedbackCard("✓", "RICHTIG! +10 Punkte", new Color(50, 180, 100));
        feedbackCard.setVisible(false);
        feedbackCard.setAlignmentX(Component.CENTER_ALIGNMENT);

        tippCard = new TippCard();
        tippCard.setVisible(false);
        tippCard.setAlignmentX(Component.CENTER_ALIGNMENT);

        feedbackPanel.add(feedbackCard);
        feedbackPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        feedbackPanel.add(tippCard);

        // Email zusammenbauen
        emailPanel.add(headerPanel, BorderLayout.NORTH);
        emailPanel.add(scrollPane, BorderLayout.CENTER);
        emailPanel.add(feedbackPanel, BorderLayout.SOUTH);

        centerWrapper.add(emailPanel);

        // === BOTTOM: Buttons ===
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 28));
        buttonPanel.setBackground(new Color(15, 20, 35));

        JButton sicherButton = Theme.createStyledButton(
                "✅ SICHER (A)",
                Theme.FONT_BUTTON_LARGE, // 22px
                Theme.COLOR_BUTTON_GREEN,
                Theme.COLOR_BUTTON_GREEN_HOVER,
                Theme.PADDING_BUTTON_LARGE // 15px padding
        );
        sicherButton.setPreferredSize(new Dimension(310, 75)); // Deine alte Größe
        sicherButton.addActionListener(e -> antwortGeben(false));

        JButton phishingButton = Theme.createStyledButton(
                "⚠️ PHISHING (L)",
                Theme.FONT_BUTTON_LARGE, // 22px
                Theme.COLOR_BUTTON_RED,
                Theme.COLOR_BUTTON_RED_HOVER,
                Theme.PADDING_BUTTON_LARGE // 15px padding
        );
        phishingButton.setPreferredSize(new Dimension(310, 75)); // Deine alte Größe
        phishingButton.addActionListener(e -> antwortGeben(true));

        buttonPanel.add(sicherButton);
        buttonPanel.add(phishingButton);

        add(topBar, BorderLayout.NORTH);
        add(centerWrapper, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Zeigt die nächste E-Mail an
    private void zeigeNaechsteEmail() {
        if (aktuelleEmailIndex >= emails.size()) {
            levelAbgeschlossen();
            return;
        }

        Email email = emails.get(aktuelleEmailIndex);
        absenderLabel.setText("📨 Von: " + email.getAbsender());
        betreffLabel.setText("📌 Betreff: " + email.getBetreff());
        emailAnzeigeArea.setText(email.getNachricht());

        starteTimer();
        requestFocusInWindow();
    }

    // Verarbeitet die Antwort des Spielers
    private void antwortGeben(boolean spielerSagtPhishing) {
        // Timer stoppen
        if (timer != null) {
            timer.stop();
        }

        Email email = emails.get(aktuelleEmailIndex);
        boolean istRichtig = (email.istPhishing() == spielerSagtPhishing);

        if (istRichtig) {
            richtigeInFolge++;

            // Bonus-Punkte wenn Firewall aktiv
            int punkte = firewallAktiv ? BONUS_PUNKTE : NORMALE_PUNKTE;
            score += punkte;
            scoreLabel.setText("⭐ Score: " + score);

            // NEU: Feedback ohne Popup
            zeigeFeedbackRichtig(punkte);

            // Firewall aktivieren bei X richtigen
            if (richtigeInFolge >= BONUS_RICHTIGE_NOETIG && !firewallAktiv) {
                aktiviereFirewall();
            }

            // Bei richtig: Kurze Verzögerung (1 Sekunde) dann weiter
            Timer naechsteEmailTimer = new Timer(1000, e -> {
                aktuelleEmailIndex++;
                zeigeNaechsteEmail();
                requestFocusInWindow();
            });
            naechsteEmailTimer.setRepeats(false);
            naechsteEmailTimer.start();

        } else {
            richtigeInFolge = 0;
            firewallAktiv = false;
            firewallCounter = 0;

            leben--;
            lebenLabel.setText(erstelleLebenString());

            // NEU: Feedback ohne Popup
            zeigeFeedbackFalsch("FALSCH! -1 ❤️");

            if (leben <= 0) {
                gameOver();
                return;
            }

            // Bei falsch: Längere Verzögerung (2.6 Sekunden) wegen Tipp
            Timer naechsteEmailTimer = new Timer(3200, e -> {
                aktuelleEmailIndex++;
                zeigeNaechsteEmail();
                requestFocusInWindow();
            });
            naechsteEmailTimer.setRepeats(false);
            naechsteEmailTimer.start();
        }

        // Firewall-Counter runterzählen
        if (firewallAktiv) {
            firewallCounter--;
            if (firewallCounter <= 0) {
                deaktiviereFirewall();
            }
        }


    }

    // Level erfolgreich abgeschlossen
    private void levelAbgeschlossen() {
        playSound(clipLevelGeschafft);
        hauptFenster.levelGeschafft(level);
        hauptFenster.zeigeResultScreen(level, score, leben, maxLeben, emails.size(), true);
    }

    // Spiel vorbei - keine Leben mehr
    private void gameOver() {
        playSound(clipGameOver);
        hauptFenster.zeigeResultScreen(level, score, 0, maxLeben, emails.size(), false);
    }

    // Startet den Timer für die aktuelle E-Mail
    private void starteTimer() {
        // Alten Timer stoppen falls vorhanden
        if (timer != null) {
            timer.stop();
        }

        // Zeit anpassen wenn Firewall aktiv
        int bonusZeit = firewallAktiv ? (int)(zeitProEmail * BONUS_ZEIT_MULTIPLIKATOR) : 0;
        verbleibendeSekunden = zeitProEmail + bonusZeit;

// Timer-Farbe ändern wenn Firewall aktiv
        if (firewallAktiv) {
            timerLabel.setForeground(new Color(100, 200, 255));
            timerLabel.setText("⏱️ Zeit: " + verbleibendeSekunden + "s 🛡️");  // Mit Firewall-Icon!
        } else {
            timerLabel.setForeground(new Color(255, 107, 53));
            timerLabel.setText("⏱️ Zeit: " + verbleibendeSekunden + "s");  // ✅ "Zeit:" hinzufügen!
        }

// Neuer Timer - jede Sekunde
        timer = new Timer(1000, e -> {
            verbleibendeSekunden--;

            // Mit oder ohne Firewall-Icon
            if (firewallAktiv) {
                timerLabel.setText("⏱️ Zeit: " + verbleibendeSekunden + "s 🛡️");  // ✅ Mit Icon!
            } else {
                timerLabel.setText("⏱️ Zeit: " + verbleibendeSekunden + "s");  // ✅ "Zeit:" hinzufügen!
            }

            if (verbleibendeSekunden <= 0) {
                timer.stop();
                zeitAbgelaufen();
            }
        });
        timer.start();
    }

    // Zeit ist abgelaufen - zählt als falsche Antwort
    private void zeitAbgelaufen() {
        leben--;
        lebenLabel.setText(erstelleLebenString());

        // NEU: Feedback ohne Popup
        zeigeFeedbackFalsch("⏱️ ZEIT ABGELAUFEN! -1 ❤️");

        if (leben <= 0) {
            gameOver();
            return;
        }

        aktuelleEmailIndex++;
        zeigeNaechsteEmail();
        requestFocusInWindow();
    }

    // Aktiviert FireWall Bounus
    private void aktiviereFirewall() {
        playSound(clipBonus);
        firewallAktiv = true;
        firewallCounter = BONUS_DAUER_EMAILS;
        richtigeInFolge = 0;

        setBackground(new Color(40, 60, 80));
        int prozent = (int)(BONUS_ZEIT_MULTIPLIKATOR * 100);

        // CUSTOM DIALOG - Kein hässliches JOptionPane!
        JDialog firewallDialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "🛡️ Firewall Aktiviert!", true);
        firewallDialog.setLayout(new BorderLayout());
        firewallDialog.setUndecorated(true);

        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BorderLayout(0, 20));
        dialogPanel.setBackground(new Color(25, 30, 50));
        dialogPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 180, 255), 3),
                BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));

        // Titel
        JLabel titleLabel = new JLabel("🛡️ FIREWALL AKTIVIERT! 🛡️", JLabel.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setForeground(new Color(100, 200, 255));

        // Info
        JLabel infoLabel = new JLabel(
                "<html><center style='line-height: 1.6;'>" +
                        "<span style='font-size: 16px; color: #FFFFFF;'><b>Bonus für die nächsten " + BONUS_DAUER_EMAILS + " E-Mails:</b></span><br><br>" +
                        "<span style='font-size: 15px; color: #AAAAAA;'>⏱️ <b>" + prozent + "% mehr Zeit</b> pro Email</span><br>" +
                        "<span style='font-size: 15px; color: #AAAAAA;'>⭐ <b>" + BONUS_PUNKTE + " Punkte</b> statt " + NORMALE_PUNKTE + "</span>" +
                        "</center></html>",
                JLabel.CENTER
        );

        // OK Button
        RoundedButton okButton = new RoundedButton("✓ WEITER");
        okButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        okButton.setBackground(new Color(100, 180, 255));
        okButton.setColors(new Color(100, 180, 255), new Color(120, 200, 255));
        okButton.setForeground(Color.WHITE);
        okButton.setPreferredSize(new Dimension(180, 50));
        okButton.addActionListener(e -> firewallDialog.dispose());

        // ENTER-Taste Support!
        okButton.registerKeyboardAction(
                e -> firewallDialog.dispose(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW
        );

        // Dialog auf Enter reagieren lassen
        firewallDialog.getRootPane().setDefaultButton(okButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(okButton);

        dialogPanel.add(titleLabel, BorderLayout.NORTH);
        dialogPanel.add(infoLabel, BorderLayout.CENTER);
        dialogPanel.add(buttonPanel, BorderLayout.SOUTH);

        firewallDialog.add(dialogPanel);
        firewallDialog.pack();
        firewallDialog.setLocationRelativeTo(this);
        firewallDialog.setVisible(true);
    }

    // Deaktiviert den Firewall- Bonus
    private void deaktiviereFirewall() {
        firewallAktiv = false;
        setBackground(new Color(26, 26, 46));
        timerLabel.setForeground(new Color(255, 107, 53));
    }

    // Pause oder fortsetzen
    private void togglePause() {
        if (isPausiert) {
            fortsetzen();
        } else {
            pausieren();
        }
    }

    // Pausiert das Spiel
    private void pausieren() {
        isPausiert = true;

        // Timer stoppen
        if (timer != null) {
            timer.stop();
        }

        // Pause-Menü erstellen
        pauseMenu = new PauseMenu(this, score, leben, verbleibendeSekunden);

        // GlassPane vorbereiten
        pauseGlassPane = new JPanel(new BorderLayout());
        pauseGlassPane.setOpaque(false);
        pauseGlassPane.add(pauseMenu, BorderLayout.CENTER);

        // GlassPane aktivieren
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            frame.setGlassPane(pauseGlassPane);
            frame.getGlassPane().setVisible(true);
        }
    }

    // Setzt das Spiel fort
    public void fortsetzen() {
        isPausiert = false;

        // GlassPane verstecken
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null && frame.getGlassPane() != null) {
            frame.getGlassPane().setVisible(false);
        }

        // Cleanup
        pauseMenu = null;
        pauseGlassPane = null;

        // Timer fortsetzen
        if (timer != null && !timer.isRunning()) {
            timer.start();
        }

        // Focus zurück
        SwingUtilities.invokeLater(() -> requestFocusInWindow());
    }


    // Level neu starten
    public void levelNeuStarten() {
        if (timer != null) {
            timer.stop();
        }
        isPausiert = false;

        // GlassPane verstecken
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null && frame.getGlassPane() != null) {
            frame.getGlassPane().setVisible(false);
        }

        // Cleanup
        pauseMenu = null;
        pauseGlassPane = null;

        // Level neu starten
        hauptFenster.starteLevel(level);
    }

    // Zum Hauptmenü zurück
    public void zumHauptmenue() {
        if (timer != null) {
            timer.stop();
        }
        isPausiert = false;

        // GlassPane verstecken
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null && frame.getGlassPane() != null) {
            frame.getGlassPane().setVisible(false);
        }

        // Cleanup
        pauseMenu = null;
        pauseGlassPane = null;

        // Zum Hauptmenü
        hauptFenster.zeigeLevelAuswahl();
    }


    /**
     * Helfer-Methode, um Code-Duplikate in zeigeFeedbackRichtig/Falsch zu vermeiden.
     * Nimmt eine (rote oder grüne) FeedbackCard, löscht die alte und zeigt die neue an.
     */
    private void zeigeFeedbackCard(FeedbackCard neueCard) {
        // Finde das Panel (feedbackPanel), in dem die Karten liegen
        Container parent = feedbackCard.getParent();

        if (parent instanceof JPanel) {
            JPanel feedbackPanel = (JPanel) parent;
            feedbackPanel.removeAll(); // Lösche alte Cards

            // Füge die neuen Komponenten hinzu
            neueCard.setAlignmentX(Component.CENTER_ALIGNMENT);
            tippCard.setAlignmentX(Component.CENTER_ALIGNMENT);

            feedbackPanel.add(neueCard);
            feedbackPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            feedbackPanel.add(tippCard);

            // UI aktualisieren
            feedbackPanel.revalidate();
            feedbackPanel.repaint();

            // Referenz auf die neue Karte speichern (wichtig!)
            feedbackCard = neueCard;
        }

        // Animation starten
        feedbackCard.showWithAnimation();
    }

    private void zeigeFeedbackRichtig(int punkte) {
        playSound(clipRichtig);

        // 1. Karte erstellen
        FeedbackCard neueCard = new FeedbackCard("✅", "RICHTIG!  +" + punkte + " Punkte", new Color(50, 180, 100));

        // 2. Helfer-Methode aufrufen (die die ganze UI-Arbeit macht)
        zeigeFeedbackCard(neueCard);

        // 3. Timer starten
        Timer hideTimer = new Timer(1200, e -> feedbackCard.setVisible(false));
        hideTimer.setRepeats(false);
        hideTimer.start();
    }

    private void zeigeFeedbackFalsch(String grund) {
        playSound(clipFalsch);

        // 1. Karte erstellen
        FeedbackCard neueCard = new FeedbackCard("❌", grund, new Color(220, 50, 50));

        // 2. Helfer-Methode aufrufen
        zeigeFeedbackCard(neueCard);

        // 3. Tipp anzeigen (das ist der einzige Unterschied zu "Richtig")
        zeigeTipp();

        // 4. Timer starten
        Timer hideTimer = new Timer(2800, e -> feedbackCard.setVisible(false));
        hideTimer.setRepeats(false);
        hideTimer.start();
    }

    // Zeigt den Email-spezifischen Tipp
    private void zeigeTipp() {
        Email aktuelleEmail = emails.get(aktuelleEmailIndex);
        String tipp = aktuelleEmail.getTipp();

        tippCard.showTipp(tipp);

        Timer hideTimer = new Timer(2800, e -> tippCard.setVisible(false));
        hideTimer.setRepeats(false);
        hideTimer.start();
    }

    // Lädt alle Sounds beim Start
    private void soundsVorladen() {
        clipRichtig = soundLaden("richtig.wav");
        clipFalsch = soundLaden("falsch.wav");
        clipBonus = soundLaden("bonus.wav");
        clipLevelGeschafft = soundLaden("level_geschafft.wav");
        clipGameOver = soundLaden("game_over.wav");
    }

    private Clip soundLaden(String dateiname) {
        try {
            // Der Pfad MUSS mit einem / beginnen (heißt: suche ab dem Root deines "src" Ordners)
            java.net.URL soundURL = getClass().getResource("/games/phishingdefender/assets/sounds/" + dateiname);

            if (soundURL == null) {
                System.out.println("Sound nicht gefunden (als Ressource): " + dateiname);
                return null;
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            return clip;

        } catch (Exception e) {
            System.out.println("Fehler beim Laden von " + dateiname + ": " + e.getMessage());
            e.printStackTrace(); // Gut für Debugging
            return null;
        }
    }

    // Spielt einen vorgeladenen Sound ab
    private void playSound(Clip clip) {
        if (clip != null) {
            clip.setFramePosition(0);  // Von vorne starten
            clip.start();
        }
    }

    // Erstellt Leben-String mit Herz-Icons
    private String erstelleLebenString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < leben; i++) {
            sb.append("❤️");
        }

        for (int i = leben; i < maxLeben; i++) {
            sb.append("🖤");
        }

        return sb.toString();
    }
}

