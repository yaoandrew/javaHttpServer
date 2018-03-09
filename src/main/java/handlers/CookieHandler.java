package handlers;

import messages.HTTPStatus;
import messages.Request;
import messages.Response;

public class CookieHandler extends RequestHandler {

  private Boolean setCookie = false;
  private Boolean headerHasCookie = false;
  private String cookieValue = null;


  public Response getResponse(Request request) {
    Response response = new Response();

    if (request.getParamMap().size() >= 1) {
      request.getParamMap().entrySet()
          .forEach(entry -> cookieValue = entry.getKey() + "=" + entry.getValue());
      setCookie = true;
    }

    if (request.getHeadersMap().containsKey("Cookie")) {
      cookieValue = request.getHeaderValue("Cookie");
      headerHasCookie = true;
    }
    if (setCookie) {
      response.setStatusLine(HTTPStatus.OK.getStatusLine());
      response.setBody("Eat".getBytes());
      response.setHeaders("Set-Cookie: " + cookieValue);
    } else {
      response.setStatusLine(HTTPStatus.OK.getStatusLine());
      if (headerHasCookie) {
        response.setBody(("mmmm " + cookieValue.split("=")[1]).getBytes());
      }
    }

    return response;
  }
}