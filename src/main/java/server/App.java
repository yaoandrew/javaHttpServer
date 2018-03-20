package server;

import java.net.ServerSocket;
import java.io.IOException;
import parsers.CliArgParser;
import router.Router;

public class App {

  private static int SERVER_PORT = 5000;
  private static String SERVER_DIR = System.getProperty("user.home") + "/public";

  public static void main(String[] args) throws IOException {

    CliArgParser cliArgParser = new CliArgParser(args);

    if (cliArgParser.hasUserDirSet()) {
      SERVER_DIR = cliArgParser.getUserDir();
    }

    if (cliArgParser.hasUserPortSet()) {
      SERVER_PORT = Integer.parseInt(cliArgParser.getUserPort());
    }

    ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
    Router router = new Router(SERVER_DIR);
    Server server = new Server(serverSocket, router);
    System.out.println("Server started...");
    System.out.println("Using: " + SERVER_DIR + " on port " + SERVER_PORT);
    server.run();
  }
}