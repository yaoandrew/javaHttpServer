
package router;

import handlers.CookieHandler;
import handlers.FileSystemHandler;
import handlers.ParameterHandler;
import handlers.RequestHandler;
import handlers.TeapotHandler;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import handlers.RootRequestHandler;
import handlers.FormDataHandler;
import handlers.OptionsRequestHandler;
import handlers.BadRouteHandler;
import messages.Request;

public class Router {

  private HashMap<String, RequestHandler> routeAndHandlerMap;
  private String serverDir;

  public Router (String serverDir){
    this.serverDir = serverDir;
  }

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

      File file = new File(serverDir + request.getSimpleUri());
      System.out.println("This is the file: " + file.toString());
      System.out.println("Does the file exist? " + file.exists());

      if(file.exists() || file.isDirectory()){
        FileSystemHandler fileSystemHandler = new FileSystemHandler(file);
        return fileSystemHandler;
      }
        return new BadRouteHandler();

    } else {
       return (RequestHandler) handlerMap.get(request.getSimpleUri());
    }
  }
}
