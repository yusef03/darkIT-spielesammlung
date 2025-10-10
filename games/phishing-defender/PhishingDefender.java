package games.phishingdefender;
import javax.swing.*;
import java.awt.*;

/**
 * Phishing Defender - Email Class
 * @author yusef03
 * @version 1.0
 */

// Hauptklasse für Phishing Defender
public class PhishingDefender extends JFrame {

    private JPanel mainPanel;

    public PhishingDefender() {
        // Fenster-Einstellungen
        setTitle("Phishing Defender - IT Security Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Haupt-Panel erstellen
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(26, 26, 46));

        // Willkommens-Text
        JLabel welcomeLabel = new JLabel("Phishing Defender", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 48));
        welcomeLabel.setForeground(Color.WHITE);

        // Untertitel
        JLabel subtitleLabel = new JLabel("Protect yourself from phishing emails!", JLabel.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        subtitleLabel.setForeground(new Color(255, 107, 53));

        // Texte in Panel
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(2, 1, 0, 20));
        textPanel.setBackground(new Color(26, 26, 46));
        textPanel.add(welcomeLabel);
        textPanel.add(subtitleLabel);

        // Start-Button
        JButton startButton = new JButton("START GAME");
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.setBackground(new Color(255, 107, 53));
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.setBorderPainted(false);
        startButton.setPreferredSize(new Dimension(200, 60));

        // Button-Aktion - zeigt Level-Auswahl
        startButton.addActionListener(e -> zeigeLevelAuswahl());

        // Button-Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(26, 26, 46));
        buttonPanel.add(startButton);

        // Alles zusammenfügen
        mainPanel.add(textPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    // Zeigt den Welcome Screen
    public void zeigeWelcomeScreen() {
        getContentPane().removeAll();
        getContentPane().add(mainPanel);
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

    // Startet das Spiel
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PhishingDefender game = new PhishingDefender();
            game.setVisible(true);
        });
    }
}