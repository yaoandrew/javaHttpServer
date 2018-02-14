package handlers;

import messages.Request;
import messages.Response;

public class PartialContentHandler implements RequestHandler {

public Response getResponse(Request request) {
  Response response = new Response();
    return response;
  }

}
