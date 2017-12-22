package handlers;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RootRequestHandlerTest {

  RootRequestHandler rrh = new RootRequestHandler(new String[] {"GET", "POST"});
  String expected = "HTTP/1.1 200 OK\r\n";
  String actual = rrh.getResponse().getStatusLine();

  @Test
  public void RootRequestHandlerReturnsCorrectStatus() {
    assertEquals(expected, actual);
  }
}
