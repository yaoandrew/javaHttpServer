package handlers;

import messages.Request;
import messages.Response;

public class RedirectHandler implements RequestHandler{

  @Override
  public Response getResponse(Request request) {
    Response response = new Response();
    response.setStatusLine("HTTP/1.1 302 FOUND\r\n");
    response.setHeaders("Location: /");
    return response;
  }
}
