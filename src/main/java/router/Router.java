
package router;

import handlers.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import messages.Request;

public class Router {

  private String serverDir;

  public Router (String serverDir){
    this.serverDir = serverDir;
  }

  private Map createRouteAndHandlerMap() {

    HashMap<String, RequestHandler> routeAndHandlerMap = new HashMap<>();

    routeAndHandlerMap.put("/form", new FormDataHandler(new String[] {"GET", "POST", "PUT", "HEAD"}));
    routeAndHandlerMap.put("/method_options", new OptionsRequestHandler(new String[] {"GET", "POST", "PUT", "HEAD", "OPTIONS"}));
    routeAndHandlerMap.put("/method_options2", new RootRequestHandler(new String[] {"GET", "OPTIONS"}));
    routeAndHandlerMap.put("/cookie", new CookieHandler());
    routeAndHandlerMap.put("/eat_cookie", new CookieHandler());
    routeAndHandlerMap.put("/parameters", new ParameterHandler());
    routeAndHandlerMap.put("/coffee", new TeapotHandler());
    routeAndHandlerMap.put("/tea", new TeapotHandler());
    routeAndHandlerMap.put("/redirect", new RedirectHandler());
    routeAndHandlerMap.put("/logs", new ProtectedRouteHandler(new LogFileHandler()));

    return routeAndHandlerMap;
  }

  private Map handlerMap = createRouteAndHandlerMap();

  public RequestHandler getHandler(Request request) {
    if (!routeExistsInMap(request.getSimpleUri())) {

      File file = new File(serverDir + request.getSimpleUri());
      //System.out.println("This is the path: " + file.toString());
      //System.out.println("Does the path exist? " + file.exists());

      if(isPartialContentRequest(request) & isValidPathAndFile(file)){
        return new PartialContentHandler(file);
      }

      if(isValidPathAndFile(file)){
        return new FileSystemHandler(file);
      }

      if(isValidPathAndDirectory(file)){
        return new DirectoryHandler(file);
      }
        return new BadRouteHandler();

    } else {
       return (RequestHandler) handlerMap.get(request.getSimpleUri());
    }
  }

  private Boolean routeExistsInMap(String route) {
    return handlerMap.containsKey(route);
  }

  private Boolean isValidPathAndFile(File file) {
    return file.exists() && file.isFile();
  }

  private Boolean isValidPathAndDirectory(File file) {
    return file.exists() && file.isDirectory();
  }

  private Boolean isPartialContentRequest(Request request){
    return request.getHeadersMap().containsKey("Range");
  }
}
