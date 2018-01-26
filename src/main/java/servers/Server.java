package servers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import handlers.ClientHandler;


public class Server {
    private static ServerSocket serverSocket;

    Server (ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void run() throws IOException {
        while (true) {
            Socket clientSocket = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(clientSocket);
            (new Thread(clientHandler)).start();
        }

    }

}
