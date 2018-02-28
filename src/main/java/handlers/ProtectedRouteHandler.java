package handlers;

import messages.HTTPStatus;
import messages.Request;
import messages.Response;

public class ProtectedRouteHandler implements RequestHandler {

  RequestHandler authorizedHandler;
  String realm = "Access to protected endpoint";

  public ProtectedRouteHandler (RequestHandler handler) {
    this.authorizedHandler = handler;
  }

  public Response getResponse(Request request) {
    Response response = new Response();
    if (isAuthorized(request)) {
      return authorizedHandler.getResponse(request);
    } else {
      response.setStatusLine(HTTPStatus.UNAUTHORIZED.getStatusLine());
      response.setHeaders("WWW-Authenticate: Basic realm=\"" + realm);
    }
    return response;
  }

  Boolean isAuthorized(Request request) {
    if (request.getHeaderValue("Authorization") != null) {
      return true;
    } else {
      return false;
    }
  }
}
