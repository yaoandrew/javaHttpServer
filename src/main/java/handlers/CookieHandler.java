package handlers;

import messages.Request;
import messages.Response;
import parsers.CookieParser;

public class CookieHandler implements RequestHandler {
  Boolean setCookie = false;
  String cookieValue = null;

  public CookieHandler (Request request) {

    if(request.hasParams()) {
      String rawCookie = CookieParser.parseUri(request.getUri());
      cookieValue = CookieParser.parseRawCookie(rawCookie);
    }

    if (request.getUri().contains("type")){
      setCookie = true;
    }

  }
  public Response getResponse() {
    Response response = new Response();
    if (setCookie) {
      response.setStatusLine("HTTP/1.1 200 OK\r\n");
      response.setbody("Eat");
      response.setHeaders("Set-Cookie: chocolate");
    } else {
      response.setStatusLine("HTTP/1.1 200 OK\r\n");
      response.setbody("mmmm chocolate");
    }

    return response;
  }
}