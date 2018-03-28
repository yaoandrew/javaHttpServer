package handlers;

import messages.HTTPStatus;
import messages.Request;
import messages.Response;
import logging.MyLogger;

public class LogFileHandler extends RequestHandler {

  @Override
  public Response getResponse(Request request) {
    Response response = new Response();
    MyLogger myLogger = MyLogger.getInstance();
    response.setStatusLine(HTTPStatus.OK.getStatusLine());
    response.setBody(myLogger.getLog().getBytes());

    return response;
  }
}
