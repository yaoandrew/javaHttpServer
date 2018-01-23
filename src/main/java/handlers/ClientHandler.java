package handlers;

import java.io.*;
import java.net.Socket;

import messages.Request;
import parsers.RequestParser;
import router.Router;

public class ClientHandler implements Runnable {

  private Socket client;

  public ClientHandler(Socket client) {
    this.client = client;
  }

  public void run() {
    try {

      BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
      BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
      System.out.println("Client connected");

      RequestParser parser = new RequestParser();

      Request parsedRequest = parser.parse(reader.readLine());

      System.out.println("Request received");

      Router router = new Router();

      RequestHandler handler = router.getResponder(parsedRequest);

      writer.write(handler.getResponse().getStatusLine());

      if (handler.getResponse().getHeaders().length() > 0) {
        writer.write(handler.getResponse().getHeaders());
        System.out.println(handler.getResponse().getHeaders());
      }

      writer.write(handler.getResponse().getSeparator());
      writer.write(handler.getResponse().getBody());
      System.out.println("Response sent");
      writer.close();
      reader.close();

    } catch (IOException e) {
      System.out.println(e);
    }
  }
}