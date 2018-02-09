package handlers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import messages.Request;
import messages.Response;

public class FileSystemHandler implements RequestHandler {
  private File file;
  private Boolean isImageFile = false;
  private Boolean isTxtFile = false;
  private String imageFileExtension;
  private long contentLength;


  public FileSystemHandler (File file){
    this.file = file;
    if (file.getName().contains(".jpeg") || file.getName().contains(".png") || file.getName().contains(".gif")){
      isImageFile = true;
      imageFileExtension = file.getName().split("\\.")[1];
    }

    if (file.getName().contains(".txt")){
      isTxtFile = true;
    }
   //need to redo header [] to set content length
    contentLength = file.length();

  }


  @Override
  public Response getResponse(Request request) {
    Response response = new Response();

    response.setStatusLine("HTTP/1.1 200 OK\r\n");

    if (isImageFile) {
      response.setHeaders("Content-type: image/" + imageFileExtension);
    }

    if (isTxtFile) {
      response.setHeaders("Content-type: text/plain");
    }

    if (!isTxtFile && !isImageFile) {
      response.setHeaders("Content-type: application/octet-stream");
    }

    try {
      response.setBody(Files.readAllBytes(file.toPath()));
    } catch (IOException e) {
      System.out.println(e);
    }

    return response;
  }
}
