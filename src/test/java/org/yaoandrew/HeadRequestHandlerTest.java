package org.yaoandrew;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class HeadRequestHandlerTest {

  @Test
  public void HeadRequestHandlerReturnsResponseWithStatus200() {
    String expected = "HTTP/1.1 200 OK\r\n";
    HeadRequestHandler handleHead = new HeadRequestHandler(true);

    assertEquals(expected, handleHead.getResponse().getStatusLine());
  }

  @Test
  public void HeadRequestHandlerReturnsResponseWithStatus404() {
    String expected = "HTTP/1.1 404 Not Found\r\n";
    HeadRequestHandler handleHead = new HeadRequestHandler(false);

    assertEquals(expected, handleHead.getResponse().getStatusLine());
  }
}
