package org.sanskar.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketWrapper {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private SocketListener socketListener;

    public void startServer(int port) {
        Thread serverThread = new Thread(() -> {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(port);
                System.out.println("Server started. Listening on port " + port + "...");
                socket = serverSocket.accept();
                System.out.println("Client connected: " + socket.getInetAddress());

                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                if (socketListener != null) {
                    socketListener.onClientConnected(socket.getInetAddress().getHostAddress());
                }

                String receivedMessage;
                while ((receivedMessage = in.readLine()) != null) {
                    if (socketListener != null) {
                        socketListener.onMessageReceived(receivedMessage);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                cleanup();
                if (serverSocket != null) {
                    try {
                        serverSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        serverThread.start();
    }

    public void connectToServer(String host, int port) {
        Thread clientThread = new Thread(() -> {
            try {
                socket = new Socket(host, port);
                System.out.println("Connected to server.");

                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                if (socketListener != null) {
                    socketListener.onClientConnected(socket.getLocalAddress().getHostAddress());
                }

                String serverResponse;
                while ((serverResponse = in.readLine()) != null) {
                    if (socketListener != null) {
                        socketListener.onMessageReceived(serverResponse);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                cleanup();
            }
        });

        clientThread.start();
    }

    public void sendMessage(String message) {
        if (out != null) {
            out.println(message);
        }
    }

    public void setSocketListener(SocketListener listener) {
        socketListener = listener;
    }

    public void stop() {
        cleanup();
    }

    private void cleanup() {
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public interface SocketListener {
        void onClientConnected(String clientAddress);

        void onMessageReceived(String message);
    }
}
