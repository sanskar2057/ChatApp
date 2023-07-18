package org.sanskar.client;

import org.sanskar.aes.AES;
import org.sanskar.config.FileHandler;
import org.sanskar.config.ThemePreferences;
import org.sanskar.server.ServerFrame;
import org.sanskar.socket.SocketWrapper;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.Base64;

public class ClientFrame extends javax.swing.JFrame {

    private final int SERVER_PORT;
    private final String SERVER_HOST;
    private final SocketWrapper socketWrapper;
    private final Style receivedStyle;
    private final Style sentStyle;
    private String name;

    public ClientFrame(String name) {
        this.name = name;
        initComponents();
        SERVER_HOST = JOptionPane.showInputDialog("Enter server address");
        SERVER_PORT = Integer.parseInt(JOptionPane.showInputDialog("Enter port number"));
        title.setText("ChatApp - " + name);
        sentStyle = textbox.addStyle("SentStyle", null);
        StyleConstants.setForeground(sentStyle, Color.MAGENTA);
        StyleConstants.setBold(sentStyle, true);
        receivedStyle = textbox.addStyle("ReceivedStyle", null);
        StyleConstants.setForeground(receivedStyle, Color.GREEN);
        StyleConstants.setItalic(receivedStyle, true);
        socketWrapper = new SocketWrapper();
        socketWrapper.setSocketListener(new SocketListenerImpl());
        connectToServer();
        ThemePreferences.applyTheme();
        themeSwitch.setSelected(ThemePreferences.getTheme());
        input.requestFocus();
    }

    private void connectToServer() {
        Thread clientThread = new Thread(() -> {
            socketWrapper.connectToServer(SERVER_HOST, SERVER_PORT);
        });
        clientThread.start();
    }

    private class SocketListenerImpl implements SocketWrapper.SocketListener {
        @Override
        public void onClientConnected(String clientAddress) {
            SwingUtilities.invokeLater(() -> textbox.setText("Server connected: " + clientAddress + "\n"));
        }

        @Override
        public void onMessageReceived(String message) {
            byte[] messageByte = Base64.getDecoder().decode(message);
            byte[] keyByte = ServerFrame.hashString(key.getText()).getBytes();
            String decryptedMessage = new String(AES.decrypt(messageByte, keyByte, 10));
            String finalDecryptedMessage = decryptedMessage.replace("message:", "");
            String name = finalDecryptedMessage.split(" ")[0];
            String msg = finalDecryptedMessage.replace(name, "").trim();
            SwingUtilities.invokeLater(() -> appendToPane(name + ": " + msg, receivedStyle));
        }
    }

    private void appendToPane(String message, Style style) {
        StyledDocument doc = textbox.getStyledDocument();
        try {
            doc.insertString(doc.getLength(), message + "\n", style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private void appendToTextArea(String message) {
        textbox.setText(textbox.getText() + "\n" + message);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title = new javax.swing.JLabel();
        keyLabel = new javax.swing.JLabel();
        key = new javax.swing.JPasswordField();
        themeSwitch = new javax.swing.JRadioButton();
        select = new javax.swing.JButton();
        input = new javax.swing.JTextField();
        send = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        textbox = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Client App");

        title.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("ChatApp Client ");

        keyLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        keyLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        keyLabel.setText("Key");

        key.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        key.setText("1234567812345678");
        key.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keyActionPerformed(evt);
            }
        });

        themeSwitch.setSelected(true);
        themeSwitch.setText("Dark Mode");
        themeSwitch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themeSwitchActionPerformed(evt);
            }
        });

        select.setBackground(new java.awt.Color(255, 255, 204));
        select.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        select.setForeground(new java.awt.Color(51, 51, 0));
        select.setText("Select File");
        select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectActionPerformed(evt);
            }
        });

        input.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        input.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 102, 0), 1, true));
        input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputActionPerformed(evt);
            }
        });

        send.setBackground(new java.awt.Color(153, 255, 153));
        send.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        send.setForeground(new java.awt.Color(0, 102, 51));
        send.setText("Send");
        send.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendActionPerformed(evt);
            }
        });

        textbox.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jScrollPane2.setViewportView(textbox);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(select)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(input)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(send))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(keyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(key, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(themeSwitch, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(key, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(themeSwitch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(title)
                        .addComponent(keyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(input, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(send, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(select, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void keyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keyActionPerformed
    }//GEN-LAST:event_keyActionPerformed

    private void themeSwitchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themeSwitchActionPerformed
        ThemePreferences.changeTheme();
        this.repaint();
        this.revalidate();
    }//GEN-LAST:event_themeSwitchActionPerformed

    private void sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendActionPerformed
        String message = input.getText();
        if (message.equals("")) {
            return;
        }
        if (key.getText().length() < 16) {
            JOptionPane.showMessageDialog(this, "Key must be at least 16 characters long");
            return;
        }
        if (message.equals("clear")) {
            textbox.setText("");
            input.setText("");
            return;
        }
        if (message.equals("exit")) {
            System.exit(0);
        }
        try {
            byte[] encryptedMessage = AES.encrypt((name + " " + input.getText()).getBytes(), ServerFrame.hashString(key.getText()).getBytes(), 10);
            String encryptedMessageString = Base64.getEncoder().encodeToString(encryptedMessage);
            socketWrapper.sendMessage(encryptedMessageString);
            appendToPane(name + ": " + message, sentStyle);
        } catch (Exception e) {
            System.out.println("  Error sending message");
        }
    }//GEN-LAST:event_sendActionPerformed

    private void inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputActionPerformed
        send.doClick();
    }//GEN-LAST:event_inputActionPerformed

    private void selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectActionPerformed
        input.setText(new FileHandler().readTextFile());
    }//GEN-LAST:event_selectActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField input;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPasswordField key;
    private javax.swing.JLabel keyLabel;
    private javax.swing.JButton select;
    private javax.swing.JButton send;
    private javax.swing.JTextPane textbox;
    private javax.swing.JRadioButton themeSwitch;
    private javax.swing.JLabel title;

    public static void main(String[] args) {
        new ClientFrame("ch1").setVisible(true);
    }
}
