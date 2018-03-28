package handlers;

import messages.HTTPStatus;
import messages.Request;
import messages.Response;
import server.Server;

public class FormDataHandler extends RequestHandler {

  public Response getResponse(Request request) {
    if (request.getHttpMethod().equals("POST") || request.getHttpMethod().equals("PUT") || request
        .getHttpMethod().equals("DELETE")) {
      Server.setDataStore(request.getBody());
    }

    response.setStatusLine(HTTPStatus.OK.getStatusLine());

    if (Server.getDataStore() == null) {
      Server.setDataStore("");
    }

    response.setBody(Server.getDataStore().getBytes());

    return response;
  }
}

