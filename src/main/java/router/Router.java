
package router;

import handlers.CookieHandler;
import handlers.ParameterHandler;
import handlers.RequestHandler;
import handlers.TeapotHandler;
import java.util.HashMap;
import java.util.Map;

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
    routeAndHandlerMap.put("/method_options2", new RootRequestHandler(new String[] {"GET", "OPTIONS"}));
    routeAndHandlerMap.put("/cookie", new CookieHandler());
    routeAndHandlerMap.put("/eat_cookie", new CookieHandler());
    routeAndHandlerMap.put("/parameters", new ParameterHandler());
    routeAndHandlerMap.put("/coffee", new TeapotHandler());
    routeAndHandlerMap.put("/tea", new TeapotHandler());
    return routeAndHandlerMap;
  }

  Map handlerMap = createRouteAndHandlerMap();

  public RequestHandler getResponder(Request request) {
    if (handlerMap.get(request.getSimpleUri()) == null) {
//      should we serve a file? check file exists in default dir
//        return response with file data in body
//        if file does'nt exist but directory does
//        return response with dir contents
//      else
        return new BadRouteHandler();
    } else {
       return (RequestHandler) handlerMap.get(request.getSimpleUri());
      }
  }
}
