package handlers;

import java.util.Arrays;
import java.util.Base64;
import messages.HTTPStatus;
import messages.Request;
import messages.Response;
import messages.ResponseHeaderField;

public class ProtectedRouteHandler extends RequestHandler {

  private RequestHandler authorizedHandler;
  private String realm = "Access to protected endpoint";
  private static String USERNAME = "admin";
  private static String PASSWORD = "hunter2";

  public ProtectedRouteHandler(RequestHandler handler) {
    this.authorizedHandler = handler;
  }

  public Response getResponse(Request request) {
    if (isAuthorized(request)) {
      return authorizedHandler.getResponse(request);
    } else {
      response.setStatusLine(HTTPStatus.UNAUTHORIZED.getStatusLine());
      response.setHeaders(ResponseHeaderField.WWW_AUTH.getHeaderField() + "Basic realm=\"" + realm +"\"");
    }
    return response;
  }

  private Boolean isAuthorized(Request request) {
    if (request.getHeaderValue("Authorization") != null && isValidUser(
        getUserInfo(request.getHeaderValue("Authorization")))) {
      return true;
    } else {
      return false;
    }
  }

  private Boolean isValidUser(String userInfo) {
    return Arrays.equals(Base64.getMimeDecoder().decode(userInfo.getBytes()),
        (USERNAME + ":" + PASSWORD).getBytes());
  }

  private String getUserInfo(String headerValue) {
    return headerValue.split("\\s")[1];
  }


}
