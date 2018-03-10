package handlers;

import messages.HTTPStatus;
import messages.Request;
import messages.Response;
import messages.ResponseHeaderField;

public class RootRequestHandler extends RequestHandler {

  private String[] supportedHttpMethods = {"GET", "OPTIONS"};

  @Override
  public Response getResponse(Request request) {
    response.setStatusLine(HTTPStatus.OK.getStatusLine());
    response.setHeaders(ResponseHeaderField.ALLOW.getHeaderField()
        + String.join(",", supportedHttpMethods));

    return response;
  }
}