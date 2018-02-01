package  handlers;

import messages.Request;
import messages.Response;

public class FormDataHandler implements RequestHandler {
  private String[] supportedHttpMethods;
  private Response response = new Response();

  public String getFormData() {
    return formData;
  }

  public void setFormData(String formData) {
    this.formData = formData;
  }

  String formData;

  public FormDataHandler (String[] supportedHttpMethods, Request request) {
    this.supportedHttpMethods = supportedHttpMethods;
    this.formData = request.getBody();
  }

  public Response getResponse() {
    response.setStatusLine("HTTP/1.1 200 OK\r\n");
    response.setBody(formData);

    return response;
  }
}
