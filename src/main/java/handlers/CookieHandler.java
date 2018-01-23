package handlers;

import messages.Request;
import messages.Response;
import parsers.CookieParser;

public class CookieHandler implements RequestHandler {
  Boolean setCookie = false;
  String cookieValue = null;

  public CookieHandler (Request request) {

    if(request.hasCookies()) {
      cookieValue = request.getCookie();
    }

    if (request.hasCookies()){
      setCookie = true;
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
      response.setBody("mmmm chocolate");
    }

    return response;
  }
}