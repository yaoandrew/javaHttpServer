package org.yaoandrew;

public class OptionsRequestHandler implements RequestHandler {
    String[] supportedHttpMethods;

    public OptionsRequestHandler (String[] supportedHttpMethods){
        this.supportedHttpMethods = supportedHttpMethods;
    }

    public Response getResponse() {
        Response response = new Response();
        response.setStatusLine("HTTP/1.1 200 OK\r\n");
        response.setHeaders("Allow: GET,HEAD,POST,OPTIONS,PUT\r\n");

        return response;
    }
}
