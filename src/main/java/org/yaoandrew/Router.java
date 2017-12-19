package org.yaoandrew;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Router {

  private Request request;
  private HashMap<String, RequestHandler> routeResponderMap;
  private HashMap<String, RequestHandler> routeAndHandlerMap;
  Set<String> routes = new HashSet<>(Arrays.asList("/", "/method_options", "method_options2","/form"));
  Set <String> methods = new  HashSet<>(Arrays.asList("GET", "POST", "PUT", "HEAD", "OPTIONS"));

  boolean isValidRoute() {
    return routes.contains(request.getUri());
  }

  boolean isValidMethod() {
    return methods.contains(request.getHttpMethod());
  }

  private Map createRouteAndHandlerMap() {
    routeAndHandlerMap = new HashMap<>();
    routeAndHandlerMap.put("/", new RootRequestHandler(new String[] {"GET", "POST", "PUT", "HEAD"}));
    routeAndHandlerMap.put("/form", new FormDataHandler(new String[] {"GET", "POST", "PUT", "HEAD"}));

    return routeAndHandlerMap;
  }

  RequestHandler getResponder(Request request) {
    Map responderMap = createRouteAndHandlerMap();
    if (responderMap.get(request.getUri()) == null) {
      return new BadRouteHandler();
    } else {
       return (RequestHandler) responderMap.get(request.getUri());
      }
  }

}
