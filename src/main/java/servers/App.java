package servers;

import java.net.ServerSocket;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import parsers.CliArgParser;

public class App {

  private static int DEFAULT_PORT = 5000;
  private static String DEFAULT_DIR = System.getProperty("user.home") + "/public";

  public static void main( String[] args ) throws IOException {

    CliArgParser cliArgParser = new CliArgParser(args);
    ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT);
    Server server = new Server(serverSocket);
    System.out.println("Server started...");
    System.out.println("Using: " + DEFAULT_DIR);
    server.run();
  }
}