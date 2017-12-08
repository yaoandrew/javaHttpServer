package org.yaoandrew;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Router {

  Request request;
  static Set<String> routes = new HashSet<String>(Arrays.asList("/", "/method_options", "/form"));
  static Set<String> methods = new HashSet<String>(Arrays.asList("GET", "POST", "PUT", "HEAD", "OPTIONS"));

  public Router(Request request) {
    this.request = request;
  }

  public boolean isValidRoute(String route) {
    return routes.contains(route);
  }

  public boolean isValidMethod(String method) {
    return methods.contains(method);
  }

}
