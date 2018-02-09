package handlers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
    try {
      response.setBody(Files.readAllBytes(file.toPath()));
    } catch (IOException e) {
      System.out.println(e);
    }

    return response;
  }
}
