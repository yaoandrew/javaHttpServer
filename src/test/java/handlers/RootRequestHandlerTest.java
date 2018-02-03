package handlers;

import org.junit.Test;
import parsers.RequestParser;

import static org.junit.Assert.assertEquals;

public class RootRequestHandlerTest {

  RequestParser parser = new RequestParser();
  String request = "GET / HTTP/1.1";
  RootRequestHandler rrh = new RootRequestHandler(new String[] {"GET", "POST"});
  String expected = "HTTP/1.1 200 OK\r\n";
  String actual = rrh.getResponse(parser.parse(request)).getStatusLine();

  @Test
  public void RootRequestHandlerReturnsCorrectStatus() {
    assertEquals(expected, actual);
  }
}
