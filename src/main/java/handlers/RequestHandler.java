package handlers;
import messages.Request;
import messages.Response;

public interface RequestHandler {
  Response getResponse(Request request);

}
