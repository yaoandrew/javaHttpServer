package handlers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import messages.Response;

public class OptionsRequestHandlerTest {

  OptionsRequestHandler orh = new OptionsRequestHandler(new String[] {"OPTIONS", "POST"});
  Response actual = orh.getResponse();
  String expected_status = "HTTP/1.1 200 OK\r\n";
  String expected_header = "Allow: GET,HEAD,POST,OPTIONS,PUT\r\n";

  @Test
  public void OptionsRequestHandlerReturnsCorrectStatus() {
    assertEquals(expected_status, actual.getStatusLine());
  }

  @Test
  public void OptionsRequestHandlerReturnsCorrectHeader() {
    assertEquals(expected_header, actual.getHeaders());
  }

}
