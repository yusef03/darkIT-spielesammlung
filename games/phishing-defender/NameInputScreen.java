package games.phishingdefender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Name Input Screen - Spieler gibt seinen Detektiv-Namen ein
 */
public class NameInputScreen extends JPanel {

    private PhishingDefender hauptFenster;
    private JTextField nameField;

    public NameInputScreen(PhishingDefender hauptFenster) {
        this.hauptFenster = hauptFenster;
        setLayout(new BorderLayout());
        setupUI();
    }

    private void setupUI() {
        // Animierter Hintergrund
        AnimatedBackgroundPanel backgroundPanel = new AnimatedBackgroundPanel();
        backgroundPanel.setLayout(new BorderLayout());

        // === HAUPT CONTENT ===
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new GridBagLayout());
        mainContent.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 50, 20, 50);
        gbc.anchor = GridBagConstraints.CENTER;

        // === TITEL ===
        JLabel welcomeLabel = new JLabel("🕵️ WILLKOMMEN, REKRUT! 🕵️", JLabel.CENTER);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 42));
        welcomeLabel.setForeground(new Color(255, 140, 80));
        gbc.gridy = 0;
        mainContent.add(welcomeLabel, gbc);

        // === UNTERTITEL ===
        JLabel subtitleLabel = new JLabel(
                "<html><center>" +
                        "<span style='font-size: 18px; color: #CCCCCC;'>" +
                        "Wir brauchen deine Hilfe im Kampf gegen Cyber-Kriminelle!" +
                        "</span></center></html>",
                JLabel.CENTER
        );
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 50, 30, 50);
        mainContent.add(subtitleLabel, gbc);

        // === INPUT BOX ===
        JPanel inputBox = new JPanel();
        inputBox.setLayout(new BoxLayout(inputBox, BoxLayout.Y_AXIS));
        inputBox.setBackground(new Color(30, 35, 60, 230));
        inputBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 180, 255, 150), 3),
                BorderFactory.createEmptyBorder(25, 35, 25, 35)
        ));
        inputBox.setPreferredSize(new Dimension(600, 200));
        inputBox.setMaximumSize(new Dimension(600, 200));

        // Label
        JLabel instructionLabel = new JLabel("Gib deinen Detektiv-Code-Namen ein:");
        instructionLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        instructionLabel.setForeground(new Color(255, 107, 53));
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // TextField
        nameField = new JTextField();
        nameField.setFont(new Font("Monospace", Font.BOLD, 22));
        nameField.setForeground(Color.WHITE);
        nameField.setBackground(new Color(20, 25, 45));
        nameField.setCaretColor(new Color(255, 140, 80));
        nameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 180, 255, 100), 2),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        nameField.setHorizontalAlignment(JTextField.CENTER);
        nameField.setPreferredSize(new Dimension(500, 60));
        nameField.setMaximumSize(new Dimension(500, 60));
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Enter-Taste
        nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    submitName();
                }
            }
        });

        // Placeholder
        nameField.setForeground(new Color(150, 150, 150));
        nameField.setText("Dein Name...");
        nameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (nameField.getText().equals("Dein Name...")) {
                    nameField.setText("");
                    nameField.setForeground(Color.WHITE);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (nameField.getText().isEmpty()) {
                    nameField.setForeground(new Color(150, 150, 150));
                    nameField.setText("Dein Name...");
                }
            }
        });

// Hint
        JLabel hintLabel = new JLabel(
                "<html><center>" +
                        "<span style='font-size: 12px; color: #888888;'>" +
                        "💡 Drücke ENTER oder klicke LOS!" +
                        "</span></center></html>",
                JLabel.CENTER
        );
        hintLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        inputBox.add(instructionLabel);
        inputBox.add(Box.createRigidArea(new Dimension(0, 15)));
        inputBox.add(nameField);
        inputBox.add(Box.createRigidArea(new Dimension(0, 15)));
        inputBox.add(hintLabel);

        gbc.gridy = 2;
        gbc.insets = new Insets(20, 50, 30, 50);
        mainContent.add(inputBox, gbc);

// === BUTTONS ===
        JButton losButton = Theme.createStyledButton(
                "▶ LOS!",
                Theme.FONT_BUTTON_LARGE, // 22px
                Theme.COLOR_ACCENT_ORANGE,
                Theme.COLOR_ACCENT_ORANGE_HOVER,
                Theme.PADDING_BUTTON_LARGE // 15px padding
        );
        losButton.setPreferredSize(new Dimension(200, 55)); // Deine alte Größe
        losButton.addActionListener(e -> submitName());

        gbc.gridy = 3;
        gbc.insets = new Insets(10, 0, 10, 0);
        mainContent.add(losButton, gbc);

        JButton zurueckButton = Theme.createStyledButton(
                "← ZURÜCK",
                Theme.FONT_BUTTON_MEDIUM, // 18px
                Theme.COLOR_BUTTON_GREY,
                Theme.COLOR_BUTTON_GREY_HOVER,
                Theme.PADDING_BUTTON_MEDIUM // 12px padding
        );
        zurueckButton.setPreferredSize(new Dimension(180, 50)); // Deine alte Größe
        zurueckButton.addActionListener(e -> hauptFenster.zeigeWelcomeScreen());

        gbc.gridy = 4;
        gbc.insets = new Insets(10, 0, 40, 0);
        mainContent.add(zurueckButton, gbc);

        backgroundPanel.add(mainContent, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(backgroundPanel, BorderLayout.CENTER);

        // Focus auf TextField
        SwingUtilities.invokeLater(() -> nameField.requestFocusInWindow());
    }

    private void submitName() {
        String name = nameField.getText().trim();

        if (name.isEmpty() || name.equals("Dein Name...")) {
            // Fehler-Animation
            nameField.setBackground(new Color(150, 50, 50));
            Timer timer = new Timer(200, e -> nameField.setBackground(new Color(20, 25, 45)));
            timer.setRepeats(false);
            timer.start();

            JOptionPane.showMessageDialog(
                    this,
                    "Bitte gib einen Namen ein!",
                    "⚠️ Fehler",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Name speichern und weiter
        hauptFenster.setSpielerName(name);
        hauptFenster.zeigeLevelAuswahl();
    }
}