package org.yaoandrew;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Router {

  private Request request;
  private HashMap<String, String> routeResponderMap;
  Set<String> routes = new HashSet<String>(Arrays.asList("/", "/method_options", "/form"));
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
    routeResponderMap = new HashMap<String, String>();
    routeResponderMap.put("GET", "handleGet");
    routeResponderMap.put("POST", "handlePost");
    routeResponderMap.put("PUT", "handlePut");
    routeResponderMap.put("HEAD", "handleHead");
    routeResponderMap.put("OPTIONS", "handleOptions");
    return routeResponderMap;
  }

  String getResponder() {
    Map routes = createRouteResponderMap();
    return (String)routes.get(request.getHttpMethod());
  }

}
