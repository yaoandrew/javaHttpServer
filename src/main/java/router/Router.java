package router;

import handlers.CookieHandler;
import handlers.ParameterHandler;
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

  private Map createRouteAndHandlerMap(Request request) {
    routeAndHandlerMap = new HashMap<>();
    routeAndHandlerMap.put("/", new RootRequestHandler(new String[] {"GET", "POST", "PUT", "HEAD"}));
    routeAndHandlerMap.put("/form", new FormDataHandler(new String[] {"GET", "POST", "PUT", "HEAD"}));
    routeAndHandlerMap.put("/method_options", new OptionsRequestHandler(new String[] {"GET", "POST", "PUT", "HEAD", "OPTIONS"}));
    routeAndHandlerMap.put("/method_options2", new RootRequestHandler(new String[] {"GET", "OPTIONS"}));
    routeAndHandlerMap.put("/cookie", new CookieHandler(request));
    routeAndHandlerMap.put("/eat_cookie", new CookieHandler(request));
    routeAndHandlerMap.put("/parameters", new ParameterHandler(request));
    return routeAndHandlerMap;
  }

  public RequestHandler getResponder(Request request) {
    Map responderMap = createRouteAndHandlerMap(request);
    if (responderMap.get(request.getSimpleUri()) == null) {
      return new BadRouteHandler();
    } else {
       return (RequestHandler) responderMap.get(request.getSimpleUri());
      }
  }

}