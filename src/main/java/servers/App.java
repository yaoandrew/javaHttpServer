package servers;

import java.net.ServerSocket;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

  private static final int DEFAULT_PORT = 5000;

  public static void main( String[] args ) throws IOException {

    ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT);
    Server server = new Server(serverSocket);
    System.out.println("Server started...");
    server.run();

    }
}