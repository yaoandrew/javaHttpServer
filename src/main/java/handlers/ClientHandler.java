package handlers;

import java.io.*;
import java.net.Socket;

import messages.Request;
import messages.Response;
import parsers.RequestParser;
import router.Router;

public class ClientHandler implements Runnable {

  private Socket client;
  private Router router;

  public ClientHandler(Socket client, Router router) {
    this.client = client;
    this.router = router;
  }

  public void run() {
    try {

//wrap inside reader Takes inputStream - returns rawRequest
      String rawRequest = "";
      BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
      OutputStream writer = client.getOutputStream();
      RequestParser parser = new RequestParser();
      rawRequest += reader.readLine();
      rawRequest += "\r\n";

      while (reader.ready()){
        rawRequest += (char) reader.read();
      }


//return a raw request

      System.out.println("RAW REQUEST: " + rawRequest);

      Request parsedRequest = parser.parse(rawRequest);

      System.out.println("Request data: " + parsedRequest.getBody());
      System.out.println("Request received");

      RequestHandler handler = router.getHandler(parsedRequest);


//wrap inside a writer

      Response response = handler.getResponse(parsedRequest);
      writer.write(response.getStatusLine().getBytes());
      writer.write(System.lineSeparator().getBytes());

      if (response.getHeaders().length() > 0) {
        writer.write(response.getHeaders().getBytes());
      }

      writer.write(response.getSeparator().getBytes());

      if (response.getBody() != null){
        System.out.println("Response data: " + response.getBody().toString());
        writer.write(response.getBody());
      }
      System.out.println("Response sent\r\n\r\n-------------");

//clean up

      writer.close();
      reader.close();

    } catch (IOException e) {
      System.out.println(e);
    }
  }
}