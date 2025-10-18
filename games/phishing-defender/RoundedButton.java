package games.phishingdefender;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.GradientPaint;


/**
 * Custom Button mit abgerundeten Ecken
 */
public class RoundedButton extends JButton {

    private Color hoverColor;
    private Color normalColor;
    private boolean isHovered = false;

    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);

        // Hover-Effekt
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                isHovered = true;
                repaint();
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                isHovered = false;
                repaint();
            }
        });
    }

    public void setColors(Color normal, Color hover) {
        this.normalColor = normal;
        this.hoverColor = hover;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // SCHATTEN (dunkler, versetzt)
        g2.setColor(new Color(0, 0, 0, 80));
        g2.fill(new RoundRectangle2D.Float(3, 5, getWidth() - 3, getHeight() - 2, 20, 20));

        // GLOW (äußerer Ring)
        if (isHovered) {
            g2.setColor(new Color(255, 180, 100, 100));
            g2.fill(new RoundRectangle2D.Float(-2, -2, getWidth() + 4, getHeight() + 4, 24, 24));
        }

        // Farbe je nach Hover
        if (isHovered && hoverColor != null) {
            g2.setColor(hoverColor);
        } else if (normalColor != null) {
            g2.setColor(normalColor);
        } else {
            g2.setColor(getBackground());
        }

        // Haupt-Button
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth() - 3, getHeight() - 5, 20, 20));

        // GLANZ oben (Highlight)
        GradientPaint gloss = new GradientPaint(
                0, 0, new Color(255, 255, 255, 40),
                0, getHeight() / 2, new Color(255, 255, 255, 0)
        );
        g2.setPaint(gloss);
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth() - 3, getHeight() / 2, 20, 20));

        g2.dispose();
        super.paintComponent(g);
    }
}