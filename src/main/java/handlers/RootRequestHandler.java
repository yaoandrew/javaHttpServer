package handlers;

import messages.Request;
import messages.Response;

public class RootRequestHandler implements RequestHandler{
    private String[] supportedHttpMethods;

    public RootRequestHandler (String[] supportedHttpMethods){
        this.supportedHttpMethods = supportedHttpMethods;
    }

    @Override
    public Response getResponse(Request request) {
        Response response = new Response();
        response.setStatusLine("HTTP/1.1 200 OK\r\n");
        response.setHeaders("Allow: GET,OPTIONS");

        return response;
    }
}