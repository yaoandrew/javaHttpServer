package handlers;

import messages.Request;
import messages.Response;

public abstract class RequestHandler {

  public abstract Response getResponse(Request request);
}
