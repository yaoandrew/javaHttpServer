package handlers;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BadRouteHandlerTest {
  private BadRouteHandler brh = new BadRouteHandler();
  private String expected = "HTTP/1.1 404 Not Found\r\n";
  private String actual = brh.getResponse().getStatusLine();

  @Test
  public void BadRouteHandlerReturns404(){
   assertEquals(expected, actual);
  }

}
