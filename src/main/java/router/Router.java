
package router;

import handlers.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import messages.Request;

public class Router {

  private String serverDir;

  public Router(String serverDir) {
    this.serverDir = serverDir;
  }

  private HashMap<String, RequestHandler> createRouteAndHandlerMap() {

    HashMap<String, RequestHandler> routeAndHandlerMap = new HashMap<>();

    routeAndHandlerMap.put("/form", new FormDataHandler());
    routeAndHandlerMap.put("/method_options", new OptionsRequestHandler());
    routeAndHandlerMap.put("/method_options2", new RootRequestHandler());
    routeAndHandlerMap.put("/cookie", new CookieHandler());
    routeAndHandlerMap.put("/eat_cookie", new CookieHandler());
    routeAndHandlerMap.put("/parameters", new ParameterHandler());
    routeAndHandlerMap.put("/coffee", new TeapotHandler());
    routeAndHandlerMap.put("/tea", new TeapotHandler());
    routeAndHandlerMap.put("/redirect", new RedirectHandler());
    routeAndHandlerMap.put("/logs", new ProtectedRouteHandler(new LogFileHandler()));

    return routeAndHandlerMap;
  }


  public RequestHandler getHandler(Request request) {
    HashMap<String, RequestHandler> handlerMap = createRouteAndHandlerMap();

    if (!handlerMap.containsKey(request.getSimpleUri())) {

      File file = new File(serverDir + request.getSimpleUri());

      if (isPartialContentRequest(request) & isValidPathAndFile(file)) {
        return new PartialContentHandler(file);
      }

      if (isValidPathAndFile(file)) {
        return new FileHandler(file);
      }

      if (isValidPathAndDirectory(file)) {
        return new DirectoryHandler(file);
      }
      return new BadRouteHandler();

    } else {
      return handlerMap.get(request.getSimpleUri());
    }
  }

  private boolean isValidPathAndFile(File file) {
    return file.exists() && file.isFile();
  }

  private boolean isValidPathAndDirectory(File file) {
    return file.exists() && file.isDirectory();
  }

  private boolean isPartialContentRequest(Request request) {
    return request.getHeadersMap().containsKey("Range");
  }
}
