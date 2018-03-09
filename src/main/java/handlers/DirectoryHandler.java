package handlers;

import java.util.Arrays;
import messages.HTTPStatus;
import messages.Request;
import messages.Response;
import java.io.File;

public class DirectoryHandler extends RequestHandler {

  private File file;

  public DirectoryHandler(File file) {
    this.file = file;
  }

  public Response getResponse(Request request) {
    StringBuffer bodyContents = new StringBuffer();
    String[] sortedDirContents = file.list();

    Arrays.sort(sortedDirContents);

    response.setStatusLine(HTTPStatus.OK.getStatusLine());
    response.setHeaders("Content-Type: text/html; charset=utf-8");

    bodyContents.append("<!DOCTYPE html><html>");
    bodyContents.append("<title>Directory Listing for " + file.getName() + "</title>");
    bodyContents.append("<body><ul>");
    bodyContents.append("<h2>Directory listing for " + file.getName() + "</h2><hr>");

    for (String item : sortedDirContents) {
      bodyContents.append("<li><a href=\"/" + item + "\">" + item + "</a></li>");
    }

    bodyContents.append("</ul></body></html>");

    response.setBody(bodyContents.toString().getBytes());
    return response;
  }
}
