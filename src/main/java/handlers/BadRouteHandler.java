package handlers;

import messages.HTTPStatus;
import messages.Request;
import messages.Response;


public class BadRouteHandler extends RequestHandler {

  public Response getResponse(Request request) {
    response.setStatusLine(HTTPStatus.NOT_FOUND.getStatusLine());

    return response;
  }
}

