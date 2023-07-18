//import org.sanskar.client.ClientFrame;
//import org.sanskar.config.ThemePreferences;
//import org.sanskar.server.ServerFrame;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.*;
//
//public class FrameSelector extends javax.swing.JFrame {
//
//    private javax.swing.JButton portButton;
//    private JButton loginButton;
//    private JTextField usernameField;
//    private JPasswordField passwordField;
//
//    public FrameSelector() {
//        initComponents();
//        ThemePreferences.applyTheme();
//    }
//
//    @SuppressWarnings("unchecked")
//    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
//    private void initComponents() {
//
//        title = new javax.swing.JLabel();
//        server = new javax.swing.JButton();
//        username = new javax.swing.JTextField();
//        jLabel2 = new javax.swing.JLabel();
//        server1 = new javax.swing.JButton();
//        portButton = new javax.swing.JButton();
//        loginButton = new javax.swing.JButton();
//        usernameField = new JTextField();
//        passwordField = new JPasswordField();
//
//        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
//        setTitle("ChatAPP");
//        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
//
//        title.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
//        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//        title.setText("Welcome to ChatApp");
//        getContentPane().add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 454, 66));
//
//        server.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
//        server.setText("Start Server");
//        server.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
//        server.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                serverActionPerformed(evt);
//            }
//        });
//        getContentPane().add(server, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 320, 100));
//        getContentPane().add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 160, 30));
//
//        jLabel2.setText("Username");
//        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, -1, 30));
//
//        server1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
//        server1.setText("Start Client");
//        server1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
//        server1.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                server1ActionPerformed(evt);
//            }
//        });
//        getContentPane().add(server1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 320, 100));
//
//        portButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
//        portButton.setText("Available Ports");
//        portButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
//        portButton.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                portButtonActionPerformed(evt);
//            }
//        });
//        getContentPane().add(portButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 340, 320, 100));
//
//        loginButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
//        loginButton.setText("Login");
//        loginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
//        loginButton.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                loginButtonActionPerformed(evt);
//            }
//        });
//        getContentPane().add(loginButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 450, 320, 40));
//
//        usernameField.setToolTipText("Username");
//        getContentPane().add(usernameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 500, 160, 30));
//
//        passwordField.setToolTipText("Password");
//        getContentPane().add(passwordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 500, 160, 30));
//
//        pack();
//        setLocationRelativeTo(null);
//    }// </editor-fold>//GEN-END:initComponents
//
//    private void serverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serverActionPerformed
//        if (performServerLogin()) {
//            ServerFrame server = new ServerFrame(username.getText());
//            server.setVisible(true);
//            Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
//            server.setBounds(r.x, r.y, r.width, r.height);
//        } else {
//            JOptionPane.showMessageDialog(this, "Invalid login credentials for the server.", "Login Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }//GEN-LAST:event_serverActionPerformed
//
//    private void server1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_server1ActionPerformed
//        if (performClientLogin()) {
//            ClientFrame client = new ClientFrame(username.getText());
//            client.setVisible(true);
//            Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
//            client.setBounds(r.x, r.y, r.width, r.height);
//        } else {
//            JOptionPane.showMessageDialog(this, "Invalid login credentials for the client.", "Login Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }//GEN-LAST:event_server1ActionPerformed
//
//    private void portButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_portButtonActionPerformed
//        AvailablePortGUI portGUI = new AvailablePortGUI();
//        portGUI.updatePortList();
//        portGUI.setVisible(true);
//    }//GEN-LAST:event_portButtonActionPerformed
//
//    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
//        if (performServerLogin()) {
//            serverActionPerformed(evt);
//        } else if (performClientLogin()) {
//            server1ActionPerformed(evt);
//        } else {
//            JOptionPane.showMessageDialog(this, "Invalid login credentials.", "Login Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }//GEN-LAST:event_loginButtonActionPerformed
//
//    private boolean performServerLogin() {
//        String username = usernameField.getText();
//        String password = new String(passwordField.getPassword());
//
//        // Perform server login authentication using JDBC and MySQL
//        try {
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database_name", "your_username", "your_password");
//            String query = "SELECT * FROM server_users WHERE username = ? AND password = ?";
//            PreparedStatement stmt = conn.prepareStatement(query);
//            stmt.setString(1, username);
//            stmt.setString(2, password);
//            ResultSet rs = stmt.executeQuery();
//            return rs.next();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    private boolean performClientLogin() {
//        String username = usernameField.getText();
//        String password = new String(passwordField.getPassword());
//
//        // Perform client login authentication using JDBC and MySQL
//        try {
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database_name", "your_username", "your_password");
//            String query = "SELECT * FROM client_users WHERE username = ? AND password = ?";
//            PreparedStatement stmt = conn.prepareStatement(query);
//            stmt.setString(1, username);
//            stmt.setString(2, password);
//            ResultSet rs = stmt.executeQuery();
//            return rs.next();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//
