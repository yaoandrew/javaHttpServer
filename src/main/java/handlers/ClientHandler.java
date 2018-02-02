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

//wrap inside reader
      String rawRequest = "";
      BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
      BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
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

      RequestHandler handler = router.getResponder(parsedRequest);



//wrap inside a writer

      Response response = handler.getResponse();

      writer.write(response.getStatusLine());

      if (response.getHeaders().length() > 0) {
        writer.write(response.getHeaders());
      }

      writer.write(response.getSeparator());

      if (response.getBody() != null){
        System.out.println("Response data: " + response.getBody());
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