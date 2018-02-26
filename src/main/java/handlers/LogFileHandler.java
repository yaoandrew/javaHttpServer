package handlers;

import messages.HTTPStatus;
import messages.Request;
import messages.Response;

public class LogFileHandler implements RequestHandler {

  @Override
  public Response getResponse(Request request) {
    Response response = new Response();

    if (isAuthorized()) {
      response.setStatusLine(HTTPStatus.OK.getStatusLine());

      //get logger contents and set in body
      response.setBody("GET /log HTTP/1.1".getBytes());
    }

    response.setStatusLine(HTTPStatus.UNAUTHORIZED.getStatusLine());
    response.setHeaders("WWW-Authenticate: Basic realm=\"Access to server logs\"");

    return response;
  }

  Boolean isAuthorized() {

    //get auth info from request header
    //pass info to authenticator service
    //authenticator service checks username and password

    return false;
  }
}
