package games.phishingdefender;
import javax.swing.*;
import java.awt.*;

/**
 * Phishing Defender - LevelSelectionScreen Class
 * @author yusef03
 * @version 1.0
 */

// Screen für die Level-Auswahl
public class LevelSelectionScreen extends JPanel {

    private PhishingDefender hauptFenster;

    public LevelSelectionScreen(PhishingDefender hauptFenster) {
        this.hauptFenster = hauptFenster;

        setLayout(new BorderLayout());
        setBackground(new Color(26, 26, 46));

        // Titel
        JLabel titel = new JLabel("Wähle ein Level", JLabel.CENTER);
        titel.setFont(new Font("Arial", Font.BOLD, 36));
        titel.setForeground(Color.WHITE);
        titel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));

        // Panel für die Level-Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 20, 20));
        buttonPanel.setBackground(new Color(26, 26, 46));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 150, 50, 150));

        // Level 1 Button
        JButton level1Button = erstelleLevelButton("LEVEL 1", "10 E-Mails | 5 Sekunden", true);
        level1Button.addActionListener(e -> levelStarten(1));

        // Level 2 Button
        JButton level2Button = erstelleLevelButton("LEVEL 2", "15 E-Mails | 3 Sekunden", false);
        level2Button.addActionListener(e -> levelStarten(2));

        // Level 3 Button
        JButton level3Button = erstelleLevelButton("LEVEL 3", "20 E-Mails | 2 Sekunden", false);
        level3Button.addActionListener(e -> levelStarten(3));

        buttonPanel.add(level1Button);
        buttonPanel.add(level2Button);
        buttonPanel.add(level3Button);

        // Zurück-Button
        JButton zurueckButton = new JButton("ZURÜCK");
        zurueckButton.setFont(new Font("Arial", Font.PLAIN, 18));
        zurueckButton.setBackground(new Color(100, 100, 100));
        zurueckButton.setForeground(Color.WHITE);
        zurueckButton.setFocusPainted(false);
        zurueckButton.addActionListener(e -> hauptFenster.zeigeWelcomeScreen());

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(26, 26, 46));
        bottomPanel.add(zurueckButton);

        add(titel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Erstellt einen Level-Button mit Titel und Beschreibung
    private JButton erstelleLevelButton(String titel, String beschreibung, boolean freigeschaltet) {
        JButton button = new JButton();
        button.setLayout(new BorderLayout());
        button.setBackground(freigeschaltet ? new Color(255, 107, 53) : new Color(80, 80, 80));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setEnabled(freigeschaltet);

        JLabel titelLabel = new JLabel(titel, JLabel.CENTER);
        titelLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titelLabel.setForeground(Color.WHITE);

        JLabel beschreibungLabel = new JLabel(beschreibung, JLabel.CENTER);
        beschreibungLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        beschreibungLabel.setForeground(Color.WHITE);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(2, 1));
        textPanel.setBackground(freigeschaltet ? new Color(255, 107, 53) : new Color(80, 80, 80));
        textPanel.add(titelLabel);
        textPanel.add(beschreibungLabel);

        button.add(textPanel, BorderLayout.CENTER);

        return button;
    }

    // Startet das gewählte Level
    private void levelStarten(int level) {
        JOptionPane.showMessageDialog(this,
                "Level " + level + " wird gestartet!\n(Noch nicht implementiert)",
                "Level Start",
                JOptionPane.INFORMATION_MESSAGE);
    }
}