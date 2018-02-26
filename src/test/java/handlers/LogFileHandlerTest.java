package handlers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import messages.HTTPStatus;
import messages.Request;
import org.junit.Test;
import parsers.RequestParser;

public class LogFileHandlerTest {
  @Test
  public void Sends401ForRequestWithoutAuthorization() {
    String rawRequest = "GET /logs HTTP/1.1";
    RequestParser parser = new RequestParser();
    Request request = parser.parse(rawRequest);
    LogFileHandler logFileHandler = new LogFileHandler();

    assertEquals (HTTPStatus.UNAUTHORIZED.getStatusLine(), logFileHandler.getResponse(request).getStatusLine());
  }

  @Test
  public void SendsAuthenticationTypeForRequestWithoutAuthorization() {
    String rawRequest = "GET /logs HTTP/1.1";
    RequestParser parser = new RequestParser();
    Request request = parser.parse(rawRequest);
    LogFileHandler logFileHandler = new LogFileHandler();

    assertThat (logFileHandler.getResponse(request).getHeaders(), containsString("WWW-Authenticate: Basic"));
  }


}
