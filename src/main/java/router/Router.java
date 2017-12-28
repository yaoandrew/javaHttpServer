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
    routeAndHandlerMap.put("/cookie?type=chocolate", new CookieHandler(request));
    routeAndHandlerMap.put("/eat_cookie", new CookieHandler(request));
    routeAndHandlerMap.put("/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff", new ParameterHandler(request));
    return routeAndHandlerMap;
  }

  public RequestHandler getResponder(Request request) {
    Map responderMap = createRouteAndHandlerMap(request);
    if (responderMap.get(request.getRawUri()) == null) {
      return new BadRouteHandler();
    } else {
       return (RequestHandler) responderMap.get(request.getRawUri());
      }
  }

}
