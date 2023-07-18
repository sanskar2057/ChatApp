package org.sanskar;

import java.awt.BorderLayout;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class AvailablePortGUI extends JFrame {
    private JList<Integer> portList;
    private DefaultListModel<Integer> listModel;

    public AvailablePortGUI() {
        setTitle("Available Port Numbers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        listModel = new DefaultListModel<>();
        portList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(portList);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        setContentPane(mainPanel);
    }

    public void updatePortList() {
        listModel.clear();
        List<Integer> availablePorts = findAvailablePorts();
        for (Integer port : availablePorts) {
            listModel.addElement(port);
        }
    }

    private List<Integer> findAvailablePorts() {
        List<Integer> availablePorts = new ArrayList<>();
        for (int port = 1; port <= 65535; port++) {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                serverSocket.close();
                availablePorts.add(port);
            } catch (Exception ex) {
                // Port is not available
            }
        }
        return availablePorts;
    }

    public static void main(String[] args) {
        AvailablePortGUI portGUI = new AvailablePortGUI();
        portGUI.updatePortList();
        portGUI.setVisible(true);
    }
}
