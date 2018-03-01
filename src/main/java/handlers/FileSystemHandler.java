package handlers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import messages.HTTPStatus;
import messages.Request;
import messages.Response;

public class FileSystemHandler implements RequestHandler {
  private File file;
  private Boolean isImageFile = false;
  private Boolean isTxtFile = false;
  private String imageFileExtension;
  private long contentLength;
  private String[] supportedHttpMethods;


  public FileSystemHandler (String[] supportedHttpMethods, File file){
    this.file = file;
    this.supportedHttpMethods = supportedHttpMethods;

    if (file.getName().contains(".jpeg") || file.getName().contains(".png") || file.getName().contains(".gif")){
      isImageFile = true;
      imageFileExtension = file.getName().split("\\.")[1];
    }

    if (file.getName().contains(".txt")){
      isTxtFile = true;
    }
   //need to redo response header [] to set content length
    contentLength = file.length();

  }


  @Override
  public Response getResponse(Request request) {
    Response response = new Response();

    if (requestIsSupported(supportedHttpMethods, request.getHttpMethod())) {

      response.setStatusLine(HTTPStatus.OK.getStatusLine());

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
    } else {
      response.setStatusLine(HTTPStatus.NOT_ALLOWED.getStatusLine());
      return response;
    }
  }
}
