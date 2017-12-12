package org.yaoandrew;

public class GetRequestHandler implements RequestHandler {
  Boolean validRoute;

  public GetRequestHandler(Boolean validRoute) {
    this.validRoute = validRoute;
  }

    public Response getResponse() {
        Response response = new Response();
        if (validRoute) {
          response.setStatusLine("HTTP/1.1 200 OK\r\n");
        } else {
          response.setStatusLine("HTTP/1.1 404 Not Found\r\n");
        }

       return response;
    }
}
