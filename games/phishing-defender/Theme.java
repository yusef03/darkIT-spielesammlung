package games.phishingdefender;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Zentrale Klasse für alle UI-Konstanten (Farben, Fonts, Ränder)
 * und zum Erstellen von konsistenten UI-Komponenten (Buttons).
 */
public class Theme {

    // === FARBEN ===
    public static final Color COLOR_BACKGROUND_DARK = new Color(15, 20, 35);
    public static final Color COLOR_PANEL_DARK = new Color(25, 30, 50);
    public static final Color COLOR_TEXT_PRIMARY = new Color(230, 230, 230);
    public static final Color COLOR_TEXT_SECONDARY = new Color(160, 160, 160);

    // Button Farben
    public static final Color COLOR_ACCENT_ORANGE = new Color(255, 107, 53);
    public static final Color COLOR_ACCENT_ORANGE_HOVER = new Color(255, 140, 80);

    public static final Color COLOR_BUTTON_BLUE = new Color(100, 150, 255);
    public static final Color COLOR_BUTTON_BLUE_HOVER = new Color(120, 170, 255);

    public static final Color COLOR_BUTTON_GREY = new Color(80, 85, 110);
    public static final Color COLOR_BUTTON_GREY_HOVER = new Color(100, 105, 130);

    public static final Color COLOR_BUTTON_PURPLE = new Color(100, 70, 130);
    public static final Color COLOR_BUTTON_PURPLE_HOVER = new Color(120, 90, 150);

    public static final Color COLOR_BUTTON_GREEN = new Color(50, 180, 100);
    public static final Color COLOR_BUTTON_GREEN_HOVER = new Color(60, 200, 120);

    public static final Color COLOR_BUTTON_RED = new Color(255, 80, 80);
    public static final Color COLOR_BUTTON_RED_HOVER = new Color(255, 110, 110);


    // === SCHRIFTARTEN ===
    public static final Font FONT_TITLE = new Font("SansSerif", Font.BOLD, 42);
    public static final Font FONT_BUTTON_LARGE = new Font("SansSerif", Font.BOLD, 22);
    public static final Font FONT_BUTTON_MEDIUM = new Font("SansSerif", Font.BOLD, 18);
    public static final Font FONT_BUTTON_SMALL = new Font("SansSerif", Font.BOLD, 16);


    // === RÄNDER (Padding) ===
    public static final Border PADDING_BUTTON_LARGE = BorderFactory.createEmptyBorder(15, 35, 15, 35);
    public static final Border PADDING_BUTTON_MEDIUM = BorderFactory.createEmptyBorder(12, 30, 12, 30);


    /**
     * FABRIK-METHODE für unsere neuen "Flat Buttons".
     * Erstellt einen JButton mit unserem Flat-Design und Hover-Effekt.
     */
    public static JButton createStyledButton(String text, Font font, Color normalColor, Color hoverColor, Border padding) {
        JButton button = new JButton(text);

        button.setFont(font);
        button.setBackground(normalColor);
        button.setForeground(COLOR_TEXT_PRIMARY); // Text ist (fast) immer weiß
        button.setBorder(padding);

        button.setFocusPainted(false); // Entfernt hässlichen Fokus-Rahmen
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover-Effekt (das ist der wichtige Teil!)
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(hoverColor);
            }
            public void mouseExited(MouseEvent evt) {
                button.setBackground(normalColor);
            }
        });

        return button;
    }
}