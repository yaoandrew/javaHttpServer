package handlers;

import org.junit.Test;
import parsers.RequestParser;

import static org.junit.Assert.assertEquals;

public class BadRouteHandlerTest {
  private RequestParser parser = new RequestParser();
  private String request = "GET /foo HTTP/1.1";
  private BadRouteHandler brh = new BadRouteHandler();
  private String expected = "HTTP/1.1 404 Not Found";
  private String actual = brh.getResponse(parser.parse(request)).getStatusLine();

  @Test
  public void BadRouteHandlerReturns404(){
   assertEquals(expected, actual);
  }

}
