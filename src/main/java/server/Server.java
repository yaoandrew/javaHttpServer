package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import router.Router;

public class Server {

  private ServerSocket serverSocket;
  private Router router;
  static String dataStore;

  Server(ServerSocket serverSocket, Router router) {
    this.serverSocket = serverSocket;
    this.router = router;
  }

  public void run() throws IOException {

    while (true) {
      Socket clientSocket = serverSocket.accept();
      ClientWorker clientHandler = new ClientWorker(clientSocket, router);
      (new Thread(clientHandler)).start();
    }
  }

  public static String getDataStore() {
    return dataStore;
  }

  public static void setDataStore(String data) {
    dataStore = data;
  }

}
