package handlers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import messages.HTTPStatus;
import messages.Request;
import messages.Response;

public class PartialContentHandler extends FileSystemHandler {

  private int contentLength;
  private File file;
  int beginOfRange;
  int endOfRange;
  byte[] fullContent;
  byte[] partialContent;

  public PartialContentHandler(File file){
    super(file);
    this.file = file;

    contentLength = (int)file.length();
  }

  public Response getResponse(Request request) {
    Response response = new Response();
    String rangeValues = request.getHeaderValue("Range").replace("bytes=", "");

    try {
      fullContent = readFileContents();
    } catch (Exception e){
      System.out.println("Failed to read file");
//      response.setStatusLine(HTTPStatus.NOT_FOUND.getStatusLine());
    }

    if (rangeIsValid(rangeValues)) {
      parseRange(rangeValues);
      if ((beginOfRange >= 0 & beginOfRange < contentLength) & (endOfRange > beginOfRange & endOfRange < contentLength)) {
        partialContent = getPartialContent(fullContent, beginOfRange, endOfRange);
        response.setStatusLine(HTTPStatus.PARTIAL_CONTENT.getStatusLine());
        response.setHeaders("Content-type: text/plain");
        response.setBody(partialContent);
      } else {
        response.setStatusLine(HTTPStatus.RANGE_NOT_SATISFIABLE.getStatusLine());
      }
    } else {
      response.setStatusLine(HTTPStatus.RANGE_NOT_SATISFIABLE.getStatusLine());
    }

      return response;
  }

  void parseRange(String rangeValues) {

      if (rangeValues.startsWith("-")){
        beginOfRange = contentLength - (new Integer(rangeValues.split("-")[1])) ;
        endOfRange = contentLength - 1;
      }

      else if (rangeValues.endsWith("-")){
        beginOfRange = new Integer(rangeValues.split("-")[0]);
        endOfRange =  contentLength - 1;
      }

      else {
        beginOfRange = new Integer(rangeValues.split("-")[0]);
        endOfRange = new Integer(rangeValues.split("-")[1]);
      }
  }

  long getContentLength() {
    return contentLength;
  }

  Boolean rangeIsValid(String rangeValues) {
    // check if rangeValues contains a -
    // check if split parts can be integers
    // range values match rules
    if (!rangeValues.contains("-")) {
      return false;
    }

    if (!isProperlyFormattedRange(rangeValues)) {
      return false;
    }
    return true;
  }

  byte[] getPartialContent (byte[] content, int begin, int end){
    return Arrays.copyOfRange(content, begin, end + 1);
  }


  byte[] readFileContents() throws IOException {
    return Files.readAllBytes(file.toPath());
  }

  Boolean isProperlyFormattedRange(String rangeValues) {
    if (rangeValues.startsWith("-")){
      try {
        Integer.parseInt(rangeValues.split("-")[1]);
      } catch (NumberFormatException e) {
        return false;
      }
    } else if (rangeValues.endsWith("-")){
      try {
        Integer.parseInt(rangeValues.split("-")[0]);
      } catch (NumberFormatException e) {
        return false;
      }
    } else {
      try {
        Integer.parseInt(rangeValues.split("-")[0]);
        Integer.parseInt(rangeValues.split("-")[1]);
      } catch (NumberFormatException e) {
        return false;
      }
    }
    return true;
  }

}
