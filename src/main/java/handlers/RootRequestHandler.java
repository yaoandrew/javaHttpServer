package handlers;

import messages.HTTPStatus;
import messages.Request;
import messages.Response;

public class RootRequestHandler extends RequestHandler {

  private String[] supportedHttpMethods = {"GET", "OPTIONS"};

  @Override
  public Response getResponse(Request request) {
    Response response = new Response();
    response.setStatusLine(HTTPStatus.OK.getStatusLine());
    response.setHeaders("Allow: " + String.join(",", supportedHttpMethods));

    return response;
  }
}