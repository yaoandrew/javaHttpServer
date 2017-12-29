package handlers;

import messages.Response;

public class RootRequestHandler implements RequestHandler{
    private String[] supportedHttpMethods;

    public RootRequestHandler (String[] supportedHttpMethods){
        this.supportedHttpMethods = supportedHttpMethods;
    }

    @Override
    public Response getResponse() {
        Response response = new Response();
        response.setStatusLine("HTTP/1.1 200 OK\r\n");
        response.setHeaders("Allow: GET,OPTIONS");

        return response;
    }
}