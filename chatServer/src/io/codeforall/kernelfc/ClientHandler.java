package io.codeforall.kernelfc;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {

    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            out.println("Welcome to chat server :)");

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Client message: " + message);
                out.println("Received: " + message);

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Client disconnected.");
        }
    }
}
