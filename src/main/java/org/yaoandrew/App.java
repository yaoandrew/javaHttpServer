package org.yaoandrew;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Principal;

public class App {

    static final int DEFAULT_PORT = 5000;
    static final String HTTP_HEADER = "";
    static final String GET_RESPONSE = "";
    static ServerSocket serverSocket;

    public static void main( String[] args ) {
        try {

            serverSocket = new ServerSocket(DEFAULT_PORT);
            System.out.println("Server started...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected");

            InputStreamReader reader = new InputStreamReader(clientSocket.getInputStream());
            System.out.println(reader);

            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            writer.println("HTTP/1.1 OK 200");


        } catch (IOException e) {
            System.out.println(e);
        }
    }
}