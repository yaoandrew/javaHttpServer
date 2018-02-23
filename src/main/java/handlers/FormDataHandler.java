package  handlers;

import messages.HTTPStatus;
import messages.Request;
import messages.Response;

public class FormDataHandler implements RequestHandler {
  private String[] supportedHttpMethods;
  private Response response = new Response();
  String formData;

  public FormDataHandler (String[] supportedHttpMethods) {
    this.supportedHttpMethods = supportedHttpMethods;
    this.formData = "";
  }


  public Response getResponse(Request request) {
    if (request.getHttpMethod().equals("POST") || request.getHttpMethod().equals("PUT") || request.getHttpMethod().equals("DELETE")) {
      formData = request.getBody();
    }
    response.setStatusLine(HTTPStatus.OK.getStatusLine());
    if (formData == null){
      formData = "";
    }
    response.setBody(formData.getBytes());

    return response;
  }
}

