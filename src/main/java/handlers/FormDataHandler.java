package handlers;

import messages.HTTPStatus;
import messages.Request;
import messages.Response;

public class FormDataHandler extends RequestHandler {

  private Response response = new Response();
  private String formData;

  public FormDataHandler() {
    this.formData = "";
  }


  public Response getResponse(Request request) {
    if (request.getHttpMethod().equals("POST") || request.getHttpMethod().equals("PUT") || request
        .getHttpMethod().equals("DELETE")) {
      formData = request.getBody();
    }
    response.setStatusLine(HTTPStatus.OK.getStatusLine());
    if (formData == null) {
      formData = "";
    }
    response.setBody(formData.getBytes());

    return response;
  }
}

