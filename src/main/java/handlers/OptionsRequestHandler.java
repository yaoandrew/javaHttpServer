package handlers;

import messages.HTTPStatus;
import messages.Request;
import messages.Response;

public class OptionsRequestHandler implements RequestHandler {
    private String[] supportedHttpMethods = {"GET", "POST", "PUT", "HEAD", "OPTIONS"};

    public Response getResponse(Request request) {
        Response response = new Response();
        response.setStatusLine(HTTPStatus.OK.getStatusLine());
        response.setHeaders("Allow: GET,HEAD,POST,OPTIONS,PUT\r\n");

        return response;
    }
}
