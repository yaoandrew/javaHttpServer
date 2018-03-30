package handlers;

import messages.Request;
import messages.Response;

import java.io.File;

public class FileSystemHandler extends RequestHandler {

    String serverDir;
    Request request;

    public FileSystemHandler (String serverDir, Request request) {
        this.serverDir = serverDir;
        this.request = request;
    }

    public Response getResponse(Request request) {
        File file = new File(serverDir + request.getSimpleUri());

        if (isPartialContentRequest(request) & isValidPathAndFile(file)) {
            PartialContentHandler pch = new PartialContentHandler(file);
            return pch.getResponse(request);
        }
        if (isValidPathAndFile(file)) {
            FileHandler fh = new FileHandler(file);
            return fh.getResponse(request);
        }
        if (isValidPathAndDirectory(file)) {
            DirectoryHandler dh = new DirectoryHandler(file);
            return dh.getResponse(request);
        }

        BadRouteHandler brh = new BadRouteHandler();
        return brh.getResponse(request);
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
