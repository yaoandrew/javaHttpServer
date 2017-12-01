package org.yaoandrew;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class App {

    static final int DEFAULT_PORT = 5000;
    static ServerSocket serverSocket;

    public static void main( String[] args ) {
        try {

            serverSocket = new ServerSocket(DEFAULT_PORT);
            System.out.println("Server started...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                new Thread(clientHandler).start();
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}