package handlers;

import java.util.Arrays;
import messages.Request;
import messages.Response;

public interface RequestHandler {
  Response getResponse(Request request);

  default boolean requestIsSupported(String[] supportedHttpMethods, String method) {
    return Arrays.asList(supportedHttpMethods).contains(method);
  }


}
