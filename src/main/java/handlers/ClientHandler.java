package handlers;

import io.InputReader;
import java.io.*;
import java.net.Socket;
import messages.Request;
import messages.Response;
import parsers.RequestParser;
import router.Router;
import servers.MyLogger;

public class ClientHandler implements Runnable {

  private Socket client;
  private Router router;
  private InputReader reader;
  private String rawRequest;

  public ClientHandler(Socket client, Router router) {
    this.client = client;
    this.router = router;
  }

  public void run() {
    MyLogger myLogger = MyLogger.getInstance();
    RequestParser parser = new RequestParser();

    try {
      reader = new InputReader(client.getInputStream());
      reader.setupReader();
      rawRequest = reader.readFullRequest();
    } catch (IOException e) {
      System.out.println(e);
    }

    Request parsedRequest = parser.parse(rawRequest);
    myLogger.add(parsedRequest.getHttpMethod() + " " + parsedRequest.getRawUri() + " " +parsedRequest.getHttpVersion());

      System.out.println("Request data: " + parsedRequest.getBody());
      System.out.println("Request received");

    RequestHandler handler = router.getHandler(parsedRequest);
    Response response = handler.getResponse(parsedRequest);

    try {
      OutputStream writer = client.getOutputStream();
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