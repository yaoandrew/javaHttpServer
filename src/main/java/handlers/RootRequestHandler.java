package handlers;

import messages.HTTPStatus;
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
        response.setStatusLine(HTTPStatus.OK.getStatusLine());
        response.setHeaders("Allow: GET,OPTIONS");

        return response;
    }
}