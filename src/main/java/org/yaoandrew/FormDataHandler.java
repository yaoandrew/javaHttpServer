package org.yaoandrew;

public class FormDataHandler implements RequestHandler {
    private String [] supportedHttpMethods;

    public FormDataHandler (String [] supportedHttpMethods) {
        this.supportedHttpMethods = supportedHttpMethods;
    }

    public Response getResponse() {
        Response response = new Response();

        response.setStatusLine("HTTP/1.1 200 OK\r\n");

        return response;
    }
}
