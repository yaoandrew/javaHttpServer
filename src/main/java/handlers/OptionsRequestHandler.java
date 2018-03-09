package handlers;

import messages.HTTPStatus;
import messages.Request;
import messages.Response;

public class OptionsRequestHandler extends RequestHandler {

  private String[] supportedHttpMethods = {"GET", "HEAD", "POST", "OPTIONS", "PUT"};

  public Response getResponse(Request request) {
    Response response = new Response();
    response.setStatusLine(HTTPStatus.OK.getStatusLine());
    response.setHeaders("Allow: " + String.join(",", supportedHttpMethods) + "\r\n");

    return response;
  }
}
