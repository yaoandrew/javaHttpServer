package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import router.Router;

public class Server {

  private ServerSocket serverSocket;
  private String serverDir;

  Server(ServerSocket serverSocket, String serverDir) {
    this.serverSocket = serverSocket;
    this.serverDir = serverDir;
  }

  public void run() throws IOException {
    Router router = new Router(serverDir);

    while (true) {
      Socket clientSocket = serverSocket.accept();
      ClientWorker clientHandler = new ClientWorker(clientSocket, router);
      (new Thread(clientHandler)).start();
    }
  }
}
