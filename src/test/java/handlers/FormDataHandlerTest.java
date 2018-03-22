package handlers;

import messages.Request;
import org.junit.Test;
import parsers.RequestParser;

import static org.junit.Assert.assertEquals;

public class FormDataHandlerTest {

  @Test
  public void ReturnsCorrectResponseStatus() {

    String requestWithData = "GET /form HTTP/1.1\r\n";
    RequestParser parser = new RequestParser();
    Request request = parser.parse(requestWithData);

    FormDataHandler fdh = new FormDataHandler();

    assertEquals("HTTP/1.1 200 OK", fdh.getResponse(request).getStatusLine());
  }

  @Test
  public void ReturnsCorrectResponse(){

    String requestWithData = "POST /form HTTP/1.1\r\n\r\ndata=fatcat";
    RequestParser parser = new RequestParser();
    Request request = parser.parse(requestWithData);

    FormDataHandler fdh = new FormDataHandler();

    assertEquals("data=fatcat", new String (fdh.getResponse(request).getBody()));
  }
}
