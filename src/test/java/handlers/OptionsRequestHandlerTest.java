package handlers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import messages.Response;
import parsers.RequestParser;

public class OptionsRequestHandlerTest {

  private OptionsRequestHandler orh = new OptionsRequestHandler();
  private RequestParser parser = new RequestParser();

  private String request = "OPTIONS / HTTP.1.1";
  private Response actual = orh.getResponse(parser.parse(request));
  private String expected_status = "HTTP/1.1 200 OK";
  private String expected_header = "Allow: GET,HEAD,POST,OPTIONS,PUT\r\n";

  @Test
  public void OptionsRequestHandlerReturnsCorrectStatus() {
    assertEquals(expected_status, actual.getStatusLine());
  }

  @Test
  public void OptionsRequestHandlerReturnsCorrectHeader() {
    assertThat(actual.getHeaders(), containsString(expected_header));
  }

}
