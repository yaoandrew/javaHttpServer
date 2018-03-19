package handlers;

import messages.HTTPStatus;
import messages.Request;
import messages.Response;
import messages.ResponseHeaderField;

public class CookieHandler extends RequestHandler {

  public Response getResponse(Request request) {

    if (requestHasParams(request)) {
      response.setStatusLine(HTTPStatus.OK.getStatusLine());
      response.setBody("Eat".getBytes());
      response.setHeaders(ResponseHeaderField.SET_COOKIE.getHeaderField() + getCookieType(request));
    } else {
        response.setStatusLine(HTTPStatus.OK.getStatusLine());
        if (request.getHeaderValue("Cookie") != null) {
          String cookieValue = request.getHeaderValue("Cookie");
          response.setBody(("mmmm " + cookieValue.split("=")[1]).getBytes());
        }
    }
    return response;
  }

  boolean requestHasParams(Request request) {
    return request.getParamMap().size() >= 1;
  }

  private String getCookieType(Request request) {
    String typeKey = "type";
    String typeValue = request.getParamValue(typeKey);
    return typeKey + "=" + typeValue;
  }
}