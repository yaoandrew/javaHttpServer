package handlers;

import messages.Request;
import messages.Response;

public class CookieHandler implements RequestHandler {
  private Boolean setCookie = false;
  private Boolean headerHasCookie = false;
  private String cookieValue = null;
  private Request request;

  public CookieHandler (Request request) {
    this.request = request;
    if (request.getParamMap().size() >= 1) {
      request.getParamMap().entrySet().stream().forEach(entry -> cookieValue = entry.getKey() + "=" + entry.getValue());
      setCookie = true;
    }

    if (request.getHeadersMap().containsKey("Cookie")) {
      cookieValue = request.getHeaderValue("Cookie");
      headerHasCookie = true;
    }
   }

  public Response getResponse() {
    Response response = new Response();
    if (setCookie) {
      response.setStatusLine("HTTP/1.1 200 OK\r\n");
      response.setBody("Eat");
      response.setHeaders("Set-Cookie: " + cookieValue);
    } else {
      response.setStatusLine("HTTP/1.1 200 OK\r\n");
      if (headerHasCookie) {
        response.setBody("mmmm " + cookieValue.split("=")[1]);
      }
    }

    return response;
  }
}