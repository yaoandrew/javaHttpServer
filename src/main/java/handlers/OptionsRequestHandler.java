package handlers;

import messages.HTTPStatus;
import messages.Request;
import messages.Response;
import messages.ResponseHeaderField;

public class OptionsRequestHandler extends RequestHandler {

  private String[] supportedHttpMethods = {"GET", "HEAD", "POST", "OPTIONS", "PUT"};

  public Response getResponse(Request request) {
    response.setStatusLine(HTTPStatus.OK.getStatusLine());
    response.setHeaders(ResponseHeaderField.ALLOW.getHeaderField() +
        String.join(",", supportedHttpMethods));

    return response;
  }
}
