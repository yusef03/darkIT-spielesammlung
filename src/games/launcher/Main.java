package games.launcher;

import javax.swing.*;

/**
 * Hauptklasse - Einstiegspunkt für den darkIT Launcher.
 *
 * @author yusef03
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {
        // Set Look & Feel auf System-Standard
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Starte GUI im Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            LauncherWindow launcher = new LauncherWindow();
            launcher.setVisible(true);
        });
    }
}