package io.codeforall.kernelfc;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClientHandler extends Thread {

    private static final List<ClientHandler> clients = new CopyOnWriteArrayList<>();

    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private String username;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            // Setup I/O
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Get username
            out.println("Enter your username:");
            username = in.readLine();

            if (username == null || username.trim().isEmpty()) {
                username = "Anonymous";
            }

            clients.add(this);
            out.println("Welcome, " + username + "! You can start chatting.");
            broadcast("💬 " + username + " has joined the chat.");

            String message;
            while ((message = in.readLine()) != null) {
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }

                System.out.println(username + ": " + message);
                broadcast("[" + username + "]: " + message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    private void broadcast(String message) {
        for (ClientHandler client : clients) {
            if (client != this) {
                client.out.println(message);
            }
        }
    }

    private void disconnect() {
        clients.remove(this);
        broadcast("❌ " + username + " has left the chat.");
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(username + " disconnected.");
    }
}
