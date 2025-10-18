package games.phishingdefender;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Moderne Tipp Card
 */
public class TippCard extends JPanel {

    private String tippText;
    private float alpha = 0f;

    public TippCard() {
        setOpaque(false);
        setPreferredSize(new Dimension(800, 80));
        setMaximumSize(new Dimension(850, 80));
    }

    public void showTipp(String text) {
        this.tippText = text;
        setVisible(true);
        alpha = 0f;

        Timer fadeTimer = new Timer(25, null);
        fadeTimer.addActionListener(e -> {
            alpha += 0.1f;
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

        if (!isVisible() || alpha <= 0 || tippText == null) return;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        // Schatten
        g2.setColor(new Color(0, 0, 0, (int)(70 * alpha)));
        g2.fill(new RoundRectangle2D.Float(3, 3, w - 3, h - 3, 12, 12));

        // Haupt-Box (dunkles Gold/Braun)
        GradientPaint gradient = new GradientPaint(
                0, 0, new Color(70, 60, 30),
                0, h, new Color(50, 45, 25)
        );
        g2.setPaint(gradient);
        g2.fill(new RoundRectangle2D.Float(0, 0, w - 3, h - 3, 12, 12));

        // Leuchtender Border
        g2.setColor(new Color(255, 193, 7, (int)(200 * alpha)));
        g2.setStroke(new BasicStroke(2.5f));
        g2.draw(new RoundRectangle2D.Float(0, 0, w - 3, h - 3, 12, 12));



        g2.setFont(new Font("Arial", Font.PLAIN, 32));
        g2.setColor(new Color(255, 220, 100));
        g2.drawString("💡", 22, 52);


        g2.setFont(new Font("SansSerif", Font.BOLD, 16));
        g2.setColor(new Color(255, 240, 200));

        // Text umbrechen wenn zu lang
        FontMetrics fm = g2.getFontMetrics();
        int maxWidth = w - 90;
        String displayText = tippText;

        if (fm.stringWidth(tippText) > maxWidth) {
            displayText = "TIPP: " + tippText.substring(0, Math.min(80, tippText.length())) + "...";
        } else {
            displayText = "TIPP: " + tippText;
        }

        g2.drawString(displayText, 70, 52);

        g2.dispose();
    }
}