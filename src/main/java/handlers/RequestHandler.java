package handlers;

import messages.Request;
import messages.Response;

public abstract class RequestHandler {
  abstract Response getResponse(Request request);
}
