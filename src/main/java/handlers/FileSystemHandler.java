package handlers;

import java.io.File;
import messages.Request;
import messages.Response;

public class FileSystemHandler implements RequestHandler {
  private File file;
  private byte[] fileData;

  public FileSystemHandler (File file){
    this.file = file;
  }

  @Override
  public Response getResponse(Request request) {
    Response response = new Response();

    response.setStatusLine("HTTP/1.1 200 OK\r\n");
    response.setBody("File1 contents");

    return response;
  }
}
