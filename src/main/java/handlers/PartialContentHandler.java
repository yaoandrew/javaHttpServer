package handlers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.regex.Pattern;
import messages.HTTPStatus;
import messages.Request;
import messages.Response;

public class PartialContentHandler extends FileSystemHandler {

  private int contentLength;
  private File file;
  private byte[] fullContent;

  enum Range {
    FULL_RANGE,
    VALUE_TO_END,
    VALUE_FROM_END
  }

  public PartialContentHandler(File file) {
    super(file);
    this.file = file;
  }

  public Response getResponse(Request request) {
    byte[] partialContent;
    Response response = new Response();
    contentLength = (int)file.length();
    String rangeValues = request.getHeaderValue("Range").replace("bytes=", "");
    Range rangeType = getRangeType(rangeValues);

    try {
      fullContent = readFileContents();
    } catch (IOException e){
      System.out.println("Failed to read file");
      response.setStatusLine(HTTPStatus.NOT_FOUND.getStatusLine());
      return response;
    }

    if (canBeParsedIntoRealNumber(rangeValues, rangeType)) {
      int beginOfRange = parseBeginOfRange(rangeValues, rangeType);
      int endOfRange = parseEndOfRange(rangeValues, rangeType);

      if (rangeIsValid(beginOfRange, endOfRange)) {
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

  private Range getRangeType(String rangeValues) {
    if (rangeValues.startsWith("-")) {
      return Range.VALUE_TO_END;
    } else if (rangeValues.endsWith("-")) {
      return Range.VALUE_FROM_END;
    } else {
      return Range.FULL_RANGE;
    }
  }

  private int parseBeginOfRange(String rangeValues, Range rangeType) {
    if (rangeType == Range.VALUE_TO_END) {
      return contentLength - (new Integer(rangeValues.split("-")[1]));
    } else {
      return new Integer(rangeValues.split("-")[0]);
    }
  }

  private int parseEndOfRange(String rangeValues, Range rangeType) {
      if (rangeType == Range.VALUE_TO_END || rangeType == Range.VALUE_FROM_END) {
        return contentLength - 1;
      } else {
        return new Integer(rangeValues.split("-")[1]);
      }
  }

  private Boolean canBeParsedIntoRealNumber(String rangeValues, Range rangeType) {
    if (rangeType == Range.VALUE_TO_END) {
      return Pattern.matches("-\\d+", rangeValues);
    }
    if (rangeType == Range.VALUE_FROM_END) {
      return Pattern.matches("\\d+-", rangeValues);
    } else {
      return Pattern.matches("\\d+-\\d+", rangeValues);
    }
  }

  private Boolean rangeIsValid(int begin, int end) {
    return (begin>= 0 & begin < contentLength) & (end > begin & end < contentLength);
  }

  private byte[] getPartialContent (byte[] content, int begin, int end) {
    return Arrays.copyOfRange(content, begin, end + 1);
  }

  private byte[] readFileContents() throws IOException {
    return Files.readAllBytes(file.toPath());
  }
}
