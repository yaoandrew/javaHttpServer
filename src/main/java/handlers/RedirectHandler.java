package handlers;

import messages.HTTPStatus;
import messages.Request;
import messages.Response;

public class RedirectHandler extends RequestHandler{

  @Override
  public Response getResponse(Request request) {
    Response response = new Response();
    response.setStatusLine(HTTPStatus.FOUND.getStatusLine());
    response.setHeaders("Location: /");
    return response;
  }
}
