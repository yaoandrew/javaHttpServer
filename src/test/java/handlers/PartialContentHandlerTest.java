package handlers;

import messages.Request;
import org.junit.Test;
import parsers.RequestParser;

public class PartialContentHandlerTest {

  @Test
  public void PartialContentHandlerCanParseBeginEndRange() {

    String rawRequest = "GET /partial_content.txt HTTP/1.1\r\nRange: bytes=0-4\r\n\r\n";
    RequestParser parser = new RequestParser();
    Request request = parser.parse(rawRequest);


  }

}
