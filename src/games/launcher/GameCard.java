package games.launcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

/**
 * Eine einzelne Spiel-Karte im Launcher.
 * Zeigt Icon, Name, Beschreibung und Start-Button.
 * Hat einen Glow-Effekt beim Hover (wie deine Level-Cards!).
 *
 * @author yusef03
 * @version 1.0
 */
public class GameCard extends JPanel {

    private final GameInfo game;
    private boolean hovered = false;
    private float glowAlpha = 0f;

    public GameCard(GameInfo game, Runnable onStartClick) {
        this.game = game;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        setPreferredSize(new Dimension(Theme.CARD_WIDTH, Theme.CARD_HEIGHT));
        setMaximumSize(new Dimension(Theme.CARD_WIDTH, Theme.CARD_HEIGHT));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        setupUI(onStartClick);
        setupHoverEffect();
    }

    private void setupUI(Runnable onStartClick) {
        // Icon (großes Emoji)
        JLabel iconLabel = new JLabel(game.getIcon(), JLabel.CENTER);
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 60));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Name
        JLabel nameLabel = new JLabel(game.getName(), JLabel.CENTER);
        nameLabel.setFont(Theme.FONT_TITLE_MEDIUM);
        nameLabel.setForeground(Theme.TEXT_PRIMARY);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Genre
        JLabel genreLabel = new JLabel(game.getGenre(), JLabel.CENTER);
        genreLabel.setFont(Theme.FONT_BODY);
        genreLabel.setForeground(Theme.ACCENT_GREEN);
        genreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Beschreibung
        JLabel descLabel = new JLabel("<html><center>" +
                shortenText(game.getDescription(), 70) +
                "</center></html>", JLabel.CENTER);
        descLabel.setFont(Theme.FONT_SMALL);
        descLabel.setForeground(Theme.TEXT_SECONDARY);
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        descLabel.setMaximumSize(new Dimension(240, 60));

        // Entwickler
        JLabel devLabel = new JLabel("von " + game.getDeveloper(), JLabel.CENTER);
        devLabel.setFont(new Font("SansSerif", Font.ITALIC, 11));
        devLabel.setForeground(new Color(120, 120, 130));
        devLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Start-Button
        JButton startBtn = Theme.createStyledButton(
                "▶ STARTEN",
                Theme.BUTTON_GREEN,
                Theme.BUTTON_GREEN_HOVER
        );
        startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        startBtn.addActionListener(e -> onStartClick.run());

        // Alles zusammenbauen
        add(iconLabel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(nameLabel);
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(genreLabel);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(descLabel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(devLabel);
        add(Box.createVerticalGlue());
        add(startBtn);
    }

    private void setupHoverEffect() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hovered = true;
                animateGlow(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hovered = false;
                animateGlow(false);
            }
        });
    }

    private Timer glowTimer = null;  // ← OBEN in der Klasse hinzufügen (Zeile 23)

    private void animateGlow(boolean fadeIn) {
        // Stoppe vorherigen Timer (wichtig!)
        if (glowTimer != null && glowTimer.isRunning()) {
            glowTimer.stop();
        }

        glowTimer = new Timer(50, null);  // ← 50ms statt 30ms (langsamer = weniger CPU)
        glowTimer.addActionListener(e -> {
            if (fadeIn) {
                glowAlpha += 0.15f;  // ← Größere Schritte (schneller fertig)
                if (glowAlpha >= 1.0f) {
                    glowAlpha = 1.0f;
                    glowTimer.stop();
                }
            } else {
                glowAlpha -= 0.15f;
                if (glowAlpha <= 0f) {
                    glowAlpha = 0f;
                    glowTimer.stop();
                }
            }
            repaint();
        });
        glowTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        // Glow-Effekt beim Hover
        if (hovered && glowAlpha > 0) {
            g2.setColor(new Color(Theme.ACCENT_GREEN.getRed(),
                    Theme.ACCENT_GREEN.getGreen(),
                    Theme.ACCENT_GREEN.getBlue(),
                    (int)(80 * glowAlpha)));
            g2.fill(new RoundRectangle2D.Float(-5, -5, w + 10, h + 10, 25, 25));
        }

        // Schatten
        g2.setColor(new Color(0, 0, 0, 100));
        g2.fill(new RoundRectangle2D.Float(5, 5, w - 5, h - 5, 20, 20));

        // Hauptkarte (Gradient)
        GradientPaint gradient = new GradientPaint(
                0, 0, Theme.PANEL_DARK,
                0, h, new Color(24, 25, 27)
        );
        g2.setPaint(gradient);
        g2.fill(new RoundRectangle2D.Float(0, 0, w - 5, h - 5, 20, 20));

        // Border
        g2.setColor(hovered ? Theme.ACCENT_GREEN : new Color(60, 70, 100));
        g2.setStroke(new BasicStroke(2));
        g2.draw(new RoundRectangle2D.Float(0, 0, w - 5, h - 5, 20, 20));

        // Highlight oben
        GradientPaint highlight = new GradientPaint(
                0, 0, new Color(255, 255, 255, 20),
                0, h / 3, new Color(255, 255, 255, 0)
        );
        g2.setPaint(highlight);
        g2.fill(new RoundRectangle2D.Float(0, 0, w - 5, h / 3, 20, 20));

        g2.dispose();
    }

    /**
     * Kürzt Text auf maxLength Zeichen und fügt "..." hinzu.
     */
    private String shortenText(String text, int maxLength) {
        if (text.length() <= maxLength) {
            return text;
        }
        return text.substring(0, maxLength - 3) + "...";
    }
}