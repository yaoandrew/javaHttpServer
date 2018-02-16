package handlers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import messages.HTTPStatus;
import messages.Request;
import messages.Response;

public class PartialContentHandler extends FileSystemHandler {

  private String unparsedRange;
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

    unparsedRange = request.getHeaderValue("Range");
    parseRange(unparsedRange);

    try {
      fullContent = readFileContents();
    } catch (Exception e){
      System.out.println("Failed to read file");
    }

    partialContent = getPartialContent(fullContent, beginOfRange, endOfRange);

    Response response = new Response();
      response.setStatusLine(HTTPStatus.PARTIAL_CONTENT.getStatusLine());
      response.setHeaders("Content-type: text/plain");
      response.setBody(partialContent);

      return response;
  }

  void parseRange(String unparsedRange) {
      String rangeValues = unparsedRange.replace("bytes=", "");

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

  byte[] getPartialContent (byte[] content, int begin, int end){
    return Arrays.copyOfRange(content, begin, end + 1);
  }


  byte[] readFileContents() throws IOException {
    return Files.readAllBytes(file.toPath());
  }
}
