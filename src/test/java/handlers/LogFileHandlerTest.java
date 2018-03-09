package handlers;

import static org.junit.Assert.assertEquals;

import messages.Request;
import org.junit.Test;
import parsers.RequestParser;
import servers.MyLogger;

public class LogFileHandlerTest {

  @Test
  public void ReturnsLogFileData() {
    RequestParser parser = new RequestParser();
    String rawRequest = "GET /foobar HTTP/1.1";
    LogFileHandler logFileHandler = new LogFileHandler();
    MyLogger myLogger = MyLogger.getInstance();

    Request request = parser.parse(rawRequest);
    myLogger.add(request.getSimpleUri());

    assertEquals(request.getSimpleUri(), new String (logFileHandler.getResponse(request).getBody()));
  }
}
