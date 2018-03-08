package handlers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import messages.HTTPStatus;
import messages.Request;
import messages.Response;

public class FileSystemHandler implements RequestHandler {
  private File file;
  private Boolean isImageFile = false;
  private Boolean isTxtFile = false;
  private String imageFileExtension;
  private String[] supportedHttpMethods = {"GET", "PATCH"};


  public FileSystemHandler (File file){
    this.file = file;
  }


  @Override
  public Response getResponse(Request request) {
    Response response = new Response();

    if (file.getName().contains(".jpeg") || file.getName().contains(".png") || file.getName().contains(".gif")){
      isImageFile = true;
      imageFileExtension = file.getName().split("\\.")[1];
    }

    if (file.getName().contains(".txt")) {
      isTxtFile = true;
    }

    if (requestIsSupported(request.getHttpMethod())) {

      if (request.getHttpMethod().equals("PATCH")) {
        patchContents(file, request);
        response.setStatusLine(HTTPStatus.NO_CONTENT.getStatusLine());

      } else {
        response.setStatusLine(HTTPStatus.OK.getStatusLine());
      }

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

  private boolean requestIsSupported(String method) {
      return Arrays.asList(supportedHttpMethods).contains(method);
  }

  private void patchContents(File file, Request request) {

    if (getEtagFromHeader(request).equals(getEtagFromFile(file))) {
      try {
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(request.getBody());
        fileWriter.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private String getEtagFromHeader (Request request) {
    return request.getHeaderValue("If-Match");
  }

  private String getEtagFromFile (File file) {


    return "dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec";
  }
}
