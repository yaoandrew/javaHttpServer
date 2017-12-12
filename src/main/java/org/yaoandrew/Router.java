package org.yaoandrew;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Router {

  private Request request;
  private HashMap<String, RequestHandler> routeResponderMap;
  Set<String> routes = new HashSet<String>(Arrays.asList("/", "/method_options", "method_options2","/form"));
  Set <String> methods = new  HashSet<String>(Arrays.asList("GET", "POST", "PUT", "HEAD", "OPTIONS"));

  Router(Request request) {
    this.request = request;
  }

  boolean isValidRoute() {
    return routes.contains(request.getResource());
  }

  boolean isValidMethod() {
    return methods.contains(request.getHttpMethod());
  }

  private Map createRouteResponderMap() {
    routeResponderMap = new HashMap<>();
    routeResponderMap.put("GET", new GetRequestHandler(isValidRoute()));
    routeResponderMap.put("POST", new PostRequestHandler());
    routeResponderMap.put("PUT", new PutRequestHandler());
    routeResponderMap.put("HEAD", new HeadRequestHandler(isValidRoute()));
    routeResponderMap.put("OPTIONS", new OptionsRequestHandler(request.getResource()));
    return routeResponderMap;
  }

  RequestHandler getResponder() {
    Map responderMap = createRouteResponderMap();
    return (RequestHandler)responderMap.get(request.getHttpMethod());
  }

}
