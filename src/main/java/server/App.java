package server;

import java.net.ServerSocket;
import java.io.IOException;
import parsers.CliArgParser;
import router.Router;

public class App {

  private static final int DEFAULT_SERVER_PORT = 5000;
  private static final String DEFAULT_SERVER_DIR = System.getProperty("user.home") + "/public";

  public static void main(String[] args) throws IOException {
    String SERVER_DIR;
    int SERVER_PORT;

    CliArgParser cliArgParser = new CliArgParser(args);

    SERVER_DIR = cliArgParser.hasUserDirSet() ? cliArgParser.getUserDir() : DEFAULT_SERVER_DIR;
    SERVER_PORT = cliArgParser.hasUserPortSet() ? Integer.parseInt(cliArgParser.getUserPort()) : DEFAULT_SERVER_PORT;

    ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
    Router router = new Router(SERVER_DIR);
    Server server = new Server(serverSocket, router);
    System.out.println("Server started...");
    System.out.println("Using: " + SERVER_DIR + " on port " + SERVER_PORT);
    server.run();
  }
}