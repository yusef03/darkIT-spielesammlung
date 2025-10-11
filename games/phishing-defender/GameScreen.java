package games.phishingdefender;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * Phishing Defender - GameScreen Class
 * @author yusef03
 * @version 1.0
 */

// Der Haupt-Spielbildschirm
public class GameScreen extends JPanel {

    private PhishingDefender hauptFenster;
    private int level;
    private List<Email> emails;
    private int aktuelleEmailIndex;
    private int score;
    private int leben;
    private int maxLeben;
    private int zeitProEmail;
    private int verbleibendeSekunden;

    // UI Komponenten
    private JLabel scoreLabel;
    private JLabel lebenLabel;
    private JTextArea emailAnzeigeArea;
    private JLabel absenderLabel;
    private JLabel betreffLabel;
    private Timer timer;
    private JLabel timerLabel;

    public GameScreen(PhishingDefender hauptFenster, int level) {
        this.hauptFenster = hauptFenster;
        this.level = level;
        this.aktuelleEmailIndex = 0;
        this.score = 0;

        // Leben basierend auf Level
        if (level == 1) {
            this.maxLeben = 3;
        } else if (level == 2) {
            this.maxLeben = 4;
        } else {
            this.maxLeben = 5;
        }
        this.leben = maxLeben;

        // Zeit pro Mail basierend auf Level
        if (level == 1) {
            this.zeitProEmail = 5;
        } else if (level == 2) {
            this.zeitProEmail = 3;
        } else {
            this.zeitProEmail = 2;
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
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    antwortGeben(false);
                } else if (e.getKeyCode() == KeyEvent.VK_L) {
                    antwortGeben(true);
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    hauptFenster.zeigeLevelAuswahl();
                }
            }
        });
    }

    // Erstellt die Benutzeroberfläche
    private void setupUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(26, 26, 46));

        // Oben: Timer, Score und Leben
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 3));
        topPanel.setBackground(new Color(26, 26, 46));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setForeground(Color.WHITE);

        lebenLabel = new JLabel("Leben: " + leben + "/" + maxLeben);
        lebenLabel.setFont(new Font("Arial", Font.BOLD, 24));
        lebenLabel.setForeground(Color.WHITE);
        lebenLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        timerLabel = new JLabel("Zeit: 5s");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        timerLabel.setForeground(new Color(255, 107, 53));
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        topPanel.add(scoreLabel);
        topPanel.add(timerLabel);
        topPanel.add(lebenLabel);

        // Mitte: E-Mail Anzeige
        JPanel emailPanel = new JPanel();
        emailPanel.setLayout(new BorderLayout());
        emailPanel.setBackground(Color.WHITE);
        emailPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        absenderLabel = new JLabel("Von: ");
        absenderLabel.setFont(new Font("Arial", Font.BOLD, 16));

        betreffLabel = new JLabel("Betreff: ");
        betreffLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(2, 1));
        headerPanel.setBackground(Color.WHITE);
        headerPanel.add(absenderLabel);
        headerPanel.add(betreffLabel);

        emailAnzeigeArea = new JTextArea();
        emailAnzeigeArea.setFont(new Font("Arial", Font.PLAIN, 14));
        emailAnzeigeArea.setEditable(false);
        emailAnzeigeArea.setLineWrap(true);
        emailAnzeigeArea.setWrapStyleWord(true);
        emailAnzeigeArea.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(emailAnzeigeArea);

        emailPanel.add(headerPanel, BorderLayout.NORTH);
        emailPanel.add(scrollPane, BorderLayout.CENTER);

        // Unten: Steuerung
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 20, 0));
        buttonPanel.setBackground(new Color(26, 26, 46));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 30, 100));

        JButton sicherButton = new JButton("SICHER (A)");
        sicherButton.setFont(new Font("Arial", Font.BOLD, 20));
        sicherButton.setBackground(new Color(76, 175, 80));
        sicherButton.setForeground(Color.WHITE);
        sicherButton.setFocusPainted(false);
        sicherButton.addActionListener(e -> antwortGeben(false));

        JButton phishingButton = new JButton("PHISHING (L)");
        phishingButton.setFont(new Font("Arial", Font.BOLD, 20));
        phishingButton.setBackground(new Color(244, 67, 54));
        phishingButton.setForeground(Color.WHITE);
        phishingButton.setFocusPainted(false);
        phishingButton.addActionListener(e -> antwortGeben(true));

        buttonPanel.add(sicherButton);
        buttonPanel.add(phishingButton);

        add(topPanel, BorderLayout.NORTH);
        add(emailPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Zeigt die nächste E-Mail an
    private void zeigeNaechsteEmail() {
        if (aktuelleEmailIndex >= emails.size()) {
            levelAbgeschlossen();
            return;
        }

        Email email = emails.get(aktuelleEmailIndex);
        absenderLabel.setText("Von: " + email.getAbsender());
        betreffLabel.setText("Betreff: " + email.getBetreff());
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
            score += 10;
            scoreLabel.setText("Score: " + score);
            JOptionPane.showMessageDialog(this, "RICHTIG! +10 Punkte", "Korrekt!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            leben--;
            lebenLabel.setText("Leben: " + leben + "/" + maxLeben);
            JOptionPane.showMessageDialog(this, "FALSCH! -1 Leben", "Fehler!", JOptionPane.ERROR_MESSAGE);

            if (leben <= 0) {
                gameOver();
                return;
            }
        }

        aktuelleEmailIndex++;
        zeigeNaechsteEmail();

        // WICHTIG: Fokus zurückholen!
        requestFocusInWindow();
    }

    // Level erfolgreich abgeschlossen
    private void levelAbgeschlossen() {
        JOptionPane.showMessageDialog(this,
                "Level " + level + " geschafft!\n\n" +
                        "Endpunktzahl: " + score + "\n" +
                        "Verbleibende Leben: " + leben,
                "Level Geschafft!",
                JOptionPane.INFORMATION_MESSAGE);
        hauptFenster.zeigeLevelAuswahl();
    }

    // Spiel vorbei - keine Leben mehr
    private void gameOver() {
        JOptionPane.showMessageDialog(this,
                "GAME OVER!\n\n" +
                        "Erreichte Punkte: " + score + "\n" +
                        "Versuche es nochmal!",
                "Game Over",
                JOptionPane.ERROR_MESSAGE);
        hauptFenster.zeigeLevelAuswahl();
    }

    // Startet den Timer für die aktuelle E-Mail
    private void starteTimer() {
        // Alten Timer stoppen falls vorhanden
        if (timer != null) {
            timer.stop();
        }

        verbleibendeSekunden = zeitProEmail;
        timerLabel.setText("Zeit: " + verbleibendeSekunden + "s");

        // Neuer Timer - jede Sekunde
        timer = new Timer(1000, e -> {
            verbleibendeSekunden--;
            timerLabel.setText("Zeit: " + verbleibendeSekunden + "s");

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
        lebenLabel.setText("Leben: " + leben + "/" + maxLeben);
        JOptionPane.showMessageDialog(this,
                "ZEIT ABGELAUFEN! -1 Leben",
                "Zu langsam!",
                JOptionPane.WARNING_MESSAGE);

        if (leben <= 0) {
            gameOver();
            return;
        }

        aktuelleEmailIndex++;
        zeigeNaechsteEmail();
        requestFocusInWindow();
    }
}