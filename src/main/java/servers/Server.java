package servers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import handlers.ClientHandler;
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
    MyLogger myLogger = new MyLogger();

    while (true) {
      Socket clientSocket = serverSocket.accept();
      ClientHandler clientHandler = new ClientHandler(clientSocket, router, myLogger);
      (new Thread(clientHandler)).start();
    }
  }
}
