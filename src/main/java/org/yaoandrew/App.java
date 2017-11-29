package org.yaoandrew;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class App {

    static final int DEFAULT_PORT = 5000;
    static ServerSocket server;

    public static void main( String[] args ) {

        try {

            server = new ServerSocket(DEFAULT_PORT);

        } catch (IOException e) {

            System.out.println(e);

        }

        System.out.println("Server started...");

        try {

            Socket client = server.accept();

        } catch (IOException e) {

            System.out.println(e);
        }

        System.out.println("Client connected");
    }
}
