package handlers;

import messages.Request;
import org.junit.Test;
import parsers.RequestParser;

import static org.junit.Assert.assertEquals;

public class FormDataHandlerTest {

  @Test
  public void FormDataHandlerReturnsCorrectResponseStatus() {

    String requestWithData = "GET /form HTTP/1.1\r\n";
    RequestParser parser = new RequestParser();
    Request request = parser.parse(requestWithData);

    FormDataHandler fdh = new FormDataHandler(new String[]{"GET", "DELETE", "PUT", "POST"});

    assertEquals("HTTP/1.1 200 OK\r\n", fdh.getResponse(request).getStatusLine());
  }

  @Test
  public void FormDataHandlerReturnsCorrectResponse(){

    String requestWithData = "POST /form HTTP/1.1\r\n\r\ndata=fatcat";
    RequestParser parser = new RequestParser();
    Request request = parser.parse(requestWithData);

    FormDataHandler fdh = new FormDataHandler(new String[] {"GET", "DELETE", "PUT", "POST"});

    assertEquals("data=fatcat", new String (fdh.getResponse(request).getBody()));
  }
}
