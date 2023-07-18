package org.sanskar.config;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.util.prefs.Preferences;

/**
 * ThemePreferences class is used to change the theme of the application. dark and light mode is supported.
 */
public class ThemePreferences {


    public static void changeTheme() {
        setTheme(!getTheme());
        applyTheme();
    }

    public static void applyTheme() {
        if (getTheme()) {
            try {
                UIManager.setLookAndFeel(new FlatDarkLaf());
                FlatLaf.updateUI();
            } catch (UnsupportedLookAndFeelException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
                FlatLaf.updateUI();
            } catch (UnsupportedLookAndFeelException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void setTheme(boolean darkMode) {
        Preferences.userNodeForPackage(ThemePreferences.class).putBoolean("darkMode", darkMode);
    }

    public static boolean getTheme() {
        return Preferences.userNodeForPackage(ThemePreferences.class).getBoolean("darkMode", false);
    }
}
