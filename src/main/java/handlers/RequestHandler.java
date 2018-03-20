package handlers;

import messages.Request;
import messages.Response;

public abstract class RequestHandler {

  Response response = new Response();

  public abstract Response getResponse(Request request);
}
