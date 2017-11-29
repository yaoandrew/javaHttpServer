package org.yaoandrew;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class App {

    static final int DEFAULT_PORT = 5000;
    static final String HTTP_HEADER = ""
    static ServerSocket server;

    public static void main( String[] args ) {
        try {

            server = new ServerSocket(DEFAULT_PORT);
            System.out.println("Server started...");

            Socket client = server.accept();
            System.out.println("Client connected");

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
