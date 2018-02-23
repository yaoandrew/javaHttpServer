package handlers;

import messages.HTTPStatus;
import messages.Request;
import messages.Response;


public class BadRouteHandler implements RequestHandler{

    @Override
    public Response getResponse(Request request) {
        Response response = new Response();
        response.setStatusLine(HTTPStatus.NOT_FOUND.getStatusLine());

        return response;
    }
}

