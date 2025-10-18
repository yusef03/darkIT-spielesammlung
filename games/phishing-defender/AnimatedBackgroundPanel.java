package games.phishingdefender;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Panel mit animiertem Hintergrund (fallende Partikel)
 */
public class AnimatedBackgroundPanel extends JPanel {

    private List<Particle> particles;
    private Timer animationTimer;
    private Random random;

    public AnimatedBackgroundPanel() {
        particles = new ArrayList<>();
        random = new Random();


        for (int i = 0; i < 25; i++) {
            particles.add(new Particle());
        }


        animationTimer = new Timer(33, e -> {
            updateParticles();
            repaint();
        });
        animationTimer.start();
    }

    private void updateParticles() {
        for (Particle p : particles) {
            p.update();

            // Wenn Partikel unten raus ist, oben neu starten
            if (p.y > getHeight()) {
                p.reset();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Gradient-Hintergrund
        GradientPaint gradient = new GradientPaint(
                0, 0, new Color(10, 15, 35),
                0, getHeight(), new Color(5, 8, 20)
        );
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Cyber-Grid Linien (horizontal)
        g2d.setColor(new Color(30, 100, 200, 20));  // Auch transparenter!
        for (int i = 0; i < getHeight(); i += 60) {  // 60 statt 40 = weniger Linien!
            g2d.drawLine(0, i, getWidth(), i);
        }

        // Partikel zeichnen
        for (Particle p : particles) {
            p.draw(g2d);
        }
    }

    // Partikel Klasse
    private class Particle {
        float x, y;
        float speed;
        int size;
        Color color;

        Particle() {
            reset();
        }

        void reset() {
            x = random.nextInt(800);
            y = -random.nextInt(600);
            speed = 0.5f + random.nextFloat() * 2f;
            size = 2 + random.nextInt(4);

            // Verschiedene Blau/Cyan Töne
            int r = 50 + random.nextInt(100);
            int g = 150 + random.nextInt(105);
            int b = 200 + random.nextInt(55);
            int alpha = 100 + random.nextInt(156);
            color = new Color(r, g, b, alpha);
        }

        void update() {
            y += speed;
        }

        void draw(Graphics2D g2d) {
            // Jeden 5. Partikel als Email-Icon
            if ((int)x % 5 == 0) {
                // Mini Email-Symbol
                g2d.setColor(new Color(100, 180, 255, color.getAlpha()));
                // Envelope Body
                g2d.fillRect((int)x, (int)y, size * 3, size * 2);
                // Envelope Flap
                int[] xPoints = {(int)x, (int)x + (size * 3) / 2, (int)x + size * 3};
                int[] yPoints = {(int)y, (int)y + size, (int)y};
                g2d.fillPolygon(xPoints, yPoints, 3);
            } else {
                // Normale Partikel
                g2d.setColor(color);
                g2d.fillOval((int)x, (int)y, size, size);

                // Glow
                g2d.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 50));
                g2d.fillOval((int)x - 2, (int)y - 2, size + 4, size + 4);
            }
        }
    }
}