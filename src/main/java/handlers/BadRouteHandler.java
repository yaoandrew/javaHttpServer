package handlers;

import messages.Request;
import messages.Response;


public class BadRouteHandler implements RequestHandler{

    @Override
    public Response getResponse(Request request) {
        Response response = new Response();
        response.setStatusLine("HTTP/1.1 404 Not Found\r\n");

        return response;
    }
}

