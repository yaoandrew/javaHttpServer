package handlers;

import messages.Response;


public class BadRouteHandler implements RequestHandler{

    @Override
    public Response getResponse() {
        Response response = new Response();
        response.setStatusLine("HTTP/1.1 404 Not Found\r\n");

        return response;
    }
}

