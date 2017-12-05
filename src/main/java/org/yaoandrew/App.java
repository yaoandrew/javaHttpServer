package org.yaoandrew;

import java.net.ServerSocket;
import java.io.IOException;

public class App {

    static final int DEFAULT_PORT = 5000;
    static ServerSocket serverSocket;

    public static void main( String[] args ) {
        try {

            serverSocket = new ServerSocket(DEFAULT_PORT);
            Server server = new Server(serverSocket);
            System.out.println("Server started...");
            server.run();

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}