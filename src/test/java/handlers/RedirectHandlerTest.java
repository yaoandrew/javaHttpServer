package handlers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import parsers.RequestParser;

public class RedirectHandlerTest {

  RedirectHandler redirectHandler = new RedirectHandler();
  String rawRequest = "GET /redirect HTTP/1.1\r\n";
  RequestParser parser = new RequestParser();

  @Test
  public void RedirectHandlerSetsStatusCode304(){

    String expectedStatusLine =  "HTTP/1.1 302 Found";

    assertEquals(expectedStatusLine, redirectHandler.getResponse(parser.parse(rawRequest)).getStatusLine());

  }

  @Test
  public void RedirectHandlerSetsProperHeader(){

    String expectedHeader = "Location: /";

    assertEquals(expectedHeader, redirectHandler.getResponse(parser.parse(rawRequest)).getHeaders());
  }

}
