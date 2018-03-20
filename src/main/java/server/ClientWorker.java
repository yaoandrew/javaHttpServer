package server;

import handlers.RequestHandler;
import io.InputReader;
import io.OutputWriter;
import java.io.*;
import java.net.Socket;
import logging.MyLogger;
import messages.Request;
import messages.Response;
import parsers.RequestParser;
import router.Router;

public class ClientWorker implements Runnable {

  private Socket client;
  private Router router;
  private InputReader inputReader;
  private String rawRequest;

  public ClientWorker(Socket client, Router router) {
    this.client = client;
    this.router = router;
  }

  public void run() {
    MyLogger myLogger = MyLogger.getInstance();
    RequestParser parser = new RequestParser();

    try {
      inputReader = new InputReader(client.getInputStream());
      inputReader.setupReader();
      rawRequest = inputReader.readFullRequest();
    } catch (IOException e) {
      System.err.println("Unable to read request input stream");
      e.printStackTrace();
    }

    Request parsedRequest = parser.parse(rawRequest);
    myLogger.add(parsedRequest.getHttpMethod() + " " + parsedRequest.getRawUri() + " " +parsedRequest.getHttpVersion());

    RequestHandler handler = router.getHandler(parsedRequest);
    Response response = handler.getResponse(parsedRequest);

    try {
      OutputWriter outputWriter = new OutputWriter(client.getOutputStream());
      outputWriter.write(response.getStatusLine().getBytes());
      outputWriter.write(System.lineSeparator().getBytes());

      if (response.getHeaders().length() > 0) {
        outputWriter.write(response.getHeaders().getBytes());
      }

      outputWriter.write(response.getSeparator().getBytes());

      if (response.getBody() != null) {
        outputWriter.write(response.getBody());
      }

      outputWriter.close();
      inputReader.close();

    } catch (IOException e) {
        System.err.println("Unable to write response output stream");
        e.printStackTrace();
    }
  }
}