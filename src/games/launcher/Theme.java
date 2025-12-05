package games.launcher;

import javax.swing.*;
import java.awt.*;

/**
 * Design-Konstanten für den darkIT Launcher. (Version 2.0 - Dunkler)
 * Definiert Farben, Schriftarten und den runden Button-Style.
 *
 * @author yusef03
 * @version 2.0
 */
public class Theme {

    // === GRUNDFARBEN  ===
    public static final Color BACKGROUND_DARK = new Color(18, 18, 20);
    public static final Color PANEL_DARK = new Color(32, 34, 37);
    public static final Color PANEL_HOVER = new Color(42, 44, 48);

    // === AKZENTFARBEN  ===
    public static final Color ACCENT_GREEN = new Color(0, 255, 128);
    public static final Color ACCENT_ORANGE_DARK = new Color(230, 90, 30);

    // === BUTTON-FARBEN
    public static final Color BUTTON_GREEN = new Color(23, 168, 86);
    public static final Color BUTTON_GREEN_HOVER = new Color(33, 188, 106);
    public static final Color BUTTON_RED = new Color(200, 35, 51);
    public static final Color BUTTON_RED_HOVER = new Color(220, 55, 71);

    // === TEXTFARBEN  ===
    public static final Color TEXT_PRIMARY = new Color(230, 230, 230);
    public static final Color TEXT_SECONDARY = new Color(160, 160, 160);

    // === SCHRIFTARTEN ===
    public static final Font FONT_TITLE_LARGE = new Font("SansSerif", Font.BOLD, 48);
    public static final Font FONT_TITLE_MEDIUM = new Font("SansSerif", Font.BOLD, 32);
    public static final Font FONT_SUBTITLE = new Font("SansSerif", Font.BOLD, 18);
    public static final Font FONT_BODY = new Font("SansSerif", Font.PLAIN, 14);
    public static final Font FONT_SMALL = new Font("SansSerif", Font.PLAIN, 12);
    public static final Font FONT_BUTTON = new Font("SansSerif", Font.BOLD, 16);

    // === ABSTÄNDE  ===
    public static final int CARD_WIDTH = 280;
    public static final int CARD_HEIGHT = 320;


    /**
     * Erstellt einen stylischen, RUNDEN Button im darkIT-Design.
     *
     * @param text Button-Text
     * @param bgColor Hintergrundfarbe
     * @param hoverColor Farbe beim Hover
     * @return Gestylter JButton
     */
    public static JButton createStyledButton(String text, Color bgColor, Color hoverColor) {

        // JButton erstellen
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Hintergrundfarbe je nach Zustand (hover, pressed)
                if (getModel().isPressed()) {
                    g2.setColor(hoverColor.darker());
                } else if (getModel().isRollover()) {
                    g2.setColor(hoverColor);
                } else {
                    g2.setColor(bgColor);
                }

                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);

                // Text zentriert malen
                g2.setColor(getForeground());
                g2.setFont(getFont());
                FontMetrics fm = g2.getFontMetrics();
                int textX = (getWidth() - fm.stringWidth(getText())) / 2;
                int textY = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
                g2.drawString(getText(), textX, textY);

                g2.dispose();
            }
        };

        // Standard-Malerei von Java ausschalten
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(false);

        // Restliches Styling
        button.setFont(FONT_BUTTON);
        button.setForeground(TEXT_PRIMARY);
        button.setBorder(BorderFactory.createEmptyBorder(12, 30, 12, 30));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }
}