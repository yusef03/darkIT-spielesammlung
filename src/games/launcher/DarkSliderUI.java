package games.launcher;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Ein custom UI für JSlider, das zum darkIT-Theme passt.
 * Runder Balken, grüner Füllstand, grüner Regler.
 *
 * @author yusef03 (inspiriert von Online-Beispielen)
 * @version 1.0
 */
public class DarkSliderUI extends BasicSliderUI {

    public DarkSliderUI(JSlider slider) {
        super(slider);
    }

    @Override
    public void paintTrack(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Höhe und Y-Position des Balkens
        int trackHeight = 8;
        int trackY = trackRect.y + (trackRect.height - trackHeight) / 2;
        int trackWidth = trackRect.width;

        // Runder Hintergrund-Balken (dunkelgrau)
        g2d.setColor(Theme.PANEL_DARK.brighter());
        g2d.fill(new RoundRectangle2D.Float(trackRect.x, trackY, trackWidth, trackHeight, 8, 8));

        // Runder Füll-Balken (grün)
        // Berechne, wie weit der Balken gefüllt ist
        int fillWidth = thumbRect.x + (thumbRect.width / 2) - trackRect.x;
        g2d.setColor(Theme.ACCENT_GREEN);
        g2d.fill(new RoundRectangle2D.Float(trackRect.x, trackY, fillWidth, trackHeight, 8, 8));
    }

    @Override
    public void paintThumb(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Zeichne den Regler-Knopf (grün mit weißem Punkt)
        g2d.setColor(Theme.ACCENT_GREEN);
        g2d.fillOval(thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height);

        g2d.setColor(Color.WHITE);
        g2d.fillOval(thumbRect.x + 4, thumbRect.y + 4, thumbRect.width - 8, thumbRect.height - 8);
    }

    @Override
    protected Dimension getThumbSize() {
        // Macht den Knopf größer (20x20)
        return new Dimension(20, 20);
    }
}