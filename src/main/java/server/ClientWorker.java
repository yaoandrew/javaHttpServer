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

  ClientWorker(Socket client, Router router) {
    this.client = client;
    this.router = router;
  }

  public void run() {
    MyLogger myLogger = MyLogger.getInstance();
    RequestParser parser = new RequestParser();

    try {
      InputReader inputReader = new InputReader(client.getInputStream());
      inputReader.setupReader();
      String rawRequest = inputReader.readFullRequest();

      Request parsedRequest = parser.parse(rawRequest);
      myLogger.add(parsedRequest.getHttpMethod() + " " + parsedRequest.getRawUri() + " " +parsedRequest.getHttpVersion());

      RequestHandler handler = router.getHandler(parsedRequest);
      Response response = handler.getResponse(parsedRequest);

      OutputWriter outputWriter = new OutputWriter(client.getOutputStream());
      outputWriter.write(response);

      outputWriter.close();
      inputReader.close();

    } catch (IOException e) {
        e.printStackTrace();
    }
  }
}