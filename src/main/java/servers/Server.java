package servers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import handlers.ClientHandler;
import router.Router;


public class Server {

  private static ServerSocket serverSocket;

  Server(ServerSocket serverSocket) {
    this.serverSocket = serverSocket;
  }

  public void run() throws IOException {
    Router router = new Router();

    while (true) {
      Socket clientSocket = serverSocket.accept();
      ClientHandler clientHandler = new ClientHandler(clientSocket, router);
      (new Thread(clientHandler)).start();
    }

  }

}
