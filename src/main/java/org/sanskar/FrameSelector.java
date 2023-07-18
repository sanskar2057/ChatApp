package org.sanskar;

import org.sanskar.client.ClientFrame;
import org.sanskar.config.ThemePreferences;
import org.sanskar.server.ServerFrame;

import javax.swing.*;
import java.awt.*;

public class FrameSelector extends javax.swing.JFrame {

    private javax.swing.JButton portButton;
    public FrameSelector() {
        initComponents();
        ThemePreferences.applyTheme();
    }

    @SuppressWarnings("unchecked")

    private void initComponents() {

        title = new javax.swing.JLabel();
        server = new javax.swing.JButton();
        username = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        server1 = new javax.swing.JButton();
        portButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ChatAPP");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        title.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Welcome to ChatApp");
        getContentPane().add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 454, 66));

        server.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        server.setText("Start Server");
        server.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        server.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serverActionPerformed(evt);
            }
        });
        getContentPane().add(server, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 320, 100));
        getContentPane().add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 160, 30));

        jLabel2.setText("Username");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, -1, 30));

        server1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        server1.setText("Start Client");
        server1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        server1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                server1ActionPerformed(evt);
            }
        });
        getContentPane().add(server1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 320, 100));

        portButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        portButton.setText("Available Ports");
        portButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        portButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                portButtonActionPerformed(evt);
            }
        });
        getContentPane().add(portButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 340, 320, 100));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void serverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serverActionPerformed
        ServerFrame server = new ServerFrame(username.getText());
        server.setVisible(true);
        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        server.setBounds(r.x, r.y, r.width, r.height);
    }//GEN-LAST:event_serverActionPerformed

    private void server1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_server1ActionPerformed
        ClientFrame client = new ClientFrame(username.getText());
        client.setVisible(true);
        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        client.setBounds(r.x, r.y, r.width, r.height);
    }//GEN-LAST:event_server1ActionPerformed

    private void portButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_portButtonActionPerformed
        AvailablePortGUI portGUI = new AvailablePortGUI();
        portGUI.updatePortList();
        portGUI.setVisible(true);
    }//GEN-LAST:event_portButtonActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new FrameSelector().setVisible(true);
        });
    }

    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton server;
    private javax.swing.JButton server1;
    private javax.swing.JLabel title;
    private javax.swing.JTextField username;

}
