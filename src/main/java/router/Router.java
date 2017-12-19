package router;

import java.util.HashMap;
import java.util.Map;

import handlers.RequestHandler;
import handlers.RootRequestHandler;
import handlers.FormDataHandler;
import handlers.OptionsRequestHandler;
import handlers.BadRouteHandler;
import messages.Request;

public class Router {

  private HashMap<String, RequestHandler> routeAndHandlerMap;

  private Map createRouteAndHandlerMap() {
    routeAndHandlerMap = new HashMap<>();
    routeAndHandlerMap.put("/", new RootRequestHandler(new String[] {"GET", "POST", "PUT", "HEAD"}));
    routeAndHandlerMap.put("/form", new FormDataHandler(new String[] {"GET", "POST", "PUT", "HEAD"}));
    routeAndHandlerMap.put("/method_options", new OptionsRequestHandler(new String[] {"GET", "POST", "PUT", "HEAD", "OPTIONS"}));

    return routeAndHandlerMap;
  }

  public RequestHandler getResponder(Request request) {
    Map responderMap = createRouteAndHandlerMap();
    if (responderMap.get(request.getUri()) == null) {
      return new BadRouteHandler();
    } else {
       return (RequestHandler) responderMap.get(request.getUri());
      }
  }

}
