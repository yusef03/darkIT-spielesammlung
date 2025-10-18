package games.phishingdefender;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Moderne Feedback Card (RICHTIG/FALSCH)
 */
public class FeedbackCard extends JPanel {

    private String text;
    private Color mainColor;
    private String icon;
    private float alpha = 0f;

    public FeedbackCard(String icon, String text, Color mainColor) {
        this.icon = icon;
        this.text = text;
        this.mainColor = mainColor;

        setOpaque(false);
        setPreferredSize(new Dimension(350, 50));
        setMaximumSize(new Dimension(400, 50));
    }

    public void showWithAnimation() {
        setVisible(true);
        alpha = 0f;

        Timer fadeTimer = new Timer(20, null);
        fadeTimer.addActionListener(e -> {
            alpha += 0.08f;
            if (alpha >= 1.0f) {
                alpha = 1.0f;
                fadeTimer.stop();
            }
            repaint();
        });
        fadeTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!isVisible() || alpha <= 0) return;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        // Transparenz anwenden
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        // MEGA Glow (mehrere Schichten)
        for (int i = 15; i > 0; i--) {
            int alphaGlow = (int)(25 * alpha * (i / 15.0));
            g2.setColor(new Color(mainColor.getRed(), mainColor.getGreen(), mainColor.getBlue(), alphaGlow));
            g2.fill(new RoundRectangle2D.Float(-i, -i, w + i * 2, h + i * 2, 20 + i, 20 + i));
        }

        // Schatten
        g2.setColor(new Color(0, 0, 0, (int)(100 * alpha)));
        g2.fill(new RoundRectangle2D.Float(4, 4, w - 4, h - 4, 18, 18));

        // Haupt-Card mit Gradient
        GradientPaint gradient = new GradientPaint(
                0, 0, mainColor,
                0, h, new Color(
                Math.max(0, mainColor.getRed() - 30),
                Math.max(0, mainColor.getGreen() - 30),
                Math.max(0, mainColor.getBlue() - 30)
        )
        );
        g2.setPaint(gradient);
        g2.fill(new RoundRectangle2D.Float(0, 0, w - 4, h - 4, 18, 18));

        // Glanz oben
        GradientPaint gloss = new GradientPaint(
                0, 0, new Color(255, 255, 255, (int)(50 * alpha)),
                0, h / 2, new Color(255, 255, 255, 0)
        );
        g2.setPaint(gloss);
        g2.fill(new RoundRectangle2D.Float(0, 0, w - 4, h / 2, 18, 18));

// Icon (mini)
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.PLAIN, 24));
        g2.drawString(icon, 15, 33);

// Text (mini)
        g2.setFont(new Font("SansSerif", Font.BOLD, 16));
        g2.drawString(text, 50, 32);

        g2.dispose();
    }
}