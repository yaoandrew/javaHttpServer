
package router;

import handlers.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import messages.Request;

public class Router {

  private HashMap<String, RequestHandler> routeAndHandlerMap;
  private String serverDir;

  public Router (String serverDir){
    this.serverDir = serverDir;
  }

  private Map createRouteAndHandlerMap() {

    routeAndHandlerMap = new HashMap<>();

    routeAndHandlerMap.put("/form", new FormDataHandler(new String[] {"GET", "POST", "PUT", "HEAD"}));
    routeAndHandlerMap.put("/method_options", new OptionsRequestHandler(new String[] {"GET", "POST", "PUT", "HEAD", "OPTIONS"}));
    routeAndHandlerMap.put("/method_options2", new RootRequestHandler(new String[] {"GET", "OPTIONS"}));
    routeAndHandlerMap.put("/cookie", new CookieHandler());
    routeAndHandlerMap.put("/eat_cookie", new CookieHandler());
    routeAndHandlerMap.put("/parameters", new ParameterHandler());
    routeAndHandlerMap.put("/coffee", new TeapotHandler());
    routeAndHandlerMap.put("/tea", new TeapotHandler());
    routeAndHandlerMap.put("/redirect", new RedirectHandler());
    routeAndHandlerMap.put("/logs", new LogFileHandler());

    return routeAndHandlerMap;
  }

  Map handlerMap = createRouteAndHandlerMap();

  public RequestHandler getHandler(Request request) {
    if (!routeExistsInMap(request.getSimpleUri())) {

      File file = new File(serverDir + request.getSimpleUri());
      System.out.println("This is the path: " + file.toString());
      System.out.println("Does the path exist? " + file.exists());

      if(isPartialContentRequest(request) & isValidPathAndFile(file)){
        PartialContentHandler partialContentHandler = new PartialContentHandler(file);
        return partialContentHandler;
      }

      if(isValidPathAndFile(file)){
        FileSystemHandler fileSystemHandler = new FileSystemHandler(file);
        return fileSystemHandler;
      }

      if(isValidPathAndDirectory(file)){
        DirectoryHandler directoryHandler = new DirectoryHandler(file);
        return directoryHandler;
      }
        return new BadRouteHandler();

    } else {
       return (RequestHandler) handlerMap.get(request.getSimpleUri());
    }
  }

  Boolean routeExistsInMap(String route) {
    return handlerMap.containsKey(route);
  }

  Boolean isValidPathAndFile(File file) {
    return file.exists() && file.isFile();
  }

  Boolean isValidPathAndDirectory(File file) {
    return file.exists() && file.isDirectory();
  }

  Boolean isPartialContentRequest(Request request){
    return request.getHeadersMap().containsKey("Range");
  }
}
