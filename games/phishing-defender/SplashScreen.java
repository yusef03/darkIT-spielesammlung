package games.phishingdefender;

import javax.swing.*;
import java.awt.*;

/**
 * Splash Screen beim Spielstart
 */
public class SplashScreen extends JWindow {

    public SplashScreen() {
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                // Gradient-Hintergrund (wie Welcome Screen)
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(15, 20, 45),
                        0, getHeight(), new Color(5, 10, 25)
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new BorderLayout());

        // Logo (Text-Version wenn Bild nicht geladen werden kann)
        JLabel logoLabel;
        try {
            ImageIcon icon = new ImageIcon("games/phishing-defender/assets/images/logo.png");
            Image scaled = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            logoLabel = new JLabel(new ImageIcon(scaled), JLabel.CENTER);
        } catch (Exception e) {
            logoLabel = new JLabel("🛡️", JLabel.CENTER);
            logoLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        }
        logoLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));

        // Titel
        JLabel titleLabel = new JLabel("PHISHING DEFENDER", JLabel.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setForeground(new Color(255, 140, 80));

        // Loading Text mit Animation-Punkten
        JLabel loadingLabel = new JLabel("Wird geladen", JLabel.CENTER);
        loadingLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        loadingLabel.setForeground(new Color(150, 150, 150));
        loadingLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        // Animierte Punkte
        Timer dotTimer = new Timer(400, null);
        final int[] dotCount = {0};
        dotTimer.addActionListener(e -> {
            dotCount[0] = (dotCount[0] + 1) % 4;
            String dots = ".".repeat(dotCount[0]);
            loadingLabel.setText("Wird geladen" + dots);
        });
        dotTimer.start();

        // Progress Bar (optional)
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setBackground(new Color(30, 35, 60));
        progressBar.setForeground(new Color(255, 107, 53));
        progressBar.setBorderPainted(false);

        JPanel progressPanel = new JPanel();
        progressPanel.setOpaque(false);
        progressPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 40, 60));
        progressPanel.add(progressBar);

        // Content zusammenbauen
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);

        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPanel.add(logoLabel);
        contentPanel.add(titleLabel);
        contentPanel.add(loadingLabel);

        panel.add(contentPanel, BorderLayout.CENTER);
        panel.add(progressPanel, BorderLayout.SOUTH);

        add(panel);
    }

    public void showSplash(int milliseconds) {
        setVisible(true);
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setVisible(false);
        dispose();
    }
}