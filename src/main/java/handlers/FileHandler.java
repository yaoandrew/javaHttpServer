package handlers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import encoders.Sha1Encoder;
import messages.HTTPStatus;
import messages.Request;
import messages.Response;
import messages.ResponseHeaderField;

public class FileHandler extends RequestHandler {

  private File file;
  private String[] supportedHttpMethods = {"GET", "PATCH"};


  public FileHandler(File file) {
    this.file = file;
  }


  @Override
  public Response getResponse(Request request) {
    String statusLine = getStatusLine(request);
    response.setStatusLine(statusLine);
    writeToFile(file, request);
    String header = getHeader(file);
    response.setHeaders(header);

    try {
      response.setBody(Files.readAllBytes(file.toPath()));
    } catch (IOException e) {
      System.err.println("Unable to set data to response body");
      e.printStackTrace();
    }

    return response;
  }

  private String getStatusLine(Request request) {
    if (requestIsSupported(request.getHttpMethod())) {
      if (request.getHttpMethod().equals("PATCH")) {
        return HTTPStatus.NO_CONTENT.getStatusLine();
      }
      return HTTPStatus.OK.getStatusLine();
    } else {
      return HTTPStatus.NOT_ALLOWED.getStatusLine();
    }
  }

  private void writeToFile(File file, Request request) {
    if (request.getHttpMethod().equals("PATCH")) {
      try {
        if (getEtagFromHeader(request).equals(getEtagFromFile(file))) {
          FileWriter fileWriter = new FileWriter(file);
          fileWriter.write(request.getBody());
          fileWriter.close();
        }
      } catch (IOException e) {
        System.err.println("Patch of file contents failed");
        e.printStackTrace();
      }
    }
  }

  private String getHeader(File file) {
    if (isImageFile(file)) {
      String imageFileExtension = file.getName().split("\\.")[1];
      return ResponseHeaderField.CONTENT_TYPE.getHeaderField()
          + "image/" + imageFileExtension;
    } else if (isTxtFile(file)) {
      return ResponseHeaderField.CONTENT_TYPE.getHeaderField()
          + "text/plain";
    } else {
      return ResponseHeaderField.CONTENT_TYPE.getHeaderField()
          + "application/octet-stream";
    }
  }

  private boolean requestIsSupported(String method) {
    return Arrays.asList(supportedHttpMethods).contains(method);
  }

  private String getEtagFromHeader(Request request) {
    return request.getHeaderValue("If-Match");
  }

  private String getEtagFromFile(File file) throws IOException {
    return Sha1Encoder.encode(Files.readAllBytes(file.toPath()));
  }

  private boolean isImageFile(File file) {
    return file.getName().contains(".jpeg") || file.getName().contains(".png") ||
        file.getName().contains(".gif");
  }

  private boolean isTxtFile(File file) {
    return file.getName().contains(".txt");
  }
}
