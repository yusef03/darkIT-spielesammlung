package games.phishingdefender;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Einstellungen Dialog
 */
public class SettingsDialog extends JDialog {

    private static boolean musicMuted = false;
    private static int musicVolume = 70;  // 0-100

    public SettingsDialog(JFrame parent) {
        super(parent, "⚙️ Einstellungen", true);
        setSize(450, 300);
        setLocationRelativeTo(parent);
        setResizable(false);

        setupUI();
    }

    private void setupUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(20, 25, 45));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Titel
        JLabel titleLabel = new JLabel("🎮 EINSTELLUNGEN");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(new Color(255, 140, 80));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        // Settings Panel
        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        settingsPanel.setBackground(new Color(30, 35, 60));
        settingsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 180, 255, 100), 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // === MUSIC ON/OFF ===
        JPanel musicPanel = new JPanel(new BorderLayout(10, 0));
        musicPanel.setOpaque(false);
        musicPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        JLabel musicLabel = new JLabel("🎵 Hintergrundmusik:");
        musicLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        musicLabel.setForeground(Color.WHITE);

        JCheckBox musicCheckbox = new JCheckBox("An");
        musicCheckbox.setSelected(!musicMuted);
        musicCheckbox.setFont(new Font("SansSerif", Font.PLAIN, 14));
        musicCheckbox.setForeground(Color.WHITE);
        musicCheckbox.setOpaque(false);
        musicCheckbox.setFocusPainted(false);
        musicCheckbox.addActionListener(e -> {
            musicMuted = !musicCheckbox.isSelected();
            if (musicMuted) {
                MusicManager.stopMenuMusic();
            } else {
                MusicManager.startMenuMusic();
            }
        });

        musicPanel.add(musicLabel, BorderLayout.WEST);
        musicPanel.add(musicCheckbox, BorderLayout.EAST);

        // === VOLUME SLIDER ===
        JPanel volumePanel = new JPanel();
        volumePanel.setLayout(new BoxLayout(volumePanel, BoxLayout.Y_AXIS));
        volumePanel.setOpaque(false);

        JLabel volumeLabel = new JLabel("🔊 Lautstärke: " + musicVolume + "%");
        volumeLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        volumeLabel.setForeground(Color.WHITE);
        volumeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JSlider volumeSlider = new JSlider(0, 100, musicVolume);
        volumeSlider.setOpaque(false);
        volumeSlider.setForeground(new Color(255, 140, 80));
        volumeSlider.setMajorTickSpacing(25);
        volumeSlider.setMinorTickSpacing(5);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
        volumeSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                musicVolume = volumeSlider.getValue();
                volumeLabel.setText("🔊 Lautstärke: " + musicVolume + "%");
                // Lautstärke SOFORT anpassen!
                MusicManager.updateVolume();
            }
        });

        volumePanel.add(volumeLabel);
        volumePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        volumePanel.add(volumeSlider);

        settingsPanel.add(musicPanel);
        settingsPanel.add(volumePanel);

// Close Button
        JButton closeButton = Theme.createStyledButton(
                "✓ SCHLIESSEN",
                Theme.FONT_BUTTON_SMALL, // 16px
                Theme.COLOR_BUTTON_GREY,
                Theme.COLOR_BUTTON_GREY_HOVER,
                Theme.PADDING_BUTTON_MEDIUM // 12px padding
        );
        closeButton.setPreferredSize(new Dimension(150, 45)); // Deine alte Größe
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        closeButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        buttonPanel.add(closeButton);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(settingsPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public static boolean isMusicMuted() {
        return musicMuted;
    }

    // Getter für Lautstärke
    public static int getMusicVolume() {
        return musicVolume;
    }
}