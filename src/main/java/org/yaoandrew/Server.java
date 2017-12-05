package org.yaoandrew;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static ServerSocket serverSocket;

    public Server (ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void run() throws IOException {
        while (true) {
            Socket clientSocket = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(clientSocket);
            new Thread(clientHandler).start();
        }

    }

}
