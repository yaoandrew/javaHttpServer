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

//wrap inside reader
      String rawRequest = "";
      BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
      BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
      Router router = new Router();
      RequestParser parser = new RequestParser();
      rawRequest += reader.readLine();
      rawRequest += "\r\n";

      while (reader.ready()){
        rawRequest += (char) reader.read();
      }


//return a raw request

      System.out.println(rawRequest);

      Request parsedRequest = parser.parse(rawRequest);

      System.out.println("Request received");

      RequestHandler handler = router.getResponder(parsedRequest);


//wrap inside a writer

      writer.write(handler.getResponse().getStatusLine());

      if (handler.getResponse().getHeaders().length() > 0) {
        writer.write(handler.getResponse().getHeaders());
      }

      writer.write(handler.getResponse().getSeparator());
      writer.write(handler.getResponse().getBody());
      System.out.println("Response sent");

//clean up

      writer.close();
      reader.close();

    } catch (IOException e) {
      System.out.println(e);
    }
  }
}