package org.sanskar.config;

import javax.swing.*;
import java.io.File;

public class FileHandler {
    public String readTextFile() {
        String text = "";
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a file");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(true);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".txt") || f.isDirectory();
            }
            public String getDescription() {
                return "Text files (*.txt)";
            }
        });

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            text = new TextFileReader().readFile(selectedFile);
        }
        return text;
    }

    private class TextFileReader {
        public String readFile(File file) {
            String text = "";
            try {
                java.util.Scanner scanner = new java.util.Scanner(file);
                while (scanner.hasNextLine()) {
                    text += scanner.nextLine() + "\n";
                }
                scanner.close();
            } catch (java.io.FileNotFoundException e) {
                e.printStackTrace();
            }
            return text;
        }
    }
}
